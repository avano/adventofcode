package com.github.avano.adventofcode;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day 5.
 * Created by avano on 23.12.15.
 */
public class Day5 {
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get(Day5.class.getResource("/input.txt").toURI()), Charset.forName("UTF-8"));

		int result = 0;

		for (String line : lines) {
			if (containsAdjacent(line) || !containsDuplicateLetters(line) || !containsThreeVowels(line)) {
				// Skip
				continue;
			}
			result++;
		}

		System.out.println(result);
	}

	/**
	 * Checks if the string contains adjacent letters.
	 *
	 * @param string string
	 * @return true/false
	 */
	private static boolean containsAdjacent(String string) {
		return StringUtils.containsAny(string, "ab", "cd", "pq", "xy");
	}

	/**
	 * Checks if the string contains three vowels.
	 *
	 * @param string string
	 * @return true/false
	 */
	private static boolean containsThreeVowels(String string) {
		Map<Character, Integer> vowelsCount = new HashMap<Character, Integer>() {{
			put('a', 0);
			put('e', 0);
			put('i', 0);
			put('o', 0);
			put('u', 0);
		}};

		for (char c : string.toCharArray()) {
			if (vowelsCount.keySet().contains(c)) {
				vowelsCount.put(c, vowelsCount.get(c) + 1);
			}
		}

		int sum = 0;
		for (Map.Entry<Character, Integer> entry : vowelsCount.entrySet()) {
			sum += entry.getValue();
		}
		return sum >= 3;
	}

	/**
	 * Checks if the string contains duplicate letters.
	 *
	 * @param string string
	 * @return true/false
	 */
	private static boolean containsDuplicateLetters(String string) {
		for (int i = 0; i < string.length() - 1; i++) {
			if (string.charAt(i) == string.charAt(i + 1)) {
				return true;
			}
		}
		return false;
	}
}
