package com.imaginea.venkatesh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

public class WebCrawlHelper {
	private static Logger logger = Logger.getLogger(WebCrawlHelper.class);

	/**
	 * @param urlLink
	 * @return the html text content from Given Url
	 */
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

}
