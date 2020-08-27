package com.bdsoft.bdceo.dp.proxy;

public class Proxy1 {

    /**
     * 代理模式
     */
    public static void main(String[] args) {
        ImageProxy ip = new ImageProxy(new ImageDisplay());
        // 客户端调用时感觉只是在和代理打交道
        ip.displayImage();// 缩略图
        ip.mousePress();// 处理点击事件，显示原图
    }
}

// 图片显示逻辑抽象
interface IImage {
    void displayImage();
}

// 显示实际图片的逻辑
class ImageDisplay implements IImage {
    public void displayImage() {
        System.out.println("显示实际图片");
    }
}

// 图片代理：显示缩略图片的逻辑及实现鼠标点击显示实际图片
class ImageProxy implements IImage {

    private IImage ii;// 图片显示逻辑

    /**
     * 接口注入
     */
    public ImageProxy(IImage ii) {
        this.ii = ii;
    }

    // 显示缩略图逻辑
    public void displayImage() {
        System.out.println("显示缩略图片");
    }

    // 处理显示原图的逻辑
    public void mousePress() {// 鼠标响应，点击缩略图
        ii.displayImage();
    }
}
