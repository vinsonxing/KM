package com.vinson.pattern.chainofresponsiblity;

public class SpellCheckerProcessing extends ProcessObject<String> {

    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labdas", "lamdas");
    }
}
