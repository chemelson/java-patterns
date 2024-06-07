package ru.chemelson.patterns.gof.behavioral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * We will demonstrate fully custom iterator and Iterator from Java library
 */

public class IteratorDemo {

    public static void main(String[] args) {
        var collection = new BookCollection();
        collection.addBook(new Book("book1"));
        collection.addBook(new Book("book2"));
        collection.addBook(new Book("book3"));

        // Using our iterator
        var customIterator = collection.createCustomIterator();
        while (customIterator.hasNext()) {
            var book = customIterator.next();
            System.out.println(book.title);
        }

        System.out.println();

        // Using Java iterator
        var iterator = collection.createIterator();
        while (iterator.hasNext()) {
            var book = iterator.next();
            System.out.println(book.title);
        }
    }

    // Aggregate
    record Book(String title) {
    }

    // Custom iterator
    interface CustomIterator {
        boolean hasNext();

        Book next();
    }

    static class BookCollection {
        private final List<Book> books = new ArrayList<>();

        void addBook(Book book) {
            books.add(book);
        }

        CustomIterator createCustomIterator() {
            return new BookCustomIterator(this);
        }

        Iterator<Book> createIterator() {
            return new BookIterator(this);
        }

        Book get(int index) {
            return books.get(index);
        }

        int size() {
            return books.size();
        }

        private class BookCustomIterator implements CustomIterator {
            private BookCollection collection;
            private int currentIndex = 0;

            BookCustomIterator(BookCollection collection) {
                this.collection = collection;
            }

            @Override
            public boolean hasNext() {
                return currentIndex < collection.size();
            }

            @Override
            public Book next() {
                return hasNext() ? collection.get(currentIndex++) : null;
            }
        }

        private class BookIterator implements Iterator<Book> {
            private BookCollection collection;
            private int currentIndex = 0;

            private BookIterator(BookCollection collection) {
                this.collection = collection;
            }

            @Override
            public boolean hasNext() {
                return currentIndex < collection.size();
            }

            @Override
            public Book next() {
                return hasNext() ? collection.get(currentIndex++) : null;
            }
        }
    }

}
