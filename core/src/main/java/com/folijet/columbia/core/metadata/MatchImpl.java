package com.folijet.columbia.core.metadata;

import com.folijet.columbia.core.metadata.api.Match;
import com.folijet.columbia.core.metadata.api.MatchCriteria;
import com.folijet.columbia.core.metadata.api.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchImpl extends NodeImpl implements Match {
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
