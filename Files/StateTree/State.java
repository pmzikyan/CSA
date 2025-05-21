/**
 *	The object to store US state information.
 *
 *	@author	Petros Mzikyan
 *	@since	5/21/2025
 */
public class State implements Comparable<State>
{
	private String name;			// state name
	private String abbreviation;	// state abbreviation
	private int population;			// state population
	private int area;				// state area in sq miles
	private int reps;				// number of House Reps
	private String capital;			// state capital city
	private int month;				// month joined Union
	private int day;				// day joined Union
	private int year;				// year joined Union
	
	public State() { }
	
	@Override
	public int compareTo(State other) 
	{	return 0; }
	
	public String getName ( )
	{	return ""; }
	
	@Override
	public String toString() 
	{	return ""; }
}
