package com.cdprojektred.javatest.eventstore;

import com.cdprojektred.javatest.lib.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventStore extends MongoRepository<EventStream, String> {
    default void saveDomainAddEvent(long aggregateId, String aggregateTitle, Price aggregatePrice) {
        Optional<EventStream> eventStream = findByAggregateId(aggregateId);
        if(eventStream.isPresent()) {

        }
        save(new EventStream(aggregateId));
    }

    Optional<EventStream> findByAggregateId(long aggregateId);
}
