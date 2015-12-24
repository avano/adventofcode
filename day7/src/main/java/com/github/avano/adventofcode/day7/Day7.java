package com.github.avano.adventofcode.day7;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day 7.
 * Created by avano on 24.12.15.
 */
@SuppressWarnings("ForLoopReplaceableByForEach")
public class Day7 {
	private static Map<String, UnsignedInteger> wireSignal = new HashMap<>();
	private static List<String> lines;

	public static void main(String[] args) throws Exception {
		lines = Files.readAllLines(Paths.get(Day7.class.getResource("/input.txt").toURI()), Charset.forName("UTF-8"));

		for (int i = 0; i < lines.size(); i++) {
			String[] content = lines.get(i).split("->");
			processGate(content[0].trim(), content[1].trim());
		}

		System.out.println(wireSignal.get("a").getNumber());
	}

	/**
	 * Processes the gate.
	 *
	 * @param command command
	 * @param wire output wire
	 */
	private static void processGate(String command, String wire) {
		// If there is no command
		if (!StringUtils.containsAny(command, "AND", "OR", "NOT", "LSHIFT", "RSHIFT")) {
			// If it's just a signal, save it
			if (StringUtils.isNumeric(command)) {
				wireSignal.put(wire, new UnsignedInteger(Integer.parseInt(command)));
				return;
			}

			// If it's not a signal and it is not in the map yet, skip it
			if (!wireSignal.containsKey(command)) {
				skipLine(command + " -> " + wire);
				return;
			}

			// We have the wire already so just save it
			wireSignal.put(wire, new UnsignedInteger(wireSignal.get(command).getNumber()));
			return;
		}

		// If there is a NOT
		if (StringUtils.contains(command, "NOT")) {
			String wireName = StringUtils.substringAfter(command, " ");

			// If it's just a signal, save it
			if (StringUtils.isNumeric(wireName)) {
				wireSignal.put(wire, new UnsignedInteger(~Integer.parseInt(wireName)));
				return;
			}

			// If it's not a signal and it is not in the map yet, skip it
			if (!wireSignal.containsKey(wireName)) {
				skipLine(command + " -> " + wire);
				return;
			}

			// We have the wire already so just save it
			wireSignal.put(wire, new UnsignedInteger(~wireSignal.get(wireName).getNumber()));
			return;
		}

		// Parse the command
		String[] content = command.split(" ");
		String operand1 = content[0];
		String operand2 = content[2];

		switch (content[1]) {
			case "AND":
				process(operand1, "AND", operand2, wire);
				break;
			case "OR":
				process(operand1, "OR", operand2, wire);
				break;
			case "RSHIFT":
				process(operand1, "RSHIFT", operand2, wire);
				break;
			case "LSHIFT":
				process(operand1, "LSHIFT", operand2, wire);
				break;
		}
	}

	/**
	 * Processes the instructions.
	 *
	 * @param o1 first operand
	 * @param op operator
	 * @param o2 second operand
	 * @param wire output wire
	 */
	private static void process(String o1, String op, String o2, String wire) {
		int intO1;
		int intO2;

		if (wireSignal.containsKey(o1) && wireSignal.containsKey(o2)) {
			intO1 = wireSignal.get(o1).getNumber();
			intO2 = wireSignal.get(o2).getNumber();
		} else if (wireSignal.containsKey(o1) && StringUtils.isNumeric(o2)) {
			intO1 = wireSignal.get(o1).getNumber();
			intO2 = Integer.parseInt(o2);
		} else if (wireSignal.containsKey(o2) && StringUtils.isNumeric(o1)) {
			intO1 = Integer.parseInt(o1);
			intO2 = wireSignal.get(o2).getNumber();
		} else {
			skipLine(o1 + " " + op + " " + o2 + " -> " + wire);
			return;
		}

		switch (op) {
			case "AND":
				wireSignal.put(wire, new UnsignedInteger(intO1 & intO2));
				break;
			case "OR":
				wireSignal.put(wire, new UnsignedInteger(intO1 | intO2));
				break;
			case "RSHIFT":
				wireSignal.put(wire, new UnsignedInteger(intO1 >> intO2));
				break;
			case "LSHIFT":
				wireSignal.put(wire, new UnsignedInteger(intO1 << intO2));
				break;
		}
	}

	/**
	 * Skip the line.
	 *
	 * @param line line
	 */
	private static void skipLine(String line) {
		lines.add(line);
	}
}
