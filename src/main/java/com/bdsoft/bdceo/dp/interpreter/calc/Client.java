package com.bdsoft.bdceo.dp.interpreter.calc;

/**
 * 客户端模拟调用
 */
public class Client {

    public static void main(String[] args) {
        ParseInterpreter interpreter = new ParseInterpreter();
        double result = interpreter.calc("20*(3+1)-4*5+3");
        System.out.println("计算结果为: " + result);
    }

}
