package de.nandi.adventofcode.day.seven;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class First {

	private final List<String> cards = Arrays.asList("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2");

	public static void main(String[] args) {
		new First();
	}

	public First() {
		// -1 if 01 is before 02
		Comparator<String> comparator = ((Comparator<String>) (o1, o2) -> Integer.compare(computeType(o1), computeType(o2))).thenComparing((o1, o2) -> {
			int compare = 0;
			int index = 0;
			while (compare == 0) {
				compare = Integer.compare(cards.indexOf(String.valueOf(o2.charAt(index))),
						cards.indexOf(String.valueOf(o1.charAt(index))));
				index++;
			}
			return compare;
		});
		Map<String, Integer> hands = new HashMap<>();

		try (Scanner scanner = new Scanner(new File("src/de/nandi/adventofcode/day/seven/input.txt"))) {
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				hands.put(s.split(" ")[0], Integer.parseInt(s.split(" ")[1]));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		ArrayList<String> list = new ArrayList<>(hands.keySet().stream().toList());
		list.sort(comparator);
		int sum = 0;
		int index = 1;
		for (String key : list) {
			sum += hands.get(key) * index;
			index++;
		}
		System.out.println(sum);
	}

	public int computeType(String hand) {
		Map<String, Integer> same = new HashMap<>();
		for (char c : hand.toCharArray()) {
			String char_ = String.valueOf(c);
			same.put(char_, same.getOrDefault(char_, 0) + 1);
		}
		if (same.containsValue(5))
			return 6;
		if (same.containsValue(4))
			return 5;
		if (same.containsValue(3) && same.containsValue(2))
			return 4;
		if (same.containsValue(3))
			return 3;
		if (same.values().stream().filter(integer -> integer == 2).count() == 2)
			return 2;
		if (same.containsValue(2))
			return 1;
		return 0;
	}

}
