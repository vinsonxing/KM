package com.hsbc.security.util;
import com.hsbc.security.entity.Token;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;

public class TokenUtil {
    public static String serialize(Token token) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(token);
            oos.flush();
            byte [] data = bos.toByteArray();
            return encode(data);
        } catch (IOException e) {
            System.err.println("Failed to serialize token");
            e.printStackTrace();
        }
        return null;
    }

    public static Token deserialize(String tokenStr) {
        if (StringUtil.isBlank(tokenStr)) return null;
        byte[] tokenArr = decode(tokenStr);
        try (ByteArrayInputStream bai = new ByteArrayInputStream(tokenArr)) {
            ObjectInputStream ois = new ObjectInputStream(bai);
            Token token = (Token) ois.readObject();
            return token;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to deserialize token");
            e.printStackTrace();
        }
        return null;
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private static byte[] decode(String token) {
        return Base64.getUrlDecoder().decode(token);
    }

    public static void main(String[] args) {
        Token t = new Token("admin", new HashSet<>(Arrays.asList("admin", "normal")), -1);
        String tStr = TokenUtil.serialize(t);
        System.out.println(tStr);

        Token tt = TokenUtil.deserialize(tStr);
        System.out.println(tt);
    }
}
