package de.nandi.adventofcode.day.eight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class First {

	public static void main(String[] args) {
		int sum = 0;

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/eight/input.txt"))) {
			String way = scanner.nextLine();
			HashMap<String, String[]> maps = new HashMap<>();
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				maps.put(s.split("=")[0].trim(), Arrays.stream(s.substring(7, 15).split(","))
						.map(String::trim).toArray(String[]::new));
			}
			String cords = "AAA";
			int index = 0;
			while (!cords.equals("ZZZ")) {
				if (way.length() == index)
					index = 0;
				cords = maps.get(cords)[way.charAt(index) == 'L' ? 0 : 1];
				index++;
				sum++;
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sum);
	}

}
