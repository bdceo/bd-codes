package com.bdsoft.y2019;

/**
 * Created by bdceo on 2019/8/15.
 */
public class Exam0815 {

    public static void main(String[] args) {
        // 删除单链表中元素
        Solution so = new Solution();
        LinkNode head = so.init();
        head.show();

        System.out.println();
        head = so.removeElements(head, 6);
        head.show();
    }

}

class Solution {

    public LinkNode init() {
        LinkNode head = new LinkNode(6);
        head.add(6);
        head.add(1);
        head.add(2);
        head.add(6);
        head.add(3);
        head.add(4);
        head.add(6);
        head.add(5);
        head.add(6);
        return head;
    }

    /**
     * input: 6 > 1 > 2 > 6 > 3 > 4 > 5 > 6
     * output: 1 > 2 > 3 > 4 > 5
     *
     * @param head 链表头
     * @param val  移除指定值
     * @return
     */
    public LinkNode removeElements(LinkNode head, int val) {
        if (head == null) {
            throw new IllegalArgumentException("head element null");
        }

        // 递归删除
        head.remove(val);

        // 判断头是否相等
        if (head.getVal() == val) {
            head = head.getNext();
        }
        return head;
    }

}

class LinkNode {

    private int val;
    private LinkNode next;

    public LinkNode(int val) {
        this.val = val;
    }

    public void add(int v) {
        if (next == null) {
            next = new LinkNode(v);
        } else {
            next.add(v);
        }
    }

    public void remove(int v) {
        if (next.getVal() == v) {
            next = next.getNext();
        }
        if (next != null) {
            next.remove(v);
        }
    }

    public void show() {
        System.out.print(this.val + "\t");
        if (next != null) {
            next.show();
        }
    }

    public int getVal() {
        return val;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

}
