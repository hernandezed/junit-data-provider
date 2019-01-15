# Junit Data Provider
Run your parameterized test with json file source

## Dependencies
![Alt](https://img.shields.io/badge/Jdk-+1.8.0-orange.svg?style=flat)
![Alt](https://img.shields.io/badge/Junit-5-green.svg?style=flat)
![Alt](https://img.shields.io/badge/Jackson-+2.8-blue.svg?style=flat)

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.3.0</version>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>5.3.0</version>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.3.0</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.8.11</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.8.11</version>
</dependency>
```

Optionally, you can use yaml syntax using (version 1.0-RELEASE)
```xml
<dependency>
   <groupId>com.fasterxml.jackson.dataformat</groupId>
   <artifactId>jackson-dataformat-yaml</artifactId>
   <version>2.8.10</version>
</dependency>
```
## Install
### Add github repository to your project
```xml
 <repository>
     <id>github.io</id>
     <url>https://raw.github.com/hernandezed/junit-data-provider/repository/</url>
 </repository>
```
### Add the dependency
```xml
<dependency>
    <groupId>org.edhernandez.junit.jupiter.dataprovider</groupId>
    <artifactId>junit-data-provider</artifactId>
    <version>1.0-RELEASE</version>
    <scope>test</scope>
</dependency>
```

### How to use
#### JSON
##### YourTest.java
```java
@ParameterizedTest(name = "{0}")
@JsonSource(values = { "caseOne.json", "caseTwo.json" })
public void ptest(TestArgument testArgument) {
    // make some stuff
}
```

##### caseX.json (in your classpath)
```javascript
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
```
#### Yaml
##### YourTest.java
```java
@ParameterizedTest(name = "{0}")
@YamlSource(values = { "caseOne.yml", "caseTwo.yml" })
public void ptest(TestArgument testArgument) {
    // make some stuff
}
```

##### caseX.yml (in your classpath)
```yaml
- name: "Test name"
  scenario:
    key1: value1
    key2: value2
  expectation:
    expectation1: value1
    expectation2: 23
        
```
