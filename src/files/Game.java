package files;

public class Game {

	public final static int NUM_CATEGORIES = 13;
	public final static int MAX_PLAYERS = 4;
	public final static int NUM_DICE = 5;
	public final static int BONUS_THRESHOLD = 63;
	public final static int BONUS_AMOUNT = 35;
	
    public static javax.swing.ImageIcon[][] PlayerButtonImages = new javax.swing.ImageIcon[4][3];
    public static javax.swing.ImageIcon[] PlayButtonImages = new javax.swing.ImageIcon[2];
    public int PlayerTurn;
    
	static Player[] Players;
	static Dice[] dice = new Dice[NUM_DICE];
	
	static javax.swing.JButton RollButton;
	static javax.swing.JPanel PlayCard;
	static javax.swing.JPanel BottomArea;
	static javax.swing.JPanel EndCard;
	static javax.swing.JLabel WinnerLabel;
	
	static int activePlayer;
	static int rollNum;
	static int numRounds = 0;
	
	static int[] values = new int[NUM_DICE];
	
	public static java.awt.Color[] bg = { new java.awt.Color(255, 150, 150),
			new java.awt.Color(150, 255, 150), new java.awt.Color(150, 150, 255), new java.awt.Color(255, 255, 150) };
    
    public javax.swing.ImageIcon getImage(String path) {
        return new javax.swing.ImageIcon(getClass().getResource(path));
    }

    public Game() {
    	for (int i = 0; i < MAX_PLAYERS; i++) {
            PlayerButtonImages[i][0] = getImage("/images/" + String.valueOf(i + 1) + "a.png");
            PlayerButtonImages[i][1] = getImage("/images/" + String.valueOf(i + 1) + "b.png");
            PlayerButtonImages[i][2] = getImage("/images/" + String.valueOf(i + 1) + "c.png");
        }
        
    	rollNum = 0;
    	
        PlayButtonImages[0] = getImage("/images/PlayA.png");
        PlayButtonImages[1] = getImage("/images/PlayB.png");  
        
        Dice.DiceImages[0][0] = getImage("/images/BlankDice.png");
        for (int i = 1; i <= 6; i++) {
        	Dice.DiceImages[i][0] = getImage("/images/Dice" + String.valueOf(i) + ".png");
        	Dice.DiceImages[i][1] = getImage("/images/LockedDice" + String.valueOf(i) + ".png");
        }
        
    }
    
    public static void updateValues() {
    	for (int i = 0; i < 5; i++) {
    		values[i] = dice[i].value;
    	}
    }
    
    public static void nextPlayer(int a, boolean b) {
    	
    	rollNum = 0;
    	RollButton.setEnabled(true);
    	
    	if (b) {
    		Players[activePlayer - 1].upperScore += a;
    		if (Players[activePlayer - 1].upperScore >= BONUS_THRESHOLD && !Players[activePlayer - 1].bonus) {
    			Players[activePlayer - 1].upperScore += BONUS_AMOUNT;
    			Players[activePlayer - 1].bonus = true;
    			Players[activePlayer - 1].panel.bonusLabel.setText(String.valueOf(BONUS_AMOUNT));
    		}
    		Players[activePlayer - 1].panel.totalScores[0].setText(String.valueOf(Players[activePlayer - 1].upperScore));
    	} else {
    		Players[activePlayer - 1].lowerScore += a;
    		Players[activePlayer - 1].panel.totalScores[1].setText(String.valueOf(Players[activePlayer - 1].lowerScore));
    	}
    	
    	Players[activePlayer - 1].panel.totalScores[2].setText(String.valueOf(Players[activePlayer - 1].totalScore()));
    	
    	for (Dice d : dice) {
    		d.value = 0;
    		d.button.setSelected(false);
    		d.button.setEnabled(false);
    		d.button.setIcon(Dice.DiceImages[0][0]);
    	}
    	
    	activePlayer = (activePlayer % Player.numPlayers) + 1;
    	
    	if (activePlayer == 1) {
    		numRounds++;
    		if (numRounds >= NUM_CATEGORIES) {
    			endGame();
    			return;
    		}
    	}
    	
    	Players[activePlayer - 1].panel.title.setFont(Players[activePlayer - 1].panel.title.getFont().deriveFont(
				java.util.Collections.singletonMap(
						java.awt.font.TextAttribute.WEIGHT, java.awt.font.TextAttribute.WEIGHT_BOLD)));
    	
    	
    }
    
    public static void endGame() {
    	if (Player.numPlayers == 1) {
    		WinnerLabel.setText("Your score: " + String.valueOf(Players[0].totalScore()));
    	} else {
	    	int max = 0;
	    	int winningPlayer = 0;
	    	for (int i = 0; i < Player.numPlayers; i++) {
	    		if (Players[i].totalScore() >= max) {
	    			max = Players[i].totalScore();
	    			winningPlayer = i + 1;
	    		}
	    	}
	    	EndCard.setBackground(bg[winningPlayer - 1]);
	    	WinnerLabel.setText("Player " + String.valueOf(winningPlayer) + " wins!");
    	}
    	((java.awt.CardLayout)BottomArea.getLayout()).show(BottomArea, "End");
    }
    
}
