# Junit Json Provider
Run your parameterized test with json file source

## Dependencies
![Alt](https://img.shields.io/badge/Jdk-+1.8.0-orange.svg?style=flat)
![Alt](https://img.shields.io/badge/Junit-5-green.svg?style=flat)
![Alt](https://img.shields.io/badge/Jackson-+2.8-blue.svg?style=flat)

~~~~
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.3.0</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>5.3.0</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.3.0</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.8.4</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.8.4</version>
    <scope>provided</scope>
</dependency>
~~~~

## Install
### Add github repository to your project
~~~~
 <repository>
     <id>github.io</id>
     <url>https://raw.github.com/hernandezed/junit-json-provider/repository/</url>
 </repository>
~~~~
### Add the dependency
~~~~
<dependency>
    <groupId>com.junit.jupiter.json</groupId>
    <artifactId>json-provider</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
~~~~

### How to use

#### YourTest.java
~~~~
@ParameterizedTest(name = "{0}")
@JsonSource(values = { "caseOne.json", "caseTwo.json" })
public void ptest(JsonArgument jsonArgument) {
    // make some stuff
}
~~~~

#### caseX.json (in your classpath)
~~~~
{
    "name": "test name"
    "scenario": {
        "key1": "value1",
        "key2": "value2"
    }
    "expectation": {
        "expectation1": "value1"
        "expectation2": 23
    }
}
~~~~
