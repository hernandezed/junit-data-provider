package org.edhernandez.junit.jupiter.dataprovider.argument.gherkin.annotation;

import org.edhernandez.junit.jupiter.dataprovider.provider.JsonArgumentProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface And {

    String value(); //regex

}
