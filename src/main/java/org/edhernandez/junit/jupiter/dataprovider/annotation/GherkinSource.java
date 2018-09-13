package org.edhernandez.junit.jupiter.dataprovider.annotation;

public @interface GherkinSource {

    String[] values();

    String given();

    String when();

    String then();

}
