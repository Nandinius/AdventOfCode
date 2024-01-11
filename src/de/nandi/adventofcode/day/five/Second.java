package de.nandi.adventofcode.day.five;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class First {

	public static void main(String[] args) {
		new First();
	}

	First() {
		long[] seeds;


		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/five/input.txt"))) {
			seeds = Arrays.stream(scanner.nextLine().split(":")[1].split(" ")).filter(s -> !s.isEmpty()).mapToLong(Long::parseLong).toArray();
			ArrayList<Long> list = new ArrayList<>();
			int length = 0;
			for (int i = 1; i < seeds.length; i += 2) {
				length += seeds[i];
			}
			long[] tempSeeds = new long[length];
			int index = 0;
			for (int i = 0; i < seeds.length; i += 2) {
				long k = seeds[i];
				for (long j = 0; j < seeds[i + 1]; j++) {
					tempSeeds[index] = k + j;
					index++;
				}
			}
			seeds = tempSeeds;

			ArrayList<long[]> maps = new ArrayList<>();
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				if (s.isEmpty()) {
					for (int i = 0; i < seeds.length; i++) {
						long seed = seeds[i];
						for (long[] map : maps) {
							long sourceRangeStart = map[1];
							long targetRangeStart = map[0];
							if (seed >= sourceRangeStart && seed < sourceRangeStart + map[2]) {
								seed = targetRangeStart + (seed - sourceRangeStart);
								break;
							}
						}
						seeds[i] = seed;
					}
					continue;
				}
				if (s.contains(":")) {
					maps = new ArrayList<>();
					continue;
				}
				maps.add(Arrays.stream(s.split(" ")).mapToLong(Long::parseLong).toArray());


			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(Arrays.stream(seeds).min().getAsLong());
	}

}
