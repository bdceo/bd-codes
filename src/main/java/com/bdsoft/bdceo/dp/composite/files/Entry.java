package com.bdsoft.bdceo.dp.composite.files;

/**
 * 组件通用行为
 */
public abstract class Entry {

    abstract String getName();

    abstract int getSize();

    abstract void printList(String prefix);

    /**
     * 添加子集
     *
     * @param entry
     * @return
     */
    public Entry add(Entry entry) {
        throw new RuntimeException("不支持的操作");
    }

    @Override
    public String toString() {
        return getName() + "<" + getSize() + ">";
    }
}
