package Main;

public class PlayerFactory {
	public static final String PASSIVE = "Passive";
	public static final String AGRESSIVE = "Aggressive";
	public static final String PACIFIST = "Pacifist";
	public static final String GREEDY = "Greedy";
	public static final String ASTAR = "AStar";
	public static final String RTASTAR = "RTAStar";
	public static final String MINMAX = "Minmax";

	/*
	public PlayerFactory(String type1, String playerName1, String type2, String playerName2) {
		if (PASSIVE.equals(type1)) {
			PassivePlayer p = new PassivePlayer(playerName1);
		} else if (AGRESSIVE.equals(type1)) {
			AgressivePlayer p = new AgressivePlayer(playerName1);
		} else if (PACIFIST.equals(type1)) {
			PacifistPlayer p = new PacifistPlayer(playerName1);

		} else if (GREEDY.equals(type1)) {
			GreedyPlayer p = new GreedyPlayer(playerName1);

		} else if (RTASTAR.equals(type1)) {
			RTAStarPlayer p = new RTAStarPlayer(playerName1);

		} else if (ASTAR.equals(type1)) {
			AStarPlayer p = new AStarPlayer(playerName1);

		} else if (MINMAX.equals(type1)) {
			MinmaxPlayer p = new MinmaxPlayer(playerName1);

		}

		if (PASSIVE.equals(type2)) {
			PassivePlayer p = new PassivePlayer(playerName2);
		}

		else if (AGRESSIVE.equals(type2)) {
			AgressivePlayer p = new AgressivePlayer(playerName2);
		} else if (PACIFIST.equals(type2)) {
			PacifistPlayer p = new PacifistPlayer(playerName2);

		} else if (GREEDY.equals(type2)) {
			GreedyPlayer p = new GreedyPlayer(playerName2);

		} else if (RTASTAR.equals(type2)) {
			RTAStarPlayer p = new RTAStarPlayer(playerName2);

		} else if (ASTAR.equals(type2)) {
			AStarPlayer p = new AStarPlayer(playerName2);

		} else if (MINMAX.equals(type2)) {
			MinmaxPlayer p = new MinmaxPlayer(playerName2);

		}
	}*/

	public Player getPlayer(String type, String playerName) {
		if (PASSIVE.equals(type)) {
			PassivePlayer p = new PassivePlayer(playerName);
			return p;
		} else if (AGRESSIVE.equals(type)) {
			AgressivePlayer p = new AgressivePlayer(playerName);
			return p;
		} else if (PACIFIST.equals(type)) {
			PacifistPlayer p = new PacifistPlayer(playerName);
			return p;
		} else if (GREEDY.equals(type)) {
			GreedyPlayer p = new GreedyPlayer(playerName);
			return p;
		} else if (RTASTAR.equals(type)) {
			RTAStarPlayer p = new RTAStarPlayer(playerName);
			return p;
		} else if (ASTAR.equals(type)) {
			AStarPlayer p = new AStarPlayer(playerName);
			System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			return p;
		} else if (MINMAX.equals(type)) {
			MinmaxPlayer p = new MinmaxPlayer(playerName);
			return p;
		}
		
		return null;
	}

}