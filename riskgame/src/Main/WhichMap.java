package Main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WhichMap extends ChooseAgents {

	JFrame frame;
	JButton egyptMap, usaMap;

	static ChooseAgents agents_window;
	static int map_choice;

	static ReadCountries readCountries;
	static ArrayList<Country> array;
	Randomize randomize;

	public WhichMap() {
		initialize();
		map_choice = 0;
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		egyptButton();
		usaButton();

	}

	public void addTextMap() {
		Label welcome = new Label("Which map?!");
		welcome.setForeground(new Color(255, 255, 255));
		welcome.setBounds(75, 26, 150, 21);
		frame.getContentPane().add(welcome);
	}

	public void egyptButton() {
		egyptMap = new JButton("Egypt");
		egyptMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeWindow.windowOfMap.frame.setVisible(false);
				getAgentsWindow();
				map_choice = 0;
				try {
					readCountries();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		egyptMap.setForeground(new Color(0, 0, 0));
		egyptMap.setBackground(new Color(255, 255, 255));
		egyptMap.setBounds(70, 80, 150, 50);
		frame.getContentPane().add(egyptMap);
	}

	public void usaButton() {
		usaMap = new JButton("USA");
		usaMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeWindow.windowOfMap.frame.setVisible(false);
				getAgentsWindow();
				map_choice = 1;
				try {
					readCountries();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		usaMap.setForeground(new Color(0, 0, 0));
		usaMap.setBackground(new Color(255, 255, 255));
		usaMap.setBounds(70, 180, 150, 50);
		frame.getContentPane().add(usaMap);
	}

	public void getAgentsWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agents_window = new ChooseAgents();
					agents_window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void readCountries() throws IOException {
		readCountries = new ReadCountries();
		readCountries.createListOfCountries();
		readCountries.printCountries();

		array = new ArrayList<Country>(readCountries.world);

		randomize = new Randomize();
		randomize.randomize(array);
		randomize.printTroops();
	}

}
