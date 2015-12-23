package com.github.avano.adventofcode.day1;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

/**
 * Day 1.
 * Created by avano on 23.12.15.
 */
public class Day1 {
	public static void main(String[] args) throws Exception {
		String input;
		try (InputStream in = Day1.class.getResourceAsStream("/input.txt")) {
			input = IOUtils.toString(in);
		}

		int result = 0;

		for (char c : input.toCharArray()) {
			result += '(' == c ? 1 : -1;
		}

		System.out.println(result);
	}
}
