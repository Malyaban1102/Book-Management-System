package com.example.Book.Management.System.ControllerTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Book.Management.System.Controller.BookController;
import com.example.Book.Management.System.Entity.AvailabilityStatus;
import com.example.Book.Management.System.Entity.Book;
import com.example.Book.Management.System.Service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    private MockMvc mockMvc;
    
    @Mock
    private BookService bookService;
    
    @InjectMocks
    private BookController bookController;
    
    private Book book;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        
        book = new Book();
        book.setId(1L);
        book.setBookId("B001");
        book.setTitle("Spring in Action");
        book.setAuthor("Craig Walls");
        book.setGenre("Programming");
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
    }

    @Test
    void addBook_ShouldReturnCreatedBook() throws Exception {
        when(bookService.addBook(any(Book.class))).thenReturn(book);
        
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value("B001"));
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() throws Exception {
        List<Book> books = Arrays.asList(book);
        when(bookService.getAllBooks()).thenReturn(books);
        
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spring in Action"));
    }

    @Test
    void updateBookStatus_ShouldReturnUpdatedBook() throws Exception {
        book.setAvailabilityStatus(AvailabilityStatus.CHECKED_OUT);
        when(bookService.updateBookStatus(eq("B001"), any(AvailabilityStatus.class)))
            .thenReturn(book);
        
        mockMvc.perform(patch("/api/books/B001/status")
                .param("status", "CHECKED_OUT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.availabilityStatus").value("CHECKED_OUT"));
    }

    @Test
    void deleteBook_ShouldReturnNoContent() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    
    
}
