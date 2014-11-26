package com.imaginea.venkatesh.main;

import com.imaginea.venkatesh.apachecrawler.ApacheMailDownloader;
import com.imaginea.venkatesh.utils.WebCrawlHelper;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class WebCrawlerApp {
	public static void main(String args[]) throws IOException {
		final Logger LOGGER = Logger.getLogger(WebCrawlerApp.class);
		LOGGER.info("WebCrawler Application Intialised....");
		WebCrawlHelper helper = new WebCrawlHelper();
		Properties properties = helper.getApplicationProperties("application.properties");
		ApacheMailDownloader downloader = new ApacheMailDownloader();
		downloader.download(properties.getProperty("year"), properties.getProperty("rootDir"));
	}
}
