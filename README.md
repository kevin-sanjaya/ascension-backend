# Ascension

## About
Spring Boot back-end for Ascension.

## Stack
Spring Boot, Spring Security, JWT, Swagger, H2 (For disclosure purpose while Cassandra is used for Production), JPA, Maven.

## Accessing the API
H2 Database : http://localhost:9119/h2-console


Swagger UI  : http://localhost:9119/swagger-ui.html


Swagger Spec: http://localhost:9119/api-docs

## Build
Build using Maven: mvn clean install

## Spring Security
```
curl -X POST --header 'Content-Type: application/json' -d '{ "username":"demo", "password":"demo" }' 'http://localhost:9119/session'
```
Command above will return a bearer token that you need to attach as a request header authorization for every http request.
```
curl -X GET --header 'Accept: application/json' --header 'Authorization: {{BEARER_TOKEN}}' 'http://localhost:9119/version'
``` 
