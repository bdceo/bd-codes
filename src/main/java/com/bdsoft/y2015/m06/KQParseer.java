package com.bdsoft.y2015.m06;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bdsoft.utils.StringUtil;

/**
 * 考勤解析工具
 * 
 * @author 丁辰叶
 */
public class KQParseer {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		String path = "d:/";

		File dir = new File(path);

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isFile() && isExcel(file)) {
					parseKaoQinExcel(file);
				}
			}
		} else if (isExcel(dir)) {
			parseKaoQinExcel(dir);
		}

	}

	/**
	 * 解析考勤Excel表
	 * 
	 * @param file
	 *            考勤文件
	 */
	public static void parseKaoQinExcel(File file) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = xwb.getSheetAt(0);

			System.out.println(String.format("准备解析：%s, 表格：%s",
					file.getAbsolutePath(), sheet.getSheetName()));

			XSSFRow dayRow = null; // 日期-周几
			XSSFRow timeRow = null;// 打卡时间

			String dayCell = null;
			String timeCell = null;

			// 遍历行(从0开始)，从第3行开始
			for (int i = 9; i < sheet.getPhysicalNumberOfRows(); i = i + 2) {
				dayRow = sheet.getRow(i);
				timeRow = sheet.getRow(i + 1);

				// 忽略空行:真实
				if (dayRow.getFirstCellNum() < 0) {
					continue;
				}

				// 遍历行-列
				for (int j = dayRow.getFirstCellNum(); j < dayRow
						.getPhysicalNumberOfCells(); j++) {
					if (dayRow.getCell(j) == null) {
						continue;
					}
					dayCell = dayRow.getCell(j).toString();
					timeCell = timeRow.getCell(j).toString();
					if (StringUtil.isEmpty(dayCell)) {
						continue;
					}
					System.out.println(String.format(
							"解析 行=%d 列=%d\t>>\t日期=%s\t打卡=%s", i + 1, j,
							dayCell, timeCell));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 是否是excel文件
	 * 
	 * @param file
	 *            文件
	 * @return
	 */
	public static boolean isExcel(File file) {
		String name = file.getName();
		return (name.endsWith("xls") || name.endsWith("xlsx"));
	}

}
