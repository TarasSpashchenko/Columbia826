package com.folijet.columbia.core.processing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.EventPayload;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class KafkaEventProcessor implements EventProcessor {
    private Vertx vertx;
    private KafkaConsumer<String, String> consumer;
    private KafkaProducer<String, String> producer;
    private List<? extends AbstractJobEventHandler> handlers;
    private Set<String> topics;
    private ObjectMapper mapper = new ObjectMapper();


    public KafkaEventProcessor(Vertx vertx, List<? extends AbstractJobEventHandler> handlers, Map<String, String> config, Set<String> topics) {
        this.vertx = vertx;
        this.handlers = handlers;
        this.consumer = KafkaConsumer.create(vertx, config);
        this.producer = KafkaProducer.create(vertx, config);
        this.topics = topics;
    }

    public void startEventProcessing() {
        consumer.asStream().handler(record -> {
            try {
                EventPayload eventPayload = mapper.readValue(record.value(), EventPayload.class);
                findEventHandler(eventPayload)
                        .ifPresent(jobEventHandler -> jobEventHandler.processEvent(vertx, eventPayload, this));
            } catch (IOException e) {
                log.error("error during parsing payload", e);
            }
        });
        consumer.subscribe(topics, ar -> {
            if (ar.succeeded()) {
                log.info("subscribed on topic");
            } else {
                log.error("Could not subscribe", ar.cause());
            }
        });
    }

    public void finishEventProcessing(EventPayload eventPayload) {
        try {
            EventPayload payload = prepareEventPayloadToSend(eventPayload);
            KafkaProducerRecord<String, String> record = KafkaProducerRecord.create(payload.getEntityType(), mapper.writeValueAsString(payload));
            producer.write(record);
        } catch (JsonProcessingException e) {
            log.error("error during parsing payload", e);
        }
    }

    private EventPayload prepareEventPayloadToSend(EventPayload eventPayload) {
        if (eventPayload.getEventId()!=null){
            eventPayload.setEventId(UUID.randomUUID().toString());
            eventPayload.getEventChain().add(eventPayload.getEventId());
        }
        return eventPayload;
    }

    private Optional<? extends AbstractJobEventHandler> findEventHandler(EventPayload eventPayload) {
        return handlers.stream().filter(handler ->
                handler.getEventType().equals(eventPayload.getEventType())
                        && handler.getEntityType().equals(eventPayload.getEntityType()))
                .findFirst();
    }
}
