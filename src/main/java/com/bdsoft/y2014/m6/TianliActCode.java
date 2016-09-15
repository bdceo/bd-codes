package com.bdsoft.y2014.m6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import com.bdsoft.utils.RandomWriteFile;

public class TianliActCode {

    /**
     * 测试激活码文件合并
     * 
     * @param (参数列表)
     * @return (返回值)
     */
    public static void main(String[] args) {
        mergeFile();
    }

    public static void mergeFile() {
        String rootPath = "D:/bdsoft/vko/tianli_act_code";
        String destPath = rootPath + "/all_code.txt";

        File rootDir = new File(rootPath);
        if (rootDir.exists()) {
            File[] fileArr = rootDir.listFiles();
            DateTime start = new DateTime();
            System.out.println("开始：" + start.toLocalDateTime());
            for (File file : fileArr) {
                readFrom(destPath, file);
            }
            DateTime end = new DateTime();
            System.out.println("结束：" + end.toLocalDateTime());
            String msg = String.format("文件合并完毕,耗时：%s分 %s秒", (Minutes.minutesBetween(start, end).getMinutes() % 60),
                    (Seconds.secondsBetween(start, end).getSeconds() % 3600));
            System.out.println(msg);
        }
    }

	/**
	 * @param destPath
	 * @param file
	 */
	private static void readFrom(String destPath, File file) {
		System.out.println("读取文件-" + file.getName());

		BufferedReader br = null;
		try {
		    br = new BufferedReader(new FileReader(file));
		    int i = 0;
		    Set<String> codeSet = new HashSet<String>();
		    String line = br.readLine();
		    while (line != null) {
		        i++;
		        codeSet.add(line.trim());
		        if (i >= 5000) {
		            appendToFile(codeSet, destPath);
		            codeSet.clear();
		            i = 0;
		        }
		        line = br.readLine();
		    }
		    appendToFile(codeSet, destPath);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (Exception e2) {
		        }
		    }
		}
	}

    private static void appendToFile(Set<String> data, String dest) {
        if (data.size() == 0) {
            return;
        }
        System.out.print("开始一次输出...");
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String d : data) {
            sb.append(d).append("\r\n");
            i++;
            if (i >= 1000) {
                RandomWriteFile.writeFile(dest, sb.toString().getBytes());
                System.out.print(" 输出>" + i);
                i = 0;
                sb = new StringBuffer();
            }
        }
        if (i > 0) {
            RandomWriteFile.writeFile(dest, sb.toString().getBytes());
            System.out.println(" 输出>" + i);
        }
        System.out.println(" 结束一次输出。");
    }

}
