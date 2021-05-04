package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Heuristic {

	public float BsrCountry(HashMap<Country, String> owners, HashMap<Country, Integer> armies, Country country) {
		int SumOfTroops = 0;
		int j = 0, k = 0;
		String owner;
		int numOfArmiesInCountry = armies.get(country);
		owner = owners.get(country);
		Country currentCountry = null;
		String currentNeighbourName;
		
		for (j = 0; j < country.neighbours.size(); j++) { // loop on its neighbours
			currentNeighbourName = country.neighbours.get(j);
			Country c = null;
			for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
				if (entry.getKey().name.equals(currentNeighbourName)) {
					c = entry.getKey();
				}
			}
			for (k = 0; k < WhichMap.readCountries.world.size(); k++) { // just need to make sure that the neigbour
																		// isn't his territory
				for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
					if (entry.getKey().name.equals(WhichMap.readCountries.world.get(k).name)) {
						currentCountry = entry.getKey();
					}
				}
				//currentCountry = WhichMap.readCountries.world.get(k);
				if (currentCountry.name.equals(currentNeighbourName)) {
					if (!owners.get(currentCountry).equals(owner)) {
						SumOfTroops = SumOfTroops + armies.get(currentCountry); // sum number of troops of the
																					// surronding enemies
						break;
					}
				}
			}
		}
		
		float result = (float) SumOfTroops / (float) numOfArmiesInCountry;
		//System.out.println(result);
		return result;

	}

	public float BsrState(State s, String playerName) {
		int i;
		float sum = 0;
		Country currentCountry = null;
		for (i = 0; i < WhichMap.readCountries.world.size(); i++) {
			for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
				if (entry.getKey().name.equals(WhichMap.readCountries.world.get(i).name)) {
					currentCountry = entry.getKey();
				}
			}
			//currentCountry = WhichMap.readCountries.world.get(i);
			if (s.owners.get(currentCountry).equals(playerName)) {
				sum = sum + BsrCountry(s.owners, s.armies, currentCountry);
			}
		}

		return sum;
	}

	public float BsrAstar(State s, String playerName) {
		System.out.println("ana da5alt BSRASTAR");
		//for (Entry<Country, String> entry : s.owners.entrySet()) {
		//	System.out.println("herefl heuristiiic");
		//	System.out.println(entry.getValue() + entry.getKey().name);
		//}
		int i;
		float sum = 0;
		Country currentCountry = null;
		for (i = 0; i < WhichMap.readCountries.world.size(); i++) {
			for (Entry<Country, String> entry : RiskMap.owners.entrySet()) {
				if (entry.getKey().name.equals(WhichMap.readCountries.world.get(i).name)) {
					currentCountry = entry.getKey();
				}
			}
			//System.out.println("fmll" + s.owners.get(currentCountry));
			if (!(s.owners.get(currentCountry).equals(playerName))) {
				sum = sum + BsrCountry(s.owners, s.armies, currentCountry);
			}
		}
		System.out.println("Sum: " + sum);
		return sum;
	}

	public Country BsrMax(HashMap<Country, String> owners, HashMap<Country, Integer> armies, String playerName) {
		int i;
		float max = -100;
		float temp;
		Country currentCountry = null;
		int maxCountry = 0;
		
		for (i = 0; i < WhichMap.readCountries.world.size(); i++) {
			for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
				if (entry.getKey().name.equals(WhichMap.readCountries.world.get(i).name)) {
					currentCountry = entry.getKey();
					
				}
			}
			//currentCountry = WhichMap.readCountries.world.get(i);
			if (owners.get(currentCountry).equals(playerName)) {
				temp = BsrCountry(owners, armies, currentCountry);
				if (temp > max) {
					max = temp;
					maxCountry = i;

				}
			}
		}
		Country c = null;
		String s = WhichMap.readCountries.world.get(maxCountry).name;
		for (Map.Entry<Country, String> entry : RiskMap.owners.entrySet()) {
			if (entry.getKey().name.equals(s)) {
				c = entry.getKey();
			}
		}
		System.out.println(c.name);
		return c;
	}

	public Country BsrMini(HashMap<Country, String> owners, HashMap<Country, Integer> armies, String playerName) {
		int i;
		float mini = 1000;
		float temp;
		Country currentCountry;
		int miniCountry = 0;
		for (i = 0; i < WhichMap.readCountries.world.size(); i++) {
			currentCountry = WhichMap.readCountries.world.get(i);
			if (owners.get(currentCountry).equals(playerName)) {
				temp = BsrCountry(owners, armies, currentCountry);
				if (temp < mini) {
					mini = temp;
					miniCountry = i;

				}

			}
		}

		return WhichMap.readCountries.world.get(i);
	}

	public Heuristic() {
	}

}
