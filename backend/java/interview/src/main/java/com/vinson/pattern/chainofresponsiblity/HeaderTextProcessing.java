package com.vinson.pattern.chainofresponsiblity;

public class HeaderTextProcessing extends ProcessObject<String> {

    @Override
    protected String handleWork(String input) {
        return "From Vinson: " + input;
    }
}
