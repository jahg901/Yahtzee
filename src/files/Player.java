package files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {
	
	public static int numPlayers;
	
	int upperScore = 0;
	int lowerScore = 0;
	boolean bonus = false;
	
	PlayerPanel panel;

	public Player(int playerNum) {
		panel = new PlayerPanel(playerNum);
		panel.player = this;
	}
	
	public int totalScore() {
		return upperScore + lowerScore;
	}
	
	public class PlayerPanel extends javax.swing.JPanel {
		
		
		int playerNum;
		Player player;
		
		javax.swing.JLabel[] labels;
		ScoreButton[] scores;
		javax.swing.JLabel[] totalScores;
		javax.swing.JLabel title;
		javax.swing.JLabel bonusLabel;
		
		public class ScoreButton extends javax.swing.JButton {
			boolean locked;
			boolean upLow;
			
			public ScoreButton(boolean UL) {
				super();
				locked = false;
				upLow = UL; //true = upper, false = lower
			}
		}
		
		public PlayerPanel(int playerNum) {

			
			this.setBounds(800 / numPlayers * (playerNum - 1), 0, 800 / numPlayers, 440);
			this.setLayout(null);
			this.setBackground(Game.bg[playerNum - 1]);
			this.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black));
			
			labels = new javax.swing.JLabel[Game.NUM_CATEGORIES + 4];
			scores = new ScoreButton[Game.NUM_CATEGORIES];
			totalScores = new javax.swing.JLabel[3];
			
			for (int i = 0; i < 17; i++) {
				labels[i] = new javax.swing.JLabel();
				this.add(labels[i]);
			}
			
			for (int i = 0; i < Game.NUM_CATEGORIES; i++) {
				scores[i] = new ScoreButton(i < 6);
				scores[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				scores[i].setText("");
				scores[i].setEnabled(false);
				scores[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScoreButton s = ((ScoreButton) e.getSource());
						s.locked = true;
						s.setEnabled(false);
						disablePanel();
						Game.nextPlayer(Integer.parseInt(s.getText()), s.upLow);
					}
				});
				this.add(scores[i]);
			}
			
			for (int i = 0; i < 3; i++) {
				totalScores[i] = new javax.swing.JLabel();
				totalScores[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				totalScores[i].setText("0");
				this.add(totalScores[i]);
			}

			labels[0].setText("Aces");
			labels[1].setText("Twos");
			labels[2].setText("Threes");
			labels[3].setText("Fours");
			labels[4].setText("Fives");
			labels[5].setText("Sixes");
			labels[6].setText("63 Bonus");
			labels[7].setText("Three of a Kind");
			labels[8].setText("Four of a Kind");
			labels[9].setText("Full House");
			labels[10].setText("Small Straight");
			labels[11].setText("Large Straight");
			labels[12].setText("Yahtzee");
			labels[13].setText("Chance");
			labels[14].setText("Upper Total");
			labels[15].setText("Lower Total");
			labels[16].setText("Grand Total");
			
			for (int i = 0; i < 7; i++) {
				labels[i].setBounds(10, 40 + 20 * i, this.getWidth() - 60, 20);
				scores[i].setBounds(this.getWidth() - 50, 40 + 20 * i, 40, 20);
			}
			
			for (int i = 7; i < 14; i++) {
				labels[i].setBounds(10, 60 + 20 * i, this.getWidth() - 60, 20);
				scores[i - 1].setBounds(this.getWidth() - 50, 60 + 20 * i, 40, 20);
			}
			
			for (int i = 14; i < 17; i++) {
				labels[i].setBounds(10, 80 + 20 * i, this.getWidth() - 60, 20);
				totalScores[i - 14].setBounds(this.getWidth() - 50, 80 + 20 * i, 40, 20);
			}
			
			title = new javax.swing.JLabel();
			title.setBounds(0, 0, 800 / numPlayers, 40);
			title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			title.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
			title.setText("Player " + String.valueOf(playerNum));
			this.add(title);
			
			bonusLabel = new javax.swing.JLabel();
			bonusLabel.setBounds(this.getWidth() - 50, 160, 40, 20);
			bonusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			bonusLabel.setText("0");
			this.add(bonusLabel);
			
		}
		
		public void updatePanel(int[] n) {
			
			if (!scores[0].locked) {
				scores[0].setText(String.valueOf(Score.Number(n, 1)));
			}
			if (!scores[1].locked) {
				scores[1].setText(String.valueOf(Score.Number(n, 2)));
			}
			if (!scores[2].locked) {
				scores[2].setText(String.valueOf(Score.Number(n, 3)));
			}
			if (!scores[3].locked) {
				scores[3].setText(String.valueOf(Score.Number(n, 4)));
			}
			if (!scores[4].locked) {
				scores[4].setText(String.valueOf(Score.Number(n, 5)));
			}
			if (!scores[5].locked) {
				scores[5].setText(String.valueOf(Score.Number(n, 6)));
			}
			if (!scores[6].locked) {
				scores[6].setText(String.valueOf(Score.ThreeOfKind(n)));
			}
			if (!scores[7].locked) {
				scores[7].setText(String.valueOf(Score.FourOfKind(n)));
			}
			if (!scores[8].locked) {
				scores[8].setText(String.valueOf(Score.FullHouse(n)));
			}
			if (!scores[9].locked) {
				scores[9].setText(String.valueOf(Score.SmallStraight(n)));
			}
			if (!scores[10].locked) {
				scores[10].setText(String.valueOf(Score.LargeStraight(n)));
			}
			if (!scores[11].locked) {
				scores[11].setText(String.valueOf(Score.Yahtzee(n)));
			}
			if (!scores[12].locked) {
				scores[12].setText(String.valueOf(Score.Chance(n)));
			}
			
			for (int i = 0; i < Game.NUM_CATEGORIES; i++) {
				if (!scores[i].locked) {
					scores[i].setEnabled(true);
				}
			}
		}
		
		public void disablePanel() {
			
			title.setFont(title.getFont().deriveFont(
					java.util.Collections.singletonMap(
							java.awt.font.TextAttribute.WEIGHT, java.awt.font.TextAttribute.WEIGHT_REGULAR)));

			
			for (int i = 0; i < Game.NUM_CATEGORIES; i++) {			
				if (!scores[i].locked) {
					scores[i].setEnabled(false);
					scores[i].setText("");
				}
			}
		}
		
	}
	
}
