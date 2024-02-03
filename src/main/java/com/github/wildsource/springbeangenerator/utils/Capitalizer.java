package com.github.wildsource.springbeangenerator.utils;

public class Capitalizer {
	public static String capitalizeString(String word) {
		return word	.substring(0, 1)
					.toUpperCase()
				+ word.substring(1);
	}
}
