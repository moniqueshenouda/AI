package Main;

import java.util.Map;

public class PassivePlayer implements Player {

	public String playerName;

	PassivePlayer(String playerName) {
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
		// TODO Auto-generated method stub

	}
}