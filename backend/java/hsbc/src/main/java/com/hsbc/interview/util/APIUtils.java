package com.hsbc.interview.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class APIUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

//    public static String getHttpParameter(HttpExchange exchange, String name, String def) {
//        String param = def;
//        QueryStringDecoder dec = new QueryStringDecoder(exchange.getRequestURI());
//        List<String> params = dec.parameters().get(name);
//        if (params != null && params.size() > 0) {
//            param = params.get(0);
//        }
//        return param;
//    }
//
//    public static int getHttpParameter(HttpExchange exchange, String name, int def) {
//        int param = def;
//        QueryStringDecoder dec = new QueryStringDecoder(exchange.getRequestURI());
//        List<String> params = dec.parameters().get(name);
//        if (params != null && params.size() > 0) {
//            param = Integer.parseInt(params.get(0));
//        }
//        return param;
//    }

    public static String getQueryParam(HttpExchange httpExchange, String queryParam) {
        String query = httpExchange.getRequestURI().getQuery();
        if (query != null) {
            for (String part : query.split("&")) {
                String[] keyValue = part.split("=");
                if (keyValue.length > 1 && keyValue[0].equals(queryParam)) {
                    return keyValue[1];
                }
            }
        }
        return null;
    }

    public static boolean checkForVerb(HttpExchange exchange, String verb) {
        if(!verb.equals(exchange.getRequestMethod())) {
            try {
                exchange.sendResponseHeaders(405, -1);
            } catch (IOException e) {
            }
            exchange.close();
            return false;
        }
        return true;
    }

    public static void sendModelResponse(HttpExchange exchange, Object obj) {
        String result;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            sendExceptionResponse(exchange, e);
            return;
        }
        sendResponse(exchange, result);
    }

    public static void sendResponse(HttpExchange exchange, String result) {
        sendResponse(exchange, result, 200);
    }

    public static void sendResponse(HttpExchange exchange, String result, int code) {
        try {
            exchange.sendResponseHeaders(code, result.length());
            OutputStream output = exchange.getResponseBody();
            output.write(result.getBytes());
            output.flush();
            exchange.close();
        } catch (Exception ex) {
            sendExceptionResponse(exchange, ex);
        }
    }

    public static void sendExceptionResponse(HttpExchange exchange, Exception ex) {
        sendResponse(exchange, ex.getMessage(), 500);
    }

}
