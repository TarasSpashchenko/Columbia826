package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.folijet.columbia.core.metadata.api.MatchCriteria;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
