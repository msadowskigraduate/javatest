package com.cdprojektred.javatest.cart;

public class OrderLimitReachedException extends RuntimeException {
    public OrderLimitReachedException() {
        super("Only 3 items are allowed in cart!");
    }
}
