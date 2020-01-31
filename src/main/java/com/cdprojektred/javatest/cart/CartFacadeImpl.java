package com.cdprojektred.javatest.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {
    private final CartRepository repository;
    private final CartResponseFactory responseFactory;

    @Override
    public String handle(CartCreateCommand cartCreateCommand) {
        Cart cart = new Cart();
        return repository.save(cart).getId();
    }

    @Override
    public void handle(CartAddItemCommand cartAddItemCommand) {
        Optional<Cart> cart = repository.findById(cartAddItemCommand.getCartId());

        if (cart.isPresent()) {
            Cart cart1 = cart.get()
                             .apply(new DomainCartEvent.AddItem(cartAddItemCommand.getProductId(), cartAddItemCommand
                                     .getQuantity()));
            repository.save(cart1);
        } else {
            throw new NoCartException(cartAddItemCommand.getCartId());
        }
    }

    @Override
    public void handle(CartRemoveItemCommand cartRemoveItemCommand) {
        Optional<Cart> cart = repository.findById(cartRemoveItemCommand.getCartId());

        if (cart.isPresent()) {
            Cart cart1 = cart.get().apply(new DomainCartEvent.AddItem(cartRemoveItemCommand
                    .getProductId(), cartRemoveItemCommand.getQuantity()));
            repository.save(cart1);
        } else {
            throw new NoCartException(cartRemoveItemCommand.getCartId());
        }
    }

    @Override
    public CartQueryResponse handle(CartItemsQuery cartItemsQuery) {
        Optional<Cart> cart = repository.findById(cartItemsQuery.getCartId());

        if (cart.isPresent()) {
            return responseFactory.fromCart(cart.get());
        } else {
            throw new NoCartException(cartItemsQuery.getCartId());
        }
    }
}
