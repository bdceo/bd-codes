package com.bdsoft.y2019;

//import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;

/**
 * 环形队列 + 延迟任务
 */
public class RingQueue {

    static Random rand = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws Exception {
        // 环形队列: T+0, T+3, …… T+30
        int scanIndex = 0;
        int maxIndex = 30;
        Object[] queue = new Object[maxIndex + 1];

        // 记录线索所在环形队列索引
        Map<String, Integer> leadIndex = new HashMap<>();

        // 初始化数据，放入环形队列
        mockLeads(100).forEach(ld -> {
            Duration du = Duration.between(LocalDateTime.now(), ld.getTime());
            int index = Math.min(maxIndex, (int) du.toDays());

            // 放入指定槽位
            Set<Leads> slot = (Set<Leads>) queue[index];
            if (slot == null) {
                slot = new HashSet<>(10);
            }
            slot.add(ld);
            queue[index] = slot;

            // 记录位置
            leadIndex.put(ld.getCode(), index);
        });

        // 异步任务：每1分钟扫描一个队列槽位，30分钟一圈
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("扫描队列-pool-%d").daemon(false).build());
//        executorService.scheduleAtFixedRate(() -> {
//        }, 0, 2, TimeUnit.SECONDS);


//        Thread.sleep(1000*60*60*24L);
    }

    static List<Leads> mockLeads(int size) {
        List<Leads> data = new ArrayList<>(size);
        // 模拟队列数据初始化
        int day30 = 1000 * 60 * 60 * 24 * 15;
        long now = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            String code = "XS" + rand.nextInt(day30);
            LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(now + rand.nextInt(day30)), ZoneId.systemDefault());
            data.add(new Leads(code, time));
        }

        return data;
    }

}

class Leads {

    private String code;
    private LocalDateTime time;

    public Leads(String code, LocalDateTime time) {
        this.code = code;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Leads{" +
                "code='" + code + '\'' +
                ", time=" + time +
                '}';
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
