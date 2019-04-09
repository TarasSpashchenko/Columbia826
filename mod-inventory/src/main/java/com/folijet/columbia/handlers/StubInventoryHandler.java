package com.folijet.columbia.handlers;

import com.folijet.columbia.core.metadata.EventPayload;
import com.folijet.columbia.core.processing.AbstractJobEventHandler;
import io.vertx.core.Vertx;

public class StubInventoryHandler extends AbstractJobEventHandler {

    @Override
    protected EventPayload handle(EventPayload eventPayload, Vertx vertx) {
        return eventPayload;
    }

    @Override
    protected EventPayload prepareNextEvent(EventPayload currentEventPayload) {
        return currentEventPayload;
    }

    @Override
    public String getEventType() {
        return "ACTION";
    }

    @Override
    public String getEntityType() {
        return "ITEM";
    }
}
