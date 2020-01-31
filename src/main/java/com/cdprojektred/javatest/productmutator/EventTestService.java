package com.cdprojektred.javatest.productmutator;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component()
@RequiredArgsConstructor
public class EventTestService implements ApplicationListener<ContextRefreshedEvent> {
    private final ProductMutatorFacade facade;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        facade.handle(new ProductMutatorFacade.ProductInsertCommand(
                "Fallout", BigDecimal.valueOf(1.99), "USD"
        ));
        facade.handle(new ProductMutatorFacade.ProductInsertCommand(
                "Don't Starve", BigDecimal.valueOf(2.99), "USD"
        ));
        facade.handle(new ProductMutatorFacade.ProductInsertCommand(
                "Baldur's Gate", BigDecimal.valueOf(3.99), "USD"
        ));
        facade.handle(new ProductMutatorFacade.ProductInsertCommand(
                "Icewind Dale", BigDecimal.valueOf(4.99), "USD"
        ));
        facade.handle(new ProductMutatorFacade.ProductInsertCommand(
                "Bloodborne", BigDecimal.valueOf(5.99), "USD"
        ));
    }
}
