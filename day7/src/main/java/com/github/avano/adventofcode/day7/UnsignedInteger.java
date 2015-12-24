package com.github.avano.adventofcode.day7;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing 16bit unsigned int.
 * Created by avano on 24.12.15.
 */
public class UnsignedInteger {
	@Getter
	private int number;

	public UnsignedInteger(int number) {
		if (number < 0) {
			this.number = 65536 + number;
		} else if (number > 65536){
			this.number = number - 65536;
		} else {
			this.number = number;
		}
	}
}
