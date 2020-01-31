package com.cdprojektred.javatest.gateway;

import com.cdprojektred.javatest.cart.CartFacade;
import com.cdprojektred.javatest.cart.CartQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartGateway {

    private final CartFacade facade;

    @GetMapping("/cart/{id}")
    @ResponseBody CartQueryResponse getCartBy(@PathVariable String id) {
        return facade.handle(new CartFacade.CartItemsQuery(id));
    }

    @PutMapping("/cart/{id}/add")
    void addItemToCart(@PathVariable String id, @RequestParam long pid) {
        facade.handle(new CartFacade.CartAddItemCommand(id, pid, 1));
    }

    @DeleteMapping("/cart/{id}/delete")
    void removeItemToCart(@PathVariable String id, @RequestParam long pid) {
        facade.handle(new CartFacade.CartRemoveItemCommand(id, pid, 1));
    }

    @PostMapping("/cart")
    String createCart() {
        return facade.handle(new CartFacade.CartCreateCommand());
    }
}
