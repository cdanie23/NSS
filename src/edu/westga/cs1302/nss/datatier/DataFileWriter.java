package edu.westga.cs1302.nss.datatier;

import java.io.File;
import java.io.FileNotFoundException;

import edu.westga.cs1302.nss.model.Network;
import edu.westga.cs1302.nss.resources.UI;

/**
 * The class network file writer.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class DataFileWriter {

	// private File seismicDataFile; 
	
	/**
	 * Instantiates a new data file writer.
	 *
	 * @precondition file != null
	 * @postcondition none
	 * @param file the data file
	 */
	public DataFileWriter(File file) {
		if (file == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.FILE_CANNOT_BE_NULL);
		}
		// this.seismicDataFile = file;
		
	}

	/**
	 * Writes all earthquakes of each station in the specified network to the data
	 * file. Each earthquake will be on a separate line and of the following format:
	 * station,year,month,day,hour,minute,second,location,magnitude,significance,distance,depth
	 *
	 * @precondition network != null
	 * @postcondition none
	 * @param network the network whose data will be written to file.
	 * @throws FileNotFoundException exception to be thrown if file not found
	 */
	public void writeNetwork(Network network) throws FileNotFoundException {
		// TODO Part 3-B Step 1-a
	}

}
