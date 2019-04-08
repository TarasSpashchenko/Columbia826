package com.folijet.columbia.core.processing;

import com.folijet.columbia.core.metadata.EventPayload;
import io.vertx.core.Vertx;

public abstract class AbstractJobEventHandler implements JobEventHandler {

    protected abstract EventPayload handle(EventPayload eventPayload, Vertx vertx);

    protected abstract EventPayload prepareNextEvent(EventPayload currentEventPayload);

    public void processEvent(Vertx vertx, EventPayload eventPayload, EventProcessor eventProcessor) {
        EventPayload handledEvent = handle(eventPayload, vertx);
        eventProcessor.finishEventProcessing(prepareNextEvent(handledEvent));
    }
}
