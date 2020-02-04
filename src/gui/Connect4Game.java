package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

 

public class Connect4Game extends JFrame{
	private JPanel jpMain;
	
	Connect4Board jpBoard; 
	private Player currPlayer;
	private Player player1;
	private Player player2;
	
	
	public Connect4Game() {
		player1 = new Player("Bob", "X");
		player2 = new Player("Joe", "O");
		currPlayer = player1;
		
		
		jpMain = new JPanel();
		jpBoard = new Connect4Board();
		
		
		jpMain.setLayout(new BorderLayout());
	
		
		
		//jpMain.add(jpBoard, BorderLayout.LINE_END);
		jpBoard.setLayout(new GridLayout(0, 7));
		add(jpMain);
		add(jpBoard);
		//add(scoreboard);
		
		setSize(1000, 1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		
		
	
	}
		
	
	
		
		public class Connect4Board extends JPanel implements ActionListener{
			
			private JLabel switchTurn;
			private JLabel player1score;
			private JLabel player2score;
			private JLabel gamesPlayed;
			private JButton[] boardBtns;
			private JLabel [][] board;
			private int btns = 7;
			private int ROWS = 6;
			private int COLS = 7;
			private int [] nextRowAvailable = {5, 5, 5, 5, 5, 5, 5};
			private int games = 0;
			private int p1wins = 0;
			private int p2wins = 0;
	
		public Connect4Board() {
		
		boardBtns = new JButton[btns];
		displayBtns(); 
		
		board = new JLabel[ROWS][COLS];
		displayBoard();
		
		switchTurn = new JLabel("Current Turn: "+player2.getName());
		displayTurn();
		gamesPlayed = new JLabel("Games Played: "+currPlayer.getNumGames());
		displayGamesPlayed();
		player1score = new JLabel("Bob: "+player1.getNumWins());
		displayPlayer1Score();
		player2score = new JLabel("Joe: "+player2.getNumWins());
		displayPlayer2Score();
		
		
		updateTurn();
		updatePlayer1Wins();
		updateGamesPlayed();
		}
		
		public void updateTurn() {
			
			if(switchTurn.getText().equals("Current Turn: "+player1.getName())) {
				switchTurn.setText("Current Turn: "+player2.getName());
			}
			else {
				switchTurn.setText("Current Turn: "+player1.getName());
			}
		}
		
		public void updateGamesPlayed() {
			if(isWinner()) {
				gamesPlayed.setText("Games Played: "+currPlayer.getNumGames());
					
				}
			}
			
		
		public void updatePlayer1Wins() {
			
				
			
		}
		public void displayPlayer1Score() {
			add(player1score);
		}
		public void displayPlayer2Score() {
			add(player2score);
		}
		
		public void displayTurn() {
			add(switchTurn);
		}
		public void displayGamesPlayed() {
			add(gamesPlayed);
		}
		public void displayBtns() {
			for(int btns = 0; btns < boardBtns.length; btns++) {
				
			boardBtns[btns] = new JButton();
			boardBtns[btns].setEnabled(true);
			boardBtns[btns].addActionListener(this);
			boardBtns[btns].setText(String.valueOf(btns+1));
			boardBtns[btns].setBackground(Color.ORANGE);
			boardBtns[btns].setForeground(Color.BLACK);
			add(boardBtns[btns]); // adds the buttons
			
			}
		
		}
		public void displayBoard() {
		
			for(int row = 0; row<board.length; row++) {
			
				for(int col = 0; col<board[row].length; col++ ) {
				
				board[row][col] = new JLabel();
				Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 40);
				board[row][col].setFont(bigFont);
				
				board[row][col].setEnabled(true);
				board[row][col].setText(" _ ");
				board[row][col].setForeground(Color.GRAY);
				add(board[row][col]); // creates the board
				
				}
			}
		
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton)e.getSource();
		//btnClicked.setText(currPlayer.getSymbol());
		//btnClicked.setEnabled(true);
		
			for(int i = 0; i < boardBtns.length; i++) {
				if(btnClicked == boardBtns[i]) { // checks through the buttons 
					
				int rowToRow = nextRowAvailable[i];
				board[rowToRow][i].setText(currPlayer.getSymbol());
				nextRowAvailable[i]--; // lowers the array by 1 to create the gravity
				}
				if(isWinner()) {
					
					if(currPlayer.equals(player1)) {
						
						//currPlayer.addDraw();
						p1wins++;
						player1.addNumWins();
						player1score.setText("Bob: "+p1wins);
					}
					else {
						p2wins++;
						player2score.setText("Joe: "+p2wins);
						//currPlayer.addDraw();
						player2.addNumWins();
					}
					
					JOptionPane.showMessageDialog(null, "Winner = "+currPlayer.getName());
					int reply = JOptionPane.showConfirmDialog(null, "Want to play again?", "Play Again?",  JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION)
					{
					   games++;
					   gamesPlayed.setText("Games Played: "+games);
					   clearBoard();
					   displayTurn();// resets board
					   
					   displayGamesPlayed();
					   displayPlayer1Score();
					   displayPlayer2Score();
					}
					else {
						System.exit(0); // closes
					}
					
				}
				else if(isFull()) {
					
					JOptionPane.showMessageDialog(null, "DRAW");
					int reply = JOptionPane.showConfirmDialog(null, "The game went to a draw", "Play Again?",  JOptionPane.YES_NO_OPTION); // requires input from user
					if (reply == JOptionPane.YES_OPTION)
					{
					   clearBoard();
					   displayTurn();// resets the board
					   displayGamesPlayed();
					   displayPlayer1Score();
					   displayPlayer2Score();
					}
					else {
						System.exit(0); // closes the game
					}
					
				
				}
			
			}
			
				updateGamesPlayed();
				updateTurn();
				takeTurn();
				
		}
		
