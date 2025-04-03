import smbclient
import os
from datetime import datetime


def get_target_notes(hostname, username, password, share_name, require_most_recent=False, last_x_days=7):
    """
    Access a Windows shared folder and find the most recent .txt file

    Args:
        hostname (str): Windows machine hostname or IP address
        username (str): Username for authentication
        password (str): Password for authentication
        share_name (str): Name of the shared folder

    Returns:
        tuple: (filename, content) of the most recent .txt file
        :param require_most_recent:
    """
    # Configure the SMB client
    smbclient.ClientConfig(username=username, password=password)

    try:
        # Connect to the shared folder
        share_path = f'\\\\{hostname}\\{share_name}'

        # Find all .txt files with their modification times
        files = []
        for file in smbclient.scandir(share_path):
            if ((file.name.lower().endswith('.txt') or file.name.lower().endswith('.pdf'))
                    and file.is_file()):
                stat = smbclient.stat(os.path.join(share_path, file.name))

                if (datetime.now() - datetime.fromtimestamp(stat.st_mtime)).days <= last_x_days:
                    files.append({
                        'name': file.name,
                        'type': 'txt' if file.name.lower().endswith('.txt') else 'pdf',
                        'path': os.path.join(share_path, file.name),
                        'mtime': stat.st_mtime
                    })

        if not files:
            print("No txt or pdf files found in the shared folder.")
            return None

        # find out the txt file which has not corresponding pdf file in the same folder
        txt_files = [file for file in files if file['type'] == 'txt']
        pdf_files = [file for file in files if file['type'] == 'pdf']
        txt_files_no_pdf = [file for file in txt_files if
                            file['name'].replace('.txt', '.pdf') not in [pdf_file['name'] for pdf_file in pdf_files]]

        # Find the most recently modified file

        # Read the file content

        if require_most_recent:
            most_recent = max(txt_files_no_pdf, key=lambda x: x['mtime'])
            return [most_recent]

        return txt_files_no_pdf

    except Exception as e:
        print(f"Error accessing shared folder: {e}")
        return None

def _build_filename_content_dict(file):
    with smbclient.open_file(file['path'], mode='r', encoding='gbk', errors='replace') as f:
        content = f.read()
    return {file['name']: content}


def copy_file_to_local(hostname, username, password, share_name, file_name, local_folder):
    # Configure the SMB client
    smbclient.ClientConfig(username=username, password=password)

    try:
        # Connect to the shared folder
        share_path = f'\\\\{hostname}\\{share_name}'
        remote_path = os.path.join(share_path, file_name)
        local_file_path = os.path.join(local_folder, file_name)

        # Open remote file in binary mode and write to local file with correct encoding
        with smbclient.open_file(remote_path, mode='rb') as remote_file:
            content = remote_file.read()
            try:
                # If UTF-8 fails, try GBK (common for Chinese Windows systems)
                decoded_content = content.decode('gbk')
                with open(local_file_path, 'w', encoding='utf-8') as local_file:
                    local_file.write(decoded_content)
            except UnicodeDecodeError:
                # If fail, fall back to binary copy
                with open(local_file_path, 'wb') as local_file:
                    local_file.write(content)

        print(f"File {os.path.basename(file_name)} copied to {local_file_path} successfully.")
    except Exception as e:
        print(f"Error copying file to remote folder: {e}")
        raise e


# define a method to copy a specify file to a remote folder
def copy_file_to_remote_folder(hostname, username, password, share_name, file_path):
    # Configure the SMB client
    smbclient.ClientConfig(username=username, password=password)

    try:
        # Connect to the shared folder
        share_path = f'\\\\{hostname}\\{share_name}'
        remote_path = os.path.join(share_path, os.path.basename(file_path))
        with open(file_path, 'rb') as f:
            smbclient.open_file(remote_path, mode='wb').write(f.read())
        print(f"File {os.path.basename(file_path)} copied to {remote_path} successfully.")
    except Exception as e:
        print(f"Error copying file to remote folder: {e}")
        raise e


if __name__ == "__main__":
    from util import find_project_root

    # Configuration - replace with your Windows share details
    HOSTNAME = "192.168.0.107"  # or IP address like "192.168.1.100"
    USERNAME = "luqian"
    PASSWORD = "Lovelq123"
    SHARE_NAME = "teaching slides/Alvin/grammar notes"  # Name of the shared folder
    # Get the most recent .txt file and its content
    filename = get_target_notes(HOSTNAME, USERNAME, PASSWORD, SHARE_NAME, last_x_days=7000)
    print(f"Most recent .txt file: {filename.split('.')[0]}")
    # copy_file_to_remote_folder(HOSTNAME, USERNAME, PASSWORD, SHARE_NAME, f"{find_project_root()}/test.pdf")

    # if filename and content:
    #     print("\nFile Content:")
    #     print("-------------")
    #     print(content)
