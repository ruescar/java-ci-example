# Java CI example

This repository contains and example of a complete continuous integration process to build and test a Java RESTful Web Service.
The main technologies used are: Java 8, Spring Boot, Docker, Jenkins Pipelines, jcstress and Taurus. 


## Prerequisites

- Oracle Java 8.0 JDK
- Docker Community Edition
- [jcstress](http://openjdk.java.net/projects/code-tools/jcstress/)
- [Taurus](http://gettaurus.org/)
- [Infrastructure](https://github.com/ruescar/infrastructure/)

## Build and test App

    mvn clean verify
   
## Run Concurrency Stress tests

    ./jcstress-test/jcstress.sh
               
## Start App Docker container

    ./local-startup.sh
    
## Usage

    curl -s -X GET http://localhost:8090/greeting

    curl -s -X GET http://localhost:8090/greeting?name=Tom

## Run Component tests

    mvn -f=component-test/pom.xml verify -PcompTest
    
## Run Performance Benchmark tests

    ./performance-test/taurus.sh