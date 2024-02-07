package edu.westga.cs1302.nss.view.validator;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.resources.UI;

/**
 * The validator for ranges objects.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class RangesValidator {

	private String magnitudeRangeError;
	private String significanceRangeError;

	/**
	 * Instantiates a new RangesValidator.
	 *
	 * @precondition none
	 * @postcondition all fields are empty
	 */
	public RangesValidator() {
		this.reset();
	}

	/**
	 * Returns the magnitudeRangeError of this RangesValidator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the magnitudeRangeError
	 */
	public String getMagnitudeRangeError() {
		return this.magnitudeRangeError;
	}

	/**
	 * Returns the significanceRangeError of this RangesValidator.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the significanceRangeError
	 */
	public String getSignificanceRangeError() {
		return this.significanceRangeError;
	}

	/**
	 * Validates the magnitude range. Removes trailing and leading spaces from the
	 * specified magnitudeRangeString. A valid magnitude range has to be a value
	 * between Earthquake.MIN_MAGNITUDE and Earthquake.MAX_MAGNITUDE (incl.) and
	 * cannot have more than two digits after the decimal point.
	 * 
	 * @precondition none
	 * @postcondition getMagnitudeRangeError().isEmpty() if the passed-in string
	 *                represents a valid magnitude range; otherwise
	 *                getMagnitudeRangeError() returns a suitable error message
	 * @param magnitudeRangeString the incoming string supposedly representing the
	 *                             magnitude range for the report
	 * @return the magnitude range value represented by magnitudeRangeString; null
	 *         if magnitudeRangeString does not represent a valid magnitude range
	 */
	public Double validateMagnitudeRange(String magnitudeRangeString) {
		magnitudeRangeString = magnitudeRangeString.trim();
		
		if (magnitudeRangeString.isBlank()) {
			this.magnitudeRangeError = UI.Text.REQUIRED;
			return null;
		} else if (magnitudeRangeString.matches("^(([2-9]|10|1)((.)\\d{1,2})?)|(0.\\d{1,2})")) {
			double magnitudeRange = Double.parseDouble(magnitudeRangeString);
			if (magnitudeRange >= Earthquake.MIN_MAGNITUDE && magnitudeRange <= Earthquake.MAX_MAGNITUDE) {
				return magnitudeRange;
			} else {
				this.magnitudeRangeError = UI.ExceptionMessages.MAGNITUDE_OUT_OF_RANGE;
				return null;
			}
		} else if (magnitudeRangeString.matches("[0][\\S]*")) {
			this.magnitudeRangeError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (magnitudeRangeString.matches("[\\S]*[,][\\S]*")) {
			this.magnitudeRangeError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (magnitudeRangeString.matches("[-]+[\\S]*")) {
			this.magnitudeRangeError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else if (magnitudeRangeString.matches("^(([2-9]|10|1)((.)\\d*)?)|(0.\\d*)")) {
			this.magnitudeRangeError = UI.Text.DECIMAL_PLACES;
			return null;
		} else {
			this.magnitudeRangeError = UI.Text.INVALID;
			return null; 
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		this.magnitudeRangeError = "";
		this.significanceRangeError = "";
	}

	/**
	 * Found error.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true, if a preceding call to the method validateMagnitudeRange()
	 *         detected an error
	 */
	public boolean foundMagnitudeRangeError() {
		return !this.magnitudeRangeError.isEmpty();
	}

	/**
	 * Found error.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true, if a preceding call to the method validateSignificanceRange()
	 *         detected an error
	 */
	public boolean foundSignificanceRangeError() {
		return !this.significanceRangeError.isEmpty();
	}

	/**
	 * Validates the significance range. Removes trailing and leading spaces from
	 * the specified significanceRangeString. A valid significance range has to be a
	 * value between Earthquake.MIN_SIGNIFICANCE and Earthquake.MAX_SIGNIFICANCE
	 * (incl.)
	 * 
	 * @precondition none
	 * @postcondition getSignificanceRangeError().isEmpty() if the passed-in string
	 *                represents a valid significance range; otherwise
	 *                getSignificanceRangeError() returns a suitable error message
	 * @param significanceRangeString the incoming string supposedly representing
	 *                                the significance range for the report
	 * @return the significance range value represented by significanceRangeString;
	 *         null if significanceRangeString does not represent a valid
	 *         significance range
	 */
	public Integer validateSignificanceRange(String significanceRangeString) {
		significanceRangeString = significanceRangeString.trim();
		if (significanceRangeString.isBlank()) {
			this.significanceRangeError = UI.Text.REQUIRED;
			return null;
		} else if (significanceRangeString.matches("^[1-9][0-9]{0,3}")) { 
			int significanceRange = Integer.parseInt(significanceRangeString);
			if (significanceRange <= Earthquake.MAX_SIGNIFICANCE && significanceRange >= Earthquake.MIN_SIGNIFICANCE) {
				return significanceRange;
			} else {
				this.significanceRangeError = UI.ExceptionMessages.SIGNIFICANCE_OUT_OF_RANGE;
				return null;
			} 
		} else if (significanceRangeString.matches("[0][\\S]*")) {
			this.significanceRangeError = UI.Text.CANNOT_START_WITH_ZERO;
			return null;
		} else if (significanceRangeString.matches("[\\S]*[,][\\S]*")) {
			this.significanceRangeError = UI.Text.CANNOT_CONTAIN_COMMAS;
			return null;
		} else if (significanceRangeString.matches("[-]+[\\S]*")) {
			this.significanceRangeError = UI.Text.CANNOT_BE_NEGATIVE;
			return null;
		} else {
			this.significanceRangeError = UI.Text.INVALID;
			return null; 
		}
	}

}
