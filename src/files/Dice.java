package files;

public class Dice {

	static javax.swing.ImageIcon[][] DiceImages = new javax.swing.ImageIcon[7][2];
	javax.swing.JToggleButton button = new javax.swing.JToggleButton();
	int value;
	
	public Dice(int x) {
		button.setBounds(x, 40, 75, 75);
		button.setIcon(DiceImages[0][0]);
		button.setEnabled(false);
	}
	
	public void roll() {
		if (!button.isSelected()) {
			value = (int)(Math.random() * 6 + 1);
			button.setIcon(DiceImages[value][0]);
			button.setSelectedIcon(DiceImages[value][1]);
		}
	}
	
}
