package com.hsbc.interview;

import com.hsbc.interview.api.UserEndpoint;

public class Main {
    public static void main(String[] args) throws Exception {
        UserEndpoint userController = new UserEndpoint();
        userController.init();
    }
}