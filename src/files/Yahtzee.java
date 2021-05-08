package files;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Yahtzee extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

			
	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Yahtzee frame = new Yahtzee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the frame
	public Yahtzee() {
		
		Game game = new Game();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 622);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JLayeredPane MenuCard = new JLayeredPane();
		JLayeredPane GameCard = new JLayeredPane();
		contentPane.add(MenuCard, "MenuCard");
		contentPane.add(GameCard, "GameCard");
		GameCard.setLayout(null);
		
		JPanel PlayerPanelArea = new JPanel();
		PlayerPanelArea.setBounds(0, 0, 800, 440);
		GameCard.add(PlayerPanelArea);
		PlayerPanelArea.setLayout(null);
		
		JLabel MenuBackground = new JLabel("");
		MenuBackground.setIcon(new ImageIcon(Yahtzee.class.getResource("/images/TitleScreen.png")));
		MenuBackground.setBounds(0, 0, 800, 600);
		MenuCard.add(MenuBackground);
		
		JToggleButton[] MenuPlayerButtons = new JToggleButton[4];
		
		JPanel BottomArea = new JPanel();
		Game.BottomArea = BottomArea;
		BottomArea.setBounds(0, 440, 800, 160);
		GameCard.add(BottomArea);
		BottomArea.setLayout(new CardLayout(0, 0));
		
		JPanel PlayCard = new JPanel();
		Game.PlayCard = PlayCard;
		PlayCard.setLayout(null);
		PlayCard.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black));
		BottomArea.add(PlayCard, "Play");
			
		JPanel EndCard = new JPanel();
		Game.EndCard = EndCard;
		EndCard.setLayout(null);
		EndCard.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black));
		BottomArea.add(EndCard, "End");
		
		JLabel WinnerLabel = new JLabel("");
		Game.WinnerLabel = WinnerLabel;
		WinnerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 60));
		WinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WinnerLabel.setBounds(0, 0, 800, 160);
		EndCard.add(WinnerLabel);
		
		for (int i = 0; i < 4; i++) {
			MenuPlayerButtons[i] = new JToggleButton("");	
			MenuPlayerButtons[i].setIcon(Game.PlayerButtonImages[i][0]);
			MenuPlayerButtons[i].setSelectedIcon(Game.PlayerButtonImages[i][2]);
			MenuPlayerButtons[i].setBounds(75 + 175 * i, 350, 125, 125);			
			buttonGroup.add(MenuPlayerButtons[i]);
			MenuCard.setLayer(MenuPlayerButtons[i], 1);
			MenuCard.add(MenuPlayerButtons[i]);
		}
		
		MenuPlayerButtons[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MenuPlayerButtons[0].setIcon(Game.PlayerButtonImages[0][1]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MenuPlayerButtons[0].setIcon(Game.PlayerButtonImages[0][0]);
			}
		});
		
		MenuPlayerButtons[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MenuPlayerButtons[1].setIcon(Game.PlayerButtonImages[1][1]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MenuPlayerButtons[1].setIcon(Game.PlayerButtonImages[1][0]);
			}
		});
		
		MenuPlayerButtons[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MenuPlayerButtons[2].setIcon(Game.PlayerButtonImages[2][1]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MenuPlayerButtons[2].setIcon(Game.PlayerButtonImages[2][0]);
			}
		});
		
		MenuPlayerButtons[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MenuPlayerButtons[3].setIcon(Game.PlayerButtonImages[3][1]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MenuPlayerButtons[3].setIcon(Game.PlayerButtonImages[3][0]);
			}
		});
		
		JToggleButton MenuPlayButton = new JToggleButton("");
		MenuPlayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MenuPlayButton.setIcon(Game.PlayButtonImages[1]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MenuPlayButton.setIcon(Game.PlayButtonImages[0]);
			}
		});
		MenuPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < MenuPlayerButtons.length; i++) {
					if (MenuPlayerButtons[i].isSelected()) {
						Player.numPlayers = i + 1;
						Game.Players = new Player[Player.numPlayers];
						
						for (int j = 0; j < Player.numPlayers; j++) {
							//game.PlayerPanels[j] = new PlayerPanel(j + 1);
							Game.Players[j] = new Player(j + 1);
							PlayerPanelArea.add(Game.Players[j].panel);
						}
						
						for (int j = 0; j < 5; j++) {
							Game.dice[j] = new Dice(300 + 95 * j);
							PlayCard.add(Game.dice[j].button);
						}

						Game.activePlayer = 1;
						
						Game.Players[0].panel.title.setFont(Game.Players[0].panel.title.getFont().deriveFont(
								java.util.Collections.singletonMap(
										java.awt.font.TextAttribute.WEIGHT, java.awt.font.TextAttribute.WEIGHT_BOLD)));
						
						((CardLayout)contentPane.getLayout()).show(contentPane, "GameCard");
						break;
					}
				}
			}
		});
		MenuPlayButton.setIcon(new ImageIcon(Yahtzee.class.getResource("/images/PlayA.png")));
		MenuCard.setLayer(MenuPlayButton, 1);
		MenuPlayButton.setBounds(150, 500, 500, 75);
		MenuCard.add(MenuPlayButton);	
		
		JButton RollButton = new JButton("Roll");
		RollButton.setBounds(71, 64, 117, 29);
		PlayCard.add(RollButton);
		Game.RollButton = RollButton;

		RollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Dice d : Game.dice) {
					d.button.setEnabled(true);
					d.roll();
				}
				Game.rollNum++;
				RollButton.setEnabled(Game.rollNum < 3);
				Game.updateValues();
				Game.Players[Game.activePlayer - 1].panel.updatePanel(Game.values);
			}
		});
		
	}
}
