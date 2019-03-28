package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.folijet.columbia.core.metadata.api.EntityObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    private void unmarshallEntity(Map<String, Object> content) {
        this.entity = new JsonObject(content);
    }

    @JsonProperty("entity")
    @JsonRawValue
    private String marshallEntity() {
        return Json.encode(entity);
    }
}
