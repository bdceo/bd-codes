/**
 * GuavaTest.java
 * com.bdsoft.google.guava
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
 */
package com.google.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 https://code.google.com/p/guava-libraries/wiki/GuavaExplained?tm=6
 https://code.google.com/p/guava-libraries/wiki/GuavaExplained
 http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/index.html
 */
public class GuavaTest {

    public static void main(String[] args) {
        // 数值类型比较
        int comp = Ints.compare(1, 4);
        print(comp);

        // 字符串拼接
        Joiner joiner = Joiner.on("; ").skipNulls();
        String str = joiner.join("bd", "ceo", null, "cto");
        print(str);

        str = Joiner.on("_").join(Arrays.asList(1, 3, 4));
        print(str);

        // 字符串分隔
        Iterable<String> strIte = Splitter.on(",").trimResults().omitEmptyStrings().split("foo,bar,,   qux");
        Iterator<String> strIto = strIte.iterator();
        while (strIto.hasNext()) {
            print(strIto.next());
        }

        LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<Long, AtomicLong>() {
            @Override
            public AtomicLong load(Long key) throws Exception {
                return new AtomicLong(0);
            }
        });

    }

    public static void print(Object o) {
        System.out.println(o.toString());
    }

}
