package com.folijet.columbia;

import com.folijet.columbia.core.processing.EventProcessor;
import com.folijet.columbia.core.processing.EventProcessorBuilder;
import com.folijet.columbia.handlers.StubInventoryHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class InventoryVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        Vertx vertx = getVertx();
        EventProcessor processor = EventProcessorBuilder
                .build(vertx, Collections.singletonList(new StubInventoryHandler()), getConfig(), Collections.singleton("ITEM"));
        processor.startEventProcessing();
        log.info("InventoryVerticle started");
    }

    @Override
    public void stop(Future stopFuture) throws Exception {
        log.info("InventoryVerticle stoped");
    }

    private Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("group.id", "my_group");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");
        config.put("acks", "1");
        return config;
    }

}
