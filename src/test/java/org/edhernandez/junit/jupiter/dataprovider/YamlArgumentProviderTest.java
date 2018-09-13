package org.edhernandez.junit.jupiter.dataprovider;

import org.edhernandez.junit.jupiter.dataprovider.annotation.YamlSource;
import org.edhernandez.junit.jupiter.dataprovider.argument.TestArgument;
import org.edhernandez.junit.jupiter.dataprovider.enums.JacksonPropertyNamingStrategy;
import org.edhernandez.junit.jupiter.dataprovider.provider.YamlArgumentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YamlArgumentProviderTest {

    private YamlArgumentProvider yamlArgumentProvider;

    @BeforeEach
    public void setUp() {
        this.yamlArgumentProvider = new YamlArgumentProvider();
    }

    @Test
    public void provideArguments_withSingleYamlElement_interpretAsList() throws Exception {
        YamlSource yamlSource = new YamlSource() {

            @Override public Class<? extends Annotation> annotationType() {
                return YamlSource.class;
            }

            @Override public String[] values() {
                return new String[] { "examples/yaml/mySimpleCase.yml" };
            }

            @Override public Class<?> type() {
                return TestArgument.class;
            }

            @Override public JacksonPropertyNamingStrategy propertyNamingStrategy() {
                return JacksonPropertyNamingStrategy.LOWER_CAMEL_CASE;
            }
        };
        yamlArgumentProvider.accept(yamlSource);
        Stream<?> jsonArgumentStream = yamlArgumentProvider.provideArguments(null);
        Object[] arguments = jsonArgumentStream.toArray();
        assertEquals(1, arguments.length);
        assertEquals("value", ((TestArgument) ((Arguments) arguments[0]).get()[0])
                .getScenario().get("key"));
    }

    @Test
    public void provideArguments_withListYamlElement_interpretAsList() throws Exception {
        YamlSource yamlSource = new YamlSource() {

            @Override public Class<? extends Annotation> annotationType() {
                return YamlSource.class;
            }

            @Override public String[] values() {
                return new String[] { "examples/yaml/myListCase.yml" };
            }

            @Override public Class<?> type() {
                return TestArgument.class;
            }

            @Override public JacksonPropertyNamingStrategy propertyNamingStrategy() {
                return JacksonPropertyNamingStrategy.LOWER_CAMEL_CASE;
            }
        };
        yamlArgumentProvider.accept(yamlSource);
        Stream<?> jsonArgumentStream = yamlArgumentProvider.provideArguments(null);
        Object[] arguments = jsonArgumentStream.toArray();
        assertEquals(2, arguments.length);
        assertEquals("value", ((TestArgument) ((Arguments) arguments[0]).get()[0])
                .getScenario().get("key"));
        assertEquals("otherValue", ((TestArgument) ((Arguments) arguments[1]).get()[0])
                .getScenario().get("otherKey"));
    }
}
