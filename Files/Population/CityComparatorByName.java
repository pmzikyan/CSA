import java.util.Comparator;

/**
 *	City comparator - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Petros Mzikyan
 *	@since	12/2/2024
 */
public class CityComparatorByName implements Comparator<City> {
	public int compare(City c1, City c2) { return c1.compareTo(c2); }
	public boolean equals(City c1, City c2) { return c1.equals(c2); }
}
