package edu.westga.cs1302.nss.model;

import edu.westga.cs1302.nss.resources.UI;

/**
 * This class models a seismic station that monitors and records seismic data.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class Station {

	private String name;
	private SeismicData seismicData;

	/**
	 * Instantiates a new station object.
	 * 
	 * @precondition name != null && !name.isBlank()
	 * @postcondition getName().equals(name) && size() == 0
	 * @param name the station's name
	 */
	public Station(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_NULL);
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NAME_CANNOT_BE_EMPTY);
		}
		this.name = name;
		this.seismicData = new SeismicData();
	}

	/**
	 * Returns the name of this Station.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the seismicData of this Station.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the seismicData
	 */
	public SeismicData getSeismicData() {
		return this.seismicData;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
