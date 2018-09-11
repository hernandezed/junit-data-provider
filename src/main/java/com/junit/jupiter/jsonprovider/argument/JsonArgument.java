package com.junit.jupiter.jsonprovider.argument;

import java.util.Map;

public class JsonArgument {
    private String name;
    private Map<String, Object> scenario;
    private Map<String, Object> expectation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getScenario() {
        return scenario;
    }

    public void setScenario(Map<String, Object> scenario) {
        this.scenario = scenario;
    }

    public Map<String, Object> getExpectation() {
        return expectation;
    }

    public void setExpectation(Map<String, Object> expectation) {
        this.expectation = expectation;
    }

    @Override public String toString() {
        return name;
    }
}
