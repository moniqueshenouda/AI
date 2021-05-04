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

public class AttackWindow extends CommonGUI {

	JFrame frame;
	JTextField country_attack_from, country_attack_to;
	JLabel attack_from_user, attack_to_user;
	JButton btnAttack, btnExit;
	
	String country_from, country_to;

	public AttackWindow() {
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
		
		handlingTextFields();
		checkAttack();
	}

	public void handlingTextFields() {
		attack_from_user = DefaultComponentFactory.getInstance().createLabel("Enter the country to attack from:");
		attack_from_user.setFont(new Font("Dialog", Font.BOLD, 16));
		attack_from_user.setForeground(Color.WHITE);
		attack_from_user.setBackground(Color.DARK_GRAY);
		attack_from_user.setBounds(50, 50, 400, 30);
		frame.getContentPane().add(attack_from_user);
		
		country_attack_from = new JTextField(100);
		country_attack_from.setFont(new Font("Dialog", Font.PLAIN, 14));
		country_attack_from.setBounds(50, 100, 200, 30);
		frame.getContentPane().add(country_attack_from);
		country_attack_from.setColumns(10);
		country_attack_from.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				country_from = country_attack_from.getText().toString();
				System.out.println("Country-> attack from: " + country_from);
			}
		});
		
		attack_to_user = DefaultComponentFactory.getInstance().createLabel("Enter the country to attack to:");
		attack_to_user.setFont(new Font("Dialog", Font.BOLD, 16));
		attack_to_user.setForeground(Color.WHITE);
		attack_to_user.setBackground(Color.DARK_GRAY);
		attack_to_user.setBounds(50, 150, 300, 30);
		frame.getContentPane().add(attack_to_user);
		
		country_attack_to = new JTextField(100);
		country_attack_to.setFont(new Font("Dialog", Font.PLAIN, 14));
		country_attack_to.setBounds(50, 200, 200, 30);
		frame.getContentPane().add(country_attack_to);
		country_attack_to.setColumns(10);
		country_attack_to.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				country_to = country_attack_to.getText().toString();
				System.out.println("Country-> attack to: " + country_to);
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
				CommonGUI.attackWindow.frame.setVisible(false);
			}
		});
	}
	
	public void checkAttack() {
		btnAttack = new JButton("Attack");
		btnAttack.setBackground(Color.WHITE);
		btnAttack.setForeground(Color.DARK_GRAY);
		btnAttack.setBounds(300, 310, 120, 30);
		frame.getContentPane().add(btnAttack);
		
		btnAttack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Country one = null, two = null;
				int flag1 = 0, flag2 = 0, flag3 = 0;
				for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
					if (entry.getKey().name.equalsIgnoreCase(country_from) && entry.getValue().equals("player1")) {
						one = entry.getKey();
						System.out.println("country 1: " + one.name);
						flag1 = 1;
					} 
				}
				for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
					if (entry.getKey().name.equalsIgnoreCase(country_to) && entry.getValue().equals("player2")) {
						two = entry.getKey();
						System.out.println("country 2: " + two.name);
						flag2 = 1;
					} 
				}
				if (flag2 == 1) {
					for (Country country : ReadCountries.world) {
						if (one.name.equals(country.name)) {
							for (String s : country.neighbours) {
								if (two.name.equals(s)) {
									flag3 = 1;
									System.out.println("is a neighbour");
								}
							}
						}
					}
				}
				if (flag1 == 1 && flag2 == 1 && flag3 == 1) {
					int armies1 = RiskMap.armies.get(one);
					int armies2 = RiskMap.armies.get(two);
					System.out.println("Armies one: " + armies1);
					System.out.println("Armies two: " + armies2);
					if (armies1 >= (armies2 + 2)) {
						modifyAfterAttack(one, two, armies1, armies2);
						JOptionPane.showMessageDialog(frame,"Attack successful.");
					}
				} else {
					JOptionPane.showMessageDialog(frame,"Attack failed.");
				}
			}
		});
	}
	
	public void modifyAfterAttack(Country one, Country two, int armies1, int armies2) {
		int difference = armies1 - armies2;
		int armies1_next = difference * 1/3;
		if (armies1_next == 0) {
			armies1_next = 1;
		}
		int armies2_next = difference - armies1_next;
		RiskMap.owners.put(two, "player1");
		RiskMap.armies.put(one, armies1_next);
		RiskMap.armies.put(two, armies2_next);
		
		generateTroops();
	}

}
