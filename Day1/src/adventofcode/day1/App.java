package adventofcode.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) throws IOException {
		
		Path inputFilePath = Paths.get("resources/input.txt");
		
		System.out.println(String.format("Day1: %s", inputFilePath.toAbsolutePath().toString()));
		
		List<String> inputLines;
		try (Stream<String> lines = Files.lines(inputFilePath)) {
			inputLines = lines.collect(Collectors.toList());
		}
		
		firstPuzzle(inputLines);
		
		secondPuzzle(inputLines);
	}

	private static void firstPuzzle(List<String> inputLines) {
		int amount = 0;
		int recordAmount = 0;
		
		for(int i=0; i< inputLines.size(); i++) {
			String l = inputLines.get(i);
			if(l.equals("")) {
				amount=0;
			} else {
				amount += Integer.parseInt(l);
				if(amount > recordAmount) {
					recordAmount = amount;
				}
			}
		}
		System.out.println(String.format("First puzzle: Record amount %d", recordAmount));
	}

	private static void secondPuzzle(List<String> inputLines) {
		List<Integer> amounts = new ArrayList<Integer>();
		int amount = 0;
		
		for(int i=0; i< inputLines.size(); i++) {
			String l = inputLines.get(i);
			if(l.equals("")) {
				amounts.add(amount);
				amount=0;
			} else {
				amount += Integer.parseInt(l);
			}
		}
		System.out.println("Second puzzle: Top 3 amounts:");
		Collections.sort(amounts, Collections.reverseOrder());
		
		int topAmountsSum = 0;
		
		if(amounts.size() > 1) {
			System.out.println(amounts.get(0));
			topAmountsSum += amounts.get(0);
		}
		
		if(amounts.size() > 2) {
			System.out.println(amounts.get(1));
			topAmountsSum += amounts.get(1);
		}
		
		if(amounts.size() > 3) {
			System.out.println(amounts.get(2));
			topAmountsSum += amounts.get(2);
		}
		
		System.out.println(String.format("Top amounts sum: %d", topAmountsSum));
		
		
		
	}

}
