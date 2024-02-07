package edu.westga.cs1302.nss.resources;

import edu.westga.cs1302.nss.model.Earthquake;

/**
 * Defines strings that will be displayed in the UI (user interface).
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class UI {

	/**
	 * Defines string messages for exceptions.
	 */
	public static final class ExceptionMessages {

		public static final String FILE_CANNOT_BE_NULL = "File cannot be null.";
		public static final String STATION_CANNOT_BE_NULL = "Network cannot be null.";
		public static final String TIME_CANNOT_BE_NULL = "Time cannot be null.";
		public static final String TIME_CANNOT_BE_AFTER_NOW = "Time cannot be after now.";
		public static final String LOCATION_CANNOT_BE_NULL = "Location cannot be null.";
		public static final String LOCATION_CANNOT_BE_EMPTY = "Location cannot be empty.";
		public static final String MAGNITUDE_OUT_OF_RANGE = "Magnitude out of range (" + Earthquake.MIN_MAGNITUDE + "-"
				+ Earthquake.MAX_MAGNITUDE + ").";
		public static final String SIGNIFICANCE_OUT_OF_RANGE = "Significance out of range (" + Earthquake.MIN_DISTANCE
				+ "-" + Earthquake.MAX_SIGNIFICANCE + ").";
		public static final String DEPTH_CANNOT_BE_NEGATIVE = "Depth cannot be negative";
		public static final String DISTANCE_OUT_OF_RANGE = "Distance out of range (" + Earthquake.MIN_DISTANCE + "-"
				+ Earthquake.MAX_DISTANCE + ").";
		public static final String NAME_CANNOT_BE_NULL = "Name cannot be null.";
		public static final String NAME_CANNOT_BE_EMPTY = "Name cannot be empty.";
		public static final String EARTHQUAKE_CANNOT_BE_NULL = "Earthquake cannot be null.";
		public static final String DUPLICATE_EARTHQUAKE = "Earthquake (with same time and location) already exists.";
		public static final String EARTHQUAKES_CANNOT_BE_NULL = "Earthquakes cannot be null.";
		public static final String NETWORK_CANNOT_BE_NULL = "Network cannot be null.";
		public static final String INVALID_SEGMENT_RANGE = "Invalid segment range.";
		public static final String SEARCH_TERM_CANNOT_BE_NULL = "Term cannot be null.";

	}

	/**
	 * Defines string messages to be used in UI messages.
	 */
	public static final class Text {

		public static final String REQUIRED = "Value is required.";
		public static final String INVALID = "This value is invalid.";
		public static final String YEAR_FOUR_DIGITS = "Year should have 4 digits.";
		public static final String CANNOT_START_WITH_ZERO = "Cannot start with zero.";
		public static final String LOCATION_IS_INVALID = "Location is invalid.\n(Eg: 117km NE of Salem)";
		public static final String CANNOT_CONTAIN_COMMAS = "Cannot contain commas.";
		public static final String TOO_MANY_DECIMAL_DIGITS = "Too many decimal digits.";
		public static final String WHOLE_NUMBER = "Whole number required.";
		public static final String TOO_MANY_DIGITS = "Too many digits.";
		public static final String CANNOT_BE_NEGATIVE = "Value must be positive.";
		public static final String MONTH_TWO_DIGITS = "Month should have 1-2 digits.";
		public static final String DAY_TWO_DIGITS = "Day should have 1-2 digits.";
		public static final String HOUR_TWO_DIGITS = "Hour should have 1-2 digits";
		public static final String MINUTE_TWO_DIGITS = "Minute should have 1-2 digits";
		public static final String SECOND_TWO_DIGITS = "Second should have 1-2 digits";
		public static final String LOCATION_KM = "KM must be lowercase";
		public static final String LOCATION_OF = "OF must be lowercase";
		public static final String LOCATION_CARDINAL = "N,S,W, and E must be capital";
		public static final String DISTANCE_RANGE = "Value must be between 0-7.1";
		public static final String DECIMAL_PLACES = "Value can only have 1-2 decimal places";

	}
}
