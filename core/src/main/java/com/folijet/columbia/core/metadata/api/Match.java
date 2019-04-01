package com.folijet.columbia.core.metadata.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
public interface Match extends Node {
    String getMatchEntityType();

    MatchCriteria getMatchCriteria();

    @JsonIgnore
    List<Node> getNonMatchesChildren();

    <T extends Node> void addNonMatches(T child);

    <T extends Node> void removeNonMatches(T child);

    <T extends Node> T getNonMatchesChild(int i);

    <T extends Node> T getNonMatchesChild(String id);

    @JsonIgnore
    default List<Node> getMatchesChildren() {
        return getChildren();
    }

    default <T extends Node> void addMatches(T child) {
        add(child);
    }

    default <T extends Node> void removeMatches(T child) {
        remove(child);
    }

    default <T extends Node> T getMatchesChild(int i) {
        return getChild(i);
    }

    default <T extends Node> T getMatchesChild(String id){
        return getChild(id);
    }

}
