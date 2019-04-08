package com.folijet.columbia.core.processing;

import com.folijet.columbia.core.metadata.EventPayload;
import io.vertx.core.Vertx;

public interface JobEventHandler {

    String getEventType();

    String getEntityType();

    void processEvent(Vertx vertx, EventPayload eventPayload, EventProcessor eventProcessor);
}
