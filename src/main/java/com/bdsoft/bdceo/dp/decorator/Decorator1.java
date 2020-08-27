package com.bdsoft.bdceo.dp.decorator;

public class Decorator1 {

    /**
     * 装饰器，适配器模式
     */
    public static void main(String[] args) {
        // 适配器
        IRead iread = new ReadStrFromNet();
        SubReadStrFromFile srsff = new SubReadStrFromFile(new FConvert(iread));
        srsff.read();

        System.out.println("");
        // 装饰器
        FConvert convert = new FConvert(new ReadStrFromFile());
        convert.convert();
    }
}

/*
 * 单向适配器模式是在主模块上接入一个扩展模块，用继承法：
 * 优势：能够很好的应付扩展模块的切换
 * 缺陷：扩展模块无法很好的应付主模块的切换
 * 问题类比：Convert为ReadStrFromFile服务，以后又要为其他类服务，
 * 用单向适配器模式，就必须为其他类编写子类，很麻烦
 * */

//装饰模式要点：主模块接口注入扩展模块
interface IRead {
    void read();
}

class ReadStrFromFile implements IRead {// 主模块

    public void read() {
        System.out.println("从文件读取字符串");
    }
}

class ReadStrFromNet implements IRead {// 主模块

    public void read() {
        System.out.println("从网络读取字符串");
    }
}

// 装饰模式
class FConvert {// 扩展模块,可以用Spring装配，可以为任何实现了IRead接口的类服务
    private IRead iread;

    public FConvert(IRead iread) {
        this.iread = iread;
    }

    public void convert() {
        iread.read();
        System.out.println("转大写字母");
    }
}

// 适配器模式
class SubReadStrFromFile extends ReadStrFromFile {
    private FConvert convert;

    public SubReadStrFromFile(FConvert con) {
        this.convert = con;
    }

    /**
     * 对外暴露接口不变，内部实现增强
     */
    public void read() {
        super.read();
        convert.convert();
    }
}
