package com.example.bookunion;

public class Artist {

    public String id, name, genre;
    public int age;
    public String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public Artist() {
    }
}
