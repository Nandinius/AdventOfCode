package de.nandi.adventofcode.day.four;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class First {

	public static void main(String[] args) {
		int sum = 0;

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/four/input.txt"))) {
			while (scanner.hasNextLine()) {
				String input = scanner.nextLine();
				String[] numbers = input.split(":")[1].split("\\|");
				String[] available = numbers[1].split(" ");
				long winning = Arrays.stream(numbers[0].split(" ")).filter(s1 -> !s1.isEmpty())//winners
						.filter(s1 -> Arrays.asList(available).contains(s1)).count();
				if (winning != 0)
					sum += Math.pow(2, winning - 1);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sum);
	}

}
