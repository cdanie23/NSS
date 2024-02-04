package edu.westga.cs1302.nss.viewmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import edu.westga.cs1302.nss.datatier.DataFileReader;
import edu.westga.cs1302.nss.datatier.DataFileWriter;
import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.Network;
import edu.westga.cs1302.nss.model.SeismicData;
import edu.westga.cs1302.nss.model.Station;
import edu.westga.cs1302.nss.resources.UI;
import edu.westga.cs1302.nss.view.output.ReportGenerator;
import edu.westga.cs1302.nss.view.validator.EarthquakeValidator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * The view model of the NSS application.
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class NSSViewModel {

	private Network network;
	private ReportGenerator report;

	private StringProperty yearProperty;
	private StringProperty monthProperty;
	private StringProperty dayProperty;
	private StringProperty hourProperty;
	private StringProperty minuteProperty;
	private StringProperty secondProperty;
	private StringProperty locationProperty;
	private StringProperty magnitudeProperty;
	private StringProperty significanceProperty;
	private StringProperty distanceProperty;
	private StringProperty depthProperty;

	private StringProperty yearErrorProperty;
	private StringProperty monthErrorProperty;
	private StringProperty dayErrorProperty;
	private StringProperty hourErrorProperty;
	private StringProperty minuteErrorProperty;
	private StringProperty secondErrorProperty;
	private StringProperty locationErrorProperty;
	private StringProperty magnitudeErrorProperty;
	private StringProperty significanceErrorProperty;
	private StringProperty distanceErrorProperty;
	private StringProperty depthErrorProperty;

	private StringProperty errorProperty;

	private ObjectProperty<Earthquake> selectedEarthquakeProperty;
	private ListProperty<Earthquake> earthquakesProperty;

	private ListProperty<Station> stationsProperty;
	private StringProperty stationNameProperty;
	private ObjectProperty<Station> selectedStationProperty;

	private StringProperty summaryProperty;
	private BooleanProperty outdatedSummaryProperty;

	/**
	 * Instantiates a new NSSViewModel
	 *
	 * @precondition
	 * @postcondition
	 */
	public NSSViewModel() {

		this.network = new Network("National Seismic System");
		this.report = new ReportGenerator();

		this.stationNameProperty = new SimpleStringProperty();
		this.stationsProperty = new SimpleListProperty<Station>();
		this.selectedStationProperty = new SimpleObjectProperty<Station>();

		this.yearProperty = new SimpleStringProperty();
		this.monthProperty = new SimpleStringProperty();
		this.dayProperty = new SimpleStringProperty();
		this.hourProperty = new SimpleStringProperty();
		this.minuteProperty = new SimpleStringProperty();
		this.secondProperty = new SimpleStringProperty();
		this.locationProperty = new SimpleStringProperty();
		this.distanceProperty = new SimpleStringProperty();
		this.magnitudeProperty = new SimpleStringProperty();
		this.significanceProperty = new SimpleStringProperty();
		this.depthProperty = new SimpleStringProperty();

		this.yearErrorProperty = new SimpleStringProperty();
		this.monthErrorProperty = new SimpleStringProperty();
		this.dayErrorProperty = new SimpleStringProperty();
		this.hourErrorProperty = new SimpleStringProperty();
		this.minuteErrorProperty = new SimpleStringProperty();
		this.secondErrorProperty = new SimpleStringProperty();
		this.locationErrorProperty = new SimpleStringProperty();
		this.magnitudeErrorProperty = new SimpleStringProperty();
		this.significanceErrorProperty = new SimpleStringProperty();
		this.distanceErrorProperty = new SimpleStringProperty();
		this.depthErrorProperty = new SimpleStringProperty();
		this.errorProperty = new SimpleStringProperty();

		this.selectedEarthquakeProperty = new SimpleObjectProperty<Earthquake>();
		this.earthquakesProperty = new SimpleListProperty<Earthquake>();
		this.summaryProperty = new SimpleStringProperty();
		this.outdatedSummaryProperty = new SimpleBooleanProperty();

		this.resetStations();
	}

	/**
	 * Returns the yearProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the yearProperty
	 */
	public StringProperty yearProperty() {
		return this.yearProperty;
	}

	/**
	 * Returns the monthProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the monthProperty
	 */
	public StringProperty monthProperty() {
		return this.monthProperty;
	}

	/**
	 * Returns the dayProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the dayProperty
	 */
	public StringProperty dayProperty() {
		return this.dayProperty;
	}

	/**
	 * Returns the hourProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the hourProperty
	 */
	public StringProperty hourProperty() {
		return this.hourProperty;
	}

	/**
	 * Summary property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the summary property
	 */
	public StringProperty summaryProperty() {
		return this.summaryProperty;
	}

	/**
	 * Outdated summary property.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return the update summary property
	 */
	public BooleanProperty outdatedSummaryProperty() {
		return this.outdatedSummaryProperty;
	}

	/**
	 * Returns the minuteProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the minuteProperty
	 */
	public StringProperty minuteProperty() {
		return this.minuteProperty;
	}

	/**
	 * Returns the secondProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the secondProperty
	 */
	public StringProperty secondProperty() {
		return this.secondProperty;
	}

	/**
	 * Returns the locationProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the location
	 */
	public StringProperty locationProperty() {
		return this.locationProperty;
	}

	/**
	 * Returns the magnitudeProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the magnitude
	 */
	public StringProperty magnitudeProperty() {
		return this.magnitudeProperty;
	}

	/**
	 * Returns the significanceProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the significance
	 */
	public StringProperty significanceProperty() {
		return this.significanceProperty;
	}

	/**
	 * Returns the distanceProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the distance
	 */
	public StringProperty distanceProperty() {
		return this.distanceProperty;
	}

	/**
	 * Returns the depthProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the depth
	 */
	public StringProperty depthProperty() {
		return this.depthProperty;
	}

	/**
	 * Returns the yearErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the yearErrorProperty
	 */
	public StringProperty yearErrorProperty() {
		return this.yearErrorProperty;
	}

	/**
	 * Returns the monthErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the monthErrorProperty
	 */
	public StringProperty monthErrorProperty() {
		return this.monthErrorProperty;
	}

	/**
	 * Returns the dayErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the dayErrorProperty
	 */
	public StringProperty dayErrorProperty() {
		return this.dayErrorProperty;
	}

	/**
	 * Returns the hourErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the hourErrorProperty
	 */
	public StringProperty hourErrorProperty() {
		return this.hourErrorProperty;
	}

	/**
	 * Returns the minuteErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the minuteErrorProperty
	 */
	public StringProperty minuteErrorProperty() {
		return this.minuteErrorProperty;
	}

	/**
	 * Returns the secondErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the secondErrorProperty
	 */
	public StringProperty secondErrorProperty() {
		return this.secondErrorProperty;
	}

	/**
	 * Returns the locationErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the locationErrorProperty
	 */
	public StringProperty locationErrorProperty() {
		return this.locationErrorProperty;
	}

	/**
	 * Returns the magnitudeErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the magnitudeErrorProperty
	 */
	public StringProperty magnitudeErrorProperty() {
		return this.magnitudeErrorProperty;
	}

	/**
	 * Returns the significanceErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the significanceErrorProperty
	 */
	public StringProperty significanceErrorProperty() {
		return this.significanceErrorProperty;
	}

	/**
	 * Returns the distanceErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the distanceErrorProperty
	 */
	public StringProperty distanceErrorProperty() {
		return this.distanceErrorProperty;
	}

	/**
	 * Returns the depthErrorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the depthErrorProperty
	 */
	public StringProperty depthErrorProperty() {
		return this.depthErrorProperty;
	}

	/**
	 * Returns the errorProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the errorProperty
	 */
	public StringProperty errorProperty() {
		return this.errorProperty;
	}

	/**
	 * Returns the selectedEarthquakeProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the selectedEarthquakeProperty
	 */
	public ObjectProperty<Earthquake> selectedEarthquakeProperty() {
		return this.selectedEarthquakeProperty;
	}

	/**
	 * Returns the earthquakesProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the earthquakesProperty
	 */
	public ListProperty<Earthquake> earthquakesProperty() {
		return this.earthquakesProperty;
	}

	/**
	 * Returns the stationsProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the stationsProperty
	 */
	public ListProperty<Station> stationsProperty() {
		return this.stationsProperty;
	}

	/**
	 * Returns the stationNameProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the stationNameProperty
	 */
	public StringProperty stationNameProperty() {
		return this.stationNameProperty;
	}

	/**
	 * Returns the selectedStationProperty of this NSSViewModel.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the selectedStationProperty
	 */
	public ObjectProperty<Station> selectedStationProperty() {
		return this.selectedStationProperty;
	}

	/**
	 * Add station to the network.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true, if station successfully added; false otherwise
	 */
	public boolean addStation() {
		String stationName = this.stationNameProperty.get();
		if (this.network.addStation(stationName)) {
			// this.stationWriter.append(stationName);
			this.resetFormFields();
			this.resetStations();
			return true;
		}
		return false;
	}

	private void resetStations() {
		this.stationNameProperty.set("");
		this.stationsProperty.set(FXCollections.observableArrayList(this.network.getStations()));
	}

	/**
	 * Resets all form fields.
	 */
	public void resetFormFields() {

		this.yearProperty.set("");
		this.monthProperty.set("");
		this.dayProperty.set("");
		this.hourProperty.set("");
		this.minuteProperty.set("");
		this.secondProperty.set("");
		this.locationProperty.set("");
		this.magnitudeProperty.set("");
		this.significanceProperty.set("");
		this.distanceProperty.set("");
		this.depthProperty.set("");

		this.yearErrorProperty.set("");
		this.monthErrorProperty.set("");
		this.dayErrorProperty.set("");
		this.hourErrorProperty.set("");
		this.minuteErrorProperty.set("");
		this.secondErrorProperty.set("");
		this.locationErrorProperty.set("");
		this.magnitudeErrorProperty.set("");
		this.significanceErrorProperty.set("");
		this.distanceErrorProperty.set("");
		this.depthErrorProperty.set("");

		this.errorProperty.set("");
		this.outdatedSummaryProperty.set(true);

		Station station = this.selectedStationProperty.get();
		if (station != null) {
			this.earthquakesProperty.set(FXCollections.observableArrayList(station.getSeismicData().getEarthquakes()));
		}
	}

	/**
	 * Loads the data from the specified File object.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param file the file to load the data from
	 */
	public void loadData(File file) {
		DataFileReader reader = new DataFileReader(file);
		try {
			reader.loadAllEarthquakesToStations(this.network);
			this.resetFormFields();
			this.resetStations();
		} catch (FileNotFoundException fnfe) {
			this.errorProperty.set(fnfe.getMessage());
		}
	}

	/**
	 * Saves the seismic data to the specified File.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param file the file to save the data tos
	 */
	public void saveData(File file) {
		DataFileWriter writer = new DataFileWriter(file);
		try {
			writer.writeNetwork(this.network);
		} catch (FileNotFoundException fnfe) {
			this.errorProperty.set(fnfe.getMessage());
		}

	}

	/**
	 * Updates the summary report to display earthquakes for the selected station.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param magnitudeRange the magnitude range
	 */
	public void updateSeismicSummaryForStationByMagnitude(double magnitudeRange) {
		Station station = this.selectedStationProperty.get();
		String summaryReport = this.report.buildSeismicDataSummaryByMagnitude(station, magnitudeRange);
		this.summaryProperty.set(summaryReport);

	}

	/**
	 * Updates the summary report to display earthquakes for all network stations.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param magnitudeRange the magnitude range
	 */
	public void updateSeismicSummaryForNetworkByMagnitude(double magnitudeRange) {
		String summaryReport = this.report.buildSeismicDataSummaryByMagnitude(this.network, magnitudeRange);
		this.summaryProperty.set(summaryReport);
	}

	/**
	 * Updates the summary report to display earthquakes for the selected station.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param significanceRange the significance range
	 */
	public void updateSeismicSummaryForStationBySignificance(int significanceRange) {
		Station station = this.selectedStationProperty.get();
		String summaryReport = this.report.buildSeismicDataSummaryBySignificance(station, significanceRange);
		this.summaryProperty.set(summaryReport);

	}

	/**
	 * Updates the summary report to display earthquakes for all network stations.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param significanceRange the significance range
	 */
	public void updateSeismicSummaryForNetworkBySignificance(int significanceRange) {
		String summaryReport = this.report.buildSeismicDataSummaryBySignificance(this.network, significanceRange);
		this.summaryProperty.set(summaryReport);

	}

	/**
	 * Updates the summary report to display earthquakes containing searthTerm in
	 * location from the selected station
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param searchTerm the search term
	 */
	public void updateSeismicSummaryForStationByTerm(String searchTerm) {
		Station station = this.selectedStationProperty.get();
		String summaryReport = this.report.buildEarthquakeSummaryByLocation(station, searchTerm);
		this.summaryProperty.set(summaryReport);
	}

	/**
	 * Updates the summary report to display earthquakes containing searthTerm in
	 * location
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param searchTerm the search term
	 */
	public void updateSeismicSummaryForNetworkByTerm(String searchTerm) {
		String summaryReport = this.report.buildEarthquakeSummaryByLocation(this.network, searchTerm);
		this.summaryProperty.set(summaryReport);
	}

	/**
	 * Adds an earthquake with specified attributes to the station.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return true if earthquake added successfully, false otherwise
	 */
	public boolean addEarthquake() {

		Station station = this.selectedStationProperty.get();
		EarthquakeValidator validator = new EarthquakeValidator();

		Integer year = validator.validateYear(this.yearProperty().get());
		Integer month = validator.validateMonth(this.monthProperty().get());
		Integer day = validator.validateDay(this.dayProperty().get());
		Integer hour = validator.validateHour(this.hourProperty().get());
		Integer minute = validator.validateMinute(this.minuteProperty().get());
		Integer second = validator.validateSecond(this.secondProperty().get());
		String location = validator.validateLocation(this.locationProperty().get());
		Double magnitude = validator.validateMagnitude(this.magnitudeProperty().get());
		Integer significance = validator.validateSignificance(this.significanceProperty().get());
		Double distance = validator.validateDistance(this.distanceProperty().get());
		Double depth = validator.validateDepth(this.depthProperty().get());

		this.yearErrorProperty.set(validator.getYearError());
		this.monthErrorProperty.set(validator.getMonthError());
		this.dayErrorProperty.set(validator.getDayError());
		this.hourErrorProperty.set(validator.getHourError());
		this.minuteErrorProperty.set(validator.getMinuteError());
		this.secondErrorProperty.set(validator.getSecondError());
		this.locationErrorProperty.set(validator.getLocationError());
		this.magnitudeErrorProperty.set(validator.getMagnitudeError());
		this.significanceErrorProperty.set(validator.getSignificanceError());
		this.distanceErrorProperty.set(validator.getDistanceError());
		this.depthErrorProperty.set(validator.getDepthError());

		if (validator.foundError()) {
			return false;
		}

		try {
			LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute, second);
			Earthquake earthquake = new Earthquake(time, location, magnitude, significance, distance, depth);
			SeismicData seismicData = station.getSeismicData();
			if (seismicData.add(earthquake)) {
				this.resetFormFields();
				return true;
			}
		} catch (IllegalArgumentException | DateTimeException exception) {
			this.errorProperty.set(exception.getMessage());
		}

		return false;
	}

	/**
	 * Updates selected earthquake with new values
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true, if successful
	 */
	public boolean updateEarthquake() {

		Earthquake earthquake = this.selectedEarthquakeProperty.get();
		EarthquakeValidator validator = new EarthquakeValidator();

		if (earthquake != null) {
			Integer year = validator.validateYear(this.yearProperty().get());
			Integer month = validator.validateMonth(this.monthProperty().get());
			Integer day = validator.validateDay(this.dayProperty().get());
			Integer hour = validator.validateHour(this.hourProperty().get());
			Integer minute = validator.validateMinute(this.minuteProperty().get());
			Integer second = validator.validateSecond(this.secondProperty().get());
			String location = validator.validateLocation(this.locationProperty().get());
			Double magnitude = validator.validateMagnitude(this.magnitudeProperty().get());
			Integer significance = validator.validateSignificance(this.significanceProperty().get());
			Double distance = validator.validateDistance(this.distanceProperty().get());
			Double depth = validator.validateDepth(this.depthProperty().get());

			this.yearErrorProperty.set(validator.getYearError());
			this.monthErrorProperty.set(validator.getMonthError());
			this.dayErrorProperty.set(validator.getDayError());
			this.hourErrorProperty.set(validator.getHourError());
			this.minuteErrorProperty.set(validator.getMinuteError());
			this.secondErrorProperty.set(validator.getSecondError());
			this.locationErrorProperty.set(validator.getLocationError());
			this.magnitudeErrorProperty.set(validator.getMagnitudeError());
			this.significanceErrorProperty.set(validator.getSignificanceError());
			this.distanceErrorProperty.set(validator.getDistanceError());
			this.depthErrorProperty.set(validator.getDepthError());

			if (validator.foundError()) {
				return false;
			}

			try {
				LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute, second);
				Station station = this.selectedStationProperty.get();
				SeismicData seismicData = station.getSeismicData();
				Earthquake newEq = new Earthquake(time, location, magnitude, significance, distance, depth);

				if (!seismicData.contains(newEq)) {
					earthquake.setTime(time);
					earthquake.setLocation(location);
					earthquake.setMagnitude(magnitude);
					earthquake.setSignificance(significance);
					earthquake.setDistance(distance);
					earthquake.setDepth(depth);

					this.resetFormFields();
					return true;
				} else {
					this.errorProperty.set(UI.ExceptionMessages.DUPLICATE_EARTHQUAKE);
				}
			} catch (IllegalArgumentException ex) {
				this.errorProperty.set(ex.getMessage());
			}
		}
		return false;
	}

	/**
	 * Remove selected earthquake.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return true, if earthquake successfully deleted, false otherwise
	 */
	public boolean deleteEarthquake() {
		Station station = this.selectedStationProperty.get();
		Earthquake earthquake = this.selectedEarthquakeProperty.get();

		SeismicData seismicData = station.getSeismicData();
		if (seismicData.remove(earthquake)) {
			this.resetFormFields();
			return true;
		}
		return false;

	}

}
