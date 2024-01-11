package de.nandi.adventofcode.day.ten;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class First { // started 17:10

	public static void main(String[] args) {
		int sum = 0;
		ArrayList<char[]> list = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/ten/input.txt"))) {
			while (scanner.hasNextLine()) {
				list.add(scanner.nextLine().toCharArray());
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		char[][] map = list.toArray(char[][]::new);
		int x = 0;
		int y = 0;
		for (int sy = 0; sy < map.length; sy++)
			for (int sx = 0; sx < map[sy].length; sx++)
				if (map[sy][sx] == 'S') {
					x = sx;
					y = sy;
				}
		int lastPlace = 0; // 1 = nach oben gegangen 2 = nach rechts gegangen ...
		char lastSymbol = 'S'; // 1 = nach oben gegangen 2 = nach rechts gegangen ...
		ArrayList<Cord> cords = new ArrayList<>();
		while (true) {
			sum++;
			if (y - 1 >= 0 && lastPlace != 3 && oneEquals(lastSymbol, '|', 'L', 'J', 'S') &&
					oneEquals(map[y - 1][x], '|', '7', 'F', 'S')) {
				if (map[y - 1][x] == 'S')
					break;
				y--;
				lastPlace = 1;
			} else if (x - 1 >= 0 && lastPlace != 4 && oneEquals(lastSymbol, '-', 'J', '7', 'S') &&
					oneEquals(map[y][x - 1], '-', 'L', 'F', 'S')) {
				if (map[y][x - 1] == 'S')
					break;
				x--;
				lastPlace = 2;
			} else if (y + 1 < map.length && lastPlace != 1 && oneEquals(lastSymbol, '|', 'F', '7', 'S') &&
					oneEquals(map[y + 1][x], '|', 'J', 'L', 'S')) {
				if (map[y + 1][x] == 'S')
					break;
				y++;
				lastPlace = 3;
			} else if (x + 1 < map[y].length && lastPlace != 2 && oneEquals(lastSymbol, '-', 'L', 'F', 'S') &&
					oneEquals(map[y][x + 1], '-', 'J', '7', 'S')) {
				if (map[y][x + 1] == 'S')
					break;
				x++;
				lastPlace = 4;
			}
			lastSymbol = map[y][x];
//			cords.add(new Cord(x, y));
//			print(map, cords);
		}
		System.out.println((double) sum / 2D);
	}

	public static boolean oneEquals(char first, char... tests) {
		for (char c : tests)
			if (first == c)
				return true;
		return false;
	}

	public static void print(char[][] map, ArrayList<Cord> cords) {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				if (cords.contains(new Cord(x, y)))
					sb.append("\u001B[32m");
				sb.append(map[y][x]);
				sb.append("\u001B[37m");
			}
			sb.append("\n");
		}
		sb.append("\n\n");
		System.out.println(sb);
	}

}

record Cord(int x, int y) {
}
