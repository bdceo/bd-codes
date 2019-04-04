package com.bdsoft.y2016;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 图片合成器
 */
public class ImgMaker {

	public static void main(String[] args) throws Exception {
		// 原二维码
		String qrc = "d:/home/vko.png";
		File src = new File(qrc);

		// 美化后生成的图片
		String mh = "d:/home/meihua.png";
		File target = new File(mh);
		int tarw = 300;
		int tarh = 400;

		Image srcImg = ImageIO.read(src);
		int srcw = srcImg.getWidth(null);
		int srch = srcImg.getHeight(null);
		System.out.println("原图：" + srcw + "*" + srch);

		// 准备画布
		BufferedImage img = new BufferedImage(tarw, tarh, 6);
		Graphics2D g = img.createGraphics();

		// 画一个白底
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, tarw, tarh);

		// 画二维码
		g.drawImage(srcImg, 0, 0, null);

		// 画班级信息
		g.setColor(Color.GREEN);
		g.setFont(new Font("", 1, 21));
		int x = 0, y = srch + 20;
		g.drawString("学校：浆水中学", x, y);
		y += 30;
		g.drawString("年级：高一", x, y);
		y += 30;
		g.drawString("班级：189班", x, y);

		g.dispose();
		ImageIO.write(img, "png", target);
		System.out.println("图片已合成>>" + mh);
	}

}
