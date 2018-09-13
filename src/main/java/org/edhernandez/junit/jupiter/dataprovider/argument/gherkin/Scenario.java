package org.edhernandez.junit.jupiter.dataprovider.argument.gherkin;

import org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.step.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scenario {
    private String name;
    private List<Step> given = new ArrayList<>();
    private List<Step> when = new ArrayList<>();
    private List<Step> then = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Step> getGiven() {
        return given;
    }

    public void setGiven(List<Step> given) {
        this.given = given;
    }

    public List<Step> getWhen() {
        return when;
    }

    public void setWhen(List<Step> when) {
        this.when = when;
    }

    public List<Step> getThen() {
        return then;
    }

    public void setThen(List<Step> then) {
        this.then = then;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Scenario))
            return false;
        Scenario scenario = (Scenario) o;
        return Objects.equals(getName(), scenario.getName()) &&
                Objects.equals(getGiven(), scenario.getGiven()) &&
                Objects.equals(getWhen(), scenario.getWhen()) &&
                Objects.equals(getThen(), scenario.getThen());
    }

    @Override public int hashCode() {

        return Objects.hash(getName(), getGiven(), getWhen(), getThen());
    }
}
