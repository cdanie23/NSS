package edu.westga.cs1302.nss.model;

import java.time.LocalDateTime;

import edu.westga.cs1302.nss.resources.UI;

/**
 * This class models an earthquake for the NSS app.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class Earthquake {

	public static final double MIN_MAGNITUDE = 0.01;
	public static final double MAX_MAGNITUDE = 10.0;
	public static final int MIN_SIGNIFICANCE = 0;
	public static final int MAX_SIGNIFICANCE = 3000;
	public static final double MIN_DISTANCE = 0.0;
	public static final double MAX_DISTANCE = 7.1;
	public static final double MIN_DEPTH = 0;

	private LocalDateTime time;
	private String location;
	private double magnitude;
	private int significance;
	private double distance;
	private double depth;

	/**
	 * Instantiates a new Earthquake
	 *
	 * @precondition time != null && time not in future && location != null &&
	 *               !location.isBlank() && MIN_MAGNITUDE <= magnitude <=
	 *               MAX_MAGNITUDE && MIN_SIGNIFICANCE <= significance <=
	 *               MIN_SIGNIFICANCE && depth > 0 && MIN_DISTANCE <= distance <=
	 *               MAX_DISTANCE && depth >= MIN_DEPTH
	 * @postcondition getTime() == time && getLocation() == location &&
	 *                getMagnitude() == magnitude && getSignificane() ==
	 *                significance && getDepth() == depth && getDistance() ==
	 *                distance
	 * @param time         the start in local date and time
	 * @param location     the location as text
	 * @param magnitude    the magnitude on the Richter scale
	 * @param significance this number describes how significant the event is and it
	 *                     is determined on a number of factors (e.g., duration,
	 *                     felt reports, estimated impact). Ranges from 0 to 3,000.
	 * @param distance     the rough distance that this earthquake occurred away
	 *                     from the reporting station, measured in degrees (1 degree
	 *                     is approximately 111.2 km). This number is between 0-7.1
	 *                     and the smaller it is, the more reliable the calculated
	 *                     depth of the earthquake.
	 * @param depth        the depth of the event measured in kilometers
	 */
	public Earthquake(LocalDateTime time, String location, double magnitude, int significance, double distance,
			double depth) {

		if (time == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.TIME_CANNOT_BE_NULL);
		}
		if (time.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException(UI.ExceptionMessages.TIME_CANNOT_BE_AFTER_NOW);
		}

		if (location == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.LOCATION_CANNOT_BE_NULL);
		}

		if (location.isBlank()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.LOCATION_CANNOT_BE_EMPTY);
		}

		if (magnitude < MIN_MAGNITUDE || magnitude > MAX_MAGNITUDE) {
			throw new IllegalArgumentException(UI.ExceptionMessages.MAGNITUDE_OUT_OF_RANGE);
		}

		if (significance < MIN_SIGNIFICANCE || significance > MAX_SIGNIFICANCE) {
			throw new IllegalArgumentException(UI.ExceptionMessages.SIGNIFICANCE_OUT_OF_RANGE);
		}

		if (distance < MIN_DISTANCE || distance > MAX_DISTANCE) {
			throw new IllegalArgumentException(UI.ExceptionMessages.DISTANCE_OUT_OF_RANGE);
		}

		if (depth < 0) {
			throw new IllegalArgumentException(UI.ExceptionMessages.DEPTH_CANNOT_BE_NEGATIVE);
		}

		this.time = time;
		this.location = location;
		this.magnitude = magnitude;
		this.significance = significance;
		this.distance = distance;
		this.depth = depth;

	}

	/**
	 * Returns the time of this earthquake.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return this.time;
	}

	/**
	 * Sets the time of this earthquake.
	 * 
	 * @precondition time != null
	 * @postcondition getTime() == time
	 * @param time the time to set
	 */
	public void setTime(LocalDateTime time) {
		if (time == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.TIME_CANNOT_BE_NULL);
		}
		this.time = time;
	}

	/**
	 * Returns the location of this earthquake.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location of this earthquake.
	 * 
	 * @precondition location != null && !location.isBlank()
	 * @postcondition getLocation() == location
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		if (location == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.LOCATION_CANNOT_BE_NULL);
		}
		if (location.isBlank()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.LOCATION_CANNOT_BE_EMPTY);
		}
		this.location = location;
	}

	/**
	 * Returns the magnitude of this earthquake.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the magnitude
	 */
	public double getMagnitude() {
		return this.magnitude;
	}

	/**
	 * Sets the magnitude of this earthquake.
	 * 
	 * @precondition MIN_MAGNITUDE <= magnitude <= MAX_MAGNITUDE
	 * @postcondition getMagnitude() == magnitude
	 * @param magnitude the magnitude to set
	 */
	public void setMagnitude(double magnitude) {
		if (magnitude < MIN_MAGNITUDE || magnitude > MAX_MAGNITUDE) {
			throw new IllegalArgumentException(UI.ExceptionMessages.MAGNITUDE_OUT_OF_RANGE);
		}
		this.magnitude = magnitude;
	}

	/**
	 * Returns the significance of this earthquake.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the significance
	 */
	public int getSignificance() {
		return this.significance;
	}

	/**
	 * Sets the significance of this earthquake.
	 * 
	 * @precondition MIN_SIGNIFICANCE <= significance <= MIN_SIGNIFICANCE
	 * @postcondition getSignificance() == significance
	 * @param significance the significance to set
	 */
	public void setSignificance(int significance) {
		if (significance < MIN_SIGNIFICANCE || significance > MAX_SIGNIFICANCE) {
			throw new IllegalArgumentException(UI.ExceptionMessages.SIGNIFICANCE_OUT_OF_RANGE);
		}
		this.significance = significance;
	}

	/**
	 * Returns the distance of this earthquake.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the distance
	 */
	public double getDistance() {
		return this.distance;
	}

	/**
	 * Sets the distance of this earthquake.
	 * 
	 * @precondition MIN_DISTANCE <= distance <= MAX_DISTANCE
	 * @postcondition getDistance() == distance
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		if (distance < MIN_DISTANCE || distance > MAX_DISTANCE) {
			throw new IllegalArgumentException(UI.ExceptionMessages.DISTANCE_OUT_OF_RANGE);
		}
		this.distance = distance;
	}

	/**
	 * Returns the depth of this earthquake.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the depth
	 */
	public double getDepth() {
		return this.depth;
	}

	/**
	 * Sets the depth of this earthquake.
	 * 
	 * @precondition depth >= MIN_DEPTH
	 * @postcondition getDepth() == depth
	 * @param depth the depth to set
	 */
	public void setDepth(double depth) {
		if (depth < MIN_DEPTH) {
			throw new IllegalArgumentException(UI.ExceptionMessages.DEPTH_CANNOT_BE_NEGATIVE);
		}
		this.depth = depth;
	}

	@Override
	public String toString() {
		return this.time + ", " + this.magnitude + ", " + this.location;
	}

}
