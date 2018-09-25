/**
 * Copyright 2018-2018 Eduardo Hernandez
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.edhernandez.junit.jupiter.dataprovider.provider;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.edhernandez.junit.jupiter.dataprovider.argument.TestArgument;
import org.edhernandez.junit.jupiter.dataprovider.enums.JacksonPropertyNamingStrategy;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Generalization of ArgumentProvider
 *
 * @author Eduardo Hernandez
 */
abstract class ArgumentProvider<T extends Annotation> implements ArgumentsProvider, AnnotationConsumer<T> {
    String[] values;
    Class<?> type;
    ObjectMapper objectMapper;

    PropertyNamingStrategy getPropertyNamingStrategy(JacksonPropertyNamingStrategy propertyNamingStrategyEnum) {
        switch (propertyNamingStrategyEnum) {
            case KEBAB_CASE:
                return PropertyNamingStrategy.KEBAB_CASE;
            case LOWER_CASE:
                return PropertyNamingStrategy.LOWER_CASE;
            case SNAKE_CASE:
                return PropertyNamingStrategy.SNAKE_CASE;
            case LOWER_CAMEL_CASE:
                return PropertyNamingStrategy.LOWER_CAMEL_CASE;
            case UPPER_CAMEL_CASE:
                return PropertyNamingStrategy.UPPER_CAMEL_CASE;
        }
        return null;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Arrays.stream(values)
                .map(fileName -> {
                    try {
                        JavaType jt = objectMapper.getTypeFactory().constructParametricType(List.class, type);
                        List arguments = objectMapper.readValue(getClass().getResourceAsStream(fixFilename(fileName)), jt);
                        if (type.equals(TestArgument.class)) {
                            arguments.forEach(argument -> {
                                if (((TestArgument) argument).getScenario() != null) {
                                    ((TestArgument) argument).getScenario().setObjectMapper(objectMapper);
                                }
                                if (((TestArgument) argument).getExpectation() != null) {
                                    ((TestArgument) argument).getExpectation().setObjectMapper(objectMapper);
                                }
                            });
                        }
                        return arguments;
                    } catch (IOException e) {
                        throw new IllegalStateException("File " + fileName + " doesn't exists.", e);
                    }
                })
                .flatMap(List::stream)
                .map(Arguments::of);
    }

    private String fixFilename(String filename) {
        if (!filename.startsWith("/")) {
            return "/" + filename;
        }
        return filename;
    }
}
