package com.folijet.columbia;

import com.folijet.columbia.core.processing.EventProcessor;
import com.folijet.columbia.core.processing.EventProcessorBuilder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Server extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        Map<String, String> kafkaConfig = new HashMap<>();
        kafkaConfig.put("bootstrap.servers", "EPUAKHAL0027.kyiv.epam.com:9092");
        kafkaConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaConfig.put("group.id", "my_group");
        kafkaConfig.put("auto.offset.reset", "earliest");
        kafkaConfig.put("enable.auto.commit", "false");
        Set<String> topics = new HashSet<>();
        topics.add("ORDER");

        EventProcessor processor = EventProcessorBuilder.build(vertx, Collections.singletonList(new OrderHandler()), kafkaConfig, topics);
        processor.startEventProcessing();

        vertx.deployVerticle(new Server());
    }
}
