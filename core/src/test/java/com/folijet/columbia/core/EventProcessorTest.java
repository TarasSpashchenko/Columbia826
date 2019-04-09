package com.folijet.columbia.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.ActionImpl;
import com.folijet.columbia.core.metadata.EventPayload;
import com.folijet.columbia.core.metadata.JobImpl;
import com.folijet.columbia.core.metadata.MappingImpl;
import com.folijet.columbia.core.metadata.MatchCriteriaImpl;
import com.folijet.columbia.core.metadata.MatchImpl;
import com.folijet.columbia.core.metadata.api.Action;
import com.folijet.columbia.core.metadata.api.Job;
import com.folijet.columbia.core.metadata.api.Match;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class EventProcessorTest {

    @Test
    public void testMessageSending() throws JsonProcessingException {
        Vertx vertx = Vertx.vertx();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", "1");

        KafkaProducer<String, String> producer = KafkaProducer.create(vertx, config);

        EventPayload eventPayload = new EventPayload();
        eventPayload.setEventId(UUID.randomUUID().toString());
        eventPayload.setEntityType("ITEM");
        eventPayload.setEventType("ACTION");
        eventPayload.setEventChain(Collections.singletonList(eventPayload.getEventId()));
        Action action1 = new ActionImpl(UUID.randomUUID().toString(), "Create ITEM", "CREATE", "ITEM", new MappingImpl());
        Action action2 = new ActionImpl(UUID.randomUUID().toString(), "Create ORDER", "CREATE", "ORDER", new MappingImpl());
        Match match = new MatchImpl(UUID.randomUUID().toString(), "Match ORDER", "CREATE", new MatchCriteriaImpl());
        Job job = new JobImpl(UUID.randomUUID().toString(), "TEST JOB");
        job.add(action1);
        job.add(action2);
        job.add(match);
        eventPayload.setJobProfile(job);
        eventPayload.setCurrentNodePath(job.getId() + "." + action1.getId());

        KafkaProducerRecord<String, String> record =
                KafkaProducerRecord.create("ITEM", "ITEM", mapper.writeValueAsString(eventPayload));
        producer.write(record);

    }
}
