package com.test.prepareDataset;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import com.gargoylesoftware.htmlunit.html.HtmlImage;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CleanImage {
	static final int DELTA = 3;

	public static boolean isEligible(BufferedImage img, int x, int y) {

		int left = x - 1;
		while (left < 0 && x - left < 2 * DELTA) {
			if (img.getRGB(left, y) == Color.WHITE.getRGB()) {
				break;
			}
			left--;
		}
		if (left < 0) {
			return false;
		}
		int right = x + 1;

		while (right < img.getWidth() && right - left < 2 * DELTA) {
			if (img.getRGB(right, y) == Color.WHITE.getRGB()) {
				break;
			}
			right++;
		}
		if (right > img.getWidth()) {
			return false;
		}
		int top = y - 1;
		while (top > 0 && y - top < 2 * DELTA) {
			if (img.getRGB(x, top) == Color.WHITE.getRGB()) {
				break;
			}
			top--;
		}
		if (top < 0) {
			return false;
		}
		int bottom = y + 1;
		while (bottom < img.getHeight() && bottom - top < 2 * DELTA) {
			if (img.getRGB(x, bottom) == Color.WHITE.getRGB()) {
				break;
			}
			bottom++;
		}
		if (bottom > img.getHeight()) {
			return false;
		}

		int width = right - left;
		int height = bottom - top;
		if (width >= DELTA && height >= DELTA) {
			return true;
		}
		return false;

	}

	public static BufferedImage cleanImage(BufferedImage source) {
		BufferedImage clone = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics2D g2d = clone.createGraphics();
		g2d.drawImage(source, 0, 0, null);
		g2d.dispose();
		for (int i = 1; i < clone.getWidth(); i++) {
			for (int j = 1; j < clone.getHeight(); j++) {
				int rgb = clone.getRGB(i, j);
				if (rgb == Color.WHITE.getRGB()) {
					continue;
				}
				if (isEligible(clone, i, j)) {
					continue;
				} else {
					clone.setRGB(i, j, Color.WHITE.getRGB());
				}

			}
		}

		return clone;

	}

	public static String cleanResult(String result) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length(); i++) {
			if (Character.isAlphabetic(result.charAt(i)) || Character.isDigit(result.charAt(i))) {
				sb.append(result.charAt(i));
			}
		}
		return sb.toString();
	}

	public String getCaptcha(HtmlImage imageDetails) throws IOException, TesseractException {

		System.out.println("Solving captcha.......");

		ImageReader imageReader = imageDetails.getImageReader();
		BufferedImage captchaIimage = imageReader.read(0);
		String result = "";
		BufferedImage image = captchaIimage;
		BufferedImage clean = cleanImage(image);
		ImageIO.write(clean, "png", new File("E:/CaptchaImages/cleaned/captcha_image.png"));

		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");

		result = tesseract.doOCR(clean);
		result = cleanResult(result);

		System.out.println("captcha : " + result);

		return result;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {

			
			try {
				BufferedImage captchaIimage = ImageIO.read(new File("E:/CaptchaImages/captcha_image" + i + ".png"));

				String result = "";
				BufferedImage image = captchaIimage;
				BufferedImage clean = cleanImage(image);
				ImageIO.write(clean, "png", new File("E:/CaptchaImages/cleaned/captcha_image" + i + ".png"));

				Tesseract tesseract = new Tesseract();
				tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");

				result = tesseract.doOCR(clean);
				result = cleanResult(result);

				System.out.println("captcha"+i+" : " + result);
			} catch (IOException | TesseractException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}