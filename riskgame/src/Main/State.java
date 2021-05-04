package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class State implements Comparable<State>{

	HashMap<Country, String> owners = new HashMap<Country, String>();
	HashMap<Country, Integer> armies = new HashMap<Country, Integer>();
	float fn;
	int utility;
	State parent;
	//String p = state.

	public State(HashMap<Country, String> owners, HashMap<Country, Integer> armies, State parent) {
		this.owners.putAll(owners);
		this.armies.putAll(armies);
		this.parent = parent;
	//	this.parent.CopyState(parent);
	}


	@Override
	public int compareTo(State o) {
		// TODO Auto-generated method stub
		if(this.owners.equals(o.owners) && this.armies.equals(o.armies))
		return 1;
		else return 0;
	}

	
	public void CopyState ( State s2) {
	
		//s1.owners.putAll(s2.owners);
	//	s1.armies.putAll(s2.armies);
		//if(s2==null) return;
		this.parent=s2.parent;
		//this.parent.CopyState(s2.parent);
	
		for (Map.Entry<Country, String> entry : s2.owners.entrySet()) {
			this.owners.put(entry.getKey(), entry.getValue());
			
		}
		for (Map.Entry<Country, Integer> entry : s2.armies.entrySet()) {
			this.armies.put(entry.getKey(), entry.getValue());
			
		}
	}
	
	
	
}