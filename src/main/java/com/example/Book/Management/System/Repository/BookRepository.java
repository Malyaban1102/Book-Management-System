package com.example.Book.Management.System.Repository;

import com.example.Book.Management.System.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByBookId(String bookId);
    List<Book> findByTitleContainingIgnoreCase(String title);
    boolean existsByBookId(String bookId);
}
