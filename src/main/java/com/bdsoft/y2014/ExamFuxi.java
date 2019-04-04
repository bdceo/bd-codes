package com.bdsoft.y2014;

public class ExamFuxi {

    public static void main(String[] args) {
        // 继承中，构造函数继承问题
//        ex022001();

        // try-catch中return后finally是否继续执行
//		 ex022601();

        // 线程sleep和wait的区别
        ex022801();
    }

    private boolean isRun = true;

    private static void ex022801() {
        // 参考:http://blog.csdn.net/myyate/article/details/2185113
        final ExamFuxi obj = new ExamFuxi();
        Thread thd = new Thread() {
            public void run() {
                while (obj.isRun) {
                    try {
                        System.out.println("thd exe");
                        synchronized (obj) {
                            System.out.println("--	wait begin");
                            obj.wait();
                            System.out.println("--	wait finish");
                        }
                        System.out.println("==	wait out");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thd exe over!");
            }
        };
        try {
            thd.start();
            Thread.sleep(100L);
            obj.isRun = false;
            synchronized (obj) {
                System.out.println("++	notify begin");
                obj.notify();
                System.out.println("++	notify finish");
            }
            System.out.println("==	notify out");
//            Thread.sleep(100L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main exe over!");

    }

    static void ex022001() {
        // http://www.webjx.com/exam/java-2714.html
        // http://www.blogjava.net/kingace/archive/2008/07/02/212081.html

        // 实例化子类时，将默认调用无参数的父类构造函数
        // 如果父类无无参构造函数，将会编译出错
        // 此时，需要在子类构造函数中调用其他有参的父类构造函数

        Super suber = new Suber();
        System.out.println("---");

        Super s2 = new Suber(4); // 如果注释super的调用，则默认会调用父类的无参构造函数

        // 所以，总结，继承过程中，构造函数不存在继承关系，只是默认隐式调用问题
    }

    static void ex022601() {
        try {
            int i = 1 / 0;
            System.out.println("try");
        } catch (Exception e) {
            System.out.println("catch");
            return;
        } finally {
            System.out.println("finally");
        }
        System.out.println("no code");
    }
}

// 模拟父类
class Super {
    int id;

    public Super() {
        System.out.println("父类.Super()");
    }

    public Super(int id) {
        System.out.println("父类.Super(int id)");
        this.id = id;
    }
}

// 模拟子类
class Suber extends Super {

    int id;

    public Suber() {
        super(12); // 指定会调用父类的有参构造函数
        System.out.println("子类.Suber()");
    }

    public Suber(int id) {
//		super(33); // 注释，则会调用父类的无参构造函数
        System.out.println("子类.Suber(int id)");
        this.id = id;
        Super ss = new Super(id);
    }
}