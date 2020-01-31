package com.cdprojektred.javatest.cart;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class CartQueryResponse {
    private List<CartReponseItem> items;
    private BigDecimal total;


    @Value(staticConstructor = "of")
    static class CartReponseItem {
        private String name;
        private BigDecimal price;
        private String currencyCode;
    }
}
