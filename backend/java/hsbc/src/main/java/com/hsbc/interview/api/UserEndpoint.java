package com.hsbc.interview.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.interview.exception.HsbcException;
import com.hsbc.interview.model.Role;
import com.hsbc.interview.model.Token;
import com.hsbc.interview.model.User;
import com.hsbc.interview.service.UserService;
import com.hsbc.interview.util.APIUtils;
import com.hsbc.interview.util.StringUtil;
import com.hsbc.interview.util.TokenUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserEndpoint {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    UserService userService;
    AuthFilter authFilter;

    public UserEndpoint() {
        userService= new UserService();
        authFilter = new AuthFilter();
    }

    public void init() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/v1/user", exchange -> createUser(exchange)).getFilters().add(authFilter);
        server.createContext("/api/v1/user", exchange -> deleteUser(exchange)).getFilters().add(authFilter);
        server.createContext("/api/v1/users", exchange -> getUsers(exchange)).getFilters().add(authFilter);
        server.createContext("/api/v1/user/role", exchange -> createRole(exchange)).getFilters().add(authFilter);
        server.createContext("/api/v1/user/role", exchange -> deleteRole(exchange)).getFilters().add(authFilter);
        server.createContext("/api/v1/user/role/check", exchange -> checkRole(exchange)).getFilters().add(authFilter);
        server.createContext("/api/v1/user/roles", exchange -> getRoles(exchange)).getFilters().add(authFilter);
        server.createContext("/api/v1/user/login", exchange -> authenticate(exchange));
        server.createContext("/", exchange -> {
            System.out.println("Unmapped API called " + exchange.getRequestURI());
            exchange.sendResponseHeaders(404, -1);
            exchange.close();
        }).getFilters().add(authFilter);
        System.out.println("Http Server is running");
        server.start();
    }

    private void createRole(HttpExchange exchange) throws HsbcException {
        if(!APIUtils.checkForVerb(exchange, "POST")) {
            return;
        }

        try (InputStream input = exchange.getRequestBody()) {
            String requestBody = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            Role role = objectMapper.readValue(requestBody, Role.class);
            userService.createRole(role.getType());
        } catch (IOException ex) {
            APIUtils.sendExceptionResponse(exchange, ex);
            return;
        }

        APIUtils.sendResponse(exchange, "OK");
    }

    private void checkRole(HttpExchange exchange) throws HsbcException {
        if(!APIUtils.checkForVerb(exchange, "POST")) {
            return;
        }

        String role = APIUtils.getQueryParam(exchange, "role");
        if (StringUtil.isBlank(role)) {
            APIUtils.sendExceptionResponse(exchange, new HsbcException("no role provided"));
            return;
        }
        boolean valid = userService.checkRole(TokenUtil.getToken(exchange), role);

        APIUtils.sendResponse(exchange, valid ? "Authorized" : "Not Authorized");
    }

    private void getRoles(HttpExchange exchange) throws HsbcException {
        if(!APIUtils.checkForVerb(exchange, "GET")) {
            return;
        }

        String token = TokenUtil.getToken(exchange);
        Token tokenObj = TokenUtil.toToken(token);
        Set<String> roles = userService.getAllRoles(tokenObj.getUsername());
        APIUtils.sendResponse(exchange, String.format("{\"roles\": [%s]}", String.join(", ", roles)));
    }

    private void deleteRole(HttpExchange exchange) throws HsbcException {
        if(!APIUtils.checkForVerb(exchange, "DELETE")) {
            return;
        }

        String role = APIUtils.getQueryParam(exchange, "role");
        if (StringUtil.isBlank(role)) {
            APIUtils.sendExceptionResponse(exchange, new HsbcException("no role provided"));
            return;
        }
        userService.deleteRole(role);
        APIUtils.sendResponse(exchange, "OK");
    }
    private void createUser(HttpExchange exchange) throws HsbcException {
        if(!APIUtils.checkForVerb(exchange, "POST")) {
            return;
        }

        try (InputStream input = exchange.getRequestBody()) {
            String requestBody = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            User user = objectMapper.readValue(requestBody, User.class);
            userService.createUser(user);
        } catch (IOException ex) {
            APIUtils.sendExceptionResponse(exchange, ex);
            return;
        }

        APIUtils.sendResponse(exchange, "OK");
    }

    private void deleteUser(HttpExchange exchange) throws HsbcException {
        if(!APIUtils.checkForVerb(exchange, "DELETE")) {
            return;
        }

        String username = APIUtils.getQueryParam(exchange, "username");
        if (StringUtil.isBlank(username)) {
            APIUtils.sendExceptionResponse(exchange, new HsbcException("no username provided"));
            return;
        }
        userService.deleteUser(username);
        APIUtils.sendResponse(exchange, "OK");
    }

    private void getUsers(HttpExchange exchange) throws HsbcException {
        if(!APIUtils.checkForVerb(exchange, "GET")) {
            return;
        }

        String token = TokenUtil.getToken(exchange);
        Token tokenObj = TokenUtil.toToken(token);
        Set<String> roles = userService.getAllUsers();
        APIUtils.sendResponse(exchange, String.format("{\"users\": [%s]}", String.join(", ", roles)));
    }

    private void authenticate(HttpExchange exchange) {
        if(!APIUtils.checkForVerb(exchange, "POST")) {
            return;
        }

        List<String> authorization = exchange.getRequestHeaders().getOrDefault("Authorization", null);
        if (authorization == null || authorization.size() == 0) {
            return;
        }
        String basic = authorization.get(0);
        String bearer = basic.substring("Basic ".length());
        String credential = new String(Base64.getDecoder().decode(bearer)); // username:password
        String[] up = credential.split(":");
        String token = null;
        try {
            token = userService.authenticate(up[0], up[1]);
        } catch (HsbcException e) {
            APIUtils.sendResponse(exchange, e.getMessage(), 401);
            return;
        }
        if (token != null) {
            APIUtils.sendResponse(exchange, String.format("{\"token\":\"%s\"}", token));
        } else {
            APIUtils.sendResponse(exchange, "Unknown", 401);
        }
    }


}
