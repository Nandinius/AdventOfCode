package de.nandi.adventofcode.day.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

public class First {
	Predicate<String> isNumber = s12 -> {
		try {
			Integer.valueOf(s12);
			return true;
		} catch (NumberFormatException notANumber) {
			return false;
		}
	};

	public static void main(String[] args) {
		new First();
	}

	public First() {
		int sum = 0;

		List<Coordinate> cords = new ArrayList<>();
		List<Coordinate> symbolsCords = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/three/input.txt"))) {
			int line = 0;
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				line++;
				String[] allThings = getAllThings(s);

				String[] numbers = Arrays.stream(allThings).filter(isNumber).toArray(String[]::new);
				String[] symbols = Arrays.stream(allThings).filter(isNumber.negate()).toArray(String[]::new);
				int lastIndex = 0;
				for (String number : numbers) {
					int index = s.indexOf(number, lastIndex);
					lastIndex = index + number.length();
					cords.add(new Coordinate(index, line, number.length(), number));
				}
				lastIndex = 0;
				for (String symbol : symbols) {
					int index = s.indexOf(symbol, lastIndex);
					lastIndex = index + symbol.length();
					symbolsCords.add(new Coordinate(index, line, symbol.length(), symbol, true));
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		for (Coordinate nr : cords) {
			for (Coordinate symbol : symbolsCords) {
				if (nr.isAdjacent(symbol)) {
					sum += nr.getNr();
					break;
				}
			}
		}
		List<Coordinate> cordsAll = new ArrayList<>(cords);
		cordsAll.addAll(symbolsCords);

		Comparator<Coordinate> comparator = (o1, o2) -> {//- ist nach vorne
			if (o1.y < o2.y)
				return -1;
			else if (o1.y == o2.y)
				return Integer.compare(o1.x, o2.x);
			return 1;
		};
		cordsAll = cordsAll.stream().sorted(comparator).toList();
		StringBuilder sb = new StringBuilder();
		int line = 1;
		int x = 0;
		for (Coordinate cord : cordsAll) {
			while (cord.y > line) {
				if (140 - x != 0)
					sb.append(String.format("%" + (140 - x) + "s", "").replace(' ', '.'));
				sb.append("\n");
				x = 0;
				line++;
			}
			if (cord.x - x != 0)
				sb.append(String.format("%" + (cord.x - x) + "s", "").replace(' ', '.'));
			x = cord.x + cord.length;
			if (cord.isAdjacent) {
				sb.append("\u001B[32m");
				sb.append(cord.nr);
				sb.append("\u001B[0m");
			} else if (cord.isSymbol) {
				sb.append("\u001B[33m");
				sb.append(cord.nr);
				sb.append("\u001B[0m");
			} else {
				sb.append("\u001B[31m");
				sb.append(cord.nr);
				sb.append("\u001B[0m");
			}
		}
		System.out.println(sb);

		System.out.println(sum);
	}

	private String[] getAllThings(String s) {
		ArrayList<String> things = new ArrayList<>();
		StringBuilder tempString = new StringBuilder();
		boolean isInt = false;
		for (char c : s.toCharArray()) {
			if (c == '.') {
				if (tempString.length() > 0) {
					things.add(tempString.toString());
					tempString = new StringBuilder();
					isInt = false;
				}
			} else if (isNumber.test(String.valueOf(c))) {
				if (isInt)
					tempString.append(c);
				else {
					if (tempString.length() == 0) {
						tempString.append(c);
					} else {
						things.add(tempString.toString());
						tempString = new StringBuilder().append(c);
					}
					isInt = true;
				}
			} else {
				if (isInt) {
					things.add(tempString.toString());
					tempString = new StringBuilder().append(c);
					isInt = false;
				} else
					tempString.append(c);
			}
		}
		if (!tempString.isEmpty())
			things.add(tempString.toString());
		return things.toArray(String[]::new);
	}

}
