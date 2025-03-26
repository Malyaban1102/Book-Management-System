package com.example.Book.Management.System.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Book.Management.System.Entity.AvailabilityStatus;
import com.example.Book.Management.System.Entity.Book;
import com.example.Book.Management.System.Exception.BookNotFoundException;
import com.example.Book.Management.System.Repository.BookRepository;
import com.example.Book.Management.System.Service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setUp(){
        book = new Book();
        book.setId(1L);
        book.setBookId("B001");
        book.setTitle("Spring in Action");
        book.setAuthor("Craig Walls");
        book.setGenre("Programming");
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
    }

    @Test
    void addBook_ShouldReturnSavedBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        
        Book savedBook = bookService.addBook(book);
        
        assertNotNull(savedBook);
        assertEquals("B001", savedBook.getBookId());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void addBook_WithDuplicateBookId_ShouldThrowException() {
        when(bookRepository.existsByBookId("B001")).thenReturn(true);
        
        assertThrows(IllegalArgumentException.class, () -> {
            bookService.addBook(book);
        });
    }

    @Test
    void getBookById_ShouldReturnBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        
        Book foundBook = bookService.getBookById(1L);
        
        assertNotNull(foundBook);
        assertEquals("Spring in Action", foundBook.getTitle());
    }

    @Test
    void getBookById_WithInvalidId_ShouldThrowException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(BookNotFoundException.class, () -> {
            bookService.getBookById(99L);
        });
    }

    @Test
    void updateBookStatus_ShouldUpdateStatus() {
        Book updatedBook = new Book();
        updatedBook.setId(1L);
        updatedBook.setBookId("B001");
        updatedBook.setAvailabilityStatus(AvailabilityStatus.CHECKED_OUT);
        
        when(bookRepository.findByBookId("B001")).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);
        
        Book result = bookService.updateBookStatus("B001", AvailabilityStatus.CHECKED_OUT);
        
        assertEquals(AvailabilityStatus.CHECKED_OUT, result.getAvailabilityStatus());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void deleteBook_ShouldInvokeRepositoryDelete() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);
        
        bookService.deleteBook(1L);
        
        verify(bookRepository, times(1)).delete(book);
    }
    
}
