package com.folijet.columbia.core.metadata;

import com.folijet.columbia.core.metadata.api.EntityObject;
import com.folijet.columbia.core.metadata.api.Job;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class EventPayload {
    // uuid of the event
    private String eventId;

    //All previous events' UUIDs that caused this event
    private List<String> eventChain;

    private Job jobProfile;

    private String currentNodePath;//???

    private Map<String, EntityObject<JsonObject>> objects;


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public List<String> getEventChain() {
        return eventChain;
    }

    public void setEventChain(List<String> eventChain) {
        this.eventChain = eventChain;
    }

    public Job getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(Job jobProfile) {
        this.jobProfile = jobProfile;
    }

    public String getCurrentNodePath() {
        return currentNodePath;
    }

    public void setCurrentNodePath(String currentNodePath) {
        this.currentNodePath = currentNodePath;
    }

    public Map<String, EntityObject<JsonObject>> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, EntityObject<JsonObject>> objects) {
        this.objects = objects;
    }
}
