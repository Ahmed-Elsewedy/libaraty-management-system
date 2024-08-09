package com.library_management_system.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Author is required")
    private String author;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters long")
    private String isbn;

    @Column(nullable = false)
    @NotNull(message = "Publication year is required")
    private int publicationYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
