package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.Map.Entry;

public class RTAStarPlayer implements Player {
	static int l = 0, t = 0;
	//HashMap<Country, String> o = new HashMap<Country, String>();
//	HashMap<Country, Integer> a = new HashMap<Country, Integer>();
	//State parent;
	public RTAStarPlayer(String playerName) {
		this.playerName = playerName;
	}
	
	Comparator<State> comparator = new Comparator<State>() {

		@Override
		public int compare(State t1, State t2) {
			if (t1.fn > t2.fn)
				return 1;
			else if (t1.fn < t2.fn)
				return -1;
			return 0;
		}
	};

	public String playerName;
	Heuristic heuristic = new Heuristic();

	public ArrayList<State> getchildren(State state) {
		l++;
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<State> children = new ArrayList<>();
		int t, w;
		String s = "";
		int armyCount = 0;
		Country c = null;
		
		for (Map.Entry<Country, String> entry : state.owners.entrySet()) {
			if ((state.owners.get(entry.getKey())).equals(playerName)) {
				for (Country requiredCountry : WhichMap.readCountries.world) {
					if (requiredCountry.name.equals(entry.getKey().name)) {
						temp = requiredCountry.neighbours;
					}
				}		
				
				//temp = entry.getKey().neighbours;
				for (int j = 0; j < temp.size(); j++) {
					for (Entry<Country, String> entry2 : RiskMap.owners.entrySet()) {
						if (entry2.getKey().name.equals(temp.get(j))) {
							s = entry2.getValue();
							c = entry2.getKey();
						}
					}
							
					
					for (Entry<Country, Integer> entry3 : RiskMap.armies.entrySet()) {
						if (entry3.getKey().name.equals(temp.get(j))) {
							armyCount = entry3.getValue();
						}
					}
							
					
					//State childstate = null  ;
					if ((!s.equals(playerName)) && (armyCount) + 2 <= state.armies.get(entry.getKey())) {
						State childstate = new State(state.owners , state.armies ,state);
						//System.out.println(""state.parent);
						t = childstate.armies.get(entry.getKey());
						w = childstate.armies.get(c);
						int k = t - w;
						int a = k * 2 / 3;
						if (a <= 0) {
							a = 1;
						}
						
						int b = k - a;
						if (b <= 0) {
							b = 1;
						}

						for (Map.Entry<Country, String> entry7 : childstate.owners.entrySet()) {
							if (entry7.getKey().name.equals(temp.get(j))) {
								entry7.setValue(playerName);
							}
						}
						for (Map.Entry<Country, Integer> entry9 : childstate.armies.entrySet()) {
							if (entry9.getKey().name.equals(entry)) {
								entry9.setValue(b);
							}
							if (entry9.getKey().name.equals(temp.get(j))) {
								entry9.setValue(a);
							}
						}
						children.add(childstate);
						/*for (Entry<Country, String> entry22 : childstate.owners.entrySet()) {
							System.out.println("k  ");
							System.out.println(entry22.getValue() + entry22.getKey().name);
						}
						*/
						
					}
					
				
				/*	for (Entry<Country, String> entry22 : childstate.owners.entrySet()) {
						//System.out.println("k  "+ k);
						System.out.println(entry22.getValue() + entry22.getKey().name);
					}
					*/
				//	children.add(childstate);
					
				}
			}
		}
		for(int k=0;k<children.size();k++) {
			System.out.println("fooooooooooooooooooooooooooooooooooooooooooofa "+children.get(k));
		}
		/*for (int k =0 ; k< children.size() ; k++) {
			State ss = null ;
			CopyState(ss,children.get(k));
		//	System.out.println("k  "+ k);
			for (Entry<Country, String> entry : ss.owners.entrySet()) {
				System.out.println("k  "+ k);
				System.out.println(entry.getValue() + entry.getKey().name);
			}
			
			
		}*/
		return children;
	}

	//State state;

