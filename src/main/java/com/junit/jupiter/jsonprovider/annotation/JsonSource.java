package com.junit.jupiter.jsonprovider.annotation;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.junit.jupiter.jsonprovider.argument.JsonArgument;
import com.junit.jupiter.jsonprovider.provider.JsonArgumentProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JsonArgumentProvider.class)
public @interface JsonSource {

    String[] values();

    Class<?> type() default JsonArgument.class;

    Class<?> propertyNamingStrategy() default PropertyNamingStrategy.class;
}
