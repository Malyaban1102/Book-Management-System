package com.example.Book.Management.System.Service;

import com.example.Book.Management.System.Entity.AvailabilityStatus;
import com.example.Book.Management.System.Entity.Book;
import com.example.Book.Management.System.Exception.BookNotFoundException;
import com.example.Book.Management.System.Repository.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private  BookRepository bookRepository;


    @Override
    public Book addBook(Book book) {
        if (bookRepository.existsByBookId(book.getBookId())) {
            throw new IllegalArgumentException("Book with ID " + book.getBookId() + " already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book getBookByBookId(String bookId) {
        return bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with bookId: " + bookId));
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setAvailabilityStatus(bookDetails.getAvailabilityStatus());

        return bookRepository.save(book);
    }

    @Override
    public Book updateBookStatus(String bookId, AvailabilityStatus status) {
        Book book = getBookByBookId(bookId);
        book.setAvailabilityStatus(status);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
