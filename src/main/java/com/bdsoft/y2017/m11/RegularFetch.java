package com.bdsoft.y2017.m11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能 http://tool.oschina.net/uploads/apidocs/jquery/regexp.html
 *
 * @version 1.0
 * @auth 丁辰叶
 * @date 2017/11/3 17:13
 */
public class RegularFetch {

    public static void main(String[] args) {
        String str = "authc,roles[1],role,roleOr[\"1\",\"2\"],port[],protral,rest[]";
        sop(str);

        String reg = "([a-zA-Z]+\\b)";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);

        while (mat.find()) {
            sop("find: " + mat.group());
        }

    }

    public static void sop(String str) {
        System.out.println(str);
    }
}
