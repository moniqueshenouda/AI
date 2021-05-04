package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class CommonGUI {

	public ImageIcon image;
	public JLabel labelForImage;
	static ArrayList<JLabel> countriesPlayer1;
	static ArrayList<JLabel> countriesPlayer2;
	
	static ReinforcementWindow reinformentWindow;
	static AttackWindow attackWindow;
	
	static int xDim, yDim;
	int flag_sim_1 = 1, flag_sim_2 = 0;
	
	JButton reinforcement, attack, take_turn;
	
	public void generateImage() {
		if (WhichMap.map_choice == 0) {
			image = new ImageIcon("egypt.jpeg");	
		} else {
			image = new ImageIcon("usa.jpeg");
		}
		labelForImage = new JLabel(image);
		labelForImage.setBounds(400, 50, 1000, 700);
		
	}
	
	public void createReinforce_Attack_takeTurn() {
		if (WelcomeWindow.flagOfHuman == 1) {
			reinforcement = new JButton("Reinforcement");
			reinforcement.setBounds(700, 50, 150, 50);
			reinforcement.setForeground(Color.BLACK);
			reinforcement.setBackground(Color.WHITE);
			
			attack = new JButton("Attack");
			attack.setBounds(950, 50, 150, 50);
			attack.setForeground(Color.BLACK);
			attack.setBackground(Color.WHITE);
		}
		
		take_turn = new JButton("Take turn");
		take_turn.setBounds(820, 750, 150, 50);
		take_turn.setForeground(Color.BLACK);
		take_turn.setBackground(Color.WHITE);
		
		control3Buttons();
	}
	
	
	public void generateFirstTroops() {
		countriesPlayer1 = new ArrayList<>();
		countriesPlayer2 = new ArrayList<>();
		
		JLabel x;
		xDim = 100;
		yDim = 100;
		
		for (Country country : Randomize.player1Countries) {
			x = DefaultComponentFactory.getInstance().createLabel(country.name + "  " + RiskMap.armies.get(country));
			x.setFont(new Font("Dialog", Font.BOLD, 16));
			x.setForeground(Color.WHITE);
			x.setBounds(xDim, yDim, 300, 30);
			countriesPlayer1.add(x);
			if (WelcomeWindow.flagOfHuman == 1) {
				GameGUIHumane.frame.getContentPane().add(x);
			} else {
				GameGUISimulation.frame.getContentPane().add(x);
			}
			yDim += 30;
		}
		
		xDim = 1500;
		yDim = 100;
		
		for (Country country : Randomize.player2Countries) {
			x = DefaultComponentFactory.getInstance().createLabel(country.name + "  " + RiskMap.armies.get(country));
			x.setFont(new Font("Dialog", Font.BOLD, 16));
			x.setForeground(Color.WHITE);
			x.setBounds(xDim, yDim, 300, 30);
			countriesPlayer2.add(x);
			if (WelcomeWindow.flagOfHuman == 1) {
				GameGUIHumane.frame.getContentPane().add(x);
			} else {
				GameGUISimulation.frame.getContentPane().add(x);
			}
			yDim += 30;
		}
	}
	
	public void generateTroops() {
		for (JLabel jlabel : countriesPlayer1) {
			jlabel.setVisible(false);
		}
		for (JLabel jlabel : countriesPlayer2) {
			jlabel.setVisible(false);
		}
		countriesPlayer1 = new ArrayList<>();
		countriesPlayer2 = new ArrayList<>();
		
		JLabel x;
		xDim = 100;
		yDim = 100;
		
		for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getValue().equals("player1")) {
				x = DefaultComponentFactory.getInstance().createLabel(entry.getKey().name + "  " + RiskMap.armies.get(entry.getKey()));
				x.setFont(new Font("Dialog", Font.BOLD, 16));
				x.setForeground(Color.WHITE);
				x.setBounds(xDim, yDim, 300, 30);
				countriesPlayer1.add(x);
				if (WelcomeWindow.flagOfHuman == 1) {
					GameGUIHumane.frame.getContentPane().add(x);
				} else {
					GameGUISimulation.frame.getContentPane().add(x);
				}
				yDim += 30;
			} 
		}
		
		xDim = 1500;
		yDim = 100;
		
		for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getValue().equals("player2")) {
				x = DefaultComponentFactory.getInstance().createLabel(entry.getKey().name + "  " + RiskMap.armies.get(entry.getKey()));
				x.setFont(new Font("Dialog", Font.BOLD, 16));
				x.setForeground(Color.WHITE);
				x.setBounds(xDim, yDim, 300, 30);
				countriesPlayer2.add(x);
				if (WelcomeWindow.flagOfHuman == 1) {
					GameGUIHumane.frame.getContentPane().add(x);
				} else {
					GameGUISimulation.frame.getContentPane().add(x);
				}
				yDim += 30;
			}
		}
	}
	
	public void control3Buttons() {
		if (WelcomeWindow.flagOfHuman == 1) {
			reinforcement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (WelcomeWindow.flagOfHuman == 1) {
						getReinforcementWindow();
					}
				}
			});
			
			attack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (WelcomeWindow.flagOfHuman == 1) {
						getAttackWindow();
					}
				}
			});
		}
		
		take_turn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (WelcomeWindow.flagOfHuman == 1) {
					//String agent = ChooseAgents.player2_human;
					ChooseAgents.playerVSHuman.reinforce();
					generateTroops();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						Thread.currentThread().interrupt();
					}
					ChooseAgents.playerVSHuman.attack();
					generateTroops();
					//ChooseAgents.playerVSHuman.attack();
				} else {
					validateSimulation();
					generateTroops();
					if (flag_sim_1 == 1) {
						flag_sim_1 = 0;
					} else {
						flag_sim_1 = 1;
					}
					if (flag_sim_2 == 1) {
						flag_sim_2 = 0;
					} else {
						flag_sim_2 = 1;
					}
				}
				validateEndOfGame();
			}
		});
	}
	
	public void validateEndOfGame() {
		String s1 = "player1", s2 = "player2";
		int flag1 = 0, flag2 = 0;
		for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getValue().equals(s1)) {
				flag1 += 1;
			}
		}
		for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (!entry.getValue().equals(s1)) {
				flag2 += 1;
			}
		}
		if (flag1 == RiskMap.owners.entrySet().size()) {
			if (WelcomeWindow.flagOfHuman == 1) {
				JOptionPane.showMessageDialog(GameGUIHumane.frame, "Congratulations, you won :)");
			} else {
				JOptionPane.showMessageDialog(GameGUISimulation.frame, "Congratulations, player 1 won :)");
			}
			
		} else if (flag2 == RiskMap.owners.entrySet().size()) {
			if (WelcomeWindow.flagOfHuman == 1) {
				JOptionPane.showMessageDialog(GameGUIHumane.frame, "Sorry, you lost.");
			} else {
				JOptionPane.showMessageDialog(GameGUISimulation.frame, "Congratulations, player 2 won.");
			}
		}
		if (WelcomeWindow.flagOfHuman == 1) {
			if (ChooseAgents.player2_human.equals("Greedy")) {
				Evaluation evaluate = new Evaluation(GreedyPlayer.l, GreedyPlayer.t);
				evaluate.calceval(GreedyPlayer.l, GreedyPlayer.t);
				evaluate.calceval2(GreedyPlayer.l, GreedyPlayer.t);
				evaluate.calceval3(GreedyPlayer.l, GreedyPlayer.t);
			} else if (ChooseAgents.player2_human.equals("AStar")) {
				Evaluation evaluate = new Evaluation(AStarPlayer.l, AStarPlayer.t);
				evaluate.calceval(AStarPlayer.l, AStarPlayer.t);
				evaluate.calceval2(AStarPlayer.l, AStarPlayer.t);
				evaluate.calceval3(AStarPlayer.l, AStarPlayer.t);
			} else if (ChooseAgents.player2_human.equals("RTAStar")) {
				Evaluation evaluate = new Evaluation(RTAStarPlayer.l, RTAStarPlayer.t);
				evaluate.calceval(RTAStarPlayer.l, RTAStarPlayer.t);
				evaluate.calceval2(RTAStarPlayer.l, RTAStarPlayer.t);
				evaluate.calceval3(RTAStarPlayer.l, RTAStarPlayer.t);
			}
		} else {
			if (ChooseAgents.player1.equals("Greedy") || ChooseAgents.player2.equals("Greedy")) {
				Evaluation evaluate = new Evaluation(GreedyPlayer.l, GreedyPlayer.t);
				evaluate.calceval(GreedyPlayer.l, GreedyPlayer.t);
				evaluate.calceval2(GreedyPlayer.l, GreedyPlayer.t);
				evaluate.calceval3(GreedyPlayer.l, GreedyPlayer.t);
			} else if (ChooseAgents.player1.equals("AStar") || ChooseAgents.player2.equals("AStar")) {
				Evaluation evaluate = new Evaluation(AStarPlayer.l, AStarPlayer.t);
				evaluate.calceval(AStarPlayer.l, AStarPlayer.t);
				evaluate.calceval2(AStarPlayer.l, AStarPlayer.t);
				evaluate.calceval3(AStarPlayer.l, AStarPlayer.t);
			} else if (ChooseAgents.player1.equals("RTAStar") || ChooseAgents.player2.equals("RTAStar")) {
				Evaluation evaluate = new Evaluation(RTAStarPlayer.l, RTAStarPlayer.t);
				evaluate.calceval(RTAStarPlayer.l, RTAStarPlayer.t);
				evaluate.calceval2(RTAStarPlayer.l, RTAStarPlayer.t);
				evaluate.calceval3(RTAStarPlayer.l, RTAStarPlayer.t);
			}
		}
		
		
		int max = 100;
		for (Entry<Country, Integer> entry : RiskMap.armies.entrySet()) {
			if (entry.getValue() > max) {
				if (WelcomeWindow.flagOfHuman == 0) {
					if (ChooseAgents.player1.equals(ChooseAgents.player2)) {
						JOptionPane.showMessageDialog(GameGUISimulation.frame, "Stuck !!!");
					}
				}
			}
		}
	}
	
	public void validateSimulation() {
		if (flag_sim_1 == 1 && flag_sim_2 == 0) {
			ChooseAgents.player_1.reinforce();
			ChooseAgents.player_1.attack();
		} else if (flag_sim_2 == 1 && flag_sim_1 == 0) {
			ChooseAgents.player_2.reinforce();
			ChooseAgents.player_2.attack();
		}
	}
	
	public void getReinforcementWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reinformentWindow = new ReinforcementWindow();
					reinformentWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getAttackWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					attackWindow = new AttackWindow();
					attackWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
