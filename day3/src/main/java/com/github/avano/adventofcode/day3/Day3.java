package com.github.avano.adventofcode.day3;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Day 3.
 * Created by avano on 23.12.15.
 */
public class Day3 {
	private static Set<Point> visits = new HashSet<>();

	static int x = 0;
	static int y = 0;

	public static void main(String[] args) throws Exception {
		String input;
		try (InputStream in = Day3.class.getResourceAsStream("/input.txt")) {
			input = IOUtils.toString(in);
		}

		// First row - that's where Santa starts
		visits.add(new Point(0, 0));

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
		visits.add(new Point(x, y));
	}

	/**
	 * Counts the total visits.
	 * @return visits count
	 */
	private static int countVisits() {
		return visits.size();
	}
}
