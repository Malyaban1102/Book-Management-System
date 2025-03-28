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

## Testing the application:
./gradlew test

1. Add a new Book
POST http://localhost:8080/api/books
Headers:
  Content-Type: application/json
Body (raw - JSON):
{
    "bookId": "B001",
    "title": "Spring in Action",
    "author": "Craig Walls",
    "genre": "Programming",
    "availabilityStatus": "AVAILABLE"
}

2. Get all Books
GET http://localhost:8080/api/books

3. Get book by ID
GET http://localhost:8080/api/books/1

4. Get Book by BookId
GET http://localhost:8080/api/books/bookId/B001

5. Search Book by Title
GET http://localhost:8080/api/books/search?title=spring

6. Update Book details
PUT http://localhost:8080/api/books/1
Headers:
  Content-Type: application/json
Body (raw - JSON):
{
    "bookId": "B001",
    "title": "Spring in Action (6th Edition)",
    "author": "Craig Walls",
    "genre": "Programming, Java",
    "availabilityStatus": "CHECKED_OUT"
}

7. Update Book status only
PATCH http://localhost:8080/api/books/B001/status?status=AVAILABLE

8. Delete Book
DELETE http://localhost:8080/api/books/1

   
