package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class ReinforcementWindow extends CommonGUI {

	JFrame frame;
	JTextField country_for_reinforce, number_of_troops;
	JLabel availableTroops, country, troops;
	JButton btnReinforce, btnExit;
	
	String country_reinforce;
	int desired_troops, numberOfTroops;

	public ReinforcementWindow() {
		country_reinforce = "";
		desired_troops = 0;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.BLACK);
		frame.setBounds(600, 200, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		printingNumberOfTroops();
		handlingTextFields();
		checkReinforce();
	}

	public void printingNumberOfTroops() {
		numberOfTroops = 0;
		for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getValue().equalsIgnoreCase("player1")) {
				numberOfTroops++;
			}
		}
		numberOfTroops = numberOfTroops / 3;
		availableTroops = DefaultComponentFactory.getInstance().createLabel("You have " + numberOfTroops + " troops.");
		availableTroops.setFont(new Font("Dialog", Font.BOLD, 16));
		availableTroops.setForeground(Color.WHITE);
		availableTroops.setBackground(Color.DARK_GRAY);
		availableTroops.setBounds(50, 50, 200, 30);
		frame.getContentPane().add(availableTroops);	
	}

	public void handlingTextFields() {
		
		country = DefaultComponentFactory.getInstance().createLabel("Enter the country for putting your troops:");
		country.setFont(new Font("Dialog", Font.BOLD, 16));
		country.setForeground(Color.WHITE);
		country.setBackground(Color.DARK_GRAY);
		country.setBounds(50, 100, 500, 30);
		frame.getContentPane().add(country);
		
		country_for_reinforce = new JTextField(100);
		country_for_reinforce.setFont(new Font("Dialog", Font.PLAIN, 14));
		country_for_reinforce.setBounds(50, 150, 200, 30);
		frame.getContentPane().add(country_for_reinforce);
		country_for_reinforce.setColumns(10);
		country_for_reinforce.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				country_reinforce = country_for_reinforce.getText().toString();
				System.out.println("Country-> reinforce: " + country_reinforce);
			}
		});
		
		country = DefaultComponentFactory.getInstance().createLabel("Enter the number of troops:");
		country.setFont(new Font("Dialog", Font.BOLD, 16));
		country.setForeground(Color.WHITE);
		country.setBackground(Color.DARK_GRAY);
		country.setBounds(50, 220, 300, 30);
		frame.getContentPane().add(country);
		
		number_of_troops = new JTextField();
		number_of_troops.setFont(new Font("Dialog", Font.PLAIN, 14));
		number_of_troops.setBounds(50, 270, 100, 30);
		frame.getContentPane().add(number_of_troops);
		number_of_troops.setColumns(10);
		
		number_of_troops.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				desired_troops = Integer.parseInt(number_of_troops.getText().toString());
				System.out.println("Country-> reinforce (troops): " + desired_troops);
			}
		});
		
	}
	
	public void checkReinforce() {
		btnReinforce = new JButton("Reinforce");
		btnReinforce.setBackground(Color.WHITE);
		btnReinforce.setForeground(Color.DARK_GRAY);
		btnReinforce.setBounds(300, 310, 120, 30);
		frame.getContentPane().add(btnReinforce);
		
		btnReinforce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (desired_troops <= numberOfTroops) {
					for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
						if (entry.getKey().name.equals(country_reinforce)) {
							if (entry.getValue().equals("player1")) {
								RiskMap.armies.put(entry.getKey(), RiskMap.armies.get(entry.getKey()) + desired_troops);
								System.out.println("New troops: " + RiskMap.armies.get(entry.getKey()));
								modifyTroops(entry.getKey());
								numberOfTroops -= desired_troops;
								System.out.println("Current troops: " + numberOfTroops);
								
								availableTroops.setText("You have " + numberOfTroops + " troops.");
								
								JOptionPane.showMessageDialog(frame,"Successful reinforcement.");
							} else {
								JOptionPane.showMessageDialog(frame,"Reinforcement failed.");
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(frame,"Reinforcement failed.");
				}
				
			}
		});
		
		btnExit = new JButton("Done :)");
		btnExit.setBackground(Color.WHITE);
		btnExit.setForeground(Color.DARK_GRAY);
		btnExit.setBounds(300, 370, 120, 30);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommonGUI.reinformentWindow.frame.setVisible(false);
			}
		});
	}
	
	public void modifyTroops(Country country_name) {
		for (JLabel jlabel : CommonGUI.countriesPlayer1) {
			if (jlabel.getText().contains(country_name.name)) {
				jlabel.setText(country_name.name + "  " + RiskMap.armies.get(country_name));
			}
		}
	}
	
	
}
