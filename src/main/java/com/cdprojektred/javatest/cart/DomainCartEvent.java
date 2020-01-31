package com.cdprojektred.javatest.cart;

import lombok.Value;

interface DomainCartEvent {
    class CreateCart implements DomainCartEvent {}

    @Value
    class AddItem implements DomainCartEvent {
        private long productId;
        private Integer quantity;
    }

    @Value
    class RemoveItem implements DomainCartEvent {
        private long productId;
        private Integer quantity;
    }
}
