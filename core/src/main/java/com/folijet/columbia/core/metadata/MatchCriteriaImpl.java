package com.folijet.columbia.core.metadata;

import com.folijet.columbia.core.metadata.api.MatchCriteria;

public class MatchCriteriaImpl implements MatchCriteria {
    private String criteriaDetails;

    public void setCriteriaDetails(String criteriaDetails) {
        this.criteriaDetails = criteriaDetails;
    }

    @Override
    public String getCriteriaDetails() {
        return criteriaDetails;
    }
}
