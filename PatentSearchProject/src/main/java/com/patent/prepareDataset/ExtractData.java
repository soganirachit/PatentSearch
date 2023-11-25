package com.patent.prepareDataset;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.patent.db.SaveDataToDB;
import com.patent.model.PatentDetails;
import com.patent.util.ReadWriteXls;

public class ExtractData {

	private static String url = "https://iprsearch.ipindia.gov.in/PublicSearch/";
	private static Map<String, String> status = new HashMap<>();

	public static void main(String[] args) {
		ReadWriteXls readWriteXls = new ReadWriteXls();
		try {
			List<String> applNums = readWriteXls.getapplNums("C:\\Users\\rachit sogani\\Downloads\\AplNumFile.xlsx");
			System.out.println("appNums is : " + applNums.subList(0, 100));
			webPageHit(applNums);

		} catch (IOException | FailingHttpStatusCodeException | OutOfMemoryError | IndexOutOfBoundsException
				| ParseException e) {
			System.out.println(status);
			System.out.println(e.getMessage());
		} finally {
			System.out.println(status);
			boolean fileStatus = readWriteXls.writeExcel(status,
					"C:\\Users\\rachit sogani\\Downloads\\AplNumFileOutput.txt");
			System.out.println("Writing status to excel is : " + fileStatus);
		}

	}

	public static void webPageHit(List<String> applNums) throws FailingHttpStatusCodeException, MalformedURLException,
			IOException, OutOfMemoryError, IndexOutOfBoundsException, ParseException {

		WebClient webClient = new WebClient();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setDownloadImages(true);
		webClient.getOptions().setCssEnabled(false);

		HtmlPage page = webClient.getPage(url);
		HtmlForm form = (HtmlForm) page.getByXPath("//form").get(0);
		HtmlInput searchButton;
		HtmlPage page2;
		HtmlButton ApplicationNum;
		HtmlPage page3;
		HtmlTable table;
		HtmlTextInput titleField;
		Boolean flag = true;
		PatentDetails pd;
		Map<String, PatentDetails> pdMap = new HashMap<>();
		MapRowDataToPojo mapRowDataToPojo = new MapRowDataToPojo();
		SaveDataToDB saveDataToDB = new SaveDataToDB();

		fillCaptcha(form);

//		for (int i = 0; i < applNums.size(); i++) {
		for (int i = 1; i < 2000; i++) {

			pd = new PatentDetails();
			System.out.println("Attempting to fetch data for application number : " + applNums.get(i));

			titleField = form.getInputByName("TextField4");
			if (!titleField.getValue().equalsIgnoreCase(applNums.get(i))) {
				titleField.type(applNums.get(i));
			}

			searchButton = form.getInputByName("submit");
			page2 = searchButton.click();

			flag = page2.asNormalizedText().contains("Invalid captcha");
			System.out.println(
					flag ? ".....................Invalid Captcha tr again" : "......................Captcha passed");

			if (!flag) {
				ApplicationNum = page2.getFirstByXPath("//button[@name='ApplicationNumber']");
				if (ApplicationNum != null && ApplicationNum.isDisplayed()) {

					page3 = ApplicationNum.click();
					table = page3.getFirstByXPath("//*[@id=\"home\"]/table");

					if (table != null) {
						pd = mapRowDataToPojo.mapData(table);
						pdMap.put(applNums.get(i), pd);
					} else {
						status.put(applNums.get(i), "Failure due to table not found");
						System.out.println("Table not found.");
					}

				} else {
					status.put(applNums.get(i), "Failure due to button not found");
					System.out.println("Button not found.");
				}
			}
			titleField.reset();

			if (i % 10 == 0 | (i == 2000 - 1)) {
				System.out.println("Attempting to save 50 records");
				saveDataToDB.saveData(pdMap, status);
				pdMap = new HashMap<>();
				heapMonitor();
			}
		}
		saveDataToDB.commitAndCoseTransaction();
		System.out.println("Status of all ids is : " + status);
		webClient.close();
	}

	public static void fillCaptcha(HtmlForm form) throws IOException {
		HtmlImage imageDetails = (HtmlImage) form.getByXPath("//*[@id=\"Captcha\"]").get(0);
		ImageReader imageReader = imageDetails.getImageReader();
		BufferedImage captchaIimage = imageReader.read(0);
		ImageIO.write(captchaIimage, "png", new File("E:\\CaptchaImages\\NewDownload\\captcha.png"));
		System.out.println("Image downloaded");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Captacha : ");
		String captcha = scanner.nextLine();

		HtmlTextInput abstractField = form.getInputByName("CaptchaText");
		abstractField.type(captcha);
		scanner.close();
	}

	public static void heapMonitor() {
		// Get the MemoryMXBean
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

		// Get the heap memory usage
		MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

		// Print heap memory usage information

		System.out.println("Max Heap Size: " + heapMemoryUsage.getMax() / (1024 * 1024) + " MB");
		System.out.println("Used Heap Size: " + heapMemoryUsage.getUsed() / (1024 * 1024) + " MB");
	}

}
