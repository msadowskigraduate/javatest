package com.cdprojektred.javatest.productmutator;

public class NoProductFoundException extends RuntimeException {
    public NoProductFoundException(long id) {
        super("No product with given id: " + id + "found!");
    }
}
