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
        String data = "2021/3/4\t1.5216\t1.5976\n" +
                "2021/3/3\t1.5504\t1.6264\n" +
                "2021/3/2\t1.5114\t1.5874\n" +
                "2021/3/1\t1.5327\t1.6087\n" +
                "2021/2/26\t1.5086\t1.5846\n" +
                "2021/2/25\t1.5608\t1.6368\n" +
                "2021/2/24\t1.5447\t1.6207\n" +
                "2021/2/23\t1.5898\t1.6658\n" +
                "2021/2/22\t1.5741\t1.6501\n" +
                "2021/2/19\t1.5932\t1.6692\n" +
                "2021/2/18\t1.5885\t1.6645\n" +
                "2021/2/10\t1.5567\t1.6327\n" +
                "2021/2/9\t1.5308\t1.6068\n" +
                "2021/2/8\t1.5253\t1.6013\n" +
                "2021/2/5\t1.5252\t1.6012\n" +
                "2021/2/4\t1.514\t1.59\n" +
                "2021/2/3\t1.5251\t1.6011\n" +
                "2021/2/2\t1.5237\t1.5997\n" +
                "2021/2/1\t1.5024\t1.5784\n" +
                "2021/1/29\t1.4733\t1.5493\n" +
                "2021/1/28\t1.4901\t1.5661\n";

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
        String data = "20210128\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.512\t1058.400\n" +
                "20210128\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.489\t1042.300\n" +
                "20210129\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.474\t1031.800\n" +
                "20210204\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.509\t1056.300\n" +
                "20210209\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.525\t1067.500\n" +
                "20210226\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.515\t1060.500\n" +
                "20210301\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.527\t1068.900\n" +
                "20210302\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.515\t1060.500\n" +
                "20210304\t证券买入\t159920\t恒生ETF\t证券买入\t700\t1.515\t1060.500\n";

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
