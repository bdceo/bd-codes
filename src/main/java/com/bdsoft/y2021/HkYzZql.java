package com.bdsoft.y2021;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 乙组中签率
 */
@Slf4j
public class HkYzZql {

    static String data = "2,500,000 6 566,000股股份，另6份申請中的2份額外獲發2,000股股份 22.67%\n" +
            "3,000,000 1 680,000股股份 22.67%\n" +
            "3,438,000 8 778,000股股份，另8份申請中的4份額外獲發2,000股股份 22.66%";

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
        int applySum = 0;
        int allotSum = 0;
        for (YzItem item : data) {
            applySum += item.getApplyTotal() * item.getUserTotal();
            allotSum += item.getUserTotal() * item.getAllotTotal();
            if (item.getExtUser() != 0) {
                allotSum += item.getExtUser() * item.getExtAllot();
            }
        }
        log.info("乙组中签率={}%", BigDecimal.valueOf(allotSum)
                .divide(BigDecimal.valueOf(applySum), 4, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100)).setScale(2).toPlainString());
    }

    static void parse() throws Exception {
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
    private int applyTotal;

    /**
     * 有效申请数目：人数
     */
    private int userTotal;

    /**
     * 分配股份数目
     */
    private int allotTotal;

    /**
     * 额外分配人数
     */
    private int extUser;

    /**
     * 额外分配股份数目
     */
    private int extAllot;

    public YzItem(String str) {
        NumberFormat nf = NumberFormat.getInstance();
        String[] data = str.split(" ");
        try {
            this.applyTotal = nf.parse(data[0]).intValue();
            this.userTotal = nf.parse(data[1]).intValue();

            String[] numbers = this.parseExt(data[2]);
            this.allotTotal = nf.parse(numbers[0]).intValue();
            if (numbers[2] != null && numbers[3] != null) {
                this.extUser = nf.parse(numbers[2]).intValue();
                this.extAllot = nf.parse(numbers[3]).intValue();
            } else {
                this.extUser = 0;
                this.extAllot = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String[] parseExt(String str) {
        String[] numbers = new String[4];
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
