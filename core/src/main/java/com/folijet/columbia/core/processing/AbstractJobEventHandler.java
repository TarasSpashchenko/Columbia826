package com.folijet.columbia.core.processing;

import com.folijet.columbia.core.metadata.EventPayload;
import io.vertx.core.Vertx;

/**
 * Common Handler for all event handlers. All EventHandlers should extend this class
 */
public abstract class AbstractJobEventHandler implements JobEventHandler {

    /**
     * Handle event and doing the main job of event handler
     *
     * @param eventPayload - event payload received by consumer
     * @param vertx        - application vertx object
     * @return - handled eventPayload
     */
    protected abstract EventPayload handle(EventPayload eventPayload, Vertx vertx);

    /**
     * Prepare new Event Payload for sending. Should update currentNodePath, entityType, eventType for next message
     *
     * @param currentEventPayload - current payload
     * @return - prepared next payload
     */
    protected abstract EventPayload prepareNextEvent(EventPayload currentEventPayload);

    public void processEvent(Vertx vertx, EventPayload eventPayload, EventProcessor eventProcessor) {
        EventPayload handledEvent = handle(eventPayload, vertx);
        eventProcessor.finishEventProcessing(prepareNextEvent(handledEvent));
    }
}
