package com.imaginea.venkatesh.apachecrawler;

import com.imaginea.venkatesh.utils.WebCrawlHelper;
import java.io.IOException;
import java.util.Properties;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApacheMailDownloaderTest {

	private static ApacheMailDownloader apacheMailDownloader;
	private static WebCrawlHelper helper;

	@BeforeClass
	public static void initialize() {
		apacheMailDownloader = new ApacheMailDownloader();
		helper = new WebCrawlHelper();
	}

	@Test(expected = IOException.class)
	public void downloadFalseTestCaseWithInvalidDirectory() throws IOException {
		Properties properties = helper.getApplicationProperties("application.properties");
		apacheMailDownloader.download(properties.getProperty("year"), "Imaginea/venkatesh/Kumar");
	}
}
