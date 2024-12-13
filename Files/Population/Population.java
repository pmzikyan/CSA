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
	
	public static void main(String[] args) {
		Population pop = new Population();
		pop.fileReaderLoader();
		pop.run();
	}
	
	public void run()
	{
		printIntroduction();
		
		//System.out.println(cities.size() + "cities in database");
	}
	
	public void fileReaderLoader()
	{
		FileUtils fu = new FileUtils();
		Scanner text = fu.openToRead(DATA_FILE).useDelimiter("[\t\n]");;
		
		cities = new ArrayList<City>();
		while (text.hasNextLine())
		{
			City city = new City();
			city.setUpCity(text.next(), text.next(), text.next(), text.nextInt());
			cities.add(city);
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
		
	}
}
