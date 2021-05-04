package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class GameGUISimulation extends CommonGUI {

	public static JFrame frame;
	JLabel firstAgent, second_agent;

	public GameGUISimulation() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1800, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		generateImage();
		frame.getContentPane().add(labelForImage);
		frame.getContentPane().setLayout(null);
		createTwoLabelsWithSecondAgent();
		generateFirstTroops();
	}

	public void createLabelFirstAgent() {
		firstAgent = DefaultComponentFactory.getInstance().createLabel(ChooseAgents.player1);
		firstAgent.setFont(new Font("Dialog", Font.BOLD, 18));
		firstAgent.setForeground(Color.WHITE);
		firstAgent.setBounds(100, 30, 150, 30);
		frame.getContentPane().add(firstAgent);
	}
	
	public void createSecondAgentSim() {
		System.out.println("Second player: " + ChooseAgents.player2);
		second_agent = DefaultComponentFactory.getInstance().createLabel(ChooseAgents.player2);
		second_agent.setFont(new Font("Dialog", Font.BOLD, 18));
		second_agent.setBounds(1500, 30, 150, 30);
		second_agent.setForeground(Color.WHITE);
		second_agent.setBackground(Color.WHITE);
	}
	
	public void createTwoLabelsWithSecondAgent() {
		createReinforce_Attack_takeTurn();
		createLabelFirstAgent();
		if (WelcomeWindow.flagOfHuman == 1) {
			frame.getContentPane().add(reinforcement);
			frame.getContentPane().add(attack);
		}
		frame.getContentPane().add(take_turn);
		createSecondAgentSim();
		frame.getContentPane().add(second_agent);
	}
}
