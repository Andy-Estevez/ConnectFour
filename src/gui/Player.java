package gui;

public class Player {
	
	private String playerName;
	private String playerSymbol;
	private int numGames;
	private int numWins;
	private int numLosses;
	
	public Player() { // default constructor 
		
		playerName = "Doe";
		playerSymbol = "X";
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		
	}
	
	public Player(String name, String symbol) { // overloaded constructor
		
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		playerName = name;
		playerSymbol = symbol;
		
	}
	public String getSymbol() {
		return playerSymbol;
	}
	public String getName() {
		return playerName;
	}
	
	public int getNumGames() {
		return numGames;
	}
	public int getNumWins() {
		return numWins;
	}
	public int getNumLosses() {
		return numLosses;
	}
	public void addNumWins() {
		numWins++;
		numGames++;
	}
	public void addNumLosses() {
		numLosses++;
		numGames++;
	}
	public void addDraw() {
		numGames++;
	}

}
