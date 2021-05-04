package Main;

import java.util.ArrayList;
import java.util.Random;

public class Randomize {

	static ArrayList<Country> player1Countries;
	static ArrayList<Country> player2Countries;

	public Randomize() {
		player1Countries = new ArrayList<Country>();
		player2Countries = new ArrayList<Country>();
	}

	public void randomize(ArrayList<Country> array) {

		Random rand = new Random();
		int i = 0, n, m, counter = 20;
		String playerName = null;
		Country c;
		while (array.size() != 0) {
			playerName = "player1";
			/*
			 * for (int j=0;j<array.size();j++) { System.out.println("index " +j
			 * +"  countryname " + array.get(j).name); }
			 */
			n = rand.nextInt(array.size() - 1) + 0;
			// System.out.println("player 1 n " + n + " size " +array.size());
			c = array.get(n);
			array.remove(n);
			RiskMap.owners.put(c, playerName);
			player1Countries.add(c);
			RiskMap.armies.put(c, 1);
			if (array.size() != 0) {

				playerName = "player2";
				/*
				 * for (int j=0;j<array.size();j++) { System.out.println("index " +j
				 * +"  countryname " + array.get(j).name); }
				 */
				if (array.size() == 1) {
					// System.out.println("player 2 n " + "0"+ " size " +array.size());
					c = array.get(0);
					player2Countries.add(c);
					RiskMap.owners.put(c, playerName);
					RiskMap.armies.put(c, 1);
					array.remove(0);
					// System.out.println("player 2 n " + "0"+ " size " +array.size());
					break;

				}

				n = rand.nextInt(array.size() - 1) + (0);
				// System.out.println("player 2 n " + n+ " size " +array.size());
				c = array.get(n);
				array.remove(n);
				player2Countries.add(c);
				RiskMap.owners.put(c, playerName);
				RiskMap.armies.put(c, 1);
			}

		}

		while (counter != 0) {

			if (counter > 5) {
				n = rand.nextInt(5) + 1;
				m = rand.nextInt(player1Countries.size()) + 0;
				c = player1Countries.get(m);
				int troops = RiskMap.armies.get(c);
				troops = troops + n;
				RiskMap.armies.put(c, troops);
				counter = counter - n;
			} else {
				n = rand.nextInt(counter) + 1;
				m = rand.nextInt(player1Countries.size()) + 0;
				c = player1Countries.get(m);
				int troops = RiskMap.armies.get(c);
				troops = troops + n;
				RiskMap.armies.put(c, troops);
				counter = counter - n;

			}

		}
		counter = 20;
		while (counter != 0) {

			if (counter > 5) {
				n = rand.nextInt(5) + 1;
				m = rand.nextInt(player2Countries.size()) + 0;
				c = player2Countries.get(m);
				int troops = RiskMap.armies.get(c);
				troops = troops + n;
				RiskMap.armies.put(c, troops);
				counter = counter - n;
			} else {
				n = rand.nextInt(counter) + 1;
				m = rand.nextInt(player2Countries.size()) + 0;
				c = player2Countries.get(m);
				int troops = RiskMap.armies.get(c);
				troops = troops + n;
				RiskMap.armies.put(c, troops);
				counter = counter - n;

			}
		}

	}

	/*
	public void simulation(String type1,String type2) {
		PlayerFactory  playerFactory = new PlayerFactory();
		Player player1;
		Player player2;
		player1 = playerFactory.getPlayer("player1", type1);
		player2 = playerFactory.getPlayer("player2", type2);
		
		while(RiskMap.owners.containsValue("player1")&&RiskMap.owners.containsValue("player2")) {
		player1.reinforce();
		player1.attack();
		if(!RiskMap.owners.containsValue("player2")) {
			break;
		}
		player2.reinforce();
		player2.attack();
		}
	}*/
	
	public void printTroops() {
		int sum = 0;
		int troops;
		for (int i = 0; i < player1Countries.size(); i++) {
			troops = RiskMap.armies.get(player1Countries.get(i));
			System.out.println(player1Countries.get(i).name + " " + troops);
			sum += troops;
		}
		System.out.println(sum);
		sum = 0;
		for (int i = 0; i < player2Countries.size(); i++) {
			troops = RiskMap.armies.get(player2Countries.get(i));
			System.out.println(player2Countries.get(i).name + " " + troops);
			sum += troops;
		}
		System.out.println(sum);
	}
}
