package com.imaginea.venkatesh.utils;

import com.imaginea.venkatesh.webcrawlerconstants.Constants;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

public class WebCrawlHelperTest {
	static WebCrawlHelper webCrawlHelper;

	@BeforeClass
	public static void initialize() {
		webCrawlHelper = new WebCrawlHelper();
	}

	@Test(expected = IOException.class)
	public void getHtmlContentTestWithException() throws IOException {
		String urlLink = "http://mail-archives.apache.org/mod_mbox/maven-userafaf%20fafafas";
		assertNotEquals(urlLink, Constants.WEB_URL_PATH);
		webCrawlHelper.getHtmlContent(urlLink);
	}

	@Test
	public void getHtmlContentTest() throws IOException {
		String urlLink = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		assertEquals(urlLink, Constants.WEB_URL_PATH);
		String htmlContent = webCrawlHelper.getHtmlContent(urlLink);
		assertNotNull(htmlContent);
	}
}
