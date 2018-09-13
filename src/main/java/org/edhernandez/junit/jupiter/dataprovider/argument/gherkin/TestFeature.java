package org.edhernandez.junit.jupiter.dataprovider.argument.gherkin;

import java.util.Objects;

public class TestFeature {

    private String feature;
    private String description;
    private Scenario scenario;

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TestFeature))
            return false;
        TestFeature that = (TestFeature) o;
        return Objects.equals(getFeature(), that.getFeature()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getScenario(), that.getScenario());
    }

    @Override public int hashCode() {

        return Objects.hash(getFeature(), getDescription(), getScenario());
    }
}
