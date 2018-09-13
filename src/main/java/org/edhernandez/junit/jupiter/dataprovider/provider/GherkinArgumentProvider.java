package org.edhernandez.junit.jupiter.dataprovider.provider;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ast.Feature;
import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import org.edhernandez.junit.jupiter.dataprovider.annotation.GherkinSource;
import org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.Scenario;
import org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.TestFeature;
import org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.step.Step;
import org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.step.StepType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GherkinArgumentProvider implements ArgumentsProvider, AnnotationConsumer<GherkinSource> {

    Parser<GherkinDocument> parser;

    String[] values;
    String given;
    String when;
    String then;

    public GherkinArgumentProvider() {
        parser = new Parser<>(new AstBuilder());
    }

    @Override public void accept(GherkinSource gherkinSource) {
        values = gherkinSource.values();
        given = gherkinSource.given();
        when = gherkinSource.when();
        then = gherkinSource.then();
    }

    @Override public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Arrays.stream(values)
                .map(fileName -> {
                    try {
                        GherkinDocument gherkinDocument = parser.parse(new FileReader(getClass().getResource("/" + fileName).getFile()));
                        return gherkinDocument;
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);//changeeeee
                    }
                })
                .map(gherkinDocument -> {
                    TestFeature gherkinTestArgument = new TestFeature();
                    gherkinTestArgument.setFeature(gherkinDocument.getFeature().getName());
                    gherkinTestArgument.setDescription(gherkinDocument.getFeature().getDescription().replaceAll("(\\s){2,}", " ").replaceAll("^\\s", ""));
                    Feature feature = gherkinDocument.getFeature();
                    List<ScenarioDefinition> scenarioDefinitions = feature.getChildren();
                    List<TestFeature> features = scenarioDefinitions.stream().map(scenarioDefinition -> {
                        TestFeature newTestFeature = copy(gherkinTestArgument);
                        newTestFeature.setScenario(buildScenario(scenarioDefinition));
                        return newTestFeature;
                    }).collect(Collectors.toList());
                    return features;
                })
                .flatMap(List::stream)
                .map(Arguments::of);
    }

    private TestFeature copy(TestFeature testFeature) {
        TestFeature newTestFeature = new TestFeature();
        newTestFeature.setDescription(testFeature.getDescription());
        newTestFeature.setFeature(testFeature.getFeature());
        return newTestFeature;
    }

    private Scenario buildScenario(ScenarioDefinition scenarioDefinition) {
        Scenario scenario = new Scenario();
        scenario.setName(scenarioDefinition.getName());
        scenarioDefinition.getSteps().stream().forEach(s -> {
            Step step = new Step();
            step.setDescription(s.getText());
            step.setStepType(StepType.valueOf(s.getKeyword().trim().toUpperCase()));
            if (scenario.getWhen().isEmpty() && (step.getStepType().equals(StepType.AND) || step.getStepType().equals(StepType.GIVEN))) {
                scenario.getGiven().add(step);
            } else if (scenario.getThen().isEmpty() && (step.getStepType().equals(StepType.AND) || step.getStepType().equals(StepType.WHEN))) {
                scenario.getWhen().add(step);
            } else {
                scenario.getThen().add(step);
            }
        });
        return scenario;
    }
}
