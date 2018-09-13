package org.edhernandez.junit.jupiter.dataprovider;

import org.edhernandez.junit.jupiter.dataprovider.annotation.JsonSource;
import org.edhernandez.junit.jupiter.dataprovider.argument.TestArgument;
import org.edhernandez.junit.jupiter.dataprovider.enums.JacksonPropertyNamingStrategy;
import org.edhernandez.junit.jupiter.dataprovider.provider.JsonArgumentProvider;
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
                return new String[] { "examples/json/mySimpleCase.json" };
            }

            @Override public Class<?> type() {
                return TestArgument.class;
            }

            @Override public JacksonPropertyNamingStrategy propertyNamingStrategy() {
                return JacksonPropertyNamingStrategy.LOWER_CAMEL_CASE;
            }
        };
        jsonArgumentProvider.accept(jsonSource);
        Stream<?> jsonArgumentStream = jsonArgumentProvider.provideArguments(null);
        Object[] arguments = jsonArgumentStream.toArray();
        assertEquals(1, arguments.length);
        assertEquals("value", ((TestArgument) ((Arguments) arguments[0]).get()[0])
                .getScenario().get("key"));
    }


    @Test
    public void provideArguments_withListJsonElement_interpretAsList() throws Exception {
        JsonSource jsonSource = new JsonSource() {

            @Override public Class<? extends Annotation> annotationType() {
                return JsonSource.class;
            }

            @Override public String[] values() {
                return new String[] { "examples/json/myListCase.json" };
            }

            @Override public Class<?> type() {
                return TestArgument.class;
            }

            @Override public JacksonPropertyNamingStrategy propertyNamingStrategy() {
                return JacksonPropertyNamingStrategy.LOWER_CAMEL_CASE;
            }
        };
        jsonArgumentProvider.accept(jsonSource);
        Stream<?> jsonArgumentStream = jsonArgumentProvider.provideArguments(null);
        Object[] arguments = jsonArgumentStream.toArray();
        assertEquals(2, arguments.length);
        assertEquals("value", ((TestArgument) ((Arguments) arguments[0]).get()[0])
                .getScenario().get("key"));
        assertEquals("otherValue", ((TestArgument) ((Arguments) arguments[1]).get()[0])
                .getScenario().get("otherKey"));
    }
}
