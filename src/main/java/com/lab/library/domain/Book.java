package com.lab.library.domain;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @ManyToOne
    private User rentedBy;

    public Book() {
    }
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public User getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(User rentedBy) {
        this.rentedBy = rentedBy;
    }
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

}


