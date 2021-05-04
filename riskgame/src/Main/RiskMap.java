package Main;

import java.util.HashMap;

public class RiskMap {

	static HashMap<Country, String> owners = new HashMap<Country, String>();
	static HashMap<Country, Integer> armies = new HashMap<Country, Integer>();

	public RiskMap(HashMap<Country, String> owners, HashMap<Country, Integer> armies) {
		this.owners = owners;
		this.armies = armies;
	}

	public String getCountryOwner(Country country) {
		return this.owners.get(country);
	}

	public void setCountryOwner(Country country, String owner) {
		try {
			this.owners.put(country, owner);
		} catch (Exception e) {
			System.out.println("RMAPe " + e);
		}
	}

	public int getCountryArmies(Country country) {
		return this.armies.get(country);
	}

	public void addCountryArmies(Country country, int numArmies) {
		this.armies.put(country, this.armies.get(country) + numArmies);
	}

	public void setCountryArmies(Country country, int numArmies) {
		this.armies.put(country, numArmies);
	}

	private void loadMap() {
		/*
		 * 
		 * for (Country country : Country.values()) { country.init();
		 * this.owners.put(country, null); this.armies.put(country, 0); }
		 */
	}
}
