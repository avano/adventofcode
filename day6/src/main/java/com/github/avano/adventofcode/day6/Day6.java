package com.github.avano.adventofcode.day6;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Day 6.
 * Created by avano on 24.12.15.
 */
public class Day6 {
	private static boolean[][] array = new boolean[1000][1000];
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get(Day6.class.getResource("/input.txt").toURI()), Charset.forName("UTF-8"));

		for (String line : lines) {
			String[] content = line.split(" ");
			if ("toggle".equals(content[0])) {
				toggle(parseCoords(line));
			} else {
				boolean value = false;
				if ("on".equals(content[1])) {
					value = true;
				}
				set(parseCoords(line), value);
			}
		}

		System.out.println(countResult());
	}

	/**
	 * Parse the coordinates from the line.
	 * @param line line
	 * @return int array of coordinates
	 */
	private static int[] parseCoords(String line) {
		int[] coords = new int[4];

		String[] from, to;
		String[] content = line.split(" ");
		if ("toggle".equals(content[0])) {
			from = content[1].split(",");
			to = content[3].split(",");
		} else {
			from = content[2].split(",");
			to = content[4].split(",");
		}

		coords[0] = Integer.parseInt(from[0]);
		coords[1] = Integer.parseInt(from[1]);
		coords[2] = Integer.parseInt(to[0]);
		coords[3] = Integer.parseInt(to[1]);

		return coords;
	}

	/**
	 * Toggle the lights.
	 * @param coords coordinates array
	 */
	private static void toggle(int[] coords) {
		for (int i = coords[0]; i <= coords[2]; i++) {
			for (int j = coords[1]; j <= coords[3]; j++) {
				array[i][j] = !array[i][j];
			}
		}
	}

	/**
	 * Turn on/off the lights.
	 * @param coords coordinates
	 * @param value turn on/off flag
	 */
	private static void set(int[] coords, boolean value) {
		for (int i = coords[0]; i <= coords[2]; i++) {
			for (int j = coords[1]; j <= coords[3]; j++) {
				array[i][j] = value;
			}
		}
	}

	/**
	 * Count the result.
	 * @return result
	 */
	private static int countResult() {
		int result = 0;

		for (int i = 0; i <= 999; i++) {
			for (int j = 0; j <= 999; j++) {
				result += array[i][j] ? 1 : 0;
			}
		}

		return result;
	}
}
