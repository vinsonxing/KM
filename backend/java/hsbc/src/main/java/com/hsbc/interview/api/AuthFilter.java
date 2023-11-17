package com.hsbc.interview.api;

import com.hsbc.interview.util.APIUtils;
import com.hsbc.interview.util.StringUtil;
import com.hsbc.interview.util.TokenUtil;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AuthFilter extends Filter {

    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
        String token = TokenUtil.getToken(httpExchange);
        if (StringUtil.isBlank(token)) {
            APIUtils.sendResponse(httpExchange, "Invalid token", 401);
            return;
        }
        chain.doFilter(httpExchange);
    }

    @Override
    public String description() {
        return "Http Filter to validate the token";
    }
}
