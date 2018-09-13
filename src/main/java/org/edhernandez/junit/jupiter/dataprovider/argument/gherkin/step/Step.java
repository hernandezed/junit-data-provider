package org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.step;

import java.util.Objects;

public class Step {
    private String description;
    private StepType stepType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StepType getStepType() {
        return stepType;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Step))
            return false;
        Step step = (Step) o;
        return Objects.equals(getDescription(), step.getDescription()) &&
                getStepType() == step.getStepType();
    }

    @Override public int hashCode() {

        return Objects.hash(getDescription(), getStepType());
    }
}
