package de.nandi.adventofcode.day.one;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class First {

	public static void main(String[] args) {
		int sum = 0;

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/one/input.txt"))) {
			while (scanner.hasNext()) {
				String doubleDigit = "";
				String s = scanner.next();
				for (char c : s.toCharArray())
					try {
						int value = Integer.parseInt(String.valueOf(c));
						doubleDigit += value;
						break;
					} catch (NumberFormatException ignored) {}
				for (char c : new StringBuilder(s).reverse().toString().toCharArray())
					try {
						int value = Integer.parseInt(String.valueOf(c));
						doubleDigit += value;
						break;
					} catch (NumberFormatException ignored) {}
				sum += Integer.parseInt(doubleDigit);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sum);
	}

}
