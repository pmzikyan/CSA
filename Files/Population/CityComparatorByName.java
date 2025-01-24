import java.util.Comparator;

/**
 *	City comparator - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Petros Mzikyan
 *	@since	12/2/2024
 */
public class CityComparatorByName implements Comparator<City> {
	/**
	 * Compares two City objects by name, and if they're the same then by population
	 * @param c1	first city
	 * @param c2	second city
	 * @return		the difference between the names/populations of the two cities
	 */
	public int compare(City c1, City c2)
	{

		int out = c1.getName().compareTo(c2.getName());
		if (out != 0) return out;

		return c1.getPopulation() - c2.getPopulation();
	}
}
