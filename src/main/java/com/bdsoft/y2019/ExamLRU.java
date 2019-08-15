package com.bdsoft.y2019;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/15.
 */
public class ExamLRU {

    public static void main(String[] args) {


    }

    int max = 100;
    Map<String, Object> keyMap = new HashMap<>();
    LinkedList<String> keyList = new LinkedList<>();

    public void put(String k, Object o) {
        // 如果满，删除尾部
        if (keyMap.size() == max) {
            keyList.removeLast();
        }

        // 已存在：移除原始位置，放置链表头
        if (keyMap.containsKey(k)) {
            keyList.remove(k);
            keyList.addFirst(k);
        }
        // 不存在：直接放到头
        else {
            keyList.addFirst(k);
        }
        // 更新缓存
        keyMap.put(k, o);
    }

    public Object get(String k) {
        if (keyMap.containsKey(k)) {
            keyList.remove(k);
            keyList.addFirst(k);

            return keyMap.get(k);
        }

        return null;
    }

}
