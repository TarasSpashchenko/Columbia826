package com.folijet.columbia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.EventPayload;
import com.folijet.columbia.core.processing.AbstractJobEventHandler;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class OrderHandler extends AbstractJobEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderHandler.class);

    @Override
    protected EventPayload handle(EventPayload eventPayload, Vertx vertx) {
        LOGGER.info("ORDER CREATED!");
        try {
            LOGGER.info(new ObjectMapper().writeValueAsString(eventPayload));
        } catch (JsonProcessingException e) {
            LOGGER.error(e);
        }
        return eventPayload;
    }

    @Override
    protected EventPayload prepareNextEvent(EventPayload currentEventPayload) {
        currentEventPayload.setEventType("FINISHING");
        return currentEventPayload;
    }

    @Override
    public String getEventType() {
        return "ACTION";
    }

    @Override
    public String getEntityType() {
        return "ORDER";
    }
}
