# energy-consumption
Solution that allows to receive and collect data about energy consumption from different villages.

## How to run
The application can be executed in two ways:
- running with Docker (recommended)
- running with Maven

### running with Docker
- requirement: Docker
```sh
~/diff-base64: docker-compose up
```

### running with Maven
- requirement: JDK 11
```sh
~/diff-base64: mvn spring-boot:run
```

## Tech aspects

### Stack
- **Java 11**: latest Java long term support version
- **Spring Boot**: for a quick implementation
- **H2**: in memory database, for data storage
- **Lombok**: for helping with the boilerplate code on POJO objects
- **Maven**: building, packaging, dependency management
- **Docker**: for containerizing the solution

### Architecture
This solution was structured following DDD principles in order to isolate the business rules, the domain and the infrastructure layers.

The pattern used was [Ports & Adapters], also knows as Hexagonal Architecture.
>The main idea of Ports & Adapters is to define the structure of an application so that it could be run by different kinds of clients[...]

I decided to break the project in 4 modules:
- `app`: module to run the application, has Spring Boot as dependency in order to manage the beans and start the application.
- `domain`: module where are the objects that address our business domain. The idea is to do not have any dependency of frameworks and libraries, so our business become technology agnostic.
- `infrastructure`: module for external dependencies, in this case the database.
- `web`: module to expose the rest api. 

#### API
The **energy-consumption** api consists of four endpoints:

- [POST] - `<host>/counter_callback`
```json
{
    "counter_id": "1",
    "amount": 10000.123
}
```
Creates a record of consumption for the specified counter/village.
  
- [POST] - `<host>/counter`
```json
{
  "id": "1",
  "name": "Villarriba"
}
```
Creates a counter/village record.
  
- [GET] - `<host>/counter?id=<id>`

To get additional information about a counter/village.

- [GET] - `<host>/consumption_report?duration=24h`

Gives out the consumption report per village for the last 24h.

### Testing
- unit tests: JUnit + Mockito
- integration tests: Spring Boot Test

### Assumptions
- created a new endpoint to register counters
- sending any value different of `24h` to the `consumption_report` endpoint will return the report for the last week
- all values send on `amount` will have precision of 3 (n.xxx)

### Improvements
There are some points that would be nice to improve:
- add i18n for exceptions
- test obvious scenarios for endpoints (method now allowed, 404 on wrong endpoints, ...)
- improve debug logs
- upload docker image 

[Ports & Adapters]:https://softwarecampament.wordpress.com/portsadapters/