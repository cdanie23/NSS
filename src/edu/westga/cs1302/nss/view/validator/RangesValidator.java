package edu.westga.cs1302.nss.view.validator;

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
		// TODO Part 2-A Step 1.a.
		this.magnitudeRangeError = "";
		return -1.0;
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
		// TODO Part 2-A Step 1.b.
		this.significanceRangeError = "";
		return -1;
	}

}
