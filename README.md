# IntelliJ Platform Plugin Utilities

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/org.codebrewer.intellij.platform/intellij-platform-plugin-utilities.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22org.codebrewer.intellij.platform%22%20AND%20a:%22intellij-platform-plugin-utilities%22)

## About

**IntelliJ Platform Plugin Utilities** is a Java library that might be useful when creating a plugin for a product
[built on the IntelliJ Platform](https://www.jetbrains.org/intellij/sdk/docs/welcome.html), such as IntelliJ IDEA,
AppCode or CLion.

In truth the usefulness is likely to be limited as the library contains only a single class, created years ago when the
author was actively creating plugins for IntelliJ IDEA. It's now just a 'my first publication to Maven Central'
vehicle...

## Requirements

* JDK 1.8

## Usage

Click the _Maven Central_ badge at the top of this README to search the Central Repository for this project's artifact,
select a version, copy the relevant snippet (Apache Maven, Gradle Groovy DSL _etc._) and paste it into the relevant
configuration file in your own project.

## Building and Testing

Dependency management and building are handled by Maven.

```bash
mvn clean package
```

will build a jar in the `target` directory.

## Licensing

This software is licensed under the Apache License Version 2.0. Please see [LICENSE](LICENSE) for details.
