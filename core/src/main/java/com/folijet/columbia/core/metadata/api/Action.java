package com.folijet.columbia.core.metadata.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
public interface Action extends Node {
    String getActionType();

    String getEntityType();

    Mapping getMapping();
}
