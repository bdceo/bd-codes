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

    static String data = "20,000 144 2,500股股份另加144名申請人中的2名將獲發額外100股股份 12.51%\n" +
            "25,000 27 3,100股股份 12.40%\n" +
            "30,000 12 3,600股股份 12.00%\n" +
            "35,000 10 4,100股股份 11.71%\n" +
            "40,000 17 4,600股股份 11.50%\n" +
            "45,000 7 5,100股股份 11.33%\n" +
            "50,000 14 5,600股股份 11.20%\n" +
            "60,000 5 6,600股股份 11.00%\n" +
            "70,000 4 7,600股股份 10.86%\n" +
            "80,000 4 8,600股股份 10.75%\n" +
            "90,000 1 9,600股股份 10.67%\n" +
            "100,000 5 10,600股股份 10.60%\n" +
            "200,000 6 21,000股股份 10.50%\n" +
            "400,000 1 41,600股股份 10.40% 1,514,600 3 155,400股股份 10.26%";

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
