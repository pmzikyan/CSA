import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *	Population - Program that utilizes Comparator/Comparable and different types
 *	of sorting to sort US cities in different ways
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Petros Mzikyan
 *	@since	12/2/2024
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	private final String FORMAT = "%5s%-23s%-23s%-15s%10s\n";
	
	public static void main(String[] args) {
		Population pop = new Population();
		pop.fileReaderLoader();
		pop.run();
	}

	/**
	 *	Runs Population in a loop until user quits
	 * 	Utilizes other methods and classes to sort the cities field variable
	 */
	public void run()
	{
		Prompt ask = new Prompt();
		SortMethods sort = new SortMethods();
		printIntroduction();
		
		System.out.println(cities.size() + " cities in database");

		boolean loop = true;
		while (loop)
		{
			System.out.println();
			printMenu();
			int selection = ask.getInt("Enter selection");
			while (!(selection == 9 || selection >= 1 && selection <= 6))
				selection = ask.getInt("Enter selection");

			long startMillisec = System.currentTimeMillis();
			int counter = 0;
			switch (selection)
			{
				case 1:
					sort.one(cities);
					System.out.println("\nFifty least populous cities");
					break;
				case 2:
					sort.two(cities);
					System.out.println("\nFifty most populous cities");
					break;
				case 3:
					sort.three(cities);
					System.out.println("\nFifty most populous cities");
					break;
				case 4:
					sort.four(cities);
					System.out.println("\nFifty cities sorted by name descending");
					break;
				case 5:
					String state;
					System.out.println();
					do
						state = ask.getString("Enter state name (ie. Alabama)");
					while (!validState(state));
					System.out.println();

					sort.two(cities);
					printCity(-1);
					counter = 0;
					for (int i = 0; i < cities.size() && counter < 50; i++) {
						if (cities.get(i).getState().equalsIgnoreCase(state)) {
							counter++;
							City city = cities.get(i);
							System.out.printf(FORMAT, counter + ": ", city.getState(),
									city.getName(), city.getDesignation(), city.getPopulation());
						}
					}
					break;
				case 6:
					System.out.println();
					String cityName = ask.getString("Enter city name");
					System.out.println();

					sort.two(cities);
					printCity(-1);
					counter = 0;
					for (int i = 0; i < cities.size() && counter < 50; i++) {
						if (cities.get(i).getName().equalsIgnoreCase(cityName)) {
							counter++;
							City city = cities.get(i);
							System.out.printf(FORMAT, counter + ": ", city.getState(),
									city.getName(), city.getDesignation(), city.getPopulation());
						}
					}
					break;
				case 9:
					loop = false;
			}
			if (selection < 5)
				top50Printer();

			if (selection < 5)
				System.out.println("\nElapsed Time " + (System.currentTimeMillis() - startMillisec) + " milliseconds");
		}

		System.out.println("\nThanks for using Population!");
	}

	/**
	 * Checks if the inputted string is a valid US state
	 * @param state		String to check if it's a valid state
	 * @return			if state is valid (true if it is and false if not)
	 */
	private boolean validState(String state)
	{
		for (int i = 0; i < cities.size(); i++)
		{
			if (state.equalsIgnoreCase(cities.get(i).getState()))
				return true;
		}
		return false;
	}

	/**
	 * Prints the first 50 (or top 50 after sorting) cities from the cities array
	 */
	private void top50Printer()
	{
		printCity(-1);
		for (int i = 0; i < 50 && i < cities.size(); i++)
			printCity(i);
	}

	/**
	 * Prints a singular line of info for a city
	 * @param index		the index to use to get the city from the cities array
	 */
	private void printCity(int index) {
		if (index == -1)
			System.out.printf(FORMAT, "", "State", "City", "Type", "Population");
		else {
			City city = cities.get(index);
			System.out.printf(FORMAT, index + 1 + ": ", city.getState(),
					city.getName(), city.getDesignation(), city.getPopulation());
		}
	}

	/**
	 * Reads the population data text file and loads the data to the cities array
	 */
	private void fileReaderLoader()
	{
		FileUtils fu = new FileUtils();
		Scanner text = fu.openToRead(DATA_FILE).useDelimiter("[\t\n]");;
		
		cities = new ArrayList<City>();
		while (text.hasNext())
		{
			cities.add(new City (text.next(), text.next(), text.next(), text.nextInt()));
		}
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}

