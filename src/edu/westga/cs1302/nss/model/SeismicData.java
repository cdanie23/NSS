package edu.westga.cs1302.nss.model;

import java.util.ArrayList;

import edu.westga.cs1302.nss.resources.UI;

/**
 * The class seismic data stores a list of earthquakes.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class SeismicData {

	private ArrayList<Earthquake> earthquakes;

	/**
	 * Instantiates a new SeismicData object.
	 *
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public SeismicData() {
		this.earthquakes = new ArrayList<Earthquake>();
	}

	/**
	 * Adds a new earthquake to the seismic data object.
	 * 
	 * @precondition earthquake != null && another earthquake with same time and
	 *               location not already present
	 * @postcondition
	 * @param earthquake the earthquake to add
	 * @return true if added, false otherwise
	 */
	public boolean add(Earthquake earthquake) {
		if (earthquake == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EARTHQUAKE_CANNOT_BE_NULL);
		}
		for (Earthquake quake : this.earthquakes) {
			if (earthquake.getLocation().equals(quake.getLocation()) && earthquake.getTime().isEqual(quake.getTime())) {
				throw new IllegalArgumentException(UI.ExceptionMessages.DUPLICATE_EARTHQUAKE);
			} 
		}
		this.earthquakes.add(earthquake);
		return true;
	}

	/**
	 * Checks if duplicate earthquake with same time and location already in data.
	 * 
	 * @precondition earthquake != null
	 * @postcondition none
	 * @param earthquake the specified earthquake
	 * @return true if duplicate exists, false otherwise
	 */
	public boolean contains(Earthquake earthquake) {
		if (earthquake == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EARTHQUAKE_CANNOT_BE_NULL);
		}
		boolean contains = false;
		for (Earthquake quake : this.earthquakes) {
			if (earthquake.getLocation().equals(quake.getLocation()) && earthquake.getTime().isEqual(quake.getTime())) {
				contains = true;
			}
		}
		return contains;
	}

	/**
	 * Adds all the earthquakes to this seismic data.
	 * 
	 * @precondition earthquakes != null
	 * @postcondition no duplicates
	 * @param earthquakes the earthquakes to be added
	 * @return true, if this seismic data object has changed
	 */
	public boolean addAll(ArrayList<Earthquake> earthquakes) {
		if (earthquakes == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EARTHQUAKES_CANNOT_BE_NULL);
		}

		boolean changed = false;
		for (Earthquake earthquake : earthquakes) {
			if (this.add(earthquake)) {
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Deletes the specified earthquake from the seismic data.
	 * 
	 * @precondition none
	 * @postcondition if found, size() == size()@prev - 1
	 * @param earthquake the earthquake to delete
	 * @return true if the earthquake was found and deleted, false otherwise
	 */
	public boolean remove(Earthquake earthquake) {
		return this.earthquakes.remove(earthquake);
	}

	/**
	 * Number earthquakes in this seismic data.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the number of earthquake included in this seismic data.
	 */
	public int size() {
		return this.earthquakes.size();
	}

	/**
	 * Gets the earthquakes of this seismic data object.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the earthquakes
	 */
	public ArrayList<Earthquake> getEarthquakes() {
		return this.earthquakes;
	}

	/**
	 * Creates an array that holds the count of the number of earthquakes in each
	 * segment starting from 0 to segmentRange up to the last segment which includes
	 * the highest magnitude earthquake.
	 * 
	 * @precondition segmentRange >= 0.01
	 * @postcondition none
	 * @param segmentRange the range of the magnitude values
	 * @return array with number of earthquakes of this seismic data that are in
	 *         each segment. Returns null if this seismic data is empty.
	 */
	public int[] countEarthquakesByMagnitudeSegments(double segmentRange) {
		// TODO Part 2-A Step 2
		return null;
	}

	/**
	 * Creates an array that holds the count of the number of earthquakes in each
	 * segment starting from 0 to segmentRange up to the last segment which includes
	 * the highest significance earthquake.
	 * 
	 * @precondition segmentRange >= 1
	 * @postcondition none
	 *
	 * @param segmentRange the range of the significance values
	 * @return array with number of earthquakes of this seismic data that are in
	 *         each segment. Returns null if this seismic data is empty.
	 */
	public int[] countEarthquakesBySignificanceSegments(int segmentRange) {
		// TODO Part 2-A Step 4
		return null;
	}

	/**
	 * Returns the highest magnitude value of a recorded earthquake
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the highest magnitude of a recorded earthquake; -1 if
	 *         no earthquakes
	 */
	public double getHighestMagnitude() { 
		double highestMagnitude = -1;
		for (Earthquake quake : this.earthquakes) {
			if (quake.getMagnitude() > highestMagnitude) {
				highestMagnitude = quake.getMagnitude();
			}
		}
		return highestMagnitude;
	}

	/**
	 * Returns the highest significance value of a recorded earthquake
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the highest significance of a recorded earthquake; -1 value
	 *         if no earthquakes
	 */
	public int getHighestSignificance() {
		int highestSignificance = -1;
		for (Earthquake quake : this.earthquakes) {
			if (quake.getSignificance() > highestSignificance) {
				highestSignificance = quake.getSignificance();
			}
		}
		return highestSignificance;
	}

	/**
	 * Returns a list of earthquakes whose location contains the specified search
	 * term(s). If there are no earthquakes that match the search term, it returns
	 * an empty list.
	 * 
	 * @precondition searchTerm != null
	 * @param searchTerm the search term(s)
	 * @return the list of earthquakes containing the search term(s) in the location
	 *         or an empty list if there are no earthquakes containing the
	 *         searchTerm (the method should not return null)
	 */
	public ArrayList<Earthquake> getEarthquakesMatchingLocation(String searchTerm) {
		if (searchTerm == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.SEARCH_TERM_CANNOT_BE_NULL);
		}
		ArrayList<Earthquake> quakes = new ArrayList<Earthquake>();
		
		for (Earthquake quake : this.earthquakes) {
			if (quake.getLocation().contains(searchTerm)) {
				quakes.add(quake);
			}
		}
		return quakes;
	}

	@Override
	public String toString() {
		return "#Earthquakes: " + this.size();
	}

}
