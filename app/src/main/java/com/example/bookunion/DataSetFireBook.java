package com.example.bookunion;

public class DataSetFireBook {
         String book_name;
         String book_author;
         String book_genre;
         String book_language;
         String book_notes;
         String book_type;
         String book_owner;

    public  String getBook_name() {
        return book_name;
    }

    public String getBook_owner() {
        return book_owner;
    }

    public void setBook_owner(String book_owner) {
        this.book_owner = book_owner;
    }

    public  String getBook_author() {
        return book_author;
    }

    public  String getBook_genre() {
        return book_genre;
    }

    public  String getBook_language() {
        return book_language;
    }

    public  String getBook_notes() {
        return book_notes;
    }

    public  String getBook_type() {
        return book_type;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public void setBook_genre(String book_genre) {
        this.book_genre = book_genre;
    }

    public void setBook_language(String book_language) {
        this.book_language = book_language;
    }

    public void setBook_notes(String book_notes) {
        this.book_notes = book_notes;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public DataSetFireBook(String book_name, String book_author, String book_genre, String book_language, String book_notes, String book_type, String book_owner) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_genre = book_genre;
        this.book_language = book_language;
        this.book_notes = book_notes;
        this.book_type = book_type;
        this.book_owner = book_owner;
    }

    public DataSetFireBook() {
    }
}

