package com.bdsoft.y2011;

public class TestChar {

    public static void main(String[] eeeStrings) {
        // 八进制
        int i = 0011;
        System.out.println(i); // 9

        String s = "123";
        int j = (int) s.charAt(0);
        System.out.println(j); // 49

        j = Integer.parseInt(Character.toString(s.charAt(0)));
        System.out.println(j); // 1
        System.out.println(s.substring(1)); // 23
    }

}
