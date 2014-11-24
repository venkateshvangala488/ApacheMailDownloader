package com.imaginea.venkatesh.webcrawler;

import java.util.regex.Pattern;

public class Constants {
	public static final String WEB_URL_PATH = "http://mail-archives.apache.org/mod_mbox/maven-users/";
	public static final String TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	
	public static final Pattern DIGIT_PATTERN = Pattern.compile("(\\d+)");
	public static enum Month {
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	}
}
