package com.bdsoft.bdceo.dp.interpreter.calc;

/**
 * 核心解释器
 */
public interface Interpreter {

    /**
     * 实现数字计算
     *
     * @param expression 数学表达式
     * @return
     */
    double calc(String expression);

}
