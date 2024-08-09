package com.library_management_system.library.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.library_management_system.library.dto.BookRequestDto;
import com.library_management_system.library.entity.Book;
import com.library_management_system.library.exception.ResourceNotFoundException;
import com.library_management_system.library.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Async
    public CompletableFuture<List<Book>> getAllBooks() {
        return CompletableFuture.completedFuture(bookRepository.findAll());
    }

    @Async
    public CompletableFuture<Book> getBookById(Long id) {
        return CompletableFuture.completedFuture(
                bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found")));
    }

    @Async
    public CompletableFuture<Book> createBook(BookRequestDto bookRequest) {

        if (bookRepository.findByIsbn(bookRequest.getIsbn()).isPresent()) {
            throw new ResourceNotFoundException("Book with this ISBN already exists");
        }

        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setPublicationYear(bookRequest.getPublicationYear());
        return CompletableFuture.completedFuture(bookRepository.save(book));
    }

    @Async
    public CompletableFuture<Book> updateBook(Long id, BookRequestDto bookDetails) {
        return CompletableFuture.completedFuture(bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setIsbn(bookDetails.getIsbn());
            return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Book not found")));
    }

    @Async
    public CompletableFuture<Boolean> deleteBook(Long id) {
        return CompletableFuture.completedFuture(
                bookRepository.findById(id).map(book -> {
                    bookRepository.delete(book);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Book not found")));
    }
}
