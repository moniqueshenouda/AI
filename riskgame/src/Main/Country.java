package Main;

import java.io.IOException;
import java.util.ArrayList;

public class Country implements Comparable<Country>{
	
	public String name;
	public ArrayList<String> neighbours;
	public int key;
	
	public Country(String name, ArrayList<String> neighbours, int key) throws IOException {
		this.name = name;
		this.neighbours = neighbours;
		this.key = key;
	}

	@Override
	public int compareTo(Country o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
