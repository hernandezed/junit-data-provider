package org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.step;

public enum StepType {
    GIVEN("Given"), WHEN("When"), THEN("Then"), AND("And"), BUT("But");

    private String key;

    StepType(String key) {
        this.key = key;
    }
}
