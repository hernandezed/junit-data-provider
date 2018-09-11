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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.junit.jupiter.jsonprovider.annotation.JsonSource;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *  Read arguments for Junit5 test from json file
 *
 *  @author Eduardo Hernandez
 */
public class JsonArgumentProvider implements ArgumentsProvider, AnnotationConsumer<JsonSource> {

    private String[] values;
    private Class<?> type;
    private ObjectMapper objectMapper;

    @Override public void accept(JsonSource jsonSource) {
        values = jsonSource.values();
        type = jsonSource.type();
        objectMapper = new ObjectMapper();
        try {
            objectMapper.setPropertyNamingStrategy((PropertyNamingStrategy) jsonSource.propertyNamingStrategy().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Can't create object mapper with strategy with name " + jsonSource.propertyNamingStrategy() + ".", e);
        }
    }

    @Override public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        return Arrays.stream(values)
                .map(fileName -> {
                    try {
                        return objectMapper.readValue(getClass().getResourceAsStream("/" + fileName), type);
                    } catch (IOException e) {
                        throw new IllegalStateException("File " + fileName + " doesn't exists.", e);
                    }
                })
                .map(Arguments::of);

    }

}
