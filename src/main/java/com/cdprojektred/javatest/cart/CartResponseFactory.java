package com.cdprojektred.javatest.cart;

import com.cdprojektred.javatest.productmutator.ProductMutatorFacadeImpl;
import com.cdprojektred.javatest.productmutator.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CartResponseFactory {
    private final ProductRepository facade;

    CartQueryResponse fromCart(Cart cart) {
        List<CartQueryResponse.CartReponseItem> items = cart.getCartItemList().stream()
                                                            .map((cartItem) -> facade.findById(cartItem.getProductid()))
                                                            .filter(Optional::isPresent)
                                                            .map(product -> helper(product.get()))
                                                            .collect(toList());
        return CartQueryResponse.builder()
                                .items(items)
                                .total(items.stream()
                                            .map(CartQueryResponse.CartReponseItem::getPrice)
                                            .reduce(BigDecimal.ZERO, BigDecimal::add)).build();
    }

    private CartQueryResponse.CartReponseItem helper(ProductMutatorFacadeImpl.Product product) {
        return CartQueryResponse.CartReponseItem.of(product.getTitle(), product.getPrice().getAmount(),
                product.getPrice().getCurrency().getCurrencyCode());
    }
}
