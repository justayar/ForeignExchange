
# Foreign Exchange Application
[![Build Status](https://travis-ci.org/justayar/ForeignExchange.svg?branch=master)](https://travis-ci.org/github/justayar/ForeignExchange)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Foreign Exchange Application gets the exchange rates of currencies according to base and quote symbols. You can calculate the converted amount in source and target
currency. It also stores the conversions and lists them in a paginated fashion.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 4](https://maven.apache.org)
- [Lombok](https://projectlombok.org/)
    * For IntelliJ [Lombok IntelliJ Plugin] (https://plugins.jetbrains.com/plugin/6317-lombok)
    * For Eclipse [Lombok Eclipse] (https://projectlombok.org/downloads/lombok.jar)

## Running the application locally

There are several ways to run a Spring application on your local machine. One way is to execute the `main` method in the `com.canemreayar.foreignexchange.ForeignExchangeApplication` class from your IDE.

Alternatively you can use the maven spring-boot run command like:

For calling below commands,firstly you have to go the project folder directory.

```shell
mvn clean install
mvn spring-boot:run
```

After execution completed, you can open your favourite browser and reach the Swagger documentation on http://localhost:8090/swagger-ui.html#/


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
