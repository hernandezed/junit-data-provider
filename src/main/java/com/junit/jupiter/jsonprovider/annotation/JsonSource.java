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
package com.junit.jupiter.jsonprovider.annotation;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.junit.jupiter.jsonprovider.argument.JsonArgument;
import com.junit.jupiter.jsonprovider.provider.JsonArgumentProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Indicate to Junit what files are needed and type of class to map, and the name stretegy for jackson object mapper
 *
 *  @author Eduardo Hernandez
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JsonArgumentProvider.class)
public @interface JsonSource {

    String[] values();

    Class<?> type() default JsonArgument.class;

    Class<?> propertyNamingStrategy() default PropertyNamingStrategy.class;
}
