package com.imaginea.venkatesh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Logger;

public class WebCrawlHelper {

	private static Logger logger = Logger.getLogger(WebCrawlHelper.class);
	/**
	 * @param urlLink
	 * @return the html text content from Given Url
	 * @throws IOException
	 */
	public String getHtmlContent(String urlLink) throws IOException {
		StringBuffer htmlBody = new StringBuffer();
		try {
			URL url = new URL(urlLink);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferStream = new BufferedReader(new InputStreamReader(inputStream));
			String text;
			while ((text = bufferStream.readLine()) != null) {
				htmlBody.append(text + "\n");
			}
			inputStream.close();
			bufferStream.close();
		} catch (IOException ie) {
			logger.info(ie);
			throw ie;
		}
		return htmlBody.toString();
	}

	/**
	 * @param propertyFileName
	 * @return Property Object
	 * @throws IOException
	 */
	public Properties getApplicationProperties(String propertyFileName) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		properties.load(inputStream);
		return properties;
	}
}
