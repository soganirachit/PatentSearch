package com.test.prepareDataset;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class ExtractData {

	public static void main(String[] args) {

		webPageHit();
	}

	public static void webPageHit() {
		String url = "https://iprsearch.ipindia.gov.in/PublicSearch/";
		MapRowDataToPojo mapRowDataToPojo = new MapRowDataToPojo();
		Boolean flag = true;
		int counter = 0;

		try (WebClient webClient = new WebClient()) {

			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setDownloadImages(true);
			webClient.getOptions().setCssEnabled(false);

// this will be removed later
			HtmlPage page = webClient.getPage(url);
			HtmlForm form = (HtmlForm) page.getByXPath("//form").get(0);

			HtmlImage imageDetails = (HtmlImage) form.getByXPath("//*[@id=\"Captcha\"]").get(0);
			ImageReader imageReader = imageDetails.getImageReader();
			BufferedImage captchaIimage = imageReader.read(0);
			ImageIO.write(captchaIimage, "png", new File("E:\\CaptchaImages\\NewDownload\\captcha.png"));
			System.out.println("Image downloaded");

//			String captcha = cleanImage.getCaptcha(imageDetails);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Captacha : ");
			String captcha = scanner.nextLine();

			HtmlTextInput abstractField = form.getInputByName("CaptchaText");
			abstractField.type(captcha);

			String[] appNums = { "10044/DELNP/2015", "10275/CHENP/2013", "1050/DEL/2011" };

//			while (flag & counter < 100) {
			for (String appNum : appNums) {
				System.out.println(counter + " attempt to hit the web page.....");

				// paste here again

				// String appNum = "10275/CHENP/2013";
				HtmlTextInput titleField = form.getInputByName("TextField4");
				if (!titleField.getValue().equalsIgnoreCase(appNum)) {
					titleField.type(appNum);
				}

//				HtmlTextInput abstractField = form.getInputByName("CaptchaText");
//				if(captcha.length()==6) {
//					abstractField.type(captcha);
//				}else {
//					System.out.println(".....................invalid Captcha");
//					counter++;
//					continue;
//				}

				HtmlInput searchButton = form.getInputByName("submit");
				HtmlPage page2 = searchButton.click();

				flag = page2.asNormalizedText().contains("Invalid captcha");
				counter++;
				System.out.println(flag ? ".....................failed" : "......................passed");

				// No cheda chadi for above

				if (!flag) {
					HtmlButton ApplicationNum = page2.getFirstByXPath("//button[@name='ApplicationNumber']");
					if (ApplicationNum != null && ApplicationNum.isDisplayed()) {
						HtmlPage page3 = ApplicationNum.click();
						System.out.println("Button clicked successfully.");

						HtmlTable table = page3.getFirstByXPath("//*[@id=\"home\"]/table");

						if (table != null) {
							mapRowDataToPojo.mapData(table);
						} else {
							System.out.println("Table not found.");
						}

					} else {
						System.out.println("Button not found.");
					}
				}
				titleField.reset();
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
