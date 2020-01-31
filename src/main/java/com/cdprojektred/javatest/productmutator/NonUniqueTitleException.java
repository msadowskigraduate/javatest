package com.cdprojektred.javatest.productmutator;

public class NonUniqueTitleException extends RuntimeException {
    public NonUniqueTitleException() {
        super("Non-Unique Product Title!");
    }
}
