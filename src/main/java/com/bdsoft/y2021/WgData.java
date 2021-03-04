package com.bdsoft.y2021;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateParser;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 网格数据
 */
@Slf4j
public class WgData {

    /**
     * 生成折线图数据
     */
    public static void main(String[] args) {
        // 净值
        List<DailyValue> dvList = parseValue();
        dvList.sort(Comparator.comparing(v -> v.getDay()));
        dvList.forEach(v -> log.info(v.toString()));

        // 买卖点位
        List<DeliverOrder> doList = parseDeliver();
        doList.forEach(v -> log.info(v.toString()));

        // X轴：日期
        List<Date> dtList = dvList.stream().map(DailyValue::getDay).collect(Collectors.toList());

        // 生成净值线
        genDailyValLine(dvList);
        // 生成买入、卖出点
        genBuyPoint(dtList, "买入", doList);
        genBuyPoint(dtList, "卖出", doList);
    }

    /**
     * 生成净值线
     */
    public static void genDailyValLine(List<DailyValue> dvList) {
        StringBuilder label = new StringBuilder();
        StringBuilder data = new StringBuilder();
        for (DailyValue v : dvList) {
            label.append("'").append(DateFormatUtils.format(v.getDay(), "yyyy-MM-dd")).append("',");
            data.append("'").append(v.getVal()).append("',");
        }
        label.replace(label.length() - 1, label.length(), "");
        data.replace(data.length() - 1, data.length(), "");
        log.info("净值线");
        log.info("labels: [{}],", label.toString());
        log.info("data: [{}],", data.toString());
    }

    /**
     * 生成买入、卖出点
     */
    public static void genBuyPoint(List<Date> dtList, String bs, List<DeliverOrder> doList) {
        StringBuilder data = new StringBuilder();
        Map<Date, DeliverOrder> doMap = doList.stream().collect(Collectors.toMap(DeliverOrder::getDay, Function.identity(), (a1, a2) -> a1));
        for (Date d : dtList) {
            DeliverOrder order = doMap.get(d);
            if (order != null && order.getBs().contains(bs)) {
                if (dtList.contains(order.getDay())) {
                    data.append("'").append(order.getPrice()).append("',");
                } else {
                    data.append(",");
                }
            } else {
                data.append(",");
            }
        }
        data.replace(data.length() - 1, data.length(), "");
        log.info("{}点", bs);
        log.info("data: [{}],", data.toString());
    }

    /**
     * 解析收盘净值
     */
    public static List<DailyValue> parseValue() {
        String data = "2021/3/3\t1.1587\n" +
                "2021/3/2\t1.1532\n" +
                "2021/3/1\t1.1472\n" +
                "2021/2/26\t1.1187\n" +
                "2021/2/25\t1.1282\n" +
                "2021/2/24\t1.1349\n" +
                "2021/2/23\t1.1331\n" +
                "2021/2/22\t1.1368\n" +
                "2021/2/19\t1.163\n" +
                "2021/2/18\t1.1525\n" +
                "2021/2/10\t1.1489\n" +
                "2021/2/9\t1.1244\n" +
                "2021/2/8\t1.1006\n" +
                "2021/2/5\t1.0978\n" +
                "2021/2/4\t1.1197\n" +
                "2021/2/3\t1.1345\n" +
                "2021/2/2\t1.1664\n" +
                "2021/2/1\t1.1541\n" +
                "2021/1/29\t1.1497\n" +
                "2021/1/28\t1.157\n" +
                "2021/1/27\t1.2068\n" +
                "2021/1/26\t1.2028\n" +
                "2021/1/25\t1.2324\n";

        String[] arr = data.split("\n");
        List<DailyValue> dvList = new ArrayList<>(arr.length);
        for (String line : arr) {
            String[] tds = line.split("\t");
            dvList.add(new DailyValue(tds[0], tds[1]));
        }

        return dvList;
    }

    /**
     * 解析交割单
     */
    public static List<DeliverOrder> parseDeliver() {
        String data = "20210128\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.162\n" +
                "20210128\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.166\n" +
                "20210129\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.154\n" +
                "20210201\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.151\n" +
                "20210201\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.151\n" +
                "20210202\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.139\n" +
                "20210203\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.140\n" +
                "20210204\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.114\n" +
                "20210205\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.108\n" +
                "20210209\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.134\n" +
                "20210218\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.139\n" +
                "20210224\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.117\n" +
                "20210225\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.121\n" +
                "20210226\t证券买入\t512880\t证券ETF\t证券买入\t900\t1.102\n" +
                "20210301\t证券买入\t512880\t证券ETF\t证券买入\t1000\t1.089\n" +
                "20210301\t证券买入\t512880\t证券ETF\t证券买入\t1000\t1.088\n" +
                "20210302\t证券买入\t512880\t证券ETF\t证券买入\t1000\t1.078\n";

        String[] arr = data.split("\n");
        List<DeliverOrder> doList = new ArrayList<>(arr.length);
        for (String line : arr) {
            String[] tds = line.split("\t");
            DeliverOrder order = new DeliverOrder();
            order.setDay(tds[0]);
            order.setCode(tds[2]);
            order.setName(tds[3]);
            order.setBs(tds[4]);
            order.setTotal(tds[5]);
            order.setPrice(tds[6]);
            doList.add(order);
        }
        return doList;
    }

}

@Data
class DailyValue {

    private Date day;
    private Float val;

    public DailyValue(String day, String val) {
        try {
            this.day = DateUtils.parseDate(day, "yyyy/MM/dd", "yyyy-MM-dd", "yyyyMMdd", "yyyy年MM月dd日");
        } catch (ParseException e) {
            throw new RuntimeException("日期解析失败");
        }
        this.val = Float.parseFloat(val);
    }

}

@Data
class DeliverOrder {

    private Date day;
    private String code;
    private String name;

    /**
     * 买卖类型
     */
    private String bs;
    /**
     * 成交数量
     */
    private Integer total;
    /**
     * 成交价格
     */
    private Float price;

    public void setDay(String day) {
        try {
            this.day = DateUtils.parseDate(day, "yyyy/MM/dd", "yyyy-MM-dd", "yyyyMMdd", "yyyy年MM月dd日");
        } catch (ParseException e) {
            throw new RuntimeException("日期解析失败");
        }
    }

    public void setTotal(String total) {
        this.total = Integer.valueOf(total);
    }

    public void setPrice(String price) {
        this.price = Float.parseFloat(price);
    }
}
