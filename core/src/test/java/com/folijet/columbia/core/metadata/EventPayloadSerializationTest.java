package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.api.EntityObject;
import com.folijet.columbia.core.metadata.api.Job;
import com.folijet.columbia.core.metadata.api.Node;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class EventPayloadSerializationTest {
    private static final String SRS_MARC_FILE_NAME = "1b74ab75-9f41-4837-8662-a1d99118008d.json";

    @Test
    public void testEventPayloadSerialization() throws IOException {
        EventPayload simpleEventPayload = createSimpleEventPayload();

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(simpleEventPayload);

        log.info(msg);

        EventPayload eventPayload = om.readValue(msg, EventPayload.class);

        assertEquals(simpleEventPayload, eventPayload);

    }

    @Test
    public void testEventPayloadWithObjectSerialization() throws IOException {
        EventPayload simpleEventPayload = createEventPayloadWithObject();

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(simpleEventPayload);

        log.info(msg);

        EventPayload eventPayload = om.readValue(msg, EventPayload.class);

        assertEquals(simpleEventPayload, eventPayload);

    }

    private EventPayload createSimpleEventPayload() {
        EventPayload eventPayload = new EventPayload();
        eventPayload.setJobProfile(createJobWithSingleMatch());

        eventPayload.setCurrentNodePath(UUID.randomUUID().toString() + "." + UUID.randomUUID().toString() + "." + UUID.randomUUID().toString());
        eventPayload.setEventId(UUID.randomUUID().toString());
        eventPayload.setEventType("ACTION");
        eventPayload.setEntityType("ITEM");

        List<String> eventChain = new ArrayList<>();
        eventChain.add(UUID.randomUUID().toString());
        eventChain.add(UUID.randomUUID().toString());

        eventPayload.setEventChain(eventChain);

        return eventPayload;
    }

    private EventPayload createEventPayloadWithObject() {
        EventPayload eventPayload = new EventPayload();
        eventPayload.setJobProfile(createJobWithSingleMatch());

        Map<String, EntityObject<JsonObject>> objects = new HashMap<>();
        objects.put("MARC", getSourceRecordStorageMarcEntity());
        eventPayload.setObjects(objects);

        eventPayload.setCurrentNodePath(UUID.randomUUID().toString() + "." + UUID.randomUUID().toString() + "." + UUID.randomUUID().toString());
        eventPayload.setEventId(UUID.randomUUID().toString());
        eventPayload.setEventType("ACTION");
        eventPayload.setEntityType("ITEM");

        List<String> eventChain = new ArrayList<>();
        eventChain.add(UUID.randomUUID().toString());
        eventChain.add(UUID.randomUUID().toString());

        eventPayload.setEventChain(eventChain);

        return eventPayload;
    }

    private EntityObject<JsonObject> getSourceRecordStorageMarcEntity() {
        try (InputStream marcRecord = Thread.currentThread().getContextClassLoader().getResourceAsStream(SRS_MARC_FILE_NAME)) {
            JsonObject jsonObject = new JsonObject(IOUtils.toString(marcRecord, StandardCharsets.UTF_8));
            return new EntityObjectyImpl("MARC", jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Job createJobWithSingleMatch() {
        Job job = new JobImpl(UUID.randomUUID().toString(), "Test Job Profile 003");
        MatchCriteriaImpl matchCriteria = new MatchCriteriaImpl();
        matchCriteria.setCriteriaDetails("true");
        job.add(new MatchImpl(UUID.randomUUID().toString(), "Match Order line", "ORDER_LINE", matchCriteria));
        Node topLevelAction = job.getChild(0);
        topLevelAction.add(new ActionImpl(UUID.randomUUID().toString(), "Update Instance", "UPDATE", "INSTANCE", new MappingImpl()));
        topLevelAction.add(new ActionImpl(UUID.randomUUID().toString(), "Update Holdings", "UPDATE", "HOLDINGS", new MappingImpl()));
        topLevelAction.add(new ActionImpl(UUID.randomUUID().toString(), "Update Item", "UPDATE", "ITEM", new MappingImpl()));
        topLevelAction.add(new ActionImpl(UUID.randomUUID().toString(), "Create Invoice", "CREATE", "INVOICE", new MappingImpl()));
        topLevelAction.add(new ActionImpl(UUID.randomUUID().toString(), "Create MARCCat", "CREATE", "MARC_CAT", new MappingImpl()));

//        log.info(Json.encodePrettily(job));
        return job;
    }

}
