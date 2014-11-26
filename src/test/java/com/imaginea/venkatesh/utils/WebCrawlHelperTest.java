package com.imaginea.venkatesh.utils;

import com.imaginea.venkatesh.webcrawlerconstants.Constants;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.util.Properties;
import org.junit.BeforeClass;
import org.junit.Test;

public class WebCrawlHelperTest {
	
	static WebCrawlHelper webCrawlHelper;
	String urlLink = "http://mail-archives.apache.org/mod_mbox/maven-users/";
	String propertyFileName = "";

	@BeforeClass
	public static void initialize() {
		webCrawlHelper = new WebCrawlHelper();
	}

	@Test(expected = IOException.class)
	public void getHtmlContentFalseTestCaseWithException() throws IOException {
		urlLink = "http://mail-archives.apache.org/mod_mbox/maven-userafaf%20fafafas";
		assertNotEquals(urlLink, Constants.WEB_URL_PATH);
		webCrawlHelper.getHtmlContent(urlLink);
	}

	@Test
	public void getHtmlContentTrueTestCase() throws IOException {
		urlLink = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		assertEquals(urlLink, Constants.WEB_URL_PATH);
		String htmlContent = webCrawlHelper.getHtmlContent(urlLink);
		assertNotNull(htmlContent);
	}

	@Test
	public void getApplicationPropTrueTestCase() throws IOException {
		propertyFileName = "application.properties";
		Properties properties = webCrawlHelper.getApplicationProperties(propertyFileName);
		assertEquals(properties.getProperty("year"), "2014");
		assertEquals(properties.getProperty("rootDir"), "DownloadedMails");
	}

	@Test(expected = Exception.class)
	public void getApplicationPropFalseTestCaseWithInvalidFile() throws IOException {
		propertyFileName = "config.properties";
		webCrawlHelper.getApplicationProperties(propertyFileName);
	}
}
