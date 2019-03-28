package com.folijet.columbia.core.metadata;

import com.folijet.columbia.core.metadata.api.Action;
import com.folijet.columbia.core.metadata.api.Mapping;

public class ActionImpl extends NodeImpl implements Action {
    private String actionType;
    private String entityType;
    private Mapping mapping;

    public ActionImpl() {
        super();
    }

    public ActionImpl(String actionType, String entityType, Mapping mapping) {
        super();

        this.actionType = actionType;
        this.entityType = entityType;
        this.mapping = mapping;
    }

    public ActionImpl(String id, String name, String actionType, String entityType, Mapping mapping) {
        super(id, name);

        this.actionType = actionType;
        this.entityType = entityType;
        this.mapping = mapping;
    }

    @Override
    public String getActionType() {
        return actionType;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }

    @Override
    public Mapping getMapping() {
        return mapping;
    }

}
