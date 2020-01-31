package com.cdprojektred.javatest.cart;

import lombok.Value;

import java.math.BigDecimal;

public interface CartFacade {

    String handle(CartCreateCommand cartCreateCommand);

    void handle(CartAddItemCommand cartAddItemCommand);

    void handle(CartRemoveItemCommand cartRemoveItemCommand);

    CartQueryResponse handle(CartItemsQuery cartItemsQuery);


    class CartCreateCommand {

    }

    @Value
    class CartAddItemCommand {
        private String cartId;
        private long productId;
        private Integer quantity;
    }

    @Value
    class CartRemoveItemCommand {
        private String cartId;
        private long productId;
        private Integer quantity;
    }

    @Value
    class CartItemsQuery {
        private String cartId;
    }
}
