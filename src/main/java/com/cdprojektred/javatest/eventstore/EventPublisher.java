package com.cdprojektred.javatest.eventstore;

import com.cdprojektred.javatest.lib.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final EventStore eventStore;

    public void applyAdd(long id, String title, Price price) {
        Event event = new Event.DomainAddEvent(this, id, title, price);
        applicationEventPublisher.publishEvent(event);
    }

    public void applyDelete(long id) {
        Event event = new Event.DomainRemoveEvent(this, id);
        applicationEventPublisher.publishEvent(event);
    }

    public void applyUpdate(long id, String title, Price price) {
        Event event = new Event.DomainUpdateEvent(this, id, title, price);
        applicationEventPublisher.publishEvent(event);
    }
}
