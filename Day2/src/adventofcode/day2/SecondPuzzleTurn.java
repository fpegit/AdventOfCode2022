package adventofcode.day2;

import java.util.HashMap;

public class SecondPuzzleTurn {
	private String opponentMove;
	private TurnOutcome expectedOutcome;
	
	private static HashMap<String,TurnOutcome> rules;
	
	public SecondPuzzleTurn(String opponentMove, TurnOutcome expectedOutcome) {
		this.opponentMove = opponentMove;
		this.expectedOutcome = expectedOutcome;
	}
	
	private static HashMap<String, TurnOutcome> getRules() {
		
		if(rules == null) {
			// A for Rock, B for Paper, and C for Scissors
			rules = new HashMap<>();
			rules.put("AA", TurnOutcome.DRAW);
			rules.put("AB", TurnOutcome.WON);
			rules.put("AC", TurnOutcome.LOST);
			rules.put("BA", TurnOutcome.LOST);
			rules.put("BB", TurnOutcome.DRAW);
			rules.put("BC", TurnOutcome.WON);
			rules.put("CA", TurnOutcome.WON);
			rules.put("CB", TurnOutcome.LOST);
			rules.put("CC", TurnOutcome.DRAW);
		}
		
		return rules;
	}
	
	public String getOpponentMove() {
		return opponentMove;
	}
	
	public void setOpponentMove(String opponentMove) {
		this.opponentMove = opponentMove;
	}
	
	public TurnOutcome getExpectedOutcome() {
		return expectedOutcome;
	}
	
	public void setExpectedOutcome(TurnOutcome expectedOutcome) {
		this.expectedOutcome = expectedOutcome;
	}

	public String getResponse() {
		var ruleExtract = getRules().entrySet().stream()
		.filter(es -> (es.getKey().startsWith(opponentMove) && 
				es.getValue().equals(expectedOutcome)))
		.findFirst();
		
		if(ruleExtract.isPresent()) {
			return ruleExtract.get().getKey().substring(1,2);
		} 
		
		return null;
	}
}
