package de.nandi.adventofcode.day.six;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class First {

	public static void main(String[] args) {
		new First();
	}

	First() {
		int sum = 1;

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/six/input.txt"))) {
			int[] times = Arrays.stream(scanner.nextLine().split(":")[1].split(" ")).filter(s -> !s.isEmpty())
					.mapToInt(Integer::parseInt).toArray();
			int[] distance = Arrays.stream(scanner.nextLine().split(":")[1].split(" ")).filter(s -> !s.isEmpty())
					.mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < times.length; i++) {
				int maxTime = times[i];
				int minDistance = distance[i];
				int minHolding = -1;
				int time = 1;
				while (minHolding == -1) {
					if ((maxTime - time) * time > minDistance)
						minHolding = time;
					time++;
				}
				int maxHolding = -1;
				time = maxTime;
				while (maxHolding == -1) {
					if ((maxTime - time) * time > minDistance)
						maxHolding = time;
					time--;
				}
				sum *= maxHolding - minHolding + 1;
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sum);
	}

}
