package com.folijet.columbia.core.processing;

import com.folijet.columbia.core.metadata.EventPayload;

/**
 * Common entry point for start listening and processing messages
 */
public interface EventProcessor {

    /**
     * Call this method to start listening messages and consumes them
     */
    void startEventProcessing();

    /**
     * Finish event processing and send next message to the query
     *
     * @param eventPayload - prepared event payload to send
     */
    void finishEventProcessing(EventPayload eventPayload);
}
