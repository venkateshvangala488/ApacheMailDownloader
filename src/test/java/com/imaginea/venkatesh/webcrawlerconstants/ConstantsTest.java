package com.imaginea.venkatesh.webcrawlerconstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.regex.Pattern;
import org.junit.Test;

public class ConstantsTest {
	
	public static String WEB_URL_PATH = "http://mail-archives.apache.org/mod_mbox/maven-users/";
	public static String TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";

	public static enum Month {
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	}

	@Test
	public void ConstanValuesTrueTestCase() {
		assertEquals(WEB_URL_PATH, Constants.WEB_URL_PATH);
		assertEquals(TAG_PATTERN, Constants.TAG_PATTERN);
		assertTrue(Constants.DIGIT_PATTERN instanceof Pattern);
		assertTrue(Constants.Month.values().length == Month.values().length);
	}

	@Test
	public void ConstanValuesFalseTestCaseWithInvalidURLPath() {
		WEB_URL_PATH = "http://mail-archives.apache.org/mod_mbox";
		TAG_PATTERN = "(?i)<a([^>]+)";
		assertNotEquals(WEB_URL_PATH, Constants.WEB_URL_PATH);
		assertNotEquals(TAG_PATTERN, Constants.TAG_PATTERN);
		assertFalse(Constants.Month.values().length != Month.values().length);
	}

}
