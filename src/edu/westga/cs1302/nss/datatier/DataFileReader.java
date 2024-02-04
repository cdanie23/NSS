package edu.westga.cs1302.nss.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.Network;
import edu.westga.cs1302.nss.resources.GeneralConstants;
import edu.westga.cs1302.nss.resources.UI.ExceptionMessages;

/**
 * Reads seismic data from a file with extension Constants.FILE_EXTENSION which
 * is a CSV file with the following format:
 * station,year,month,day,hour,minute,second,location,magnitude,significance,distance,depth
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class DataFileReader {

	private File seismicDataFile;

	/**
	 * Instantiates a new seismic data file reader.
	 *
	 * @precondition seismicDataFile != null
	 * @postcondition none
	 * @param seismicDataFile the seismic data file
	 */
	public DataFileReader(File seismicDataFile) {
		if (seismicDataFile == null) {
			throw new IllegalArgumentException(ExceptionMessages.FILE_CANNOT_BE_NULL);
		}
		this.seismicDataFile = seismicDataFile;
	}

	/**
	 * Reads all the earthquake data from the file one line at a time where each
	 * line contains information about the earthquake as well as the station where
	 * it was recorded. Parses each line and creates an earthquake object and stores
	 * it in the corresponding station of the passed in network.
	 * 
	 * @precondition network != null
	 * @postcondition none
	 * @param network the network to load from file
	 * @return the list of names of all newly added stations
	 * @throws FileNotFoundException exception to be thrown if file is not found
	 */
	public ArrayList<String> loadAllEarthquakesToStations(Network network) throws FileNotFoundException {
		if (network == null) {
			throw new IllegalArgumentException("network cannot be null");
		}
		int lineNum = 1;
		String line = null;
		String[] tokens = null;
		ArrayList<String> names = new ArrayList<String>();

		try (Scanner scnr = new Scanner(this.seismicDataFile)) {
			while (scnr.hasNextLine()) {
				try {
					line = scnr.nextLine();
					tokens = line.split(GeneralConstants.FIELD_SEPARATOR);

					names.add(tokens[0]);

					int year = Integer.parseInt(tokens[1]);
					int month = Integer.parseInt(tokens[2]);
					int day = Integer.parseInt(tokens[3]);
					int hour = Integer.parseInt(tokens[4]);
					int minute = Integer.parseInt(tokens[5]);
					int second = Integer.parseInt(tokens[6]);
					LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);

					double magnitude = Double.parseDouble(tokens[8]);
					int significance = Integer.parseInt(tokens[9]);
					double distance = Double.parseDouble(tokens[10]);
					double depth = Double.parseDouble(tokens[10]);
					Earthquake quake = new Earthquake(dateTime, tokens[7], magnitude, significance, distance, depth);

					network.addStation(tokens[0]);

					network.findStation(tokens[0]).getSeismicData().add(quake);

					lineNum++;
				} catch (IllegalArgumentException ex) {
					System.err.println(this.formatErrorMsg(lineNum, tokens, ex));
				}

			}
		}
		return names;
	}

	private String formatErrorMsg(int lineNum, String[] tokens, IllegalArgumentException ex) {
		String formatedLineNum = String.format("%2s", lineNum);
		return "Error reading file, line " + formatedLineNum + ": " + ex.getMessage() + Arrays.toString(tokens);
	}
}
