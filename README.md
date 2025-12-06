# GlycoTrack API (Hexagonal Architecture - English)

## Overview
GlycoTrack is a sample Kotlin + Spring Boot API designed using Hexagonal Architecture (Ports & Adapters).
The project provides domain models for glucose management and a basic REST API to register and retrieve glucose measurements.

## How to run
- Configure PostgreSQL and update `src/main/resources/application.yml` credentials.
- Build: `./gradlew bootJar`
- Run: `java -jar build/libs/glycotrack-0.0.1.jar`

## Project structure
See the `src/main/kotlin/com/glycotrack` package for domain, application and infrastructure layers.
