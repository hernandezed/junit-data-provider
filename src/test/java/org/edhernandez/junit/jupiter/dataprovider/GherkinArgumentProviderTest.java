package org.edhernandez.junit.jupiter.dataprovider;

import org.edhernandez.junit.jupiter.dataprovider.annotation.GherkinSource;
import org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.TestFeature;
import org.edhernandez.junit.jupiter.dataprovider.provider.GherkinArgumentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GherkinArgumentProviderTest {

    GherkinArgumentProvider gherkinArgumentProvider;

    @BeforeEach
    void setUp() {
        gherkinArgumentProvider = new GherkinArgumentProvider();
    }

    @Test
    public void provideArguments_withSimpleGherkinElementWithoutTemplate_interpretSingleElement() throws Exception {
        GherkinSource gherkinSource = new GherkinSource() {

            @Override public Class<? extends Annotation> annotationType() {
                return GherkinSource.class;
            }

            @Override public String[] values() {
                return new String[] { "examples/gherkin/simpleCase.feature" };
            }

            @Override public String given() {
                return "";
            }

            @Override public String when() {
                return "";
            }

            @Override public String then() {
                return "";
            }
        };

        gherkinArgumentProvider.accept(gherkinSource);
        Stream<?> gherkinArgumentStream = gherkinArgumentProvider.provideArguments(null);
        Object[] arguments = gherkinArgumentStream.toArray();
        assertEquals(1, arguments.length);
        TestFeature gherkinTestArgument = ((TestFeature) ((Arguments) arguments[0]).get()[0]);
        assertEquals("Serve coffee", gherkinTestArgument.getFeature());
        assertEquals("In order to earn money Customers should be able to buy coffee at all times", gherkinTestArgument.getDescription());
        assertEquals("Buy last coffee", gherkinTestArgument.getScenario().getName());
        assertEquals(2, gherkinTestArgument.getScenario().getGiven().size());
        assertEquals(1, gherkinTestArgument.getScenario().getWhen().size());
        assertEquals(1, gherkinTestArgument.getScenario().getThen().size());
    }

    @Test
    public void provideArguments_withListGherkinElementWithoutTemplate_interpretAsList() throws Exception {
        GherkinSource gherkinSource = new GherkinSource() {

            @Override public Class<? extends Annotation> annotationType() {
                return GherkinSource.class;
            }

            @Override public String[] values() {
                return new String[] { "examples/gherkin/listCase.feature" };
            }

            @Override public String given() {
                return "";
            }

            @Override public String when() {
                return "";
            }

            @Override public String then() {
                return "";
            }
        };

        gherkinArgumentProvider.accept(gherkinSource);
        Stream<?> gherkinArgumentStream = gherkinArgumentProvider.provideArguments(null);
        Object[] arguments = gherkinArgumentStream.toArray();
        assertEquals(2, arguments.length);
        TestFeature gherkinTestArgument = ((TestFeature) ((Arguments) arguments[0]).get()[0]);
        assertEquals("Serve coffee", gherkinTestArgument.getFeature());
        assertEquals("In order to earn money Customers should be able to buy coffee at all times", gherkinTestArgument.getDescription());
        assertEquals("Buy last coffee", gherkinTestArgument.getScenario().getName());
        assertEquals(2, gherkinTestArgument.getScenario().getGiven().size());
        assertEquals(1, gherkinTestArgument.getScenario().getWhen().size());
        assertEquals(1, gherkinTestArgument.getScenario().getThen().size());

        TestFeature gherkinSecondTestArgument = ((TestFeature) ((Arguments) arguments[1]).get()[0]);
        assertEquals("Serve coffee", gherkinSecondTestArgument.getFeature());
        assertEquals("In order to earn money Customers should be able to buy coffee at all times", gherkinSecondTestArgument.getDescription());
        assertEquals("Buy two coffees", gherkinSecondTestArgument.getScenario().getName());
        assertEquals(2, gherkinSecondTestArgument.getScenario().getGiven().size());
        assertEquals(1, gherkinSecondTestArgument.getScenario().getWhen().size());
        assertEquals(1, gherkinSecondTestArgument.getScenario().getThen().size());
        assertNotEquals(gherkinTestArgument, gherkinSecondTestArgument);
    }

}