/**
 *	Used to sort City objects in different ways from the inputted List<City>
 */
class SortMethods {

	/**
	 * Sorts cities by population in increasing order
	 * @param list		list to be sorted
	 */
	public void one(List<City> list)
	{
		for (int i = 0; i < list.size(); i++) {
			int min = i;
			for (int a = i + 1; a < list.size(); a++) {
				if (list.get(min).compareTo(list.get(a)) > 0)
					swap(list, a, min);
			}
		}
	}

	/**
	 * Sorts cities by population in decreasing order
	 * @param list		list to be sorted
	 * @return 			sorted list
	 */
	public List<City> two(List<City> list)
	{
		if (list.size() > 2) {
			List<City> l1 = new ArrayList<City>();
			List<City> l2 = new ArrayList<City>();
			for (int i = 0; i < list.size(); i++)
			{
				if (i <= list.size()/2)
					l1.add(list.get(i));
				else
					l2.add(list.get(i));
			}
			l1 = two(l1);
			l2 = two(l2);

			int p1, p2; //pointers
			p1 = p2 = 0;
			while (p1 < l1.size() && p2 < l2.size())
			{
				City c1 = l1.get(p1);
				City c2 = l2.get(p2);
				if (c1.compareTo(c2) >= 0) {
					list.set(p1 + p2, c1);
					p1++;
				}
				else {
					list.set(p1 + p2, c2);
					p2++;
				}
			}
			if (p1 < l1.size())
				for (int i = p1; i < l1.size(); i++)
					list.set(i + p2, l1.get(i));
			if (p2 < l2.size())
				for (int i = p2; i < l2.size(); i++)
					list.set(i + p1, l2.get(i));
		}
		else if (list.size() == 2 && list.get(0).compareTo(list.get(1)) < 0) {
			City c = list.get(0);
			list.set(0, list.get(1));
			list.set(1, c);
		}

		return list;
	}

	/**
	 * Sorts cities by name in increasing order
	 * @param list		list to be sorted
	 */
	public void three(List<City> list)
	{
		CityComparatorByName comparator = new CityComparatorByName();
		for (int i = 1; i < list.size(); i++) {
			int j;
			for (j = i; j > 0 && comparator.compare(list.get(j), list.get(i)) >= 0; j--);
			list.set(j+1, list.get(i));
			list.remove(1+i);
		}
	}

	/**
	 * Sorts cities by name in decreasing order
	 * @param list		list to be sorted
	 * @return 			sorted list
	 */
	public List<City> four(List<City> list)
	{
		CityComparatorByName comparator = new CityComparatorByName();
		if (list.size() > 2) {
			List<City> l1 = new ArrayList<City>();
			List<City> l2 = new ArrayList<City>();
			for (int i = 0; i < list.size(); i++)
			{
				if (i <= list.size()/2)
					l1.add(list.get(i));
				else
					l2.add(list.get(i));
			}
			l1 = four(l1);
			l2 = four(l2);

			int p1, p2; //pointers
			p1 = p2 = 0;
			while (p1 < l1.size() && p2 < l2.size())
			{
				City c1 = l1.get(p1);
				City c2 = l2.get(p2);
				if (comparator.compare(c1, c2) >= 0) {
					list.set(p1 + p2, c1);
					p1++;
				}
				else {
					list.set(p1 + p2, c2);
					p2++;
				}
			}
			if (p1 < l1.size())
				for (int i = p1; i < l1.size(); i++)
					list.set(i + p2, l1.get(i));
			if (p2 < l2.size())
				for (int i = p2; i < l2.size(); i++)
					list.set(i + p1, l2.get(i));
		}
		else if (list.size() == 2 && comparator.compare(list.get(0), list.get(1)) < 0) {
			City c = list.get(0);
			list.set(0, list.get(1));
			list.set(1, c);
		}

		return list;
	}

	/**
	 * Swaps two City object of a list of the objects
	 * @param list	list of cities
	 * @param a		index of the first city
	 * @param b		index of the second city
	 */
	private void swap(List<City> list, int a, int b) {
		City city = list.get(a);
		list.set(a, list.get(b));
		list.set(b, city);
	}
}
