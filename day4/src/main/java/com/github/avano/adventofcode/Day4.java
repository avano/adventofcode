package com.github.avano.adventofcode;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * Day 4.
 * Created by avano on 23.12.15.
 */
public class Day4 {
	private static final String INPUT = "bgvyzdsv";

	public static void main(String[] args) throws Exception {
		int i;
		for (i = 0; ; i++) {
			byte[] md5 = MessageDigest.getInstance("MD5").digest((INPUT + i).getBytes());
			String hex = new String(Hex.encodeHex(md5));
			if (hex.startsWith("00000")) {
				break;
			}
		}

		System.out.println(i);
	}
}
