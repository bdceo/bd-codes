package com.bdsoft.bdceo.dp.iterator.book;

/**
 * 测试入口
 */
public class Main {

    public static void main(String[] args) {
        BookShelf shelf = new BookShelf(4);
        shelf.addBook(new Book("有钱人和你想的不一样"));
        shelf.addBook(new Book("穷爸爸富爸爸"));
        shelf.addBook(new Book("高财商"));
        shelf.addBook(new Book("财富自由"));
        Iterator ite = shelf.iterator();
        while (ite.hasNext()) {
            Book book = (Book) ite.next();
            System.out.println(book.getName());
        }
    }

}
