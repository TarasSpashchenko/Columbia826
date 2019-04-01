package com.folijet.columbia.core.metadata.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
public interface Node {
    @JsonIgnore
    List<Node> getChildren();

    <T extends Node> void add(T child);
    <T extends Node> void remove(T child);
    <T extends Node> T getChild(int i);
    <T extends Node> T getChild(String id);
    <T extends Node> T findChild(String path); //TODO: not sure this method should be here

    String getId();
    String getName();
}
