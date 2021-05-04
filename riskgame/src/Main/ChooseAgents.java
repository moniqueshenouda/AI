package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ChooseAgents {

	JFrame frame;
	JComboBox<String> comboBox1, comboBox2;
	GameGUISimulation window_simulation;
	GameGUIHumane window_humane;
	
	static String player1, player2, player2_human;
	
	JButton btnStart;
	
	PlayerFactory playerFactory;
	static Player playerVSHuman, player_1, player_2;
	
	public ChooseAgents() {
		initialize();
	}

	private void initialize() {
		playerFactory = new PlayerFactory();
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		chooseAgents();
		if (WelcomeWindow.flagOfHuman == 1) {
			viewHuman();
			viewListOfChoices();
			
		} else {
			viewListOfChoices();
		}
		
		btnStart = new JButton("Start! :D");
		btnStart.setBackground(Color.WHITE);
		btnStart.setForeground(Color.BLACK);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (WelcomeWindow.flagOfHuman == 1) {
					createWindowHumane();
				} else {
					createWindowSimulation();
				}
				WhichMap.agents_window.frame.setVisible(false);
				
			}
		});
		btnStart.setBounds(150, 220, 120, 30);
		frame.getContentPane().add(btnStart);
	}
	
	public void chooseAgents() {
		Label label = new Label("Choose Agents:");
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setBounds(30, 30, 130, 30);
		frame.getContentPane().add(label);
	}
	
	public void viewHuman() {
		Label label = new Label("Human agent.");
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setBounds(30, 60, 130, 30);
		frame.getContentPane().add(label);
	}
	
	public void viewListOfChoices() {
		
		comboBox1 = new JComboBox<String>();
		comboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {"Passive", "Aggressive", "Pacifist", "AStar", "RTAStar", "Minimax", "Greedy"}));
		comboBox1.setToolTipText("");
		comboBox1.setBackground(Color.WHITE);
		comboBox1.setForeground(Color.BLACK);
		comboBox1.setBounds(30, 100, 180, 40);
		comboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String player = (String) comboBox1.getSelectedItem();
				if (WelcomeWindow.flagOfHuman == 0) {
					player1 = player;
					player_1 = playerFactory.getPlayer(player1, "player1");
				} else {
					player2_human = player;
					playerVSHuman = playerFactory.getPlayer(player2_human, "player2");
					System.out.println("Created agent");
				}
				
				System.out.println("Chosen player: " + player);
			}
		});
		
		frame.getContentPane().add(comboBox1);
		
		if (WelcomeWindow.flagOfHuman == 0) {
			comboBox2 = new JComboBox<String>();
			comboBox2.setModel(new DefaultComboBoxModel<String>(new String[] {"Passive", "Aggressive", "Pacifist", "AStar", "RTAStar", "Minimax", "Greedy"}));
			comboBox2.setToolTipText("");
			comboBox2.setBackground(Color.WHITE);
			comboBox2.setForeground(Color.BLACK);
			comboBox2.setBounds(30, 150, 180, 40);
			comboBox2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String player = (String) comboBox2.getSelectedItem();
					player2 = player;
					player_2 = playerFactory.getPlayer(player2, "player2");
					System.out.println("Chosen player: " + player);
				}
			});
			frame.getContentPane().add(comboBox2);
		}
	}
	
	public void createWindowSimulation() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window_simulation = new GameGUISimulation();
					window_simulation.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void createWindowHumane() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window_humane = new GameGUIHumane();
					window_humane.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
