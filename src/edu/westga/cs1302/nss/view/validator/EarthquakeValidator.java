package edu.westga.cs1302.nss.view.validator;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.resources.UI;

/**
 * The validator for Earthquake objects.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class EarthquakeValidator {

	private String yearError;
	private String monthError;
	private String dayError;
	private String hourError;
	private String minuteError;
	private String secondError;
	private String locationError;
	private String magnitudeError;
	private String significanceError;
	private String distanceError;
	private String depthError;

	/**
	 * Instantiates a new validator for earthquake entries.
	 * 
	 * @precondition none
	 * @postcondition all fields are empty
	 */
	public EarthquakeValidator() {
		this.reset();
	}

	/**
	 * Returns the yearError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the yearError
	 */
	public String getYearError() {
		return this.yearError;
	}

	/**
	 * Returns the monthError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the monthError
	 */
	public String getMonthError() {
		return this.monthError;
	}

	/**
	 * Returns the dayError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the dayError
	 */
	public String getDayError() {
		return this.dayError;
	}

	/**
	 * Returns the hourError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the hourError
	 */
	public String getHourError() {
		return this.hourError;
	}

	/**
	 * Returns the minuteError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the minuteError
	 */
	public String getMinuteError() {
		return this.minuteError;
	}

	/**
	 * Returns the secondError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the secondError
	 */
	public String getSecondError() {
		return this.secondError;
	}

	/**
	 * Returns the locationError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the locationError
	 */
	public String getLocationError() {
		return this.locationError;
	}

	/**
	 * Returns the magnitudeError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the magnitudeError
	 */
	public String getMagnitudeError() {
		return this.magnitudeError;
	}

	/**
	 * Returns the significanceError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the significanceError
	 */
	public String getSignificanceError() {
		return this.significanceError;
	}

	/**
	 * Returns the distanceError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the distanceError
	 */
	public String getDistanceError() {
		return this.distanceError;
	}

	/**
	 * Returns the depthError of this Validator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the depthError
	 */
	public String getDepthError() {
		return this.depthError;
	}

	/**
	 * Found error.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true, if a preceding call to a validation method detected an error
	 */
	public boolean foundError() {
		return !this.yearError.isEmpty() || !this.monthError.isEmpty() || !this.dayError.isEmpty()
				|| !this.hourError.isEmpty() || !this.minuteError.isEmpty() || !this.secondError.isEmpty()
				|| !this.locationError.isEmpty() || !this.magnitudeError.isEmpty() || !this.significanceError.isEmpty()
				|| !this.distanceError.isEmpty() || !this.depthError.isEmpty();
	}

	/**
	 * Reset.
	 */
	public void reset() {
		this.locationError = "";
		this.yearError = "";
		this.monthError = "";
		this.dayError = "";
		this.hourError = "";
		this.minuteError = "";
		this.secondError = "";
		this.magnitudeError = "";
		this.significanceError = "";
		this.distanceError = "";
		this.depthError = "";

	}

	/**
	 * Validates year. Removes trailing and leading spaces from the incoming string.
	 * A valid year is a positive whole number of exactly 4 digit, and cannot start
	 * with zero. Checks if the resulting string represents a valid year and sets a
	 * suitable error message. Whether or not the value is within the correct bounds
	 * and it is legal in the context of the other specified time variables will be
	 * checked in the Earthquake constructor.
	 * 
	 * @precondition none
	 * @postcondition getYearError().isEmpty() if the passed-in string represents a
	 *                valid year; otherwise getYearError() returns a suitable error
	 *                message
	 * @param yearString the incoming string supposedly representing the year
	 * @return the year value represented by yearString after leading and trailing
	 *         spaces have been removed; null if yearString does not represent a
	 *         valid year
	 */
	public Integer validateYear(String yearString) { 
		yearString = yearString.trim();
		if (yearString.matches("[^0][0-9]{3}")) {
			return Integer.parseInt(yearString);
		} else if (yearString.isBlank()) {
			this.yearError = UI.Text.REQUIRED;
			return null;
		} else if (yearString.matches("[0][0-9]*")) {
			this.yearError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (yearString.matches("[0-9]{1,3}") || yearString.matches("[0-9]{5,}")) {
			this.yearError = UI.Text.YEAR_FOUR_DIGITS;
			return null;
		} else if (yearString.matches("[0-9]*[,][0-9]*")) {
			this.yearError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (yearString.matches("[0-9]*[.]+[0-9]*")) {
			this.yearError = UI.Text.WHOLE_NUMBER;
			return null;
		} else if (yearString.matches("[-]+[0-9]*")) {
			this.yearError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.yearError = UI.Text.INVALID;
			return null; 
		}
		
	}

	/**
	 * Validates month. Removes trailing and leading spaces from the incoming
	 * string. A valid month is a positive whole number of at most two digits and
	 * cannot start with zero. Checks if the resulting string represents a valid
	 * month and sets a suitable error message. Whether or not the value is within
	 * the correct bounds and it is legal in the context of the other specified time
	 * variables will be checked in the Earthquake constructor.
	 * 
	 * @precondition none
	 * @postcondition getMonthError().isEmpty() if the passed-in string represents a
	 *                valid month; otherwise getMonthError() returns a suitable
	 *                error message
	 * @param monthString the incoming string supposedly representing the month
	 * @return the month value represented by monthString after leading and trailing
	 *         spaces have been removed; null if monthString does not represent a
	 *         valid month
	 */
	public Integer validateMonth(String monthString) { 
		monthString = monthString.trim();
		if (monthString.isBlank()) {
			this.monthError = UI.Text.REQUIRED;
			return null;
		} else if (monthString.matches("^[1-9][0-9]{0,1}")) {
			return Integer.parseInt(monthString);
		} else if (monthString.matches("[0][0-9]*")) {
			this.monthError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (monthString.matches("[0-9]{2,}") || monthString.matches("[0-9]{5,}")) {
			this.monthError = UI.Text.MONTH_TWO_DIGITS;
			return null;
		} else if (monthString.matches("[0-9]*[,][0-9]*")) {
			this.monthError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (monthString.matches("[0-9]*[.]+[0-9]*")) {
			this.monthError = UI.Text.WHOLE_NUMBER;
			return null;
		} else if (monthString.matches("[-]+[\\S]*")) {
			this.monthError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.monthError = UI.Text.INVALID;
			return null; 
		}
		
	}

	/**
	 * Validates day. Removes trailing and leading spaces from the incoming string.
	 * Checks if the resulting string represents a valid day and sets a suitable
	 * error message. This validator must ensure that the string representing the
	 * day is a positive whole number with at most two digits. Whether or not the
	 * value is within the correct bounds and it is legal in the context of the
	 * other specified time variables will be checked in the Earthquake constructor.
	 * 
	 * @precondition none
	 * @postcondition getDayError().isEmpty() if the passed-in string represents a
	 *                valid day; otherwise getDayError() returns a suitable error
	 *                message
	 * @param dayString the incoming string supposedly representing the day
	 * @return the day value represented by dayString after leading and trailing
	 *         spaces have been removed; null if dayString does not represent a
	 *         valid day
	 */
	public Integer validateDay(String dayString) { 
		dayString = dayString.trim();
		if (dayString.isBlank()) {
			this.dayError = UI.Text.REQUIRED;
			return null;
		} else if (dayString.matches("^[1-9][0-9]{0,1}")) {
		return Integer.parseInt(dayString);
		} else if (dayString.matches("[0][0-9]*")) {
			this.dayError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (dayString.matches("[0-9]{2,}") || dayString.matches("[0-9]{5,}")) {
			this.dayError = UI.Text.DAY_TWO_DIGITS;
			return null;
		} else if (dayString.matches("[0-9]*[,][0-9]*")) {
			this.dayError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (dayString.matches("[0-9]*[.]+[0-9]*")) {
			this.dayError = UI.Text.WHOLE_NUMBER;
			return null;
		} else if (dayString.matches("[-]+[\\S]*")) {
			this.dayError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.dayError = UI.Text.INVALID;
			return null; 
		}
	}

	/**
	 * Validates hour. Removes trailing and leading spaces from the incoming string.
	 * Checks if the resulting string represents a valid hour and sets a suitable
	 * error message. This validator must ensure that the string representing the
	 * hour is a positive whole number with at most two digits. Whether or not the
	 * value is within the correct bounds will be checked in the Earthquake
	 * constructor.
	 * 
	 * @precondition none
	 * @postcondition getHourError().isEmpty() if the passed-in string represents a
	 *                valid hour; otherwise getHourError() returns a suitable error
	 *                message
	 * @param hourString the incoming string supposedly representing the hour
	 * @return the hour value represented by hourString after leading and trailing
	 *         spaces have been removed; null if hourString does not represent a
	 *         valid hour
	 */
	public Integer validateHour(String hourString) { 
		hourString = hourString.trim();
		if (hourString.isBlank()) {
			this.hourError = UI.Text.REQUIRED;
			return null;
		} else if (hourString.matches("^[1-9][0-9]{0,1}")) {
		return Integer.parseInt(hourString);
		} else if (hourString.matches("[0][0-9]*")) {
			this.hourError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (hourString.matches("[0-9]{2,}") || hourString.matches("[0-9]{5,}")) {
			this.hourError = UI.Text.HOUR_TWO_DIGITS;
			return null;
		} else if (hourString.matches("[0-9]*[,][0-9]*")) {
			this.hourError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (hourString.matches("[0-9]*[.]+[0-9]*")) {
			this.hourError = UI.Text.WHOLE_NUMBER;
			return null;
		} else if (hourString.matches("[-]+[\\S]*")) {
			this.hourError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.hourError = UI.Text.INVALID;
			return null; 
		}
	}

	/**
	 * Validates minute. Removes trailing and leading spaces from the incoming
	 * string. Checks if the resulting string represents a valid minute and sets a
	 * suitable error message. This validator must ensure that the string
	 * representing the minute is a positive whole number with at most two digits.
	 * Whether or not the value is within the correct bounds will be checked in the
	 * Earthquake constructor.
	 * 
	 * @precondition none
	 * @postcondition getMinuteError().isEmpty() if the passed-in string represents
	 *                a valid minute; otherwise getMinuteError() returns a suitable
	 *                error message
	 * @param minuteString the incoming string supposedly representing the minute
	 * @return the minute value represented by minuteString after leading and
	 *         trailing spaces have been removed; null if minuteString does not
	 *         represent a valid minute
	 */
	public Integer validateMinute(String minuteString) { 
		minuteString = minuteString.trim();
		if (minuteString.isBlank()) {
			this.minuteError = UI.Text.REQUIRED;
			return null;
		} else if (minuteString.matches("^[1-9][0-9]{0,1}")) {
		return Integer.parseInt(minuteString);
		} else if (minuteString.matches("[0][0-9]*")) {
			this.minuteError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (minuteString.matches("[0-9]{2,}") || minuteString.matches("[0-9]{5,}")) {
			this.minuteError = UI.Text.MINUTE_TWO_DIGITS;
			return null;
		} else if (minuteString.matches("[0-9]*[,][0-9]*")) {
			this.minuteError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (minuteString.matches("[0-9]*[.]+[0-9]*")) {
			this.minuteError = UI.Text.WHOLE_NUMBER;
			return null;
		} else if (minuteString.matches("[-]+[\\S]*")) {
			this.minuteError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.minuteError = UI.Text.INVALID;
			return null;
		}	
	}

	/**
	 * Validates seconds. Removes trailing and leading spaces from the incoming
	 * string. Checks if the resulting string represents a valid second and sets a
	 * suitable error message. This validator must ensure that the string
	 * representing the second is a positive whole number with at most two digits.
	 * Whether or not the value is within the correct bounds will be checked in the
	 * Earthquake constructor.
	 * 
	 * @precondition none
	 * @postcondition getSecondError().isEmpty() if the passed-in string represents
	 *                a valid second; otherwise getSecondError() returns a suitable
	 *                error message
	 * @param secondString the incoming string supposedly representing the second
	 * @return the second value represented by secondString after leading and
	 *         trailing spaces have been removed; null if secondString does not
	 *         represent a valid second
	 */
	public Integer validateSecond(String secondString) { 
		secondString = secondString.trim();
		if (secondString.isBlank()) {
			this.secondError = UI.Text.REQUIRED;
			return null;
		} else if (secondString.matches("^[1-9][0-9]{0,1}")) {
		return Integer.parseInt(secondString);
		} else if (secondString.matches("[0][0-9]*")) {
			this.secondError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (secondString.matches("[0-9]{2,}") || secondString.matches("[0-9]{5,}")) {
			this.secondError = UI.Text.SECOND_TWO_DIGITS;
			return null;
		} else if (secondString.matches("[0-9]*[,][0-9]*")) {
			this.secondError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (secondString.matches("[0-9]*[.]+[0-9]*")) {
			this.secondError = UI.Text.WHOLE_NUMBER;
			return null;
		} else if (secondString.matches("[-]+[\\S]*")) {
			this.secondError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.secondError = UI.Text.INVALID;
			return null; 
		}	
	}

	/**
	 * Validates location. A valid location has to be a String starting with a
	 * positive whole number of at most three digits (can also be zero but should
	 * not start with zero if there are more digits) followed by "km" followed by a
	 * sequence (1-3 characters in length) representing a combination of the four
	 * cardinal points (N, S, E, W) followed by the word "of" and a location. For
	 * example: "11km SSW of King City" is a valid match, "11 km NE of Little Sitkin
	 * Island" is also valid, but notice the space between the value 11 and "km".
	 * "12KM NE of Salem" is not valid because KM is in caps. The words km and of
	 * must be lower case letters, and the cardinal points must be upper case
	 * letters. The location should not contain commas.
	 * 
	 * @precondition none
	 * @postcondition getLocationError().isEmpty() if the passed-in string
	 *                represents a valid location; otherwise getLocationError()
	 *                returns a suitable error message
	 * @param locationString the incoming string supposedly representing the
	 *                       location
	 * @return the location value represented by locationString; null if
	 *         locationString does not represent a valid location
	 */
	public String validateLocation(String locationString) {
		if (locationString == null) {
			this.locationError = UI.ExceptionMessages.LOCATION_CANNOT_BE_NULL;
			return null;
		} else if (locationString.isBlank()) {
			this.locationError = UI.ExceptionMessages.LOCATION_CANNOT_BE_EMPTY;
			return null;
		} else if (locationString.matches("(^(0|[1-9]{1,3})[\\s]{0,1}km [NSWE]{1,2} of [A-Z][a-z]+[\\s]{0,1}[A-Z]{0,1}[a-z]*[\\s]{0,1}[A-Z]{0,1}[a-z]+)")) {
			return locationString;
		} else if (locationString.matches("(^(0|[1-9]{1,3})[\\s]{0,1}KM [NSWE]{1,2} of [A-Z][a-z]+[\\s]{0,1}[A-Z]{0,1}[a-z]*[\\s]{0,1}[A-Z]{0,1}[a-z]+)")) {
			this.locationError = UI.Text.LOCATION_KM;
			return null;
		} else if (locationString.matches("(^(0|[1-9]{1,3})[\\s]{0,1}km [NSWE]{1,2} OF [A-Z][a-z]+[\\s]{0,1}[A-Z]{0,1}[a-z]*[\\s]{0,1}[A-Z]{0,1}[a-z]+)")) {
			this.locationError = UI.Text.LOCATION_OF;
			return null;
		} else if (locationString.matches("(^(0|[1-9]{1,3})[\\s]{0,1}km [nswe]{1,2} of [A-Z][a-z]+[\\s]{0,1}[A-Z]{0,1}[a-z]*[\\s]{0,1}[A-Z]{0,1}[a-z]+)")) {
			this.locationError = UI.Text.LOCATION_CARDINAL;
			return null;
		} else if (locationString.matches("(^(0|[1-9]{1,3})[\\s]{0,1}km [NSWE]{1,2} of [A-Z][a-z]+[\\s]{0,1}[A-Z]{0,1}[a-z]*[\\s]{0,1}[A-Z]{0,1}[a-z]+)*[\\S]*[\\s]*[,]+[\\s]*[\\S]*")) {
			this.locationError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else {
			this.locationError = UI.Text.INVALID;
			return null;
		} 
			
	}

	/**
	 * Validates magnitude. Removes trailing and leading spaces from the incoming
	 * string. Checks if the resulting string represents a valid magnitude and sets
	 * a suitable error message. This validator must ensure that the string
	 * representing the magnitude is a positive number between 0.01 and 10 (incl.)
	 * and cannot have more than two digits after the decimal point.
	 * 
	 * @precondition none
	 * @postcondition getMagnitudeError().isEmpty() if the passed-in string
	 *                represents a valid magnitude; otherwise getMagnitudeError()
	 *                returns a suitable error message
	 * @param magnitudeString the incoming string supposedly representing the
	 *                        magnitude
	 * @return the magnitude value represented by magnitudeString after leading and
	 *         trailing spaces have been removed; null if magnitudeString does not
	 *         represent a valid magnitude
	 */
	public Double validateMagnitude(String magnitudeString) { 
		magnitudeString = magnitudeString.trim();
		if (magnitudeString.isBlank()) {
			this.magnitudeError = UI.Text.REQUIRED;
			return null;
		} else if (magnitudeString.matches("^(([2-9]|10|1)((.)\\d{1,2})?)|(0.\\d{1,2})")) {
			if (Double.parseDouble(magnitudeString) <= 10) {
				return Double.parseDouble(magnitudeString);
			} else {
				this.magnitudeError = UI.ExceptionMessages.MAGNITUDE_OUT_OF_RANGE;
				return null;
			}
			
		} else if (magnitudeString.matches("[0][\\S]*")) {
			this.magnitudeError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (magnitudeString.matches("[\\S]*[,][\\S]*")) {
			this.magnitudeError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (magnitudeString.matches("[-]+[\\S]*")) {
			this.magnitudeError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else if (magnitudeString.matches("^(([2-9]|10|1)((.)\\d*)?)|(0.\\d*)")) {
			this.magnitudeError = UI.Text.DECIMAL_PLACES;
			return null;
		} else {
			this.magnitudeError = UI.Text.INVALID;
			return null; 
		}	
	}

	/**
	 * Validates significance. Removes trailing and leading spaces from the incoming
	 * string. Checks if the resulting string represents a valid significance and
	 * sets a suitable error message. This validator must ensure that the string
	 * representing the significance is a positive number that can have at most four
	 * digits and cannot start with zero.
	 * 
	 * @precondition none
	 * @postcondition getSignificanceError().isEmpty() if the passed-in string
	 *                represents a valid significance; otherwise
	 *                getSignificanceError() returns a suitable error message
	 * @param significanceString the incoming string supposedly representing the
	 *                           significance of an earthquake
	 * @return the significance value represented by significanceString after
	 *         leading and trailing spaces have been removed; null if
	 *         significanceString does not represent a valid significance
	 */
	public Integer validateSignificance(String significanceString) { 
		significanceString = significanceString.trim();
		if (significanceString.isBlank()) {
			this.significanceError = UI.Text.REQUIRED;
			return null;
		} else if (significanceString.matches("^[1-9][0-9]{0,3}")) { 
			if (Integer.parseInt(significanceString) <= Earthquake.MAX_SIGNIFICANCE && Integer.parseInt(significanceString) >= Earthquake.MIN_SIGNIFICANCE) {
				return Integer.parseInt(significanceString);
			} else {
				this.significanceError = UI.ExceptionMessages.SIGNIFICANCE_OUT_OF_RANGE;
				return null;
			}
	
		} else if (significanceString.matches("[0][\\S]*")) {
			this.significanceError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (significanceString.matches("[\\S]*[,][\\S]*")) {
			this.significanceError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (significanceString.matches("[-]+[\\S]*")) {
			this.significanceError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.significanceError = UI.Text.INVALID;
			return null; 
		}	
	}

	/**
	 * Validates distance. Removes trailing and leading spaces from the incoming
	 * string. Checks if the resulting string represents a valid distance and sets a
	 * suitable error message. This validator must ensure that the string
	 * representing the distance is a positive number between 0 and 7.1 (incl.)
	 * 
	 * 
	 * @precondition none
	 * @postcondition getDistanceError().isEmpty() if the passed-in string
	 *                represents a valid distance; otherwise getDistanceError()
	 *                returns a suitable error message
	 * @param distanceString the incoming string supposedly representing the
	 *                       distance
	 * @return the distance value represented by distanceString after leading and
	 *         trailing spaces have been removed; null if distanceString does not
	 *         represent a valid distance
	 */
	public Double validateDistance(String distanceString) {
		distanceString = distanceString.trim();
		if (distanceString.isBlank()) {
			this.distanceError = UI.Text.REQUIRED;
			return null;
		} else if (distanceString.matches("[0-7]([.]\\d{1,2})?")) {
			double distance = Double.parseDouble(distanceString);
			if (distance <= Earthquake.MAX_DISTANCE && distance > Earthquake.MIN_DISTANCE) { 
				return distance;
			} else {
				this.distanceError = UI.ExceptionMessages.DISTANCE_OUT_OF_RANGE;
				return null;
			}
		}  else if (distanceString.matches("[0][\\S]*")) {
			this.distanceError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (distanceString.matches("[\\S]*[,][\\S]*")) {
			this.distanceError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (distanceString.matches("[-]+[\\S]*")) {
			this.distanceError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else if (distanceString.matches("[0-7]([.]\\d*)?")) {
			this.distanceError = UI.Text.DECIMAL_PLACES;
			return null;
		} else {
			this.distanceError = UI.Text.INVALID;
			return null;
		}
	}

	/**
	 * Validates depth. Removes trailing and leading spaces from the incoming
	 * string. Checks if the resulting string represents a valid depth and sets a
	 * suitable error message. This validator must ensure that the string
	 * representing the depth is a whole number greater than or equal to zero.
	 * 
	 * 
	 * @precondition none
	 * @postcondition getDepthError().isEmpty() if the passed-in string represents a
	 *                valid depth; otherwise getDepthError() returns a suitable
	 *                error message
	 * @param depthString the incoming string supposedly representing the depth
	 * @return the depth value represented by depthString after leading and trailing
	 *         spaces have been removed; null if depthString does not represent a
	 *         valid depth
	 */
	public Double validateDepth(String depthString) {
		depthString = depthString.trim();
		if (depthString.isBlank()) {
			this.depthError = UI.Text.REQUIRED;
			return null;
		} else if (depthString.matches("^(^([2-9]|10|1[0-9]*)((.)\\d{1,2})?)|(0.\\d{1,2})")) {
			return Double.parseDouble(depthString);
		}  else if (depthString.matches("[0][\\S]*")) {
			this.depthError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (depthString.matches("[\\S]*[,][\\S]*")) {
			this.depthError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (depthString.matches("[-]+[\\S]*")) {
			this.depthError = UI.ExceptionMessages.DEPTH_CANNOT_BE_NEGATIVE;
			return null;
		} else if (depthString.matches("^(^([2-9]|10|1[0-9]*)((.)\\d*)?)|(0.\\d*)")) {
			this.depthError = UI.Text.DECIMAL_PLACES;
			return null;
		} else {
			this.depthError = UI.Text.INVALID;
			return null;
		}
	} 

}
