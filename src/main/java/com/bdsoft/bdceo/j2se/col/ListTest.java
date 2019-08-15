package com.bdsoft.bdceo.j2se.col;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/12.
 */
public class ListTest {

    public static void main(String[] args) {

        LinkedList link = new  LinkedList();
        link.addLast("last");
        link.add("add");
        link.add("last");
        link.addFirst("first");

        link.forEach(System.out::println);
    }

}
