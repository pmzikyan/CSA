/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Petros Mzikyan
 *	@since	12/2/2024
 */
public class City implements Comparable<City> {
	
	// fields
	private String name, state, designation;
	private int population;
	
	// constructor
	public City(String n, String s, String d, int p)
	{
		name = n;
		state = s;
		designation = d;
		population = p;
	}
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 @Override
	 public int compareTo(City other)
	 {
		 int out = population - other.getPopulation();
		 if (out != 0) return out;
		 
		 out = state.compareTo(other.getState());
		 if (out != 0) return out;
		 
		 return name.compareTo(other.getName());
	 }
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other)
	 {
		 return name.equals(other.getName()) && state.equals(other.getState());
	 }
	
	/**	Accessor methods -- return their respective field variables */
	public String getName() { return name; }
	public String getState() { return state; }
	public String getDesignation() { return designation; }
	public int getPopulation() { return population; }
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
