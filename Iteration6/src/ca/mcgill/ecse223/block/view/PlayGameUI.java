package ca.mcgill.ecse223.block.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

import javax.swing.JPanel;
import java.awt.Color;

public class PlayGameUI implements Block223PlayModeInterface{

	private JFrame frame;
	private JLayeredPane playArea;
	private volatile String keyString = "";
	private JLabel lblScore;
	private JLabel lblLives;
	private JLabel lblLevel;
	private JButton btnStartGame;
	private JLabel errorMsg;
	private JPanel hallOfFame ;
	private JLabel lblNewLabel;
	/**
	 * Create the application.
	 */
	public PlayGameUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(106,108,110));
		frame.getContentPane().setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		frame.setBounds(100, 100, 839, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		 playArea = new PlayAreaUI();
		playArea.setBackground(Color.WHITE);
		 btnStartGame = new JButton("Start Game");
		 btnStartGame.setForeground(new Color(0, 100, 0));
		 btnStartGame.setBackground(new Color(51, 153, 102));
		btnStartGame.setFont(new Font("Century Gothic", Font.BOLD, 16));
		
		 lblLevel = new JLabel("Level: ");
		 lblLevel.setForeground(Color.BLACK);
		lblLevel.setFont(new Font("Century Gothic", Font.BOLD, 17));
		
		 lblScore = new JLabel("Score:");
		 lblScore.setForeground(Color.BLACK);
		lblScore.setFont(new Font("Century Gothic", Font.BOLD, 17));
		
		 lblLives = new JLabel("Lives: ");
		 lblLives.setForeground(Color.BLACK);
		lblLives.setFont(new Font("Century Gothic", Font.BOLD, 17));
		
		 hallOfFame = new HallOfFame();
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnGoBack.addMouseListener(new java.awt.event.MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ( btnStartGame.isVisible()) goBack();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton btnPrevious = new JButton("< Prev");
		btnPrevious.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnPrevious.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				((HallOfFame) hallOfFame).prevPage();
			}
		});
		JButton btnNext = new JButton(" Next >");
		btnNext.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				((HallOfFame) hallOfFame).nextPage();
			}
		});
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		
		refresh();
		
		lblNewLabel = new JLabel("Hall Of Fame");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 21));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(errorMsg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnStartGame)
							.addGap(119)
							.addComponent(btnGoBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblLives, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblScore, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblLevel, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
						.addComponent(playArea, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(lblNewLabel)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(hallOfFame, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(44)
									.addComponent(btnNext)
									.addGap(0, 0, Short.MAX_VALUE))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorMsg)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblScore)
					.addGap(18)
					.addComponent(lblLives)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(hallOfFame, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addComponent(playArea, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnPrevious, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnGoBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnStartGame, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
					.addGap(25))
		);
		frame.getContentPane().setLayout(groupLayout);
		btnStartGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnStartGame.setVisible(false);
			
				// initiating a thread to start the game loop
				KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
					  @Override
					  public synchronized boolean dispatchKeyEvent(final KeyEvent e) {
						  
						  int location = e.getKeyCode();
							
							if (location == KeyEvent.VK_LEFT) {
								keyString += "l";
							} else if (location == KeyEvent.VK_RIGHT) {
								keyString += "r";
							} else if (location == KeyEvent.VK_SPACE) {
								keyString += " ";
							} else {
								// ignore all other keys
							}
					    // Pass the KeyEvent to the next KeyEventDispatcher in the chain
					    return false;
					  }
					};
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
			
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						
							try {
								Block223Controller.startPlayGame(PlayGameUI.this);
								
							} catch (InvalidInputException e) {
								errorMsg.setText(e.getMessage());
							}
							btnStartGame.setVisible(true);
						
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
			}
		});
		
		frame.pack();
	}

	protected void goBack() {
		// TODO Auto-generated method stub
		if(btnStartGame.isVisible()) {
			Block223Controller.unselectGame();
			frame.dispose();
			PlayerDashUI.init();
			
		}
	}

	@Override
	public String takeInputs() {
		String passString = keyString;
		keyString = "";
		return passString;
	}

	@Override
	public void refresh() {
		playArea.repaint();
		lblScore.setText("Score: " +((PlayAreaUI)playArea).getScore());
		lblLives.setText("Lives: "+ ((PlayAreaUI)playArea).getLives());
		lblLevel.setText("Level: "+ ((PlayAreaUI)playArea).getLevel());
	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		if(nrOfLives>0) {
			((PlayAreaUI) playArea).displayCongratulations();
		}else {
			((PlayAreaUI) playArea).displayGameOver();
		}
		lblScore.setText("Score: " + Block223Controller.getScore());
		lblLives.setText("Lives: "+ nrOfLives);
		((HallOfFame)hallOfFame).refresh();
	}
}
