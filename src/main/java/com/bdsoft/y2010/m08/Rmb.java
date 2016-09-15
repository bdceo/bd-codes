package com.bdsoft.y2010.m08;

public class Rmb {

	// 金额：数字转中文大写
	public static void main(String[] args) {
		String ch = changeUpper(256635);
		System.out.println(ch);
	}

	private static String[] UNIT = { "拾", "佰", "千", "万", "亿" };
	private static String[] NUM = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒",
			"捌", "玖" };

	private static String getUnit(int i) {
		String unit = "";
		switch (i) {
		case 2:
			unit = UNIT[0];
			break;
		case 3:
			unit = UNIT[1];
			break;
		case 4:
			unit = UNIT[2];
			break;
		case 5:
			unit = UNIT[3];
			break;
		case 6:
			unit = UNIT[3];
			break;
		case 7:
			unit = UNIT[3];
			break;
		case 8:
			unit = UNIT[3];
			break;
		default:
			unit = UNIT[4];
		}
		return unit;
	}

	private static String getNum(int i) {
		return NUM[i];
	}

	public static String changeUpper(int lowerNum) {
		StringBuffer sb = new StringBuffer();
		if (lowerNum > 0 && lowerNum < Math.pow(10, 1)) {
			sb.append(getNum(lowerNum));
		} else if (lowerNum >= Math.pow(10, 1) && lowerNum < Math.pow(10, 2)) {
			sb.append(getNum(lowerNum / 10)).append(
					getUnit(String.valueOf(lowerNum).length()));
			sb.append(changeUpper(lowerNum % 10));
		} else if (lowerNum >= Math.pow(10, 2) && lowerNum < Math.pow(10, 3)) {
			int t = (int) Math.pow(10, 2);
			sb.append(getNum(lowerNum / t)).append(
					getUnit(String.valueOf(lowerNum).length()));
			int tmp = lowerNum % t;
			if (tmp > 0 && tmp < t / 10) {
				sb.append("零");
			}
			sb.append(changeUpper(lowerNum % t));
		} else if (lowerNum >= Math.pow(10, 3) && lowerNum < Math.pow(10, 4)) {
			int t = (int) Math.pow(10, 3);
			sb.append(getNum(lowerNum / t)).append(
					getUnit(String.valueOf(lowerNum).length()));
			int tmp = lowerNum % t;
			if (tmp > 0 && tmp < t / 10) {
				sb.append("零");
			}
			sb.append(changeUpper(lowerNum % t));
		} else if (lowerNum >= Math.pow(10, 4) && lowerNum < Math.pow(10, 8)) {
			int t = (int) Math.pow(10, 4);
			sb.append(changeUpper(lowerNum / t)).append(
					getUnit(String.valueOf(lowerNum).length()));
			int tmp = lowerNum % t;
			if (tmp > 0 && tmp < t / 10) {
				sb.append("零");
			}
			sb.append(changeUpper(lowerNum % t));
		} else if (lowerNum >= Math.pow(10, 8)) {
			int t = (int) Math.pow(10, 8);
			sb.append(changeUpper(lowerNum / t)).append(
					getUnit(String.valueOf(lowerNum).length()));
			int tmp = lowerNum % t;
			if (tmp > 0 && tmp < t / 10) {
				sb.append("零");
			}
			sb.append(changeUpper(lowerNum % t));
		} else {
			// sb.append("零");
		}
		return sb.toString();
	}
}
