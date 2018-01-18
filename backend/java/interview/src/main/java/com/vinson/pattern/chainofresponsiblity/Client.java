package com.vinson.pattern.chainofresponsiblity;

public class Client {

    public static void main(String[] args) {
        ProcessObject<String> p1 = new HeaderTextProcessing();
        ProcessObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);

        String ret = p1.handle("Aren't labdas really sexy!!");
        System.out.println(ret);
    }
}
