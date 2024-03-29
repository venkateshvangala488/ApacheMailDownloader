package com.imaginea.venkatesh.apachecrawler;

import com.imaginea.venkatesh.utils.WebCrawlHelper;
import com.imaginea.venkatesh.webcrawlerconstants.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author venkateshv ApacheMailDownloader download the mails from Apache Mail
 *         Server for the given year to the given directory
 *
 */
public class ApacheMailDownloader {

	private static final Logger LOGGER = Logger.getLogger(ApacheMailDownloader.class);

	/**
	 * @param downloadYear
	 * @param dirName
	 *            Download the mails to given Directory Name
	 * @throws IOException
	 */
	public void download(String year, String rootDir) throws IOException {

		WebCrawlHelper webCrawlHelper = new WebCrawlHelper();
		final String HREF_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*" + year + "\\d{2}.mbox/date\")|'[^']*" + year
				+ "\\d{2}.mbox/date')";

		Pattern patternTag = Pattern.compile(Constants.TAG_PATTERN);
		Pattern patternLink = Pattern.compile(HREF_PATTERN);
		Matcher matcherTag = patternTag.matcher(webCrawlHelper.getHtmlContent(Constants.WEB_URL_PATH));
		List<String> links = new ArrayList<String>();

		while (matcherTag.find()) {
			String href = matcherTag.group(1); // get the values of href
			Matcher matcherLink = patternLink.matcher(href);
			while (matcherLink.find()) {
				String link = matcherLink.group(1);
				link = link.replaceAll("'", "").replaceAll("\"", "");
				links.add(Constants.WEB_URL_PATH + link.replace("date", ""));
			}
		}
		for (String link : links) {
			crawlMailBoxLinks(link, year, rootDir);
		}
	}

	private void crawlMailBoxLinks(String link, String year, String rootDir) throws IOException {
		try {
			Document doc = Jsoup.connect(link).get();
			Elements elements = doc.select("td.subject");
			String MM = getMonth(link, year);
			int counter = 1;
			for (Element element : elements) {
				writeContentToFile(link + element.children().attr("href"), MM, counter, rootDir);
				counter++;
			}
		} catch (IOException e) {
			LOGGER.error(e);
			throw e;
		}
	}

	private String getMonth(String link, String year) {
		Matcher digit = Constants.DIGIT_PATTERN.matcher(link);
		int month = 0;
		if (digit.find()) {
			month = Integer.parseInt(digit.group().replace(year, ""));
		}
		return Constants.Month.values()[month - 1].toString();
	}

	private void writeContentToFile(String urlLink, String month, int counter, String rootDir) throws IOException {
		File makeDir = new File(rootDir);
		if (!makeDir.exists()) {
			makeDir.mkdir();
		}
		String filepath = rootDir + "/" + month + "_" + counter + ".txt";
		try {
			LOGGER.info("url is " + urlLink);
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
			throw e;
		}
	}
}
