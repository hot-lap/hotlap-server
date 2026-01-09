package com.oops.common.util;

public final class StringUtil {

	private StringUtil() {
	}

	public static boolean isNotBlank(String value) {
		return value != null && !value.isBlank();
	}

	public static String nullIfBlank(String value) {
		return isNotBlank(value) ? value : null;
	}
}
