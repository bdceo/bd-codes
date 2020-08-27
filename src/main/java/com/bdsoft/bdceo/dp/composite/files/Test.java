package com.bdsoft.bdceo.dp.composite.files;

/**
 * 测试类
 */
public class Test {

    /**
     * 组合组件
     */
    public static void main(String[] args) {
        Directory root = new Directory("根目录");

        Directory life = new Directory("生活");
        File movie = new File("电影", 100);
        File book = new File("读书", 30);
        Directory travel = new Directory("旅行");
        File hb = new File("河北", 7);
        File sx = new File("山西", 4);
        travel.add(hb).add(sx);
        life.add(movie).add(book).add(travel);

        Directory work = new Directory("工作");
        File code = new File("编程", 30);
        File stock = new File("炒股", 3);
        work.add(code).add(stock);

        root.add(life).add(work);

        root.printList("BD");

        life.printList("L");

        stock.printList("S");
    }
}
