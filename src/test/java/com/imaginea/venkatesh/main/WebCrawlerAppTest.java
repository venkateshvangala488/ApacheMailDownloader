package com.imaginea.venkatesh.main;

import org.junit.BeforeClass;

import com.imaginea.venkatesh.apachecrawler.ApacheMailDownloader;

public class WebCrawlerAppTest {
	@SuppressWarnings("unused")
	private static ApacheMailDownloader downloader;

	@BeforeClass
	public static void initialize(){
		downloader = new ApacheMailDownloader();
	}
	
//	@Test
//	public static void main(String args[]){
//		downloader.download("2014", "test");
//	}
}
