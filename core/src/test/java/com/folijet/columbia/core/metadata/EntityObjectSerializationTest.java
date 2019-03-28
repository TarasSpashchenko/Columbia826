package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folijet.columbia.core.metadata.api.EntityObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class EntityObjectSerializationTest {
    private static final String SRS_MARC_FILE_NAME = "1b74ab75-9f41-4837-8662-a1d99118008d.json";

    @Test
    public void testEntityObjectSerialization() throws IOException {

        EntityObject<JsonObject> jsonObjectEntityObject = getSourceRecordStorageMarcEntity();

        ObjectMapper om = new ObjectMapper();
        String msg = om.writeValueAsString(jsonObjectEntityObject);

        log.info(msg);

        EntityObject<JsonObject> entityObject = om.readValue(msg, EntityObject.class);

        log.info(Json.encode(entityObject.getEntity()));
        assertEquals(jsonObjectEntityObject, entityObject);

    }

    private EntityObject<JsonObject> getSourceRecordStorageMarcEntity() {
        try (InputStream marcRecord = Thread.currentThread().getContextClassLoader().getResourceAsStream(SRS_MARC_FILE_NAME)) {
            JsonObject jsonObject = new JsonObject(IOUtils.toString(marcRecord, StandardCharsets.UTF_8));
            return new EntityObjectyImpl("MARC", jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
