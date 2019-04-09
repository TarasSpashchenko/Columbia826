package com.folijet.columbia.core.processing;

import com.folijet.columbia.core.metadata.EventPayload;
import io.vertx.core.Vertx;

public interface JobEventHandler {

    // event type like "ACTION", "MATCH", "ERROR", etc.
    String getEventType();

    // entity type like "INSTANCE", "HOLDINGS", etc.
    String getEntityType();

    /**
     * Process received event message
     * @param vertx - vetx object
     * @param eventPayload - received event message
     * @param eventProcessor - eventProcessor for calling finish event logic
     */
    void processEvent(Vertx vertx, EventPayload eventPayload, EventProcessor eventProcessor);
}
