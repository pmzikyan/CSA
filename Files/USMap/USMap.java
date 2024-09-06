import java.util.Scanner;

/**
 *	Visual map of the US with dots representing cities and top bigger cities varying in size.
 *
 *	@author Petros Mzikyan
 *	@since September 4, 2024
 */

public class USMap {
	
	Scanner cities;
	Scanner bigCities;
	static FileUtils FU;
	double[] xCoords, yCoords;
	int[] pop;
	String[] cityNames;
	int citiesLength, bigCitiesLength; 
	
	public static void main(String[] args) {
		USMap US = new USMap();
		FU = new FileUtils();
		US.setupCanvas();
		
		US.setup();
		US.setupCities();
	}
	
	/** Set up the canvas size and scale */
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
	
	/** Set up the arrays */
	public void setup()
	{
		cities =  = FU.openToRead("cities.txt");
		bigCities = FU.openToRead("bigCities.txt");
		
		citiesLength = docLength(cities);
		bigCitiesLength = docLength(bigCities);
		
		cities =  = FU.openToRead("cities.txt");
		bigCities = FU.openToRead("bigCities.txt");
		
		xCoords = new double[cities];
		yCoords = new double[cities];
		cityNames = new String[cities];
		
		
		
		int index = 0;
		yCoords[index] = cities.nextDouble();
		xCoords[index] = cities.nextDouble();
		
		do
		{
			cities.nextLine();
			drawCity();
			index++
		}
		while (cities.hasNextLine());
	}
	
	public int docLength(Scanner doc)
	{
		int num;
		for (num = 0; doc.hasNextLine; num++);
		return num;
	}
	
	/** Set up the city points */
	public void setupCities() {
		
		StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.GRAY);
		
		drawCity();
		do
		{
			cities.nextLine();
			drawCity();
		}
		while (cities.hasNextLine());
	}
	
	/** Draws the city points */
	public void drawCity(cityNum)
	{
		
		StdDraw.point(xCoords[cityNum], yCoords[cityNum]);
	}
}
