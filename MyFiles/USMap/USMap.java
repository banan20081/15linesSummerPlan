import java.util.ArrayList;
import java.util.Scanner;
/**
 * USMap class reads city coordinates from cities.txt & bigCities.txt
 * then draws a map of the USA by plotting these coordinates.
 * It uses the StdDraw library to plot regular cities and big cities in different colors
 * and sizes based on their populations.
 * 
 * The cities.txt file contains latitude and longitude data for many US cities.
 * The bigCities.txt file contains the names and populations of the largest cities in the USA.
 * 
 * Small cities are drawn as gray points, while big cities are drawn as blue or red points 
 * (red for the 10 largest cities).
 * 
 * @author Banan Badran
 * @since Septemeber 6th, 2024
 */

public class USMap {

	public static void main( String[] args){
		USMap us = new USMap();
		us.setupCanvas();
		us.readCities();
	}
	
	/** 
	 * Sets up the canvas size and the X and Y scales. 
	 * xScale(longitude) , yScale(latitude)
	 */
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}

	/**
	 * This method opens both cities.txt and bigCities.txt to read
	 * 
	 */
	public void readCities() {

		ArrayList<Integer> cityRank = new ArrayList(); // stores ranks of big cities in order
		ArrayList<String> rest = new ArrayList(); //storing the name of the big city with it's state
		ArrayList<Integer> population = new ArrayList(); // storing the amt of population
		int index =0;	//keeps track of current city index to store the amt of population
		double x_Coord=0, y_Coord=0; // logitude and latitude
		String cityNState ="";	//
		
		Scanner infile = CitiesReader.openToRead("cities.txt");
		Scanner bigCityInfile = CitiesReader.openToRead("bigCities.txt");
		while(bigCityInfile.hasNext()){	//bigiCities.txt
			cityRank.add(bigCityInfile.nextInt());	//ranking
			rest.add(bigCityInfile.nextLine());	 //city name and state
			population.add(Integer.parseInt(rest.get(index).substring(rest.get(index).lastIndexOf(" ")+1)));	// gets the amt of population
			index++;
		}

		while(infile.hasNext()){	//cities.txt
			x_Coord = infile.nextDouble();	// stores x coordinates to be plotted later
			y_Coord = infile.nextDouble();	// stores y coordinates to be plotted later
			cityNState = infile.nextLine();	// stores the city name
			for(int i =0; i< cityRank.size(); i++){
				if (rest.get(i).indexOf(cityNState) != -1) {	// checks if the city is a big city
					if(cityRank.get(i)>0 && cityRank.get(i)< 11){
						plotBigTenCities(x_Coord, y_Coord, population.get(i));	// red colored cities
					}
					else{
						plotBigCities(x_Coord, y_Coord, population.get(i));	// blue colored cities
					}
				}
			}
			plotDot(x_Coord, y_Coord); // plots the small/ normal cities
		}
	}
	
		/**
	 * @param x		since frame is scaled x represents y values
	 * @param y		since frame is scaled y represents x values
	 * this method is responsible for plotting the regular cities on the map
	 * iterates through an ArrayList plotting all of its coordinates
	 */
	public void plotDot(double x , double y) {
		StdDraw.setPenRadius(0.006);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.point(y, x);
	}
	
	/**
	 * This method is responsible for ploting the biggest 10 cities
	 * @param x,y			represents the coordinates to be plotted
	 * @param population	used to determine how big the city should 
	 * 						look like on the map
	 */
	public void plotBigTenCities(double x, double y, int population){
		StdDraw.setPenRadius(0.6 * (Math.sqrt(population)/18500));
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(y, x);
	}
	
	/**
	 * This method is responsible for ploting the big cities
	 * @param x,y			represents the coordinates to be plotted
	 * @param population	used to determine how big the city should 
	 * 						look like on the map
	 */
	public void plotBigCities(double x, double y, int population){
		StdDraw.setPenRadius(0.6 * (Math.sqrt(population)/18500));
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(y, x);
	}
}
