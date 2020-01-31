package com.cdprojektred.javatest.productmutator;

import com.cdprojektred.javatest.eventstore.EventPublisher;
import com.cdprojektred.javatest.lib.BaseAggregateRoot;
import com.cdprojektred.javatest.lib.CommandPolicyBus;
import com.cdprojektred.javatest.lib.Price;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductMutatorFacadeImpl implements ProductMutatorFacade {

    private final ProductRepository productRepository;
    private final CommandPolicyBus productCommandPolicyBus;
    private final EventPublisher eventPublisher;

    @Override
    public void handle(ProductInsertCommand command) {
        productCommandPolicyBus.apply(command);
        Product product = new Product(command.getTitle(), command.getPrice(),
                command.getCurrencyCode());
        Product p = productRepository.saveAndFlush(product);
        eventPublisher.applyAdd(p.id, p.title, p.price);
    }

    @Override
    public void handle(ProductUpdateCommand command) {
        Optional<Product> product = productRepository.findById(command.getId());
        //if does not exist, do nothing
        if(!product.isPresent()) throw new NoProductFoundException(command.getId());
        if(command.getTitle() != null) {
            product.get().setTitle(command.getTitle());
        }

        if(command.getPrice() != null) {
            Currency currentCurrencyCode = product.get().getPrice().getCurrency();
            if(command.getCurrencyCode() != null) {
                currentCurrencyCode = Currency.getInstance(command.getCurrencyCode());
            }
            Price price = Price.of(command.getPrice(), currentCurrencyCode);
            product.get().setPrice(price);
        }

        Product product1 = productRepository.saveAndFlush(product.get());
        eventPublisher.applyUpdate(product1.getId(), product1.getTitle(), product1.getPrice());
    }

    @Override
    public void handle(ProductRemoveCommand command) {
        productRepository.deleteById(command.getId());
        eventPublisher.applyDelete(command.getId());
    }

    /**
     * An alternative approach would be to store price patches in a collection, which would allow
     * to easily compute price history
     */
    @Entity
    @Data
    @NoArgsConstructor
    @Table(name = "Products")
    public static class Product extends BaseAggregateRoot {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private long id;

        private String title;

        @Embedded
        private Price price;

        Product(String title, BigDecimal price, String currencyCode) {
            this.title = title;
            this.price = Price.of(price, Currency.getInstance(currencyCode));
        }
    }
}
