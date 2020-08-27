package com.bdsoft.bdceo.dp.composite.files;

/**
 * 文件类，模拟叶子结点
 */
public class File extends Entry {

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    int getSize() {
        return this.size;
    }

    @Override
    void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
