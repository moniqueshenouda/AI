package Main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class AgressivePlayer extends CommonGUI implements Player {
	ArrayList<Country> myCountries;
	ArrayList<String> neighbours;
	public String playerName;

	public AgressivePlayer(String playerName) {
		myCountries = new ArrayList<Country>();
		neighbours = new ArrayList<String>();
		this.playerName = playerName;

	}

	@Override
	public void reinforce() {
		int count = 0;
		int d = 0;
		int num = 0;
		String name;
		int max = 0;
		for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			name = RiskMap.owners.get(entry.getKey());
			if (name == playerName) {
				d++;
			}
		}
		// d now has number of all my territories
		num = d / 3;
		Country c = null;

		for (Map.Entry<Country, Integer> entry5 : RiskMap.armies.entrySet()) {
			name = RiskMap.owners.get(entry5.getKey());
			if (name == playerName) {
				int armies = entry5.getValue();
				if (armies > max) {
					max = armies; // my country with the least armies
					c = entry5.getKey();
				}
			}

		}
		if (num < 3) {
			int x = max;
			x = x + 3;
			RiskMap.armies.put(c, x);
		} else {
			int x = max;
			x = x + num;
			RiskMap.armies.put(c, x);

		}
	}

	@Override
	public void attack() {
		int max = 0;
		int w, t;
		int flag = 1;
		int enemyArmies = 0, myArmies = 0;
		String alpha = playerName;
		String defend_country = "", attack_country = "";

		for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getValue().equals(alpha)) {
				myCountries.add(entry.getKey());
			}
		}
		while (flag == 1) {
			flag = 0;
			for (Country currentCountry : myCountries) {
				neighbours = getNeighboursOfCurrentCountry(currentCountry);

				for (String s : neighbours) {
					enemyArmies = getArmies(s);
					myArmies = getArmies(currentCountry.name);
					if (myArmies > enemyArmies && enemyArmies > max) {
						max = enemyArmies;
						defend_country = s;

						System.out.println("Attack to: " + defend_country);
						attack_country = currentCountry.name;
						System.out.println("Attack from: " + attack_country);
					}
				}

				if (!defend_country.equals("") && !attack_country.equals("")) {
					t = myArmies;
					w = enemyArmies;
					int k = t - w;
					int a = k * 2 / 3;
					int b = 0;
					if (a <= 0) {
						a = 1;
						b = 1;
					} else {
						if (b <= 0) {
							b = 1;
						} else {
							b = k - a;
						}
					}
					System.out.println("Valid Move YOU WON");

					for (Map.Entry<Country, String> entry7 : RiskMap.owners.entrySet()) {
						if (entry7.getKey().name.equals(defend_country)) {
							entry7.setValue(playerName);
						}
					}
					for (Map.Entry<Country, Integer> entry9 : RiskMap.armies.entrySet()) {
						if (entry9.getKey().name.equals(attack_country)) {
							entry9.setValue(b);
						}
						if (entry9.getKey().name.equals(defend_country)) {
							entry9.setValue(a);
						}
					}
					flag = 1;
					generateTroops();
					defend_country = "";
					attack_country = "";

				}

				else {
					System.out.println("No Available moves..[Can't Attack]");
				}

			}
		}
	}

	// }

	public ArrayList<String> getNeighboursOfCurrentCountry(Country country) {
		ArrayList<String> neighbours = new ArrayList<>();

		for (Country c : WhichMap.readCountries.world) {
			if (c.name.equals(country.name)) {
				for (String s : c.neighbours) {
					boolean checkNeighbour = checkMycountry(s);
					if (checkNeighbour) {
						neighbours.add(s);
					}
				}
			}
		}

		return neighbours;
	}

	public boolean checkMycountry(String s) {
		for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getKey().name.equals(s) && !entry.getValue().equals(playerName)) {
				return true;
			}
		}
		return false;
	}

	public int getArmies(String s) {
		for (Map.Entry<Country, Integer> entry : RiskMap.armies.entrySet()) {
			if (entry.getKey().name.equals(s)) {
				return entry.getValue();
			}
		}
		return 0;
	}

}