		public void clearBoard() {
			for(int row = 0; row < ROWS; row++) {
				for(int col = 0; col < board[row].length; col++) {
					board[row][col].setText(" _ ");
					board[row][col].setEnabled(true);
					add(board[row][col]);
				}
			}
			for(int i = 0; i<COLS; i++) {
				nextRowAvailable[i] = ROWS-1;
			}
		}
		public boolean isFull() {
			
			for(int row = 0; row < 6; row++) {
				for(int col = 0; col < 7; col++) {
					String cellContent = board[row][col].getText();
					if(cellContent.equalsIgnoreCase(" _ ")) {
					return false;
					}
					
				}
				
			}
			
			return true;
		}
		
		
		public boolean isWinner() {
			if(isWinnerInRow() || isWinnerInColumn() || isWinnerUpward() || isWinnerDownward()) {
				return true;
			}
			
			return false;
		}
		public boolean isWinnerInRow() {
			String symbol = currPlayer.getSymbol();
			for(int row = 0; row < 6; row++) {
				int matches = 0;
				for(int col = 0; col < 7; col++) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matches++;
					}
					else {
						matches = 0;
					}
					if(matches == 4) {
						currPlayer.addNumWins();
						return true;
					}
				}
			}
			return false;
		}
		
		public boolean isWinnerInColumn() {
			String symbol = currPlayer.getSymbol();
			for(int col = 0; col < 7; col++) {
				int matches = 0;
				for(int row = 0; row < 6; row++) { // checks through each row within the same column
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matches++; // adds counter to the consecutive matches
					}
					else {
						matches = 0;
					}
					if(matches == 4) {
						currPlayer.addNumWins();
						return true;
					}
					
				}
			}
			return false;
		}
		
		public boolean isWinnerUpward() {
			String symbol = currPlayer.getSymbol();
		
			 for (int row = 3; row < ROWS; row++){
			        for (int col = 0; col < COLS - 3; col++){
			        	// checks through the 4 upward coordinates if there is a match
			            if (board[row][col].getText().trim().equalsIgnoreCase(symbol)
			            	&& board[row-1][col+1].getText().trim().equalsIgnoreCase(symbol) 
			            	&& board[row-2][col+2].getText().trim().equalsIgnoreCase(symbol) 
			            	&& board[row-3][col+3].getText().trim().equalsIgnoreCase(symbol)) {
			            	currPlayer.addNumWins();
			                return true;
			        }
			        
			 }
			
		}
			return false;
		}
		
		public boolean isWinnerDownward() {
			String symbol = currPlayer.getSymbol();
			
			 for (int row = 3; row < ROWS; row++){
			        for (int col = 3; col < COLS; col++){
			        	//checks through the 4 downward coordinates if there is a match
			            if (board[row][col].getText().trim().equalsIgnoreCase(symbol) && board[row-1][col-1].getText().trim().equalsIgnoreCase(symbol) 
			            		&& board[row-2][col-2].getText().trim().equalsIgnoreCase(symbol) && board[row-3][col-3].getText().trim().equalsIgnoreCase(symbol)) { 
			            	currPlayer.addNumWins();
			                return true;
			        }
			        
			 }
			
		}
			return false;
		}
	
		public void takeTurn() { // alternates between players
			
			if(currPlayer.equals(player1)) {
				currPlayer = player2;
			}
			else {
				currPlayer = player1;
			}
		}
		
	}

}
