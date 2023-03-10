package com.vinson.netty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.NonWritableChannelException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.IllegalFormatException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncFileWirter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncFileWirter.class);
    
    private static final int MAX_CAPTURE_FILESIZE = 200000000; // 200 m
    private static final int MAX_CAPTURE_FILECOUNT = 10;

    private final String fileName;

    private AsynchronousFileChannel channel;

    private AtomicLong position;

    private AtomicInteger currentFileCount = new AtomicInteger(0);

    private final ReentrantLock lockObject = new ReentrantLock();

    public AsyncFileWirter(final String fileName) throws IllegalArgumentException,
         UnsupportedOperationException, IOException, SecurityException {
        this.fileName = fileName;
        openChannel();
    }
    
    public static void main(String[] args) throws Exception {
        AsyncFileWirter f1 = new AsyncFileWirter("/Users/vixing/Vinson/Study/java/netty/netty-https/test.txt");
        ByteBuffer src = ByteBuffer.wrap("Scooby Snacks lala".getBytes());
        f1.write(src);
        
    }

    public void append(ByteBuffer buf) {
        if (channel == null || buf == null) {
            /*
             * Channel is closed and never opened for a roll over file may be because the
             * max file count has reached. Nothing to do
             */
            return;
        }

        long bufLen = buf.capacity();
        try {
            boolean lockAcquired = lockObject.tryLock(2, TimeUnit.SECONDS);
            if (lockAcquired && write(buf)
                    && (position.addAndGet(bufLen) >= MAX_CAPTURE_FILESIZE)) {
                /*
                 * If the write succeeds, increment position and roll over if the file size
                 * exceeds the limit
                 */
                rollOver();
            }
        } catch (IllegalArgumentException | NonWritableChannelException | IOException ex) {
            LOGGER.error("Exception : {}", fileName, ex.getLocalizedMessage());
        } catch (InterruptedException ex) {
            LOGGER.error("Failed to acquire lock for file write");
            Thread.currentThread().interrupt();
        } finally {
            if (lockObject.isHeldByCurrentThread()) {
                lockObject.unlock();
            }
        }
    }

    public boolean isChannelCreated() {
        return channel != null;
    }

    public  void closeChannel() {
        if (channel == null) {
            return;
        }

        try {
            channel.force(false);
            channel.close();
        } catch (IOException ex) {
            LOGGER.error("Error closing channel : {}", ex.getLocalizedMessage());
            LOGGER.error("Exception trace: {}", ex);
        } finally {
            channel = null;
        }
    }

    private void rollOver() throws IOException {
        // close current channel, re-open channel
        closeChannel();
        if (renameFile()) {
            openChannel();
        }
    }

    /**
     * Writes the given buffer to the channel and waits for a fixed time (at most)
     * to get the result.
     *
     * @param buf Buffer to be written
     * @return status of the write operation
     */
    private boolean write(ByteBuffer buf) {
        if (channel == null) {
            return false;
        }

        boolean status = false;
        long bufLen = buf.capacity();

        try {
            Future<Integer> result = channel.write(buf, position.get());
            /*
             * Wait for 100 milli seconds (at most) for the operation to be complete since
             * result.get() is a blocking call. Since we are operating inside a lock, cannot
             * wait for unlimited time.
             */
            if (result.get(100, TimeUnit.MILLISECONDS) != bufLen) {
                LOGGER.error("Write to {} may have failed", fileName);
            } else {
                status = true;
            }
        } catch (CancellationException ex) {
            LOGGER.error("Write to {} is cancelled : {}", fileName, ex);
        } catch (ExecutionException ex) {
            LOGGER.error("Error writing buf to {} : {}", fileName, ex);
        } catch (TimeoutException ex) {
            LOGGER.error("Time out waiting for result {} : {}", fileName, ex);
        } catch (InterruptedException ex) {
            LOGGER.error("Thread got interrupted");
            Thread.currentThread().interrupt();
        }

        return status;
    }

    private void openChannel() throws IllegalArgumentException, UnsupportedOperationException, //NOSONAR
            IOException, SecurityException {  //NOSONAR
        renameFile();
        // Get file path and create a new file
        Path path = Paths.get(fileName);
        File file = path.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (!file.createNewFile()) {
                throw new IOException("Could not create file " + fileName);
            }
        }

        // Open an AsyncFileChannel to the file and set current position
        channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        position = new AtomicLong(channel.size());
        LOGGER.debug("Opened channel for capture file {}", fileName);
    }

    private boolean renameFile() throws IOException {
        boolean filesCapped = true;

        try {
            // Rename file
            final String newFileName = String.format("%s_%d", fileName, currentFileCount.get());
            final File oldFile = new File(fileName);
            final File newFile = new File(newFileName);
            final File zipFile = new File(newFileName + ".zip");
            oldFile.renameTo(newFile);

            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile));
                    FileInputStream fin = new FileInputStream(newFileName)) {

                zout.putNextEntry(new ZipEntry(newFile.getName()));
                int len;
                byte[] buffer = new byte[1024];
                while ((len = fin.read(buffer)) > 0) {
                    zout.write(buffer, 0, len);
                }

                if (currentFileCount.incrementAndGet() >= MAX_CAPTURE_FILECOUNT) {
                    filesCapped = false;
                }
            }

            // Delete old file
            if (!newFile.delete()) {
                LOGGER.error("Could not delete file that is zipped");
            }
        } catch (IOException | IllegalFormatException ex) {
            LOGGER.error("Error rolling over : {}", ex.getLocalizedMessage());
            LOGGER.error("Exception trace: {}", ex);
            throw ex;
        }

        return filesCapped;
    }

    public int getCurrentFileCount() {
        return currentFileCount.get();
    }
}
