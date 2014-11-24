package com.imaginea.venkatesh.apachecrawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.imaginea.venkatesh.service.WebCrawlerService;
import com.imaginea.venkatesh.utils.WebCrawlHelper;

/**
 * @author venkateshv
 * Get All the Mail Box inner links, extract them and write content into 
 * file as month wise
 *
 */
public class ApacheMailCrawler {
	public static final Logger LOGGER = Logger.getLogger(WebCrawlerService.class);

	public void crawlMailBoxLinks(String link, String year) {
		try {
			Document doc = Jsoup.connect(link).get();
			Elements elements = doc.select("td.subject");
			String MM = WebCrawlHelper.getMonth(link, year);
			int counter = 1;
			for (Element element : elements) {
				writeContentToFile(link + element.children().attr("href"), MM, counter);
				counter++;
			}
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

	private void writeContentToFile(String urlLink, String month, int counter) {
		File mkDir = new File("MailArchives");
		if (!mkDir.exists()) {
			mkDir.mkdir();
		}
		String filepath = "MailArchives/" + month + "_" + counter + ".txt";
		try {
			LOGGER.info("url is.." + urlLink);
			Document htmlBody = Jsoup.connect(urlLink).get();
			File file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferReader = new BufferedWriter(fileWriter);
			bufferReader.write(Jsoup.parse(htmlBody.toString()).text());
			bufferReader.flush();
			bufferReader.close();
		} catch (IOException e) {
			LOGGER.info(e);
		}
	}
}
