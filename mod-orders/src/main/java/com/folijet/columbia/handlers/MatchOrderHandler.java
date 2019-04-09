package com.folijet.columbia.handlers;

import com.folijet.columbia.core.metadata.EventPayload;
import com.folijet.columbia.core.processing.AbstractJobEventHandler;
import io.vertx.core.Vertx;

public class MatchOrderHandler extends AbstractJobEventHandler {

    @Override
    protected EventPayload handle(EventPayload eventPayload, Vertx vertx) {
        return null;
    }

    @Override
    protected EventPayload prepareNextEvent(EventPayload currentEventPayload) {
        return null;
    }

    @Override
    public String getEventType() {
        return "MATCH";
    }

    @Override
    public String getEntityType() {
        return "ORDER";
    }
}
