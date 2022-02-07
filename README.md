## JUMIA TEST

[[_TOC_]]

---

:scroll: **START**




### Task description

Exercise
Create a single page application in Java (Frameworks allowed) that uses the provided
database (SQLite 3) to list and categorize country phone numbers.
Phone numbers should be categorized by country, state (valid or not valid), country
code and number.
The page should render a list of all phone numbers available in the DB. It should be
possible to filter by country and state. Pagination is an extra.
Topics to take into account:
- Try to show your OOP skills
- Code standards/clean code
- Do not use external libs to validate the numbers.
- Unit Tests
Regular expressions to validate the numbers:
Cameroon | Country code: +237 | Regex = \(237\)\ ?[2368]\d{7,8}$
Ethiopia | Country code: +251 | Regex = \(251\)\ ?[1-59]\d{8}$
Morocco | Country code: +212 | Regex = \(212\)\ ?[5-9]\d{8}$
Mozambique | Country code: +258 | Regex = \(258\)\ ?[28]\d{7,8}$
Uganda | Country code: +256 | Regex = \(256\)\ ?\d{9}$

## Requirements
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven ](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.mugi.jumia.JumiaApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run

##RUN TESTS FOR THE APLICATION in backend
Use the below command
mvn '-Dtest=**/*IT.java' test

# how to build the application 
Build the project using:
 mvn clean install
 
The backend application runs on port 8585


To build the application and run on docker.
Ensure you have docker-compose installed
Use below command to run the application
docker-compose up -d

Access the web application from 
http://localhost:9095

 

