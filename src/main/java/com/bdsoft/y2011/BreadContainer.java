package com.bdsoft.y2011;

public class BreadContainer {

    /**
     * 生产&消费演示
     */
    public static void main(String[] args) {
        // 面包工厂
        BreadContainer bc = new BreadContainer(100);

        Producer p1 = new Producer(20, bc, "P-1");
        Producer p2 = new Producer(70, bc, "P-2");
        Producer p3 = new Producer(90, bc, "P-3");
        p1.start();
        p2.start();
        p3.start();

        Consumer c1 = new Consumer(140, bc, "C-1");
        Consumer c2 = new Consumer(80, bc, "C-2");
        c1.start();
        c2.start();
    }

    // 最大容量
    public static final int maxNum = 200;
    // 当前数量
    private int num;

    public BreadContainer(int num) {
        // 初始化面包数量
        this.num = num;
    }

    /**
     * 制作面包
     */
    public synchronized void produceBread(int produceNum, String producerName) {
        while (num + produceNum > maxNum) {
            System.out.println(producerName + " 要生产 " + produceNum + " 个，当前 "
                    + num + " 个，资源充足，不需要生产，" + producerName + " 去等待吧！");
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        num = num + produceNum;
        System.out.println(producerName + " 生产了 " + produceNum + " 个，现在有 "
                + num + " 个。");
        this.notifyAll();
    }

    /**
     * 消费面包
     */
    public synchronized void consumeBread(int consumeNum, String consumerName) {
        while (consumeNum > num) {
            System.out.println(consumerName + " 要消费 " + consumeNum
                    + " 个，由于现在只有 " + num + " 个，" + consumerName + " 于是去等会儿吧。");
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        num = num - consumeNum;
        System.out.println(consumerName + " 消费了 " + consumeNum + " 个，现在还剩下： "
                + num + " 个。");
        this.notifyAll();
    }
}

/**
 * 面包生产者
 */
class Producer extends Thread {

    private int produceNum;
    private BreadContainer bc;

    public Producer(int produceNum, BreadContainer bc, String producerName) {
        this.produceNum = produceNum;
        this.bc = bc;
        this.setName(producerName);
    }

    public void run() {
        bc.produceBread(produceNum, this.getName());
    }
}

/**
 * 面包消费者
 */
class Consumer extends Thread {

    private int consumeNum;
    private BreadContainer bc;

    public Consumer(int consumeNum, BreadContainer bc, String consumeName) {
        this.consumeNum = consumeNum;
        this.bc = bc;
        this.setName(consumeName);
    }

    public void run() {
        bc.consumeBread(consumeNum, this.getName());
    }
}
