package com.bdsoft.y2014.m6;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;

public class TianliCheckCode {

    /**
     * 测试调用Linux命令检测 TODO(方法作用)
     * 
     * @param (参数列表)
     * @return (返回值)
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("缺少参数[code]，退出");
            System.exit(1);
        }
        checkCode(args[0]);
    }

    public static void checkCode(String code) {
        String baseCmd = "grep ## /home/bdceo/all_code.txt";
        String cmd = baseCmd.replaceAll("##", code);
        try {
            long start = new Date().getTime();
            System.out.println("执行查找：" + cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            boolean has = false;
            if (process != null) {
                LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    if (line.equals(code)) {
                        has = true;
                        break;
                    }
                }
            }
            long cost = new Date().getTime() - start;
            System.out.println(has ? "激活码有效" : "激活码无效");
            System.out.println("耗时：" + cost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
