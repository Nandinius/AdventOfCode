package de.nandi.adventofcode.day.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class First {

	public static void main(String[] args) {
		int sum = 0;

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/two/input.txt"))) {
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				int id = Integer.parseInt(s.split(":")[0].substring(5));
				boolean possible = true;
				whole:
				for (String game : s.split(":")[1].split(";"))
					for (String colour : game.split(",")) {
						int nr = Integer.parseInt(colour.split(" ")[1]);
						switch (colour.split(" ")[2]) {
							case "red" -> {
								if (nr > 12) {
									possible = false;
									break whole;
								}
							}
							case "green" -> {
								if (nr > 13) {
									possible = false;
									break whole;
								}
							}
							case "blue" -> {
								if (nr > 14) {
									possible = false;
									break whole;
								}
							}
						}
					}
				if (possible)
					sum += id;
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sum);
	}

}