	@Override
	public void attack() {
		int cost = 0;
		PriorityQueue<State> frontier = new PriorityQueue<State>(comparator);
		Set<State> explored = new HashSet<State>();
		
		ArrayList<State> children = new ArrayList<State>();
		ArrayList<State> path = new ArrayList<State>();
		
		PriorityQueue<State> leaf = new PriorityQueue<State>(comparator);
		
		State initialState = new State(RiskMap.owners, RiskMap.armies, null);
		frontier.add(initialState);
		while (cost<4) {
			
			State state= new State(frontier.peek().owners,frontier.peek().armies,frontier.peek().parent);
			frontier.remove();
		    explored.add(state);
			// goal state check ?????
			System.out.println("cost = " + cost);
			children.addAll(getchildren(state)); // BEYDRAB HENA
			System.out.println("yalahwyyyyyyyyyyyyyyyyyyyyy " + children.size());
			if (children.size() == 0) {
				leaf.add(state);
				System.out.println("children is emptyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy !!!");
			} 
			else if (cost==3) {
				for(int z=0;z<children.size();z++)
					leaf.add(children.get(z));
			}
				else {
				for (int i = 0; i < children.size(); i++) {		
					state.CopyState(children.get(i));
					state.fn = cost + heuristic.BsrAstar(state, playerName);
					if (!(frontier.contains(state)&&explored.contains(state))) {
						System.out.println("+++++++++++++++++++++++++++++++++++da5alt fel add ana +++++++++++++++++++++++++++++++++++++");
						
						frontier.add(state);
					}
				}
			}
			cost++;
			if (cost==4)break;
		}

		if (!leaf.isEmpty()) {
			System.out.println("leaaaaf"+leaf.size());
			State tempstate=new State(leaf.peek().owners,leaf.peek().armies,leaf.peek().parent);
			path.add(tempstate);
		}
		else {
			System.out.println("fady");
		}
 
		int v = 0;
		System.out.println("3ala2 5alas");
		
		while (path.get(v).parent!=null && v<10){
				State tempstate=new State(path.get(v).parent.owners,path.get(v).parent.armies,path.get(v).parent.parent);
				path.add(tempstate);
			System.out.println("vvvvvvvvvvvvvvvvv " + v);
			v++;
			 }

		for (int l = v - 1; l >= 0; l--) {
			System.out.println("asmaa = " + l);
			// if (l < path.size()) {
			System.out.println("Gee & Menna");
			RiskMap.owners.putAll(path.get(l).owners);
			RiskMap.armies.putAll(path.get(l).armies);
			try {
				TimeUnit.SECONDS.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void reinforce() {
		t++;
		Heuristic h = new Heuristic();
		int i = 0, troops, counter = 0;
		int d = 0;
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
			System.out.println("Trooops:           " + troops);
			troops++;
			RiskMap.armies.put(maxCountry, troops);
			numOfTroops--;

		}
		System.out.println("ASar reinforcement done");
	}

	
public boolean compareHashOwner (HashMap<Country ,String> owner1 ,HashMap<Country ,String> owner2) {
	 boolean flag=true;
	 
		for (Entry<Country, String> entry : owner1.entrySet()) {
			for (Entry<Country, String> entry2 : owner2.entrySet()) {
				if(! (entry.getKey().equals( entry2.getKey() ))) {
					flag = false;
					break;
				}
				if(!( entry.getValue().equals( entry2.getValue() ) ) ) {
					flag = false;
					break;
				}
			}
		}
		 return flag;
}
		
		
		
		public boolean compareHashArmy (HashMap<Country ,Integer> army1 ,HashMap<Country ,Integer> army2) {
			 boolean flag=true;
			 
				for (Entry<Country, Integer> entry : army1.entrySet()) {
					for (Entry<Country, Integer> entry2 : army2.entrySet()) {
						if(! (entry.getKey().equals( entry2.getKey() ))) {
							flag = false;
							break;
						}
						if(!( entry.getValue().equals( entry2.getValue() ) ) ) {
							flag = false;
							break;
						}
					}
				}
				 return flag;
	
	
	 
	 
	
}

	

}