package com.bdsoft.y2011;

public class TestStringBuffer {

    /**
     * 测试StringBuffer
     */
    public static void main(String[] args) {
        StringBuffer buf = new StringBuffer("Java");
        buf.append(" Guide Verl/");
        buf.append(3);
        // 按位置插入
        int index = 5;
        buf.insert(index, "Sutdent ");
        // 插入字符
        index = 23;
        buf.setCharAt(index, '.');
        // 范围替换
        int start = 24, end = 25;
        buf.replace(start, end, "4");

        System.out.println(buf.toString());
    }

}
