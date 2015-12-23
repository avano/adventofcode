package com.github.avano.adventofcode.day2;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Day 2.
 * Created by avano on 23.12.15.
 */
public class Day2 {
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get(Day2.class.getResource("/input.txt").toURI()), Charset.forName("UTF-8"));

		int l, w, h, result = 0;
		String[] dimensions;
		for (String line : lines) {
			dimensions = line.split("x");
			l = Integer.parseInt(dimensions[0]);
			w = Integer.parseInt(dimensions[1]);
			h = Integer.parseInt(dimensions[2]);
			result += calculate(l, w, h);
		}

		System.out.println(result);
	}

	private static int calculate(int l, int w, int h) {
		int result = 0;
		int dim1, dim2, dim3, smallest;

		dim1 = 2 * l * w;
		dim2 = 2 * w * h;
		dim3 = 2 * h * l;

		result += (dim1 + dim2 + dim3);
		smallest = dim1 >= dim2 ? (dim2 >= dim3 ? dim3 : dim2) : (dim1 >= dim3 ? dim3 : dim1);
		result += (smallest / 2);
		return result;
	}
}
