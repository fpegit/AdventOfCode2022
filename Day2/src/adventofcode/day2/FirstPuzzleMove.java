package adventofcode.day2;

import java.util.HashMap;

public class FirstPuzzleMove {
	private String opponentMove;
	private String response;
	
	
	private static HashMap<String,TurnOutcome> rules;
	
	public FirstPuzzleMove(String opponentMove, String response) {
		this.opponentMove = opponentMove;
		this.response = response;
	}
	
	private static HashMap<String, TurnOutcome> getRules() {
		
		if(rules == null) {
			// A for Rock, B for Paper, and C for Scissors
			// X for Rock, Y for Paper, and Z for Scissors
			rules = new HashMap<>();
			rules.put("AX", TurnOutcome.DRAW);
			rules.put("AY", TurnOutcome.WON);
			rules.put("AZ", TurnOutcome.LOST);
			rules.put("BX", TurnOutcome.LOST);
			rules.put("BY", TurnOutcome.DRAW);
			rules.put("BZ", TurnOutcome.WON);
			rules.put("CX", TurnOutcome.WON);
			rules.put("CY", TurnOutcome.LOST);
			rules.put("CZ", TurnOutcome.DRAW);
		}
		
		return rules;
	}

	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getOpponentMove() {
		return opponentMove;
	}
	
	public void setOpponentMove(String opponentMove) {
		this.opponentMove = opponentMove;
	}
	
	public TurnOutcome getOutcome() {
		String key = String.format("%s%s", opponentMove, response);
		return getRules().get(key);
	}
}
