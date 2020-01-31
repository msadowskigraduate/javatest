package com.cdprojektred.javatest.eventstore;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Document
class EventStream {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private long aggregateId;
    private List<DomainEvent> eventList;

    public EventStream(long aggregateId) {
        this.aggregateId = aggregateId;
    }
}
