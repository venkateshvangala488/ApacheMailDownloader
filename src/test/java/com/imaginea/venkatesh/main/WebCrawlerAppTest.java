package com.imaginea.venkatesh.main;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

public class WebCrawlerAppTest {
	private static WebCrawlerApp webCrawlerApp;

	@BeforeClass
	public static void initialize() {
		webCrawlerApp = new WebCrawlerApp();
	}

	@SuppressWarnings("static-access")
	@Test
	public void mainTrueTestCase() throws IOException {
		String[] args = null;
		webCrawlerApp.main(args);
	}
}
