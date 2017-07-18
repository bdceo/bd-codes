package com.bdsoft.y2017.m07;

import com.bdsoft.bdceo.ibatis.Main;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bdceo on 2017/7/18.
 */
public class TestVorR {

    public static void main(String[] args) {
        List<String> data = Arrays.asList("bdceo","bdcto","bdcio","bdcmo","bdcoo");

        Bom bom  = new Bom(data);
        System.out.print(bom);

        f1(bom.getData());


    }

    static void f1(List<String> data){
        data.add("bdcxo");
    }


}

class Bom {

    private List<String> data;

    @Override
    public String toString() {
        return "Bom{" +
                "data=" + data +
                '}';
    }

    public Bom(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
