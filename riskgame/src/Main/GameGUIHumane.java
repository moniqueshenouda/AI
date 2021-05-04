package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class GameGUIHumane extends CommonGUI {

	public static JFrame frame;
	JLabel human, second_agent;

	public GameGUIHumane() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1800, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		generateImage();
		frame.getContentPane().add(labelForImage);
		frame.getContentPane().setLayout(null);
		
		createLabelHuman();
		createTwoLabelsWithSecondAgent();
		generateFirstTroops();
	}

	public void createLabelHuman() {
		human = DefaultComponentFactory.getInstance().createLabel("Human");
		human.setFont(new Font("Dialog", Font.BOLD, 18));
		human.setForeground(Color.WHITE);
		human.setBounds(100, 30, 100, 30);
		frame.getContentPane().add(human);
	}
	
	public void createSecondAgentHuman() {
		System.out.println("Second player: " + ChooseAgents.player2_human);
		second_agent = DefaultComponentFactory.getInstance().createLabel(ChooseAgents.player2_human);
		second_agent.setFont(new Font("Dialog", Font.BOLD, 18));
		second_agent.setBounds(1500, 30, 150, 30);
		second_agent.setForeground(Color.WHITE);
		second_agent.setBackground(Color.WHITE);
	}
	
	public void createTwoLabelsWithSecondAgent() {
		createReinforce_Attack_takeTurn();
		frame.getContentPane().add(reinforcement);
		frame.getContentPane().add(attack);
		frame.getContentPane().add(take_turn);
		createSecondAgentHuman();
		frame.getContentPane().add(second_agent);
	}
	
}
