package com.cdprojektred.javatest.cart;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Document
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Embedded
    private List<CartItem> cartItemList;

    public Cart apply(DomainCartEvent event) {
        if(event instanceof DomainCartEvent.AddItem) {
            return this.apply((DomainCartEvent.AddItem) event);
        } else if(event instanceof DomainCartEvent.RemoveItem) {
            return this.apply((DomainCartEvent.RemoveItem) event);
        }
        return this;
    }

    private Cart apply(DomainCartEvent.AddItem addItem) {
        if(this.cartItemList == null) {
            cartItemList = new ArrayList<>();
        }

        //This is a technical dept -> should be refactored to policy oriented
        if(this.cartItemList.size() >= 3) {
            throw new OrderLimitReachedException();
        }

        cartItemList.add(new CartItem(addItem.getProductId()));
        return this;
    }

    private Cart apply(DomainCartEvent.RemoveItem removeItem) {
        if(this.cartItemList == null) return this;
        Optional<CartItem> cartItem = cartItemList.stream()
                                                  .filter((item) -> item.productid == removeItem.getProductId())
                                                  .findFirst();
        cartItem.ifPresent(item -> cartItemList.remove(item));
        return this;
    }

    @Embeddable
    @Data
    static class CartItem {
       private long productid;
       private Integer quantity;

        public CartItem(long productid) {
            this.productid = productid;
            this.quantity = 1;
        }
    }
}
