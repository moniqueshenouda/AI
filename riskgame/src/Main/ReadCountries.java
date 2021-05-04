package Main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadCountries {

	public BufferedReader input_egypt;
	public BufferedReader input_usa;

	public StringTokenizer st;
	public String s;
	public String[] sArr;

	public int counter;
	public static int flagOfMap;

	public static ArrayList<Country> world;

	public ReadCountries() throws FileNotFoundException {
		world = new ArrayList<Country>();
		input_egypt = new BufferedReader(
				new InputStreamReader(new FileInputStream("C:\\Users\\User\\Downloads\\Risk - Copy\\Risk\\MapEgy.txt")));
		input_usa = new BufferedReader(
				new InputStreamReader(new FileInputStream("C:\\Users\\User\\Downloads\\Risk - Copy\\Risk\\MapEgy.txt")));
		counter = 0;
	}

	public void createListOfCountries() throws IOException {
		boolean read;
		if (WhichMap.map_choice == 0) {
			read = readLineEgypt();
			while (read) {
				read = readLineEgypt();
			}
		} else {
			read = readLineUSA();
			while (read) {
				read = readLineUSA();
			}
		}
	}

	public boolean readLineEgypt() throws IOException {
		if ((s = input_egypt.readLine()) == null) {
			return false;
		}

		st = new StringTokenizer(s, " ", false);
		sArr = new String[st.countTokens()];

		for (int i = 0; i < sArr.length; i++) {
			sArr[i] = st.nextToken();
		}

		ArrayList<String> neighboursOfCountry = new ArrayList<>();
		for (int i = 1; i < sArr.length; i++) {
			neighboursOfCountry.add(sArr[i]);
		}
		world.add(new Country(sArr[0], neighboursOfCountry, counter));
		counter++;

		return true;
	}

	public boolean readLineUSA() throws IOException {
		if ((s = input_usa.readLine()) == null) {
			return false;
		}

		st = new StringTokenizer(s, " ", false);
		sArr = new String[st.countTokens()];

		for (int i = 0; i < sArr.length; i++) {
			sArr[i] = st.nextToken();
		}

		ArrayList<String> neighboursOfCountry = new ArrayList<>();
		for (int i = 1; i < sArr.length; i++) {
			neighboursOfCountry.add(sArr[i]);
		}
		world.add(new Country(sArr[0], neighboursOfCountry, counter));
		counter++;

		return true;
	}

	public void printCountries() {
		for (int i = 0; i < world.size(); i++) {
			System.out.print(world.get(i).name);
			for (int j = 0; j < world.get(i).neighbours.size(); j++) {
				System.out.print(" " + world.get(i).neighbours.get(j) + " ");
			}
			System.out.println();
		}
		System.out.println("Number of territories: " + counter + "\n\n");
	}
}
