package com.bdsoft.y2017.m07;

import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bdceo on 2017/7/24.
 */
public class TestListCp {

    public static void main(String[] args) {
        List<Prd> prds = new ArrayList<>();
        prds.add(new Prd(1, "book"));
        prds.add(new Prd(2, "phone"));

        prt("原始初始化集合：");
        prt(prds);
        prt("");

        List<Prd> tmp = deepCopyList(prds);
        prt("深度拷贝赋值给一个集合：");
        prt(tmp);
        prt("");

        tmp = new ArrayList(Arrays.asList(new String[prds.size()]));
        Collections.copy(tmp, prds);
        prt("copy集合：");
        prt(tmp);
        prt("");
    }

    public static <T> List<T> deepCopyList(List<T> src) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(out);
            oo.writeObject(src);

            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(in);
            return (List<T>) oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    public static void prt(String str) {
        System.out.println(str);
    }

    public static void prt(List<Prd> prs) {
        prs.stream().forEach(p -> {
            System.out.println(p);
        });
    }
}

// 实现序列化接口
class Prd implements Serializable {

    private int sku;
    private String name;

//    @Override
//    public String toString() {
//        return "Prd{" +
//                "sku=" + sku +
//                ", name='" + name + '\'' +
//                '}';
//    }

    public Prd(int s, String n) {
        this.sku = s;
        this.name = n;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
