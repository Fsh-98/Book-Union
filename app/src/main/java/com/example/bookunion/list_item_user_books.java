package com.example.bookunion;

public class list_item_user_books {
    String book_name;
    String author_name;

    public list_item_user_books(String book_name, String author_name) {
        this.book_name = book_name;
        this.author_name = author_name;
    }

    public String getBook_name() {

         return book_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
