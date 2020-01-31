package com.cdprojektred.javatest.cart;

public class NoCartException extends RuntimeException {
    public NoCartException(String id) {
        super("No Cart with given Id: " + id + "exists!");
    }
}
