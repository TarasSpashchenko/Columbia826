package com.folijet.columbia.core.processing;

import com.folijet.columbia.core.metadata.EventPayload;

public interface EventProcessor {

    void startEventProcessing();

    void finishEventProcessing(EventPayload eventPayload);
}
