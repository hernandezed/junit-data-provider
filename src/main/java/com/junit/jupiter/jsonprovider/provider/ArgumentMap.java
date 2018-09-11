/**
 * Copyright 2018-2018 Eduardo Hernandez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.junit.jupiter.jsonprovider.provider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

/**
 *
 * Extend hash map and add get method for automatic mapping to specific class
 *
 * @param <T> Key Class
 * @param <S> Value Class
 */
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
