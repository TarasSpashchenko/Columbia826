package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.folijet.columbia.core.metadata.api.EntityObject;
import com.folijet.columbia.core.metadata.api.Job;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventPayload {
    // uuid of the event
    private String eventId;

    // event type like "ACTION", "MATCH", "ERROR", etc.
    private String eventType;

    // entity type like "INSTANCE", "HOLDINGS", etc.
    private String entityType;

    //All previous events' UUIDs that caused this event
    private List<String> eventChain;

    // The whole Job Profile Snapshot
    private Job jobProfile;

    // Dot separated path to the current node from the root
    private String currentNodePath;

    private Map<String, EntityObject<JsonObject>> objects;

}
