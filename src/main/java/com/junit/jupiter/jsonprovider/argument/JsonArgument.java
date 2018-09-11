package com.junit.jupiter.jsonprovider.argument;

import com.junit.jupiter.jsonprovider.provider.ArgumentMap;

public class JsonArgument {
    private String name;
    private ArgumentMap<String, Object> scenario;
    private ArgumentMap<String, Object> expectation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArgumentMap<String, Object> getScenario() {
        return scenario;
    }

    public void setScenario(ArgumentMap<String, Object> scenario) {
        this.scenario = scenario;
    }

    public ArgumentMap<String, Object> getExpectation() {
        return expectation;
    }

    public void setExpectation(ArgumentMap<String, Object> expectation) {
        this.expectation = expectation;
    }

    @Override public String toString() {
        return name;
    }
}
