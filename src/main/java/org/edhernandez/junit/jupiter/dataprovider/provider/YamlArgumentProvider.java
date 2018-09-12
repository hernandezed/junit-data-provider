package org.edhernandez.junit.jupiter.dataprovider.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.edhernandez.junit.jupiter.dataprovider.annotation.YamlSource;
import org.edhernandez.junit.jupiter.dataprovider.argument.TestArgument;
import org.edhernandez.junit.jupiter.dataprovider.enums.JacksonPropertyNamingStrategy;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class YamlArgumentProvider implements ArgumentsProvider, AnnotationConsumer<YamlSource> {
    private String[] values;
    private Class<?> type;
    private ObjectMapper objectMapper;

    private PropertyNamingStrategy getPropertyNamingStrategy(JacksonPropertyNamingStrategy propertyNamingStrategyEnum) {
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

    @Override public void accept(YamlSource yamlSource) {
        values = yamlSource.values();
        type = yamlSource.type();
        objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.setPropertyNamingStrategy(getPropertyNamingStrategy(yamlSource.propertyNamingStrategy()))
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    @Override public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Arrays.stream(values)
                .map(fileName -> {
                    try {
                        JavaType jt = objectMapper.getTypeFactory().constructParametricType(List.class, type);
                        List arguments = objectMapper.readValue(getClass().getResourceAsStream("/" + fileName), jt);
                        if (type.equals(TestArgument.class)) {
                            arguments.forEach(argument -> {
                                ((TestArgument) argument).getScenario().setObjectMapper(objectMapper);
                                ((TestArgument) argument).getExpectation().setObjectMapper(objectMapper);
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

}
