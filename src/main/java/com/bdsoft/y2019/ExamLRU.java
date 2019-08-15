package com.bdsoft.y2019;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/15.
 */
public class ExamLRU {

    public static void main(String[] args) {
        ExamLRU lru = new ExamLRU();
        int[] values = {4, 3, 2, 1, 4, 3, 5, 4, 3, 2, 1, 5, 4};
        for (int v : values) {
            lru.get(String.valueOf(v));
            System.out.println();
        }
        System.out.println(lru.miss);
    }

    int miss = 0;
    int max = 4;
    Map<String, Object> keyMap = new HashMap<>();
    LinkedList<String> keyList = new LinkedList<>();

    public Object get(String key) {
        if (keyMap.containsKey(key)) {
            keyList.remove(key);
            keyList.addFirst(key);

            return keyMap.get(key);
        } else {
            miss++;
            System.out.println("Error: get key=" + key + ", miss=" + miss);
            this.put(key, key);
            return null;
        }
    }

    public void put(String key, Object val) {
        // 如果满，删除尾部
        if (keyMap.size() == max) {
            String rmKey = keyList.removeLast();
            keyMap.remove(rmKey);
        }

        // 已存在：移除原始位置，放置链表头
        if (keyMap.containsKey(key)) {
            keyList.remove(key);
            keyList.addFirst(key);
        }
        // 不存在：直接放到头
        else {
            keyList.addFirst(key);
        }

        // 更新缓存
        keyMap.put(key, val);
        System.out.println("put >> " + key + ", " + keyMap.keySet());
    }

}
