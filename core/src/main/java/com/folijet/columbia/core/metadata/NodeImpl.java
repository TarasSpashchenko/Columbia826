package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.folijet.columbia.core.metadata.api.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
abstract class NodeImpl implements Node {
    private String id;

    private String name;

    private List<Node> nodeContent = new ArrayList<>();

    public NodeImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public NodeImpl() {
        this(UUID.randomUUID().toString(),  "Undefined");
    }

    @Override
    public List<Node> getChildren() {
        return Collections.unmodifiableList(nodeContent);
    }

    @Override
    public <T extends Node> void add(T child) {
        nodeContent.add(child);
    }

    @Override
    public <T extends Node> void remove(T child) {
        if (Objects.nonNull(child)) {
            nodeContent.remove(child);
        }
    }

    @Override
    public <T extends Node> T getChild(int i) {
        return (T) nodeContent.get(i);
    }

    @Override
    public <T extends Node> T getChild(String id) {
        return (T) nodeContent.stream().filter(node -> id.equals(node.getId())).findFirst().orElse(null);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public <T extends Node> T findChild(String path) {
        //TODO: implement if needed
        return null;
    }

}
