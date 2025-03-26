package com.example.Book.Management.System.Service;

import com.example.Book.Management.System.Entity.AvailabilityStatus;
import com.example.Book.Management.System.Entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book getBookByBookId(String bookId);
    List<Book> searchBooksByTitle(String title);
    Book updateBook(Long id, Book bookDetails);
    Book updateBookStatus(String bookId, AvailabilityStatus status);
    void deleteBook(Long id);
}
