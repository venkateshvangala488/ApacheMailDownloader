package com.imaginea.venkatesh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.imaginea.venkatesh.apachecrawler.ApacheMailCrawler;
import com.imaginea.venkatesh.utils.WebCrawlHelper;
import com.imaginea.venkatesh.webcrawler.Constants;

public class WebCrawlerService {
	public static final Logger LOGGER = Logger.getLogger(WebCrawlerService.class);

	public void startCrawling(String year) {
		final String HREF_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*" + year + "\\d{2}.mbox/date\")|'[^']*" + year + "\\d{2}.mbox/date')";
		ApacheMailCrawler apacheMailCrawler = new ApacheMailCrawler();
		
		Pattern patternTag = Pattern.compile(Constants.TAG_PATTERN);
		Pattern patternLink = Pattern.compile(HREF_PATTERN);
		Matcher matcherTag = patternTag.matcher(WebCrawlHelper.getHtmlContent(Constants.WEB_URL_PATH));
		List<String> links = new ArrayList<String>();
		
		while (matcherTag.find()) {
			String href = matcherTag.group(1); // get the values of href
			Matcher matcherLink = patternLink.matcher(href);
			while (matcherLink.find()) {
				String link = matcherLink.group(1);
				links.add(WebCrawlHelper.replaceInvalidChar(link));
			}
		}
		for (String link : links) {
			apacheMailCrawler.crawlMailBoxLinks(link, year);
		}
	}
}
