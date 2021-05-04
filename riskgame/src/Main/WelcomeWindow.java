package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomeWindow {
	
	static WhichMap windowOfMap;
	
	JFrame frame;
	JButton btnNewGame, btnSimulation;
	public static int flagOfHuman;
	
	public WelcomeWindow() {
		initialize();
		flagOfHuman = 0;
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		addTextWelcome();
		newGameCriteria();
		simulationCriteria();
	}
	
	public void addTextWelcome() {
		Label welcome = new Label("Welcome to risk :)");
		welcome.setForeground(new Color(255, 255, 255));
		welcome.setBounds(75, 26, 150, 21);
		frame.getContentPane().add(welcome);
	}
	
	public void newGameCriteria() {
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flagOfHuman = 1;
				Main.welcome_window.frame.setVisible(false);
				getMapWindow();
			}
		});
		btnNewGame.setForeground(new Color(0, 0, 0));
		btnNewGame.setBackground(new Color(255, 255, 255));
		btnNewGame.setBounds(70, 80, 150, 50);
		frame.getContentPane().add(btnNewGame);
	}
	
	public void simulationCriteria() {
		btnSimulation = new JButton("Simulation");
		btnSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.welcome_window.frame.setVisible(false);
				getMapWindow();
			}
		});
		btnSimulation.setForeground(new Color(0, 0, 0));
		btnSimulation.setBackground(new Color(255, 255, 255));
		btnSimulation.setBounds(70, 180, 150, 50);
		frame.getContentPane().add(btnSimulation);
	}
	
	public void getMapWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowOfMap = new WhichMap();
					windowOfMap.frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
