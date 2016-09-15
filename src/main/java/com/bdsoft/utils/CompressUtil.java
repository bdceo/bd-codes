package com.bdsoft.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CompressUtil {

	private String input; // 原图
	private String output; // 新图
	private int w = 1280; // 输出图片宽度
	private int h = 768; // 输出图片高度
	private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

	// main测试
	// compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
	public static void main(String[] arg) {
		String src = "d:/home/ddc/IMG_3590.jpg";
		String desct = "d:/home/ddc/IMG_3590-cmp.jpg";
		CompressUtil mypic = new CompressUtil(src, desct);
		int start = (int) System.currentTimeMillis(); // 开始时间
		int status = mypic.compressPic();
		int end = (int) System.currentTimeMillis(); // 结束时间
		if (status > 0) {
			System.out.println("原图：" + CompressUtil.getPicSize(src) / 1024 + "KB");
			System.out.println("耗时: " + (end - start) + "毫秒");
			System.out.println("处理后：" + CompressUtil.getPicSize(desct) / 1024 + "KB");
		}
	}

	/**
	 * 图片大小
	 * 
	 * @param 图片路径
	 */
	public static long getPicSize(String path) {
		File file = new File(path);
		return file.length();
	}

	/**
	 * 图片压缩处理
	 * 
	 * @return 操作成功标识
	 * 
	 */
	public int compressPic() {
		FileOutputStream out = null;
		try {
			File file = new File(input);
			if (!file.exists()) {
				return -1;
			}
			Image img = ImageIO.read(file);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				System.out.println("无法读取文件");
				return 0;
			} else {
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (this.proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null)) / (double) w + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) h + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = w; // 输出的图片宽度
					newHeight = h; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT), 0, 0, null);
				out = new FileOutputStream(output);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	public CompressUtil(String inputDir, String outputDir) {
		this.input = inputDir;
		this.output = outputDir;
	}

	public CompressUtil(String inputDir, String outputDir, int width, int height) {
		this.input = inputDir;
		this.output = outputDir;
		this.w = width;
		this.h = height;
	}

	public void setOutputWidth(int outputWidth) {
		this.w = outputWidth;
	}

	public void setOutputHeight(int outputHeight) {
		this.h = outputHeight;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public boolean isProportion() {
		return proportion;
	}

	public void setProportion(boolean proportion) {
		this.proportion = proportion;
	}

}
