package com.bdsoft.bdceo.dp.interpreter.calc;

import lombok.Data;

/**
 * 解析算术表达式，构造语法树
 */
@Data
public class ParseInterpreter implements Interpreter {

    @Override
    public double calc(String expression) {
        // 1 * (2 + 3)
        // TODO

        return 0;
    }
}
