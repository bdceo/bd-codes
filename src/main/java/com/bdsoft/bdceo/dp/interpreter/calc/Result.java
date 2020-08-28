package com.bdsoft.bdceo.dp.interpreter.calc;

import lombok.Data;

/**
 * 终态解析器：计算结果
 */
@Data
public class Result implements Interpreter {

    private double result;

    public Result(double result) {
        this.result = result;
    }

    @Override
    public double calc(String expression) {
        return result;
    }

}
