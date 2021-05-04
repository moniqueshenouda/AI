package Main;

import java.util.ArrayList;
import java.util.Map;

public class GreedyPlayer implements Player {

	public String playerName;
	static int l=0, t=0;

	@Override
	public void attack() {
		Country maxCountryOfEnemy = null, attackCountry = null;
		Heuristic h = new Heuristic();
		String enemyName;
		Boolean canAttack = true;
		int i, numOfArmies, enemyArmies, max = 0;
		ArrayList<Country> myCountries = new ArrayList<Country>();
		ArrayList<Country> CanAttackCountries = new ArrayList<Country>();

		if (playerName == "player1") {
			enemyName = "player2";
		} else {
			enemyName = "player1";
		}

		while (canAttack) {
			attackCountry = null;
			maxCountryOfEnemy = h.BsrMax(RiskMap.owners, RiskMap.armies, enemyName);
			myCountries = getMyCountriesAround(maxCountryOfEnemy); // make a list of my countries around the targeted
																	// country
			enemyArmies = RiskMap.armies.get(maxCountryOfEnemy);

			for (i = 0; i < myCountries.size(); i++) {
				numOfArmies = RiskMap.armies.get(myCountries.get(i));
				if (numOfArmies > (enemyArmies + 1)) { // can attack
					if (numOfArmies > max) { // has the most num of arrmies
						System.out.println("insideeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
						attackCountry = myCountries.get(i);
						max = numOfArmies;

					}

				}
			}
			if (attackCountry != null) {
				RiskMap.owners.put(maxCountryOfEnemy, playerName);
				int newArmies = max - enemyArmies;
				l++;
				

				if (newArmies == 2) {
					RiskMap.armies.put(attackCountry, 1);
					RiskMap.armies.put(maxCountryOfEnemy, 1);
					//System.out.println("attackkkkkkkkkk");
				}

				else {
					int army1 = (2 / 3) * newArmies;
					if (army1 == 0) {
						army1 = 1;
					}
					int army2 = newArmies - army1;
					if (army2 == 0) {
						army2 = 1;
					}
					RiskMap.armies.put(attackCountry, army2);
					RiskMap.armies.put(maxCountryOfEnemy, army1);
					//System.out.println("attackkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				}
				//System.out.println("attackkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

			} else {
				canAttack = false;
			}

		}

		// TODO Auto-generated method stub

	}

	@Override
	public void reinforce() {
		Heuristic h = new Heuristic();
		int i = 0, troops, counter = 0;
		int d = 0;
		t++;
		int numOfTroops = 0;
		for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			String name = RiskMap.owners.get(entry.getKey());
			if (name == playerName) {
				d++;
			}
		}
		// d now has number of all my territories
		numOfTroops = d / 3;
		if (numOfTroops < 3) {
			numOfTroops = 3;
		}

		Country maxCountry;
		while (numOfTroops > 0) {
			maxCountry = h.BsrMax(RiskMap.owners, RiskMap.armies, playerName);
			troops = RiskMap.armies.get(maxCountry);
			troops++;
			RiskMap.armies.put(maxCountry, troops);
			numOfTroops--;

		}

		// TODO Auto-generated method stub

	}

	public ArrayList<Country> getMyCountriesAround(Country c) {
		// c.neighbours.get(index);
		int j, k;
		String currentNeighbourName;
		Country currentCountry = null;
		ArrayList<Country> myCountries = new ArrayList<Country>();

		for (j = 0; j < c.neighbours.size(); j++) { // loop on its neighbours
			currentNeighbourName = c.neighbours.get(j);
			for (k = 0; k < WhichMap.readCountries.world.size(); k++) { // just need to make sure that the neigbour
				                                                        // isn't his territory
				for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
					if (WhichMap.readCountries.world.get(k).name.equals(entry.getKey().name)) {
						currentCountry = entry.getKey();
					}
				}
				//currentCountry = WhichMap.readCountries.world.get(k);
				if (currentCountry.name.equals(currentNeighbourName)) {
					if (RiskMap.owners.get(currentCountry).equals(playerName)) {
						myCountries.add(currentCountry);
					}
				}

			}
		}
		return myCountries;
	}

	public GreedyPlayer(String playerName) {
		this.playerName = playerName;

	}

}