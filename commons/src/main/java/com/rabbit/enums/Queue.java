package com.rabbit.enums;

public enum Queue {

    TEST("test");

    private String test;

    Queue(String test) {
        this.test = test;
    }

    public String getName() {
        return test;
    }
}
