package com.library_management_system.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.library_management_system.library.entity.Book;
import com.library_management_system.library.entity.BorrowingRecord;
import com.library_management_system.library.entity.Patron;
import com.library_management_system.library.exception.BookAlreadyBorrowedException;
import com.library_management_system.library.repository.BookRepository;
import com.library_management_system.library.repository.BorrowingRecordRepository;
import com.library_management_system.library.repository.PatronRepository;

@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Async
    public CompletableFuture<Iterable<BorrowingRecord>> getAllBorrowingRecords() {
        return CompletableFuture.completedFuture(borrowingRecordRepository.findAll());
    }

    @Async
    public CompletableFuture<List<BorrowingRecord>> unretUrnedBooks() {
        return CompletableFuture.completedFuture(borrowingRecordRepository.findByReturnDateIsNull());
    }

    @Async
    public CompletableFuture<BorrowingRecord> borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Patron patron = patronRepository.findById(patronId).orElse(null);

        if (book == null || patron == null) {
            return CompletableFuture.completedFuture(null);
        }

        boolean alreadyBorrowed = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId).isPresent();
        if (alreadyBorrowed) {
            throw new BookAlreadyBorrowedException("This book has already been borrowed by the patron.");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
        return CompletableFuture.completedFuture(borrowingRecordRepository.save(borrowingRecord));
    }

    @Async
    public CompletableFuture<BorrowingRecord> returnBook(Long bookId, Long patronId) {
        return CompletableFuture.supplyAsync(() -> {
            return borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                    .map(record -> {
                        record.setReturnDate(LocalDate.now());
                        return borrowingRecordRepository.save(record);
                    })
                    .orElseThrow(() -> new RuntimeException("Borrowing record not found"));
        });
    }

    @Async
    public CompletableFuture<List<BorrowingRecord>> getBorrowingHistoryByPatronId(Long patronId) {
        Patron patron = patronRepository.findById(patronId).orElse(null);

        if (patron == null)
            throw new ResourceNotFoundException("Patron not found");

        return CompletableFuture.completedFuture(borrowingRecordRepository.findByPatronId(patronId));
    }
}
