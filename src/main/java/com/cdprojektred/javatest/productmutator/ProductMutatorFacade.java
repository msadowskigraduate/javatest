package com.cdprojektred.javatest.productmutator;

import com.cdprojektred.javatest.lib.Command;
import lombok.*;

import java.math.BigDecimal;

public interface ProductMutatorFacade {

    void handle(ProductInsertCommand command);

    void handle(ProductUpdateCommand command);

    void handle(ProductRemoveCommand command);

    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
     class ProductCommand implements Command {}

     @AllArgsConstructor
     @Getter
     class ProductInsertCommand extends ProductCommand {
        private long id;
        private final String title;
        private final BigDecimal price;
        private final String currencyCode;

         public ProductInsertCommand(String title, BigDecimal price, String currencyCode) {
             this.title = title;
             this.price = price;
             this.currencyCode = currencyCode;
         }
     }

     @Value(staticConstructor = "of")
     class ProductUpdateCommand extends ProductCommand {
        private final long id;
        private final String title;
        private final BigDecimal price;
        private final String currencyCode;
     }

     @Value(staticConstructor = "of")
     class ProductRemoveCommand extends ProductCommand {
        private final long id;
     }

}
