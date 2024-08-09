package com.library_management_system.library.controller;

import com.library_management_system.library.entity.BorrowingRecord;
import com.library_management_system.library.service.BorrowingRecordService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping("/api/borrowing-records")
    public ResponseEntity<CompletableFuture<Iterable<BorrowingRecord>>> getAllBorrowingRecords() {
        return ResponseEntity.ok(borrowingRecordService.getAllBorrowingRecords());
    }

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord record = borrowingRecordService.borrowBook(bookId, patronId).join();
        return ResponseEntity.status(201).body(record);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord record = borrowingRecordService.returnBook(bookId, patronId).join();
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/unreturned-books")
    public ResponseEntity<List<BorrowingRecord>> unreturnedBooks() {
        List<BorrowingRecord> unreturnedRecords = borrowingRecordService.unretUrnedBooks().join();
        return ResponseEntity.ok(unreturnedRecords);

    }

    @GetMapping("/api/history/{patronId}")
    public ResponseEntity<List<BorrowingRecord>> getBorrowingHistoryByPatronId(@PathVariable Long patronId) {

        List<BorrowingRecord> records = borrowingRecordService.getBorrowingHistoryByPatronId(patronId).join();
        return ResponseEntity.ok(records);
    }
}
