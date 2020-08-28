package com.bdsoft.bdceo.dp.interpreter.calc;

import lombok.Data;

/**
 * 解析器：加法
 */
@Data
public class Add implements Interpreter {

    private Interpreter left;
    private Interpreter right;

    public Add(Interpreter left, Interpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double calc(String expression) {
        return left.calc(expression) + right.calc(expression);
    }
}
