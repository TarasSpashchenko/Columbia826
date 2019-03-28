package com.folijet.columbia.core.metadata;

import com.folijet.columbia.core.metadata.api.Mapping;

public class MappingImpl implements Mapping {
    private String mappingData;

    public String getMappingData() {
        return mappingData;
    }

    public void setMappingData(String mappingData) {
        this.mappingData = mappingData;
    }
}
