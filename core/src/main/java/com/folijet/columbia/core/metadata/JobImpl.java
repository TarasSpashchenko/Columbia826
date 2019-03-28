package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.folijet.columbia.core.metadata.api.Job;
import com.folijet.columbia.core.metadata.api.Node;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class JobImpl extends NodeImpl implements Job {

    public JobImpl(String id, String name) {
        super(id, name);
    }

    public JobImpl() {
        super();
    }
}
