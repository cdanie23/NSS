package edu.westga.cs1302.nss.view.output;

//import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.Network;
import edu.westga.cs1302.nss.model.SeismicData;
import edu.westga.cs1302.nss.model.Station;
import edu.westga.cs1302.nss.resources.GeneralConstants;

/**
 * Generates reports.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class ReportGenerator {

	private static final String NO_STATION_SELECTED = "No station selected.";
	private static final String NO_DATA_EXISTS = "No data exists.";
	// private DecimalFormat decimalFormatter;
	// private DecimalFormat integerFormatter;

	/**
	 * Instantiates a new ReportGenerator.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ReportGenerator() {
		// this.decimalFormatter = new DecimalFormat("#0.00");
		// this.integerFormatter = new DecimalFormat("#,##0");
	}

	/**
	 * Builds the summary report of the specified station's seismic data. If seismic
	 * data is null, instead of throwing an exception, will return a string saying
	 * "No seismic data exists.", otherwise builds a summary report of the seismic
	 * data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param station the specified station
	 * @param range   the segment magnitude range
	 * @return a formatted summary string of the seismic data.
	 */
	public String buildSeismicDataSummaryByMagnitude(Station station, double range) {
		if (station == null) {
			return ReportGenerator.NO_STATION_SELECTED;
		}

		SeismicData seismicData = station.getSeismicData();
		if (seismicData == null) {
			return ReportGenerator.NO_DATA_EXISTS;
		}

		String header = this.getSeismicSummaryHeader(station);

		int[] sortedEarthquakes = seismicData.countEarthquakesByMagnitudeSegments(range);
		double highestMagnitude = seismicData.getHighestMagnitude();

		double segmentMax = GeneralConstants.FIRST_SEGMENT_MIN;
		double numOfSegments = (Math.ceil((double) highestMagnitude / range));
		double largestSegmentMax = numOfSegments * range;
		int indexOfArray = 0;

		String body = "\n" + "\n" + "Min magnitude  " + "Max magnitude  " + "#Earthquakes" + "\n" + "    (Richter)"
				+ "      (Richter)" + "\n";
		while (segmentMax < largestSegmentMax) {
			double segmentMin = segmentMax;
			segmentMax += range;
			body += String.format("%13.2f", segmentMin + GeneralConstants.SEGMENT_MIN_ADDITIVE)
					+ String.format("%15.2f", segmentMax) + String.format("%14d", sortedEarthquakes[indexOfArray])
					+ "\n";
			indexOfArray++;
		}

		return header + body;

	}

	/**
	 * Builds the summary report of the specified station's seismic data. If seismic
	 * data is null, instead of throwing an exception, will return a string saying
	 * "No seismic data exists.", otherwise builds a summary report of the seismic
	 * data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param station the specified station
	 * @param range   the segment significance range
	 * @return a formatted summary string of the seismic data.
	 */
	public String buildSeismicDataSummaryBySignificance(Station station, int range) {
		if (station == null) {
			return ReportGenerator.NO_STATION_SELECTED;
		}

		SeismicData seismicData = station.getSeismicData();
		if (seismicData == null) {
			return ReportGenerator.NO_DATA_EXISTS;
		}

		String summary = this.getSeismicSummaryHeader(station);
		int highestSignificance = seismicData.getHighestSignificance();
		int [] sortedEarthquakes = seismicData.countEarthquakesBySignificanceSegments(range);
		double numOfSegments = (Math.ceil((double) highestSignificance / range));
		double largestSegmentMax = range * numOfSegments;
		int indexOfArray = 0;
		int segmentMax = Earthquake.MIN_SIGNIFICANCE;
		
		String body = "\n" + "\n" + "Min significance  " + "Max significance  " + "#Earthquakes" + "\n";
		
		while (segmentMax < largestSegmentMax) {
			int segmentMin = segmentMax;
			if (segmentMin > 0) {
				segmentMin++;
			}
			segmentMax += range;
			
			body += String.format("%16d", segmentMin) + String.format("%18d", segmentMax) + String.format("%14d", sortedEarthquakes[indexOfArray]) + "\n";
			indexOfArray++;
		}
		
		return summary + body;
	}

	/**
	 * Builds the report listing all earthquakes of the specified station's seismic
	 * data that contain the search term in the location. If seismic data is null,
	 * instead of throwing an exception, will return a string saying "No seismic
	 * data exists.", otherwise builds the report.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param station    the specified station
	 * @param searchTerm the specified search term
	 * @return a formatted summary string of the requested earthquakes
	 */
	public String buildEarthquakeSummaryByLocation(Station station, String searchTerm) {
		if (station == null) {
			return ReportGenerator.NO_STATION_SELECTED;
		}
		SeismicData seismicData = station.getSeismicData();
		if (seismicData == null) {
			return ReportGenerator.NO_DATA_EXISTS;
		}

		String summary = null;
		ArrayList<Earthquake> earthquakes = seismicData.getEarthquakesMatchingLocation(searchTerm);
		if (earthquakes == null) {
			summary = "TODO Part 1-D Step 1 [not yet implemented]";
		}

		// TODO Part 1-D Step 2
		summary = "Earthquakes at station " + station.getName() + " containing " + "\"" + searchTerm + "\"" + ":"
				+ "\n";
		String summaryOfQuakes = "";
		for (Earthquake quake : earthquakes) {
			summaryOfQuakes = quake.getTime() + GeneralConstants.FIELD_SEPARATOR + " " + quake.getLocation()
					+ GeneralConstants.FIELD_SEPARATOR + " " + quake.getMagnitude() + "\n";
		}
		if (summaryOfQuakes.isEmpty()) {
			summaryOfQuakes = "No mathces found!";
		}

		return summary + summaryOfQuakes;
	}

	private String getSeismicSummaryHeader(Station station) {
		return "Station: " + station.getName() + "\n" + "#Earthquakes: " + station.getSeismicData().size() + "\n"
				+ "Highest magnitude: " + station.getSeismicData().getHighestMagnitude() + "\n"
				+ "Highest significance: " + station.getSeismicData().getHighestSignificance();

	}

	/**
	 * Builds the report listing all earthquakes of all the specified network's
	 * seismic stations data that contain the search term in the location. If the
	 * network is null, instead of throwing an exception, will return a string
	 * saying "No seismic data exists.", otherwise builds the report.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param network    the specified network
	 * @param searchTerm the specified search term
	 * @return a formatted summary string of the filtered earthquakes.
	 */
	public String buildEarthquakeSummaryByLocation(Network network, String searchTerm) {
		return "TODO Part 3-A Step 1 [not yet implemented]";
	}

	/**
	 * Builds the summary report of the specified network's seismic data. If seismic
	 * data is null, instead of throwing an exception, will return a string saying
	 * "No seismic data exists.", otherwise builds a summary report of the seismic
	 * data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param network the network
	 * @param range   the segment magnitude range
	 * @return a formatted summary string of the seismic data.
	 */
	public String buildSeismicDataSummaryByMagnitude(Network network, double range) {
		return "TODO Part 3-A Step 2 [not yet implemented]";
	}

	/**
	 * Builds the summary report of the specified network's seismic data. If seismic
	 * data is null, instead of throwing an exception, will return a string saying
	 * "No seismic data exists.", otherwise builds a summary report of the seismic
	 * data.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param network the network
	 * @param range   the segment significance range
	 * @return a formatted summary string of the seismic data.
	 */

	public String buildSeismicDataSummaryBySignificance(Network network, int range) {
		return "TODO Part 3-A Step 3 [not yet implemented]";
	}

}
