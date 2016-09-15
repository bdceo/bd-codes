package com.bdsoft.y2014.m6;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestPOI {

    /**
     * 解析Excel-2007 参考：http://kxjhlele.iteye.com/blog/321392
     * 
     */
    public static void main(String[] args) {
        String excelPath = "D:/Download/全国汇编（物化）.xlsx";

        try {
            XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelPath));
            XSSFSheet sheet = xwb.getSheetAt(0);

            XSSFRow row = null;
            String cell = null;

            Map<String, String> peOrderMap = new TreeMap<String, String>();
            int porder = 0, eorder = 0;
            // 遍历所有行
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                // 遍历每一行的所有列
                for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                    cell = row.getCell(j).toString();
                    if (j == 0 && !cell.isEmpty()) { // 试卷序号
                        porder = new Double(Double.parseDouble(cell)).intValue();
                    }
                    if (j == 2) { // 试卷下试题序号
                        eorder = new Double(Double.parseDouble(cell)).intValue();
                    }
                    if (j == 3) {// 录制老师姓名
                        peOrderMap.put(porder + "#" + eorder, cell);
                    }
                    System.out.print(cell + "\t");
                }
                System.out.println("");
            }

            for (Entry<String, String> en : peOrderMap.entrySet()) {
                System.out.println(en.getKey() + " - " + en.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
