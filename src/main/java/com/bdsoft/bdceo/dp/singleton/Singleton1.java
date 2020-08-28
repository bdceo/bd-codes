package com.bdsoft.bdceo.dp.singleton;

public class Singleton1 {

    /**
     * 单例模式
     */
    public static void main(String[] args) {
        FileOpe fo1 = FileOpe.getInstance();
        fo1.createFile();
        // 1000行代码
        // FileOpe fo2 = new FileOpe();
        // FileOpe fo2 = fo1;
        FileOpe fo2 = FileOpe.getInstance();
        fo2.createFile();
    }
}

class FileOpe {

    // 静态私有变量
    private static FileOpe fo = null;

    // 构造函数私有化
    private FileOpe() {
        System.out.println("构造函数");
    }

    // 线程同步安全
    public static synchronized FileOpe getInstance() {
        if (fo == null) {
            fo = new FileOpe();
        }
        return fo;
    }

    public void createFile() {
        System.out.println("创建文件");
    }

}
