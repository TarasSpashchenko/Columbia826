package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.folijet.columbia.core.metadata.api.EntityObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.util.Map;

public class EntityObjectyImpl implements EntityObject<JsonObject> {
    private JsonObject entity;
    private String entityType;

    public EntityObjectyImpl() {
        super();
    }

    public EntityObjectyImpl(String entityType) {
        this(entityType, new JsonObject());
    }

    public EntityObjectyImpl(String entityType, JsonObject entity) {
        this.entityType = entityType;
        ;
        this.entity = entity;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }

    @Override
    public JsonObject getEntity() {
        return entity;
    }


    @JsonProperty("entity")
    private void unmarshallEntity(String entity) {
        this.entity = JsonObject.mapFrom(entity);
    }

    @JsonProperty("entity")
    @JsonRawValue
    private String marshallEntity() {
        return Json.encode(entity);
    }
}
