package org.edhernandez.junit.jupiter.json;

import org.edhernandez.junit.jupiter.jsonprovider.annotation.JsonSource;
import org.edhernandez.junit.jupiter.jsonprovider.argument.JsonArgument;
import org.edhernandez.junit.jupiter.jsonprovider.enums.JacksonPropertyNamingStrategy;
import org.edhernandez.junit.jupiter.jsonprovider.provider.JsonArgumentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonArgumentProviderTest {

    private JsonArgumentProvider jsonArgumentProvider;

    @BeforeEach
    public void setUp() {
        this.jsonArgumentProvider = new JsonArgumentProvider();
    }

    @Test
    public void provideArguments_withSingleJsonElement_interpretAsList() throws Exception {
        JsonSource jsonSource = new JsonSource() {

            @Override public Class<? extends Annotation> annotationType() {
                return JsonSource.class;
            }

            @Override public String[] values() {
                return new String[] { "examples/mySimpleCase.json" };
            }

            @Override public Class<?> type() {
                return JsonArgument.class;
            }

            @Override public JacksonPropertyNamingStrategy propertyNamingStrategy() {
                return JacksonPropertyNamingStrategy.LOWER_CAMEL_CASE;
            }
        };
        jsonArgumentProvider.accept(jsonSource);
        Stream<?> jsonArgumentStream = jsonArgumentProvider.provideArguments(null);
        Object[] arguments = jsonArgumentStream.toArray();
        assertEquals(1, arguments.length);
        assertEquals("value", ((JsonArgument) ((Arguments) arguments[0]).get()[0])
                .getScenario().get("key"));
    }


    @Test
    public void provideArguments_withListJsonElement_interpretAsList() throws Exception {
        JsonSource jsonSource = new JsonSource() {

            @Override public Class<? extends Annotation> annotationType() {
                return JsonSource.class;
            }

            @Override public String[] values() {
                return new String[] { "examples/myListCase.json" };
            }

            @Override public Class<?> type() {
                return JsonArgument.class;
            }

            @Override public JacksonPropertyNamingStrategy propertyNamingStrategy() {
                return JacksonPropertyNamingStrategy.LOWER_CAMEL_CASE;
            }
        };
        jsonArgumentProvider.accept(jsonSource);
        Stream<?> jsonArgumentStream = jsonArgumentProvider.provideArguments(null);
        Object[] arguments = jsonArgumentStream.toArray();
        assertEquals(2, arguments.length);
        assertEquals("value", ((JsonArgument) ((Arguments) arguments[0]).get()[0])
                .getScenario().get("key"));
        assertEquals("otherValue", ((JsonArgument) ((Arguments) arguments[1]).get()[0])
                .getScenario().get("otherKey"));
    }
}
