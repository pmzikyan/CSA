

/**
 *	Visual map of the US with dots representing cities and top bigger cities varying in size.
 *
 *	@author Petros Mzikyan
 *	@since September 4, 2024
 */

public class USMap {
	public static void main(String[] args) {
		USMap US = new USMap();
		US.setupCanvas();
		US.setupCities();
	}
	
	/** Set up the canvas size and scale */
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
}
