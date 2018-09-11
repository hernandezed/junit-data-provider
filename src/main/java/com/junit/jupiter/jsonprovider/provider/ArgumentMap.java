package com.junit.jupiter.jsonprovider.provider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class ArgumentMap<T, S> extends HashMap<T, S> {
    @JsonIgnore
    private ObjectMapper objectMapper;

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <R> R get(Object key, Class<R> aClass) {
        if (super.get(key) != null) {
            return objectMapper.convertValue(super.get(key), aClass);
        }
        return null;
    }
}
