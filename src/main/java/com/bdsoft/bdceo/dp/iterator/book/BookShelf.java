package com.bdsoft.bdceo.dp.iterator.book;

import lombok.Data;

/**
 * 容器对象：书架，支持迭代
 */
@Data
public class BookShelf implements Aggregate {

    private Book[] books;
    private int last = 0;

    public BookShelf() {
        this(100);
    }

    public BookShelf(int max) {
        books = new Book[max];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public void addBook(Book book) {
        this.books[last] = book;
        last++;
    }

    public int getSize() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
