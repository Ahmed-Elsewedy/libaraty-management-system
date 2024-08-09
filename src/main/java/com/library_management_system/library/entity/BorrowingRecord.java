package com.library_management_system.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull(message = "Book cannot be null")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patron_id", nullable = false)
    @NotNull(message = "Patron cannot be null")
    private Patron patron;

    @Column(nullable = false)
    @NotNull(message = "Borrow date is mandatory")
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
