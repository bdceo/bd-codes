package com.bdsoft.bdceo.dp.composite.files;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 可扩充节点
 */
public class Directory extends Entry {

    private String name;
    /**
     * 可添加子集
     */
    private List<Entry> entries;

    public Directory(String name) {
        this.name = name;
        this.entries = new ArrayList<>();
    }

    @Override
    public Entry add(Entry entry) {
        entries.add(entry);
        return this;
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    int getSize() {
        int size = 0;
        Iterator<Entry> ite = entries.iterator();
        while (ite.hasNext()) {
            size += ite.next().getSize();
        }
        return size;
    }

    @Override
    void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        Iterator<Entry> ite = entries.iterator();
        while (ite.hasNext()) {
            ite.next().printList(prefix + "/" + this.name);
        }
    }

}
