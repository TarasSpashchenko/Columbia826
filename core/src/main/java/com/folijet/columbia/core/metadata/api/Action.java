package com.folijet.columbia.core.metadata.api;

public interface Action extends Node {
    String getActionType();
    String getEntityType();
    Mapping getMapping();
}
