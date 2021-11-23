package com.ifmo.lesson7;


import java.util.Objects;

public class Books {
    int count;
    Book book;

    public Books(Book book, int count){
        this.count = count;
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(book, books.book);
    }

    @Override
    public int hashCode() {
        return book.hashCode();
    }
}
