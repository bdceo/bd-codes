package com.bdsoft.y2019;

import com.bdsoft.bdceo.thinkinjava.io.MemoryInput;

/**
 * Created by bdceo on 2019/8/15.
 */
public class Exam0815 {






}

// 删除单链表中元素
class Solution{

    /**
     * input: 1 > 2 > 6 > 3 > 4 > 5 > 6
     * output: 1 > 2 > 3 > 4 > 5
     *
     * @param head 链表头元素
     * @param val 指定值
     * @return
     */
    public LinkNode removeElements(LinkNode head, int val){
        // 判断头是否相等
        if(head != null){
            if(head.getVal() == val){
                head = head.getNext();
            }else{
                head.remove(val);
            }
        }
        return head;
    }
}

class LinkNode{

    private int val;
    private LinkNode next;

    public void remove(int v){
        if(next != null) {
            if (next.getVal() == v) {
                next = next.getNext();
            } else {
                next.remove(v);
            }
        }
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}
