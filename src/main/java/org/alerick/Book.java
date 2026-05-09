package org.alerick;

public class Book extends Item {
    private String ISBN;
    private String genre;

    public Book(String title, Status status, String author, String ISBN, String genre) {
        super(title, status, author);
        this.ISBN = ISBN;
        this.genre = genre;
    }
}
