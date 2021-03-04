package com.bdsoft.y2021;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.*;
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
        // 成本线
        genCostLine(dtList, doList);
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
        Map<Date, List<DeliverOrder>> doMap = doList.stream().collect(Collectors.groupingBy(DeliverOrder::getDay));
        for (Date d : dtList) {
            List<DeliverOrder> orders = doMap.get(d);
            if (orders != null) {
                float cost = 0;
                int total = 0;
                for (DeliverOrder order : orders) {
                    if (order.getBs().contains(bs)) {
                        cost += order.getCost();
                        total += order.getTotal();
                    }
                }
                if (total == 0) {
                    data.append(",");
                } else {
                    float price = cost / total;
                    data.append("'").append(String.format("%.3f", price)).append("',");
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
     * 计算成本点位
     */
    public static void genCostLine(List<Date> dtList, List<DeliverOrder> doList) {
        StringBuilder data = new StringBuilder();
        Map<Date, List<DeliverOrder>> doMap = doList.stream().collect(Collectors.groupingBy(DeliverOrder::getDay));
        float cost = 0;
        int total = 0;
        for (Date d : dtList) {
            List<DeliverOrder> orders = doMap.get(d);
            if (orders != null) {
                for (DeliverOrder order : orders) {
                    if (order.getBs().contains("买入")) {
                        cost += order.getCost();
                        total += order.getTotal();
                    } else {
                        cost -= order.getCost();
                        total += order.getTotal();
                    }
                }
                float price = total == 0 ? 0 : cost / total;
                data.append("'").append(String.format("%.3f", price)).append("',");
            } else {
                if (total > 0) {
                    float price = cost / total;
                    data.append("'").append(String.format("%.3f", price)).append("',");
                } else {
                    data.append(",");
                }
            }
        }
        data.replace(data.length() - 1, data.length(), "");
        log.info("成本线");
        log.info("data: [{}],", data.toString());
    }

    /**
     * 解析收盘净值
     */
    public static List<DailyValue> parseValue() {
        String data = "2021/3/3\t2.3243\t2.3243\n" +
                "2021/3/2\t2.3151\t2.3151\n" +
                "2021/3/1\t2.3472\t2.3472\n" +
                "2021/2/26\t2.2502\t2.2502\n" +
                "2021/2/25\t2.3187\t2.3187\n" +
                "2021/2/24\t2.3536\t2.3536\n" +
                "2021/2/23\t2.4303\t2.4303\n" +
                "2021/2/22\t2.4581\t2.4581\n" +
                "2021/2/19\t2.5636\t2.5636\n" +
                "2021/2/18\t2.5439\t2.5439\n" +
                "2021/2/10\t2.5531\t\n" +
                "2021/2/9\t2.497\t2.497\n" +
                "2021/2/8\t2.4642\t2.4642\n" +
                "2021/2/5\t2.4688\t2.4688\n" +
                "2021/2/4\t2.4632\t2.4632\n" +
                "2021/2/3\t2.4604\t2.4604\n" +
                "2021/2/2\t2.4091\t2.4091\n" +
                "2021/2/1\t2.3852\t2.3852\n" +
                "2021/1/29\t2.2853\t2.2853\n" +
                "2021/1/28\t2.3175\t2.3175\n";

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
        String data = "20210128\t证券买入\t513050\t中概互联\t证券买入\t500\t2.360\t1180.000\n" +
                "20210128\t证券买入\t513050\t中概互联\t证券买入\t500\t2.321\t1160.500\n" +
                "20210129\t证券买入\t513050\t中概互联\t证券买入\t400\t2.321\t928.400\n" +
                "20210209\t证券买入\t513050\t中概互联\t证券买入\t400\t2.458\t983.200\n" +
                "20210218\t证券买入\t513050\t中概互联\t证券买入\t400\t2.572\t1028.800\n" +
                "20210224\t证券买入\t513050\t中概互联\t证券买入\t400\t2.360\t944.000\n" +
                "20210225\t证券买入\t513050\t中概互联\t证券买入\t400\t2.400\t960.000\n" +
                "20210226\t证券买入\t513050\t中概互联\t证券买入\t400\t2.284\t913.600\n" +
                "20210301\t证券买入\t513050\t中概互联\t证券买入\t400\t2.311\t924.400\n";

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
            order.setCost(tds[7]);
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

    /**
     * 成交金额
     */
    private Float cost;

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

    public void setCost(String cost) {
        this.cost = Float.parseFloat(cost);
    }

}
