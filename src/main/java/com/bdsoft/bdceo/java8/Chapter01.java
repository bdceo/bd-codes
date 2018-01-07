package com.bdsoft.bdceo.java8;

import java.io.File;
import java.io.FileFilter;

/**
 * 第一章测试
 */
public class Chapter01 {

    public static void main(String[] args) {

        // 文件过滤
        new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        // java8风格：FileFilter接口只有一个方法
        new File(".").listFiles(File::isHidden);



    }
}
