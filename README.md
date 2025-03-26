# Overview
A RESTful API service built using Spring Boot to manage Digital Library Book's  while using PostgreSQL to persist the data.

# Features
* Book management:
  + Store and manage book details such as title, author, genre, and availability status.
  + Display a list of all books with their details.
  + Allow searching for a book using its ID or title.
  + Modify book details(e.g., change availability status, update title or author).
  + Remove a book.

# Getting Started:
## Prerequisites:
* Java 17 or higher
* PostgreSQL
* Postman for API Testing 

## Configuration:
1. Open the application.properties file located in src/main/resources directory.
2. Configure the PostgreSQL database connection settings:
   
   spring.datasource.url = jdbc:postgresql://book-management-stayease.h.aivencloud.com:12236/defaultdb
   
   spring.datasource.username = avnadmin
   
   spring.datasource.password = AVNS_GlTB8PtPwboG0CBxfZ6

   Replace url, username and password with your PostgreSQL host, port, username and password respectively.

   Save the changes to the application.properties file.

## Running the application:
Run the application using Gradle:
./gradlew bootrun



   
