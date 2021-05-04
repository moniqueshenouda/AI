package Main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

class PacifistPlayer implements Player {
	ArrayList<Country> myCountries;
	ArrayList<String> neighbours;
	public String playerName;

	PacifistPlayer(String playerName) {
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
		int min = 999;
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
				if (armies < min) {
					min = armies; // my country with the least armies
					c = entry5.getKey();
				}
			}

		}
		if (num < 3) {
			int x = min;
			x = x + 3;
			RiskMap.armies.put(c, x);
		} else {
			int x = min;
			x = x + num;
			RiskMap.armies.put(c, x);

		}
	}

	@Override
	public void attack() {
		int min = 100;
		int w, t;
		int enemyArmies = 0, myArmies = 0;
		String alpha = playerName;
		String defend_country = "", attack_country = "";

		for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getValue().equals(alpha)) {
				myCountries.add(entry.getKey());
			}
		}

		for (Country currentCountry : myCountries) {
			neighbours = getNeighboursOfCurrentCountry(currentCountry);

			for (String s : neighbours) {
				enemyArmies = getArmies(s);
				myArmies = getArmies(currentCountry.name);
				if (myArmies > enemyArmies && enemyArmies < min) {
					min = enemyArmies;
					defend_country = s;

					System.out.println("Attack to: " + defend_country);
					attack_country = currentCountry.name;
					System.out.println("Attack from: " + attack_country);
				}
			}
		}

		if (!defend_country.equals("") && !attack_country.equals("")) {
			t = myArmies;
			w = enemyArmies;
			int k = t - w;
			int a = k * 2 / 3;
			int b;
			if (a == 0) {
				a = 1;
				b = 1;
			} else {
				b = k - a;
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
		} else {

			System.out.println("No Available moves..[Can't Attack]");
		}

	}

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