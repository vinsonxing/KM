package com.hsbc.interview.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.interview.model.Token;
import com.hsbc.interview.model.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;

public class TokenUtil {
    private static final long TWO_HOURS = 60 * 60 * 2L;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String generateToken(User user) {
        Token token = newToken(user, TWO_HOURS);
        try {
            String tokenStr = objectMapper.writeValueAsString(token);
            if (StringUtil.isBlank(tokenStr)) {
                return null;
            }
            return encode(tokenStr.getBytes());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

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
        Token t = new Token();
        t.setUsername("admin");
        t.setExpiry(System.currentTimeMillis() + TWO_HOURS);
        t.setRoles(new HashSet<>(Arrays.asList("admin", "normal")));
        String tStr = TokenUtil.serialize(t);
        System.out.println(tStr);

        Token tt = TokenUtil.deserialize(tStr);
        System.out.println(tt);
    }

    public static boolean validate(Token tokenObj) {
        return tokenObj.getExpiry() - System.currentTimeMillis() > 0;
    }

    public static Token toToken(String token) {
        String decoded = new String(decode(token));
        if (StringUtil.isBlank(decoded)) {
            return null;
        }
        try {
            return objectMapper.readValue(decoded, Token.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String getToken(HttpExchange exchange) {
        String ret = null;
        // get token from header first
        List<String> token = exchange.getRequestHeaders().getOrDefault("X-Auth-Token", null);
        if (token == null || token.size() == 0) {
            // get token from query string
            String authToken = APIUtils.getQueryParam(exchange, "authToken");
            if (StringUtil.isBlank(authToken)) {
                return null;
            }
            ret = authToken;
        } else {
            ret = token.get(0);
        }
        return ret;
    }

    private static Token newToken(User user, long delay) {
        Token t = new Token();
        t.setUsername(user.getUsername());
        t.setRoles(user.getRoles());
        t.setExpiry(System.currentTimeMillis() + delay);
        return t;
    }



}
