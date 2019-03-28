package com.folijet.columbia.core.metadata.api;

public interface EntityObject<T> {
    String getEntityType();
    T getEntity();
}
