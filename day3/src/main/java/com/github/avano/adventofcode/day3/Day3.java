package com.github.avano.adventofcode.day3;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Day 3.
 * Created by avano on 23.12.15.
 */
public class Day3 {
	private static Map<Point, Boolean> array = new HashMap<>();

	static int x = 0;
	static int y = 0;

	public static void main(String[] args) throws Exception {
		String input;
		try (InputStream in = Day3.class.getResourceAsStream("/input.txt")) {
			input = IOUtils.toString(in);
		}

		// First row - that's where Santa starts
		array.put(new Point(0, 0), true);

		for (char c : input.toCharArray()) {
			switch (c) {
				case '>':
					x++;
					visit();
					break;
				case '<':
					x--;
					visit();
					break;
				case '^':
					y++;
					visit();
					break;
				case 'v':
					y--;
					visit();
					break;
			}
		}

		System.out.println(countVisits());
	}

	/**
	 * Visits current location.
	 */
	private static void visit() {
		array.put(new Point(x, y), true);
	}

	private static int countVisits() {
		return array.keySet().size();
	}
}
