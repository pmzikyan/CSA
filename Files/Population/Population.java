import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *	Population - <description goes here>
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
			switch (selection)
			{
				case 1:
					sort.one(cities);
					break;
				case 2:
					sort.two(cities);
					break;
				case 3:
					sort.three(cities);
					break;
				case 4:
					sort.four(cities);
					break;
				case 5:
					sort.five(cities);
					break;
				case 6:
					sort.six(cities);
					break;
				case 9:
					loop = false;
			}
			if (selection < 6)
				top50Printer();
			else if (selection == 6)
				sameNamePrinter();

			if (selection < 5)
				System.out.println("\nElapsed Time " + (System.currentTimeMillis() - startMillisec) + " milliseconds");
		}
	}

	private void top50Printer()
	{
		printCity(-1);
		for (int i = 0; i < 50 && i < cities.size(); i++)
			printCity(i);
	}

	private void sameNamePrinter()
	{
		String cityName = cities.get(0).getName();
		int counter = 1;
		while (cities.get(counter-1).equals(cityName))
		{

		}
	}

	private void printCity(int index) {
		if (index == -1)
			System.out.printf(FORMAT, "", "State", "City", "Type", "Population");
		else {
			City city = cities.get(index);
			System.out.printf(FORMAT, index + 1 + ": ", city.getState(),
					city.getName(), city.getDesignation(), city.getPopulation());
		}
	}
	
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

class SortMethods {
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

	public void three(List<City> list)
	{
		CityComparatorByName comparator = new CityComparatorByName();
		//System.out.println(list.get(0).getName() + ", " + list.get(500).getName()
				+ comparator.compare(list.get(0), list.get(500)));
		for (int i = 1; i < list.size(); i++) {
			int j;
			for (j = i; j > 0 && comparator.compare(list.get(j), list.get(i)) >= 0; j--);
				//System.out.println(i + ", " + comparator.compare(list.get(j), list.get(i)));
			//System.out.println(j + ", " + i);
			list.set(j, list.remove(i));
		}
	}

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

	/*public void five(List<City> list)
	{

	}

	public void six(List<City> list)
	{

	}*/

	private void swap(List<City> list, int a, int b) {
		City city = list.get(a);
		list.set(a, list.get(b));
		list.set(b, city);
	}
}
