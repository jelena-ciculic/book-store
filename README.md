# book-store
Spring Boot application example.

Server side:Spring Boot, Hibernate, Spring Data JPA.

Client side: AngularJS, Bootstrap.
### Getting Started
Import project into workspace. To start application execute following sql query:
```
DROP DATABASE IF EXISTS book_store_db;
CREATE DATABASE book_store_db DEFAULT CHARACTER SET utf8;

USE book_store_db;
```
In file src/main/resources/application.properties  set username and password for DBMS and run application.
