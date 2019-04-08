package com.bdsoft.y2011;

import java.io.IOException;

public class TestExtends {

    /**
     * 测试类继承：<br>
     * 1，子类和父类的初始化过程<br>
     * 2，子类和父类属性的继承关系：属性绑定到类型，方法绑定到对象(实例)<br>
     * 3，测试方法内修改对象属性，或通过对象方法修改<br>
     */
    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
        System.out.println(ab.getA());
        System.out.println(ab.getB());
        System.out.println("-----------------------");

        Person s = new Student(4);
        System.out.println("s.id=" + s.id); // 0，此处将读取类型的属性，即Person的id，而非Student
        System.out.println("s.getId=" + s.getId()); // 4，通过方法读取属性，则会调用具体对象Student的方法返回其id
        System.out.println("-----------------------");

        test(s); // 方法内重新new，不会影响外部对象引用
        System.out.println("test.s.id=" + s.id); // 0，父类的id属性值
        System.out.println("test.s.getId=" + s.getId()); // 4，子类的方法
        System.out.println("-----------------------");

        test2(s); // 方法内通过调用对象方法修改，则会影响外部
        System.out.println("test2.s.id=" + s.id); // 0，父类的id属性值
        System.out.println("test2.s.getId=" + s.getId()); // 28，子类的方法，在test2中已修改其属性值
        System.out.println("-----------------------");

        Student st = new Student(22);
        Person ps = st; // Person ps = new Student(22);
        System.out.println("st.id=" + st.id); // 22，子类自己的id属性，即现在st类型是Student
        System.out.println("ps.id=" + ps.id); // 0，父类的id属性
        System.out.println("ps.getId()=" + ps.getId()); // 22，实际调用的是子类方法，因为ps指向的是new
        // Student()对象引用
    }

    static void test(Person p) {
        p = new Student(3);
        System.out.println("test.id=" + p.id); // 0
        System.out.println("test.getId=" + p.getId()); // 3
    }

    static void test2(Person p) {
        p.setId(28);
        // 子类未重写setId方法，输出：28, 4
        // 子类重写setId方法，则输出：0 ,28
        System.out.println("test2.id=" + p.id); // 0
        System.out.println("test2.getId=" + p.getId()); // 28
    }

}

// 父类
abstract class A {

    String name = "aaaa";

    // 子类第一次实例化时会最先调用父类的static代码块，多次实例化此代码块不会重复执行
    static {
        System.out.println("static-A");
    }

    // 子类通过无参构造函数实例化时会默认调用父类的无参构造函数
    public A() {
        System.out.println("construct-A");
    }

    String getA() {
        return name;
    }

    abstract String getB();
}

// 子类
class B extends A {

    String name = "bbbb";

    // 子类实例化时，父类静态代码块调用完毕会调用子类的静态代码块
    static {
        System.out.println("static-B");
    }

    public B() {
        System.out.println("construct-B");
        System.out.println("--------------------");
    }

    String getB() {
        return name;
    }
}

class Person {
    int id;

    public Person() {
        System.out.println("Person-1");
    }

    public Person(int id) {
        this.id = id;
        System.out.println("Person-2");
        System.out.println("Person.id=" + this.id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Student extends Person {
    int id;

    public Student() {
        System.out.println("Student-1");
    }

    public Student(int id) {
        this.id = id;
        System.out.println("Student-2");
        System.out.println("Student.id=" + this.id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

interface IntTemp {
    public void read() throws IOException;

    public void write() throws Exception;
}

// 实现类抛出异常需要时父类的子类或不抛出
class ImpTemp implements IntTemp {
    // public void read() throws Exception {
    public void read() throws IOException {

    }

    public void write() {
    }
}
