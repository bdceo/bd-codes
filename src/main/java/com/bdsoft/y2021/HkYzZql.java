package com.bdsoft.y2021;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 乙组中签率
 */
@Slf4j
public class HkYzZql {

    static String data = "40,000 7,144 500股H股另加7,144名申請人中的68名獲發額外100股H股 1.25%\n" +
            "50,000 915 600股H股 1.20%\n" +
            "60,000 561 700股H股 1.17%\n" +
            "70,000 435 800股H股 1.14%\n" +
            "80,000 251 900股H股 1.13%\n" +
            "90,000 98 1,000股H股 1.11%\n" +
            "100,000 781 1,100股H股 1.10%\n" +
            "200,000 344 1,500股H股 0.75%\n" +
            "300,000 121 1,900股H股 0.63%\n" +
            "400,000 41 2,300股H股 0.58%\n" +
            "500,000 42 2,700股H股 0.54%\n" +
            "600,000 15 3,100股H股 0.52%\n" +
            "700,000 21 3,500股H股 0.50%\n" +
            "800,000 26 3,900股H股 0.49%\n" +
            "900,000 17 4,300股H股 0.48%\n" +
            "1,000,000 39 4,700股H股 0.47%\n" +
            "1,500,000 16 6,400股H股 0.43%\n" +
            "1,949,600 26 8,200股H股 0.42%";

    /**
     * 解析+计算
     */
    public static void main(String[] args) throws Exception {
        String[] rows = data.split("\n");
        List<YzItem> list = new ArrayList<>(rows.length);
        for (String row : rows) {
            YzItem item = new YzItem(row);
            log.info(item.toString());
            list.add(item);
        }

        calc(list);
    }

    /**
     * 计算乙组中签率
     */
    static void calc(List<YzItem> data) {
        long applySum = 0;
        long allotSum = 0;
        for (YzItem item : data) {
            applySum += item.getApplyTotal() * item.getUserTotal();
            allotSum += item.getUserTotal() * item.getAllotTotal();
            if (item.getExtUser() != 0) {
                allotSum += item.getExtUser() * item.getExtAllot();
            }
        }

        BigDecimal rate = BigDecimal.valueOf(allotSum)
                .divide(BigDecimal.valueOf(applySum), 4, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100)).setScale(2);
        log.info("乙组中签率={}%", rate.toPlainString());
    }

    /**
     * 测试解析
     */
    static void testParse() throws Exception {
        String str = "1,200,000";
        Number num = NumberFormat.getInstance().parse(str);
        log.info("{}", num.intValue());

        str = "1,7600股股份另加1,103名中335名獲發額外800股股份";
        String reg = "([\\d,]+)";
        log.info("str={}, reg={}", str, reg);

        String[] numbers = new String[10];
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        int i = 0;
        int start = 0;
        while (mat.find(start)) {
            log.info("{}, {}", start, mat.group(1));
            start = mat.end();
            numbers[i++] = mat.group(1);
        }
    }

}

@Data
class YzItem {

    /**
     * 申请股份数目
     */
    private long applyTotal;

    /**
     * 有效申请数目：人数
     */
    private long userTotal;

    /**
     * 分配股份数目
     */
    private long allotTotal;

    /**
     * 额外分配人数
     */
    private long extUser;

    /**
     * 额外分配股份数目
     */
    private long extAllot;

    public YzItem(String str) {
        NumberFormat nf = NumberFormat.getInstance();
        String[] data = str.split(" ");
        try {
            this.applyTotal = nf.parse(data[0]).longValue();
            this.userTotal = nf.parse(data[1]).longValue();

            String[] numbers = this.parseExt(data[2]);
            this.allotTotal = nf.parse(numbers[0]).longValue();
            if (numbers[2] != null && numbers[3] != null) {
                this.extUser = nf.parse(numbers[2]).longValue();
                this.extAllot = nf.parse(numbers[3]).longValue();
            } else {
                this.extUser = 0;
                this.extAllot = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("乙组：").append("申购股数=").append(applyTotal);
        str.append(", 申购人数=").append(userTotal);
        str.append(", 分配股数=").append(allotTotal);
        if (extUser > 0) {
            str.append(", 额外分配人数=").append(extUser);
            str.append(", 额外分配股数=").append(extAllot);
        }
        return str.toString();
    }

    /**
     * 解析分配规则
     */
    private String[] parseExt(String str) {
        String[] numbers = new String[10];
        String reg = "([\\d,]+)";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        int i = 0;
        int start = 0;
        while (mat.find(start)) {
            start = mat.end();
            numbers[i++] = mat.group(1);
        }
        return numbers;
    }

}
