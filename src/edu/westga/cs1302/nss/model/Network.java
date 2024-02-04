package edu.westga.cs1302.nss.model;

import java.util.ArrayList;

import edu.westga.cs1302.nss.resources.UI;

/**
 * Represents a network of seismic stations.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class Network {

	private String name;
	private ArrayList<Station> stations;

	/**
	 * Instantiates a new Network with given name.
	 *
	 * @precondition name != null && !name.isBlank()
	 * @postcondition getName() == name && size() == 0
	 * @param name the name of this network
	 */
	public Network(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_NULL);
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_EMPTY);
		}
		this.name = name;
		this.stations = new ArrayList<Station>();
	}

	/**
	 * Gets the total number of earthquakes recorded from all stations.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the total number of earthquakes
	 */
	public int getTotalNumberEarthquakes() {
		int total = 0;
		for (Station currStation : this.stations) {
			total += currStation.getSeismicData().size();
		}
		return total;
	}

	/**
	 * Finds station with the specified station name
	 *
	 * @precondition none
	 * @postcondition none
	 * @param name the station name
	 * @return the station if found; null otherwise
	 */
	public Station findStation(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_NULL);
		}
		for (Station station : this.stations) {
			if (name.equalsIgnoreCase(station.getName())) {
				return station;
			}
		}
		return null;
	}

	/**
	 * Creates a new station with the specified name and adds it to the network.
	 * 
	 * @precondition name != null && !name.isBlank()
	 * @postcondition findStation(name) != null
	 * @param name the name of the new station
	 * @return true, if added the new station successfully added; false, if station
	 *         with the specified name already exists
	 */
	public boolean addStation(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_NULL);
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_EMPTY);
		}
		for (Station station : this.stations) {
			if (station.getName().equalsIgnoreCase(name)) {
				return false;
			}
		}
		Station station = new Station(name);
		return this.stations.add(station);
	}

	/**
	 * Gets the network name.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the network name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Adds the specified earthquake to the specified station.
	 * 
	 * @precondition name != null && !name.isBlank() && earthquake != null
	 * @postcondition findStation(stationName) contains earthquake
	 * @param name       the station name
	 * @param earthquake the earthquake
	 * @return true, if earthquake successfully added; false, otherwise
	 */
	public boolean addEarthquake(String name, Earthquake earthquake) {
		if (name == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_NULL);
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_EMPTY);
		}

		Station station = this.findStation(name);
		if (station != null) {
			return station.getSeismicData().add(earthquake);
		}
		return false;
	}

	/**
	 * Gets the stations in this network.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the stations if this network.
	 */
	public ArrayList<Station> getStations() {
		return this.stations;
	}

	/**
	 * Number of stations within this network.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the number of stations
	 */
	public int size() {
		return this.stations.size();
	}

	@Override
	public String toString() {
		return "Network: " + this.name + " #Stations: " + this.size();
	}
}
