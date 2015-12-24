package com.github.avano.adventofcode.day8;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Day 8.
 * Created by avano on 24.12.15.
 */
public class Day8 {
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get(Day8.class.getResource("/input.txt").toURI()), Charset.forName("UTF-8"));
		int result = 0;
		StringBuilder builder = new StringBuilder();
		for (String line : lines) {
			for (int i = 1; i < line.length() - 1; i++) {
				char current = line.charAt(i);
				char next = line.charAt(i + 1);

				// if it is not \ just append
				if (current != '\\') {
					builder.append(current);
					continue;
				}

				switch (next) {
					case '\\':
						builder.append("\\");
						// Skip the next char
						i++;
						break;
					case '\"':
						builder.append("\"");
						// Skip the next char
						i++;
						break;
					case 'x':
						builder.append(new String(Hex.decodeHex(StringUtils.substring(line, i + 2, i + 4).toCharArray())));
						// Skip the hex chars
						i += 3;
						break;
				}
			}

			result += (line.length() - builder.toString().length());
			builder = new StringBuilder();
		}

		System.out.println(result);
	}
}
