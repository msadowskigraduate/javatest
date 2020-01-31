package com.cdprojektred.javatest.productfinder;

import com.cdprojektred.javatest.eventstore.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEventListenerImpl implements ProductEventListener {
    private final ReadProductRepository readProductRepository;

    @EventListener
    @Override
    public void listen(Event.DomainAddEvent event) {
        readProductRepository.save(new ReadProduct(
                String.valueOf(event.getAggregateId()),
                event.getAggregateTitle(),
                event.getAggregatePrice().getAmount(),
                event.getAggregatePrice().getCurrency().getCurrencyCode()));
        log.info("Reached eventual consistency!");
    }

    @EventListener
    @Override
    public void listen(Event.DomainRemoveEvent event) {
        readProductRepository.deleteById(String.valueOf(event.getAggregateId()));
        log.info("Reached eventual consistency!");
    }

    @EventListener
    @Override
    public void listen(Event.DomainUpdateEvent event) {
        readProductRepository.save(new ReadProduct(String.valueOf(event.getAggregateId()),
                event.getAggregateTitle(), event.getAggregatePrice().getAmount(),
                event.getAggregatePrice().getCurrency().getCurrencyCode()));
        log.info("Reached eventual consistency!");
    }
}
