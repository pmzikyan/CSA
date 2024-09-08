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
	String[] cityNames, bigCityNames;
	int citiesLength, bigCitiesLength;
	
	public static void main(String[] args) {
		USMap US = new USMap();
		FU = new FileUtils();
		US.setupCanvas();
		
		US.setup();
	}
	
	/** Set up the canvas size and scale */
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
	
	/** Set up the arrays and draw the dots */
	public void setup()
	{
		cities = FU.openToRead("cities.txt");
		bigCities = FU.openToRead("bigCities.txt");
		
		citiesLength = docLength(cities);
		bigCitiesLength = docLength(bigCities);
		
		cities = FU.openToRead("cities.txt");
		bigCities = FU.openToRead("bigCities.txt");
		
		xCoords = new double[citiesLength];
		yCoords = new double[citiesLength];
		cityNames = new String[citiesLength];
		bigCityNames = new String[bigCitiesLength];
		pop = new int[bigCitiesLength];

		int index = 0;
		String tempStr = "";
		int tempInt = 0;

		do
		{
			bigCities.nextInt();

			tempStr = bigCities.nextLine();
			tempInt = tempStr.indexOf(" , ") + 6;

			bigCityNames[index] = tempStr.substring(0, tempInt - 1);
			pop[index] = Integer.parseInt( tempStr.substring(tempInt, tempStr.length()) );
			index++;
		}
		while (bigCities.hasNextLine());

		index = 0;
		StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.GRAY);

		boolean containsCity = false;
		do
		{
			yCoords[index] = cities.nextDouble();
			xCoords[index] = cities.nextDouble();
			cityNames[index] = cities.nextLine();
			//System.out.println(xCoords[index] + "\t" + yCoords[index] + "\t" + cityNames[index]);

			containsCity = false;
			tempInt = 0;
			for (int i = 0; i < bigCitiesLength; i++)
			{
				if (cityNames[index].equals(bigCityNames[i]))
				{
					containsCity = true;
					tempInt = i;
				}
			}
			if (!containsCity)
			{
				StdDraw.setPenRadius(0.006);
				StdDraw.setPenColor(StdDraw.GRAY);
			}
			else
			{
				StdDraw.setPenRadius(0.6 * (Math.sqrt(pop[tempInt])/18500));
				if (tempInt < 10)
					StdDraw.setPenColor(StdDraw.RED);
				else
					StdDraw.setPenColor(StdDraw.BLUE);
			}
			StdDraw.point(xCoords[index], yCoords[index]);
			index++;
		}
		while (cities.hasNextLine());
	}

	/** Finds how many lines long a .txt file is */
	public int docLength(Scanner doc)
	{
		int num = 0;
		do
		{
			num++;
			doc.nextLine();
		}
		while (doc.hasNextLine());

		return num;
	}
}
