package com.folijet.columbia.core.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.folijet.columbia.core.metadata.api.Mapping;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MappingImpl implements Mapping {
    private String mappingData;

    public String getMappingData() {
        return mappingData;
    }

    public void setMappingData(String mappingData) {
        this.mappingData = mappingData;
    }
}
