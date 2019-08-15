package com.bdsoft.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 清除项目中的.svn版本文件
 */
public class RmSvnFiles {


    static List<String> EXT_FILES = Arrays.asList("target", ".git", "logs", ".idea");

    public static void main(String[] args) {
        String path = "d:/hshc_space";

        ls4rmf(new File(path));
    }

    public static void ls4rmf(File dir) {
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                continue;
            }
            System.out.println(">>" + f.getAbsolutePath());
            if (EXT_FILES.contains(f.getName())) {
                rmf(f);
                f.deleteOnExit();
                continue;
            }
            if (f.isDirectory()) {
                ls4rmf(f);
            }
        }
    }

    /**
     * 递归删除文件夹下的文件
     */
    public static void rmf(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isFile()) {
                    System.out.println("--" + (f.isFile() ? " f " : " d ") + f.getName());
                    f.delete();
                    continue;
                } else {
                    rmf(f);
                }
                System.out.println("--" + (f.isFile() ? " f " : " d ") + f.getName());
                f.delete();
            }
        }
    }

}
