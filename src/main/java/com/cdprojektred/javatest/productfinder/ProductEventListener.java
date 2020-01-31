package com.cdprojektred.javatest.productfinder;


import com.cdprojektred.javatest.eventstore.Event;

interface ProductEventListener {
    void listen(Event.DomainAddEvent event);
    void listen(Event.DomainRemoveEvent event);
    void listen(Event.DomainUpdateEvent event);
}