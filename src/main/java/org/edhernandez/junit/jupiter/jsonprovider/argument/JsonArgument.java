/**
 * Copyright 2018-2018 Eduardo Hernandez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.edhernandez.junit.jupiter.jsonprovider.argument;

/**
 *  Represents file content for Junit5 test
 *
 *  @author Eduardo Hernandez
 */
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
