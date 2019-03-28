package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.folijet.columbia.core.metadata.api.Match;
import com.folijet.columbia.core.metadata.api.MatchCriteria;
import com.folijet.columbia.core.metadata.api.Node;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MatchImpl extends NodeImpl implements Match {
    @JsonProperty
    private List<Node> nonMatchesContent = new ArrayList<>();

    private MatchCriteria matchCriteria;

    private String matchEntityType;

    public MatchImpl() {
        super();
    }

    public MatchImpl(String matchEntityType, MatchCriteria matchCriteria) {
        super();
        this.matchEntityType = matchEntityType;
        this.matchCriteria = matchCriteria;
    }

    public MatchImpl(String id, String name, String matchEntityType, MatchCriteria matchCriteria) {
        super(id, name);
        this.matchEntityType = matchEntityType;
        this.matchCriteria = matchCriteria;
    }

    @Override
    public String getMatchEntityType() {
        return matchEntityType;
    }

    @Override
    public MatchCriteria getMatchCriteria() {
        return matchCriteria;
    }

    @JsonIgnore
    @Override
    public List<Node> getNonMatchesChildren() {
        return Collections.unmodifiableList(nonMatchesContent);
    }

    @Override
    public <T extends Node> void addNonMatches(T child) {
        nonMatchesContent.add(child);
    }

    @Override
    public <T extends Node> void removeNonMatches(T child) {
        nonMatchesContent.remove(child);
    }

    @Override
    public <T extends Node> T getNonMatchesChild(int i) {
        return (T) nonMatchesContent.get(i);
    }

    @Override
    public <T extends Node> T getNonMatchesChild(String id) {
        return null;
    }

}
