package de.nandi.adventofcode.day.nine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class First { // Teil 1 in 21 minuten geschafft;

	public static void main(String[] args) {
		int sum = 0;

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/nine/input.txt"))) {
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				ArrayList<ArrayList<Integer>> allDifferences = new ArrayList<>();
				boolean finished = false;
				ArrayList<Integer> numbers = new ArrayList<>(Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).boxed().toList());
				while (!finished) {
					ArrayList<Integer> difference = new ArrayList<>();
					int lastNumber = numbers.get(0);
					for (int i = 1; i < numbers.size(); i++) {
						int number = numbers.get(i);
						difference.add(number - lastNumber);
						lastNumber = number;
					}
					allDifferences.add(numbers);
					if (difference.stream().allMatch(i -> i == 0))
						finished = true;
					numbers = difference;
				}
				ArrayList<Integer> lastDifferences = numbers;
				for (int i = allDifferences.size() - 1; i >= 0; i--) {
					ArrayList<Integer> differences = allDifferences.get(i);
					differences.add(differences.get(differences.size() - 1) + lastDifferences.get(lastDifferences.size() - 1));
					lastDifferences = differences;
				}
				sum += lastDifferences.get(lastDifferences.size() - 1);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sum);
	}

}
