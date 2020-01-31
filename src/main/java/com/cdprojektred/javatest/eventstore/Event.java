package com.cdprojektred.javatest.eventstore;

import com.cdprojektred.javatest.lib.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.mongodb.core.mapping.Document;

abstract public class Event extends ApplicationEvent {

    public Event(Object source) {
        super(source);
    }

    @Getter
    static public class DomainAddEvent extends Event {
        private long aggregateId;
        private String aggregateTitle;
        private Price aggregatePrice;

        public DomainAddEvent(Object source, long aggregateId, String aggregateTitle, Price aggregatePrice) {
            super(source);
            this.aggregateId = aggregateId;
            this.aggregateTitle = aggregateTitle;
            this.aggregatePrice = aggregatePrice;
        }
    }

    @Getter
    static public class DomainUpdateEvent extends Event {
        private long aggregateId;
        private String aggregateTitle;
        private Price aggregatePrice;

        public DomainUpdateEvent(Object source, long aggregateId, String aggregateTitle, Price aggregatePrice) {
            super(source);
            this.aggregateId = aggregateId;
            this.aggregateTitle = aggregateTitle;
            this.aggregatePrice = aggregatePrice;
        }
    }

    @Getter
    static public class DomainRemoveEvent extends Event {
        private long aggregateId;

        public DomainRemoveEvent(Object source, long aggregateId) {
            super(source);
            this.aggregateId = aggregateId;
        }
    }
}
