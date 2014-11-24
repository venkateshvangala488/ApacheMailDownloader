package com.imaginea.venkatesh.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.imaginea.venkatesh.apachecrawler.ApacheMailDownloader;


public class WebCrawlerApp {
	public static void main(String args[]) {
		final Logger LOGGER = Logger.getLogger(WebCrawlerApp.class);
		LOGGER.info("Main App Intialised....");
		Scanner scanner = new Scanner(System.in);
		LOGGER.info("Please Enter Year.");
		String year = scanner.next();
		LOGGER.info("Please Enter Folder Name.");
		String folderName = scanner.next();
		ApacheMailDownloader downloader = new ApacheMailDownloader();
		downloader.StartDownload(year, folderName);
	}
}
