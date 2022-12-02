package adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) throws IOException {
		
		Path inputFilePath = Paths.get("resources/input.txt");
		
		System.out.println(String.format("Day2: %s", inputFilePath.toAbsolutePath().toString()));
		
		List<String> inputLines;
		try (Stream<String> lines = Files.lines(inputFilePath)) {
			inputLines = lines.collect(Collectors.toList());
		}
		
		firstPuzzle(inputLines);
		
		secondPuzzle(inputLines);
	}

	private static void firstPuzzle(List<String> inputLines) {
		// The score for a single round is the score for the shape 
		// you selected (1 for Rock, 2 for Paper, and 3 for Scissors) 
		// plus the score for the outcome of the round (0 if you lost, 
		// 3 if the round was a draw, and 6 if you won).
		
		HashMap<String, String> rps = new HashMap<>();
		rps.put("X", "Rock");
		rps.put("Y", "Paper");
		rps.put("Z", "Scissors");
		
		rps.put("A", "Rock");
		rps.put("B", "Paper");
		rps.put("C", "Scissors");
		
		HashMap<String, Integer> points = new HashMap<>();
		
		points.put("X",1); // Rock
		points.put("Y",2); // Paper
		points.put("Z",3); // Scissors
		
		points.put("LOST",0);
		points.put("DRAW",3);
		points.put("WON",6);
	
		
		List<FirstPuzzleMove> moves = new ArrayList<FirstPuzzleMove>();
		
		inputLines.forEach(l -> {
			String[] splittedLine=l.split(" ");
			if(splittedLine.length >=2) {
				moves.add(new FirstPuzzleMove(splittedLine[0], splittedLine[1]));
			}
		});
		
		int finalScore = 0;
		
		for(int i=0; i< moves.size(); i++) {
			FirstPuzzleMove currentMove = moves.get(i);
			
			int currentMovePoints = points.get(currentMove.getResponse()) + 
					points.get(currentMove.getOutcome().toString());
			
			finalScore += currentMovePoints;
			
			System.out.println(String.format("Move %d: %s vs %s => %s => %d + %d = %d => final Score: %d", 
					i, rps.get(currentMove.getOpponentMove()), 
					rps.get(currentMove.getResponse()),
					currentMove.getOutcome().toString(), 
					points.get(currentMove.getResponse()),
					points.get(currentMove.getOutcome().toString()),
					currentMovePoints, finalScore));
		}
		
		System.out.println(String.format("First puzzle: Final score %d", finalScore));
	}

	private static void secondPuzzle(List<String> inputLines) {
		// The score for a single round is the score for the shape 
		// you selected (1 for Rock, 2 for Paper, and 3 for Scissors) 
		// plus the score for the outcome of the round (0 if you lost, 
		// 3 if the round was a draw, and 6 if you won).
		
		HashMap<String, String> rps = new HashMap<>();
		rps.put("A", "Rock");
		rps.put("B", "Paper");
		rps.put("C", "Scissors");
		
		HashMap<String, TurnOutcome> expectedTurnOutcomeMap = new HashMap<>();
		
		expectedTurnOutcomeMap.put("X", TurnOutcome.LOST);
		expectedTurnOutcomeMap.put("Y", TurnOutcome.DRAW);
		expectedTurnOutcomeMap.put("Z", TurnOutcome.WON);
		
		HashMap<String, Integer> points = new HashMap<>();
		
		points.put("A",1); // Rock
		points.put("B",2); // Paper
		points.put("C",3); // Scissors
		
		points.put("LOST",0);
		points.put("DRAW",3);
		points.put("WON",6);
	
		
		List<SecondPuzzleTurn> turns = new ArrayList<>();
		
		inputLines.forEach(l -> {
			String[] splittedLine=l.split(" ");
			if(splittedLine.length >=2) {
				turns.add(new SecondPuzzleTurn(splittedLine[0], expectedTurnOutcomeMap.get(splittedLine[1])));
			}
		});
		
		int finalScore = 0;
		
		for(int i=0; i< turns.size(); i++) {
			SecondPuzzleTurn currentTurn = turns.get(i);
			
			int currentTurnPoints = points.get(currentTurn.getResponse()) + 
					points.get(currentTurn.getExpectedOutcome().toString());
			
			finalScore += currentTurnPoints;
			
			System.out.println(String.format("Move %d: %s vs %s => %s => %d + %d = %d => final Score: %d", 
					i, rps.get(currentTurn.getOpponentMove()), 
					rps.get(currentTurn.getResponse()),
					currentTurn.getExpectedOutcome().toString(), 
					points.get(currentTurn.getResponse()),
					points.get(currentTurn.getExpectedOutcome().toString()),
					currentTurnPoints, finalScore));
		}
		
		System.out.println(String.format("Second puzzle: Final score %d", finalScore));
	}

	
}
