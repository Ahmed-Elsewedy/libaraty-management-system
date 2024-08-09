package com.library_management_system.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library_management_system.library.dto.BookRequestDto;
import com.library_management_system.library.entity.Book;
import com.library_management_system.library.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks().join();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id).join();
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        Book savedBook = bookService.createBook(bookRequestDto).join();
        return ResponseEntity.status(201).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails).join();
        return updatedBook != null ? ResponseEntity.ok(updatedBook) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean isDeleted = bookService.deleteBook(id).join();
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
