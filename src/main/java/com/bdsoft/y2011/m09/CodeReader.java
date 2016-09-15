package com.bdsoft.y2011.m09;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;

//import com.google.zxing.MonochromeBitmapSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
//import com.google.zxing.client.j2se.BufferedImageMonochromeBitmapSource;

public class CodeReader {
	public static void main(String[] args) throws ReaderException,
			InterruptedException {
		Reader reader = new MultiFormatReader(); 
		String imgPath = "qr1.gif";
		Image image = java.awt.Toolkit.getDefaultToolkit().getImage(imgPath);
		BufferedImage myImage = CodeReader.imageToBufImage(image);
//		MonochromeBitmapSource source = new BufferedImageMonochromeBitmapSource(
//				myImage);
//		Result result = reader.decode(source);
//		System.out.println(result.getText());
	}

	public static BufferedImage imageToBufImage(Image image)
			throws InterruptedException {
		MediaTracker mt = new MediaTracker(new Frame());
		mt.addImage(image, 0);
		mt.waitForID(0);
		BufferedImage bufImage = new BufferedImage(image.getWidth(null), image
				.getHeight(null), BufferedImage.TYPE_INT_BGR);
		Graphics2D g2d = bufImage.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		return bufImage;
	}
}
