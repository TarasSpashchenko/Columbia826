package com.folijet.columbia.core.processing;

import io.vertx.core.Vertx;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventProcessorBuilder {
    public static EventProcessor build(Vertx vertx, List<? extends AbstractJobEventHandler> eventHandlers, Map<String, String> config, Set<String> topics) {
        return new KafkaEventProcessor(vertx, eventHandlers, config, topics);
    }
}
