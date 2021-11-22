package com.ifmo.lesson7;

public class Main {

    public static void main(String[] args){
        Library library = new Library(2);
        library.put(new Book("Stephen King", "Shining"), 2); // return true
        library.put(new Book("Stephen King", "Dark Tower"), 3); // return true
        // Эту книгу добавить не можем, т.к. лимит 2
        library.put(new Book("Tolstoy", "War and peace"), 6); // return false
        // Забираем все книги Тёмной башни, чтобы освободить место.
        library.take(new Book("Stephen King", "Dark Tower"), 3); // return 3
        // Теперь мы можем успешно добавить "Войну и мир".
        library.put(new Book("Tolstoy", "War and peace"), 6); // return true
        library.take(new Book("Tolstoy", "War and peace111"), 6); // return 0

        Library library2 = new Library(2);
        library2.put(new Book("Stephen King", "Shining"), 2); // return true
        // Все равно вернет 2, т.к. больше нет.
        library2.take(new Book("Stephen King", "Shining"), 10); // return 2


    }
}
