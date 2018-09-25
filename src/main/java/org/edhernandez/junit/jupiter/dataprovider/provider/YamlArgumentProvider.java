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

public class YamlArgumentProvider extends ArgumentProvider<YamlSource> {
    @Override
    public void accept(YamlSource yamlSource) {
        values = yamlSource.values();
        type = yamlSource.type();
        objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.setPropertyNamingStrategy(getPropertyNamingStrategy(yamlSource.propertyNamingStrategy()))
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

}
