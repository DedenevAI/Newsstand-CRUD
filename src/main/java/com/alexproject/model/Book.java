package com.alexproject.model;

public class Book extends Publication {

    private String author;

    public Book(String name, String author) {
        super(name, PublicationType.BOOK);
        this.author = author;
    }

    public Book(Long id, String name, String author) {
        super(id, name, PublicationType.BOOK);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
