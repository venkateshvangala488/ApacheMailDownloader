package com.imaginea.venkatesh.utils;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;

import com.imaginea.venkatesh.webcrawler.Constants;

public class WebCrawlHelper {
	public static Logger logger = Logger.getLogger(WebCrawlHelper.class);
	public static String getHtmlContent(String urlLink) {
		StringBuffer htmlBody = new StringBuffer();
		try {
			HttpURLConnection connection = connectUrl(urlLink);
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferStream = new BufferedReader(new InputStreamReader(inputStream));
			String text;
			while ((text = bufferStream.readLine()) != null) {
				htmlBody.append(text + "\n");
			}
			inputStream.close();
			bufferStream.close();
		} catch (MalformedURLException me) {
			logger.error(me);
		} catch (IOException ie) {
			logger.error(ie);
		}
		return htmlBody.toString();
	}

	public static HttpURLConnection connectUrl(String link) throws IOException {
		URL url = new URL(link);
		return (HttpURLConnection) url.openConnection();
	}

	public static String replaceInvalidChar(String linkElement) {
		linkElement = linkElement.replaceAll("'", "");
		linkElement = linkElement.replaceAll("\"", "");
		return Constants.WEB_URL_PATH + linkElement.replace("date", "");
	}

	public static String getMonth(String link, String year) {
		Matcher digit = Constants.DIGIT_PATTERN.matcher(link);
		int month = 0;
		if (digit.find()) {
			month = Integer.parseInt(digit.group().replace(year, ""));
		}
		return Constants.Month.values()[month - 1].toString();
	}
}
