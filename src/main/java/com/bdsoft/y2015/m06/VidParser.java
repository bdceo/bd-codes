package com.bdsoft.y2015.m06;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bdsoft.utils.RandomWriteFile;
import com.bdsoft.utils.StringUtil;

/**
 * 保利威视-视频vid映射
 * 
 * @author 丁辰叶
 */
public class VidParser {

	static Pattern REG_VID = Pattern.compile(".*/([\\da-z_]+)\\.swf$");

	static int max_id = 137219;

	static String poly_sql = "d:/home/poly.sql";

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		String path = "d:/home/videolist.xlsx";

		parse(new File(path));
	}

	/**
	 * 生成sql并写入文件
	 */
	static void appendSql(Vi v) {
		System.out.println(v.toString());

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `vko_video`.`video_url` ");
		sql.append("(`id`, `videoNo`, `storeUrl`, `clarity`, `enKey`, `storeType`) ");
		sql.append("VALUES (");
		sql.append(max_id++).append(",'").append(v.tNo).append("','").append(v.vid).append("',0,'',3);\n");

		RandomWriteFile.writeFile(poly_sql, sql.toString().getBytes());
	}

	/**
	 * 视频信息
	 */
	static class Vi {

		String tNo;
		String vid;

		public Vi(String cell) {
			String[] cells = cell.split(";");
			tNo = cells[0];

			Matcher match = REG_VID.matcher(cells[1]);
			if (match.find()) {
				vid = match.group(1);
			}
		}

		@Override
		public String toString() {
			return "Vi [tNo=" + tNo + ", vid=" + vid + "]";
		}

	}

	/**
	 * 解析导出的视频excel文件
	 */
	public static void parse(File file) {
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = xwb.getSheetAt(0);

			System.out.println(String.format("准备解析：%s, 表格：%s", file.getAbsolutePath(), sheet.getSheetName()));

			XSSFRow row = null;
			String cell = null;

			// 遍历行(从0开始)
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);

				// 忽略空行:真实
				if (row.getFirstCellNum() < 0) {
					continue;
				}

				// 遍历行-列
				for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					if (row.getCell(j) == null) {
						continue;
					}
					cell = row.getCell(j).toString();
					if (StringUtil.isEmpty(cell)) {
						continue;
					}
					System.out.println(String.format("解析 行 %d 列 %d\t>>\t%s", i + 1, j, cell));
					appendSql(new Vi(cell));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
