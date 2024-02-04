package edu.westga.cs1302.nss.view;

import java.io.File;
import java.time.LocalDateTime;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.Station;
import edu.westga.cs1302.nss.resources.GeneralConstants;
import edu.westga.cs1302.nss.view.validator.RangesValidator;
import edu.westga.cs1302.nss.viewmodel.NSSViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * The controller behind the GUI file NSSGui.fxml
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class NSSGuiCodeBehind {

	private NSSViewModel theViewModel;
	private ObjectProperty<Earthquake> selectedEarthquake;
	private ObjectProperty<Station> selectedStation;

	@FXML
	private AnchorPane pane;

	@FXML
	private Pane earthquakePane;

	@FXML
	private Pane summaryPane;

	@FXML
	private ComboBox<Station> stationsComboBox;

	@FXML
	private TextField stationNameTextField;

	@FXML
	private Button addStationButton;

	@FXML
	private TextField yearTextField;

	@FXML
	private TextField monthTextField;

	@FXML
	private TextField dayTextField;

	@FXML
	private TextField hourTextField;

	@FXML
	private TextField minuteTextField;

	@FXML
	private TextField secondTextField;

	@FXML
	private Label yearErrorLabel;

	@FXML
	private Label monthErrorLabel;

	@FXML
	private Label dayErrorLabel;

	@FXML
	private Label hourErrorLabel;

	@FXML
	private Label minuteErrorLabel;

	@FXML
	private Label secondErrorLabel;

	@FXML
	private Label locationErrorLabel;

	@FXML
	private TextField locationTextField;

	@FXML
	private TextField magnitudeTextField;

	@FXML
	private Label magnitudeErrorLabel;

	@FXML
	private TextField significanceTextField;

	@FXML
	private Label significanceErrorLabel;

	@FXML
	private TextField distanceTextField;

	@FXML
	private Label distanceErrorLabel;

	@FXML
	private TextField depthTextField;

	@FXML
	private Label depthErrorLabel;

	@FXML
	private Label errorLabel;

	@FXML
	private ListView<Earthquake> earthquakesListView;

	@FXML
	private Button addEarthquakeButton;

	@FXML
	private Button updateEarthquakeButton;

	@FXML
	private Button deleteEarthquakeButton;

	@FXML
	private ToggleGroup radioButtonGroup;

	@FXML
	private RadioButton magnitudeRadioButton;

	@FXML
	private RadioButton significanceRadioButton;

	@FXML
	private RadioButton specifiedLocationRadioButton;

	@FXML
	private TextField magnitudeRangeTextField;

	@FXML
	private Label magnitudeRangeErrorLabel;

	@FXML
	private TextField significanceRangeTextField;

	@FXML
	private Label significanceRangeErrorLabel;

	@FXML
	private TextField searchTermTextField;

	@FXML
	private Label searchTermErrorLabel;

	@FXML
	private CheckBox allStationsCheckBox;

	@FXML
	private Button generateReportButton;

	@FXML
	private TextArea summaryTextArea;

	@FXML
	private MenuItem fileOpenMenuItem;

	@FXML
	private MenuItem fileSaveMenuItem;

	@FXML
	private MenuItem helpAboutMenuItem;

	/**
	 * Instantiates a new NSSGuiCodeBehind
	 */
	public NSSGuiCodeBehind() {
		this.theViewModel = new NSSViewModel();
		this.selectedEarthquake = new SimpleObjectProperty<Earthquake>();
		this.selectedStation = new SimpleObjectProperty<Station>();
	}

	/**
	 * Initializes the GUI components, binding them to the view model properties
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@FXML
	public void initialize() {
		this.bindComponentsToViewModel();
		this.bindButtonsDisableProperty();
		this.setupListenerForListView();
		this.setupListenerForStationsComboBox();
		this.setupListenerForOutdatedSummary();
		this.setupRadioButtons();
		this.initializeUI();
	}

	private void setupRadioButtons() {

		this.magnitudeRangeTextField.setText(GeneralConstants.MAGNITUDE_RANGE_DEFAULT_VALUE);
		this.significanceRangeTextField.setText(GeneralConstants.SIGNIFICANCE_RANGE_DEFAULT_VALUE);

		this.radioButtonGroup = new ToggleGroup();
		this.magnitudeRadioButton.setToggleGroup(this.radioButtonGroup);
		this.magnitudeRadioButton.setSelected(true);
		this.significanceRadioButton.setToggleGroup(this.radioButtonGroup);
		this.specifiedLocationRadioButton.setToggleGroup(this.radioButtonGroup);

		this.magnitudeRadioButton.selectedProperty().addListener((observable, oldSelected, nowSelected) -> {
			if (!nowSelected) {
				this.magnitudeRangeTextField.disableProperty().set(true);
				this.clearSummaryReportErrorLabels();
			} else {
				this.magnitudeRangeTextField.disableProperty().set(false);
			}
		});

		this.significanceRadioButton.selectedProperty().addListener((observable, oldSelected, nowSelected) -> {
			if (!nowSelected) {
				this.significanceRangeTextField.disableProperty().set(true);
				this.clearSummaryReportErrorLabels();
			} else {
				this.significanceRangeTextField.disableProperty().set(false);
			}
		});

		this.specifiedLocationRadioButton.selectedProperty().addListener((observable, oldSelected, nowSelected) -> {
			if (!nowSelected) {
				this.searchTermTextField.disableProperty().set(true);
				this.clearSummaryReportErrorLabels();
			} else {
				this.searchTermTextField.disableProperty().set(false);
			}
		});

	}

	private void bindButtonsDisableProperty() {
		this.addStationButton.disableProperty().bind(this.stationNameTextField.textProperty().isEmpty());

		BooleanBinding earthquakeUnselectedBinding = Bindings
				.isNull(this.earthquakesListView.getSelectionModel().selectedItemProperty());

		BooleanBinding emptyFormFieldsBinding = Bindings.isEmpty(this.yearTextField.textProperty())
				.or(this.monthTextField.textProperty().isEmpty()).or(this.dayTextField.textProperty().isEmpty())
				.or(this.hourTextField.textProperty().isEmpty().or(this.minuteTextField.textProperty().isEmpty())
						.or(this.secondTextField.textProperty().isEmpty())
						.or(this.locationTextField.textProperty().isEmpty())
						.or(this.magnitudeTextField.textProperty().isEmpty())
						.or(this.significanceTextField.textProperty().isEmpty())
						.or(this.distanceTextField.textProperty().isEmpty())
						.or(this.depthTextField.textProperty().isEmpty()));

		this.deleteEarthquakeButton.disableProperty().bind(earthquakeUnselectedBinding);
		this.updateEarthquakeButton.disableProperty().bind(earthquakeUnselectedBinding.or(emptyFormFieldsBinding));
		this.addEarthquakeButton.disableProperty().bind(emptyFormFieldsBinding);

	}

	private void setupListenerForListView() {
		this.earthquakesListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldEarthquake, newEarthquake) -> {
					if (newEarthquake != null) {

						LocalDateTime time = newEarthquake.getTime();
						this.yearTextField.textProperty().set(String.valueOf(time.getYear()));
						this.monthTextField.textProperty().set(String.valueOf(time.getMonthValue()));
						this.dayTextField.textProperty().set(String.valueOf(time.getDayOfMonth()));
						this.hourTextField.textProperty().set(String.valueOf(time.getHour()));
						this.minuteTextField.textProperty().set(String.valueOf(time.getMinute()));
						this.secondTextField.textProperty().set(String.valueOf(time.getSecond()));

						this.locationTextField.textProperty().set(newEarthquake.getLocation());
						this.significanceTextField.textProperty().set(String.valueOf(newEarthquake.getSignificance()));
						this.magnitudeTextField.textProperty().set(String.valueOf(newEarthquake.getMagnitude()));
						this.distanceTextField.textProperty().set(String.valueOf(newEarthquake.getDistance()));
						this.depthTextField.textProperty().set(String.valueOf(newEarthquake.getDepth()));

						this.selectedEarthquake.set(newEarthquake);
					}
				});

	}

	private void setupListenerForOutdatedSummary() {
		this.theViewModel.outdatedSummaryProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.onGenerateReport();
			}
		});
	}

	private void initializeUI() {
		this.earthquakePane.disableProperty().set(true);
		this.summaryPane.disableProperty().set(true);
		this.selectedEarthquake.set(null);
		this.summaryTextArea.setStyle("-fx-font-family: monospace");
	}

	private void bindComponentsToViewModel() {

		this.stationsComboBox.itemsProperty().bind(this.theViewModel.stationsProperty());
		this.theViewModel.selectedStationProperty().bind(this.selectedStation);
		this.stationNameTextField.textProperty().bindBidirectional(this.theViewModel.stationNameProperty());
		this.theViewModel.selectedEarthquakeProperty().bind(this.selectedEarthquake);

		this.yearTextField.textProperty().bindBidirectional(this.theViewModel.yearProperty());
		this.monthTextField.textProperty().bindBidirectional(this.theViewModel.monthProperty());
		this.dayTextField.textProperty().bindBidirectional(this.theViewModel.dayProperty());
		this.hourTextField.textProperty().bindBidirectional(this.theViewModel.hourProperty());
		this.minuteTextField.textProperty().bindBidirectional(this.theViewModel.minuteProperty());
		this.secondTextField.textProperty().bindBidirectional(this.theViewModel.secondProperty());
		this.locationTextField.textProperty().bindBidirectional(this.theViewModel.locationProperty());
		this.magnitudeTextField.textProperty().bindBidirectional(this.theViewModel.magnitudeProperty());
		this.significanceTextField.textProperty().bindBidirectional(this.theViewModel.significanceProperty());
		this.distanceTextField.textProperty().bindBidirectional(this.theViewModel.distanceProperty());
		this.depthTextField.textProperty().bindBidirectional(this.theViewModel.depthProperty());

		this.yearErrorLabel.textProperty().bindBidirectional(this.theViewModel.yearErrorProperty());
		this.monthErrorLabel.textProperty().bindBidirectional(this.theViewModel.monthErrorProperty());
		this.dayErrorLabel.textProperty().bindBidirectional(this.theViewModel.dayErrorProperty());
		this.hourErrorLabel.textProperty().bindBidirectional(this.theViewModel.hourErrorProperty());
		this.minuteErrorLabel.textProperty().bindBidirectional(this.theViewModel.minuteErrorProperty());
		this.secondErrorLabel.textProperty().bindBidirectional(this.theViewModel.secondErrorProperty());
		this.locationErrorLabel.textProperty().bindBidirectional(this.theViewModel.locationErrorProperty());
		this.magnitudeErrorLabel.textProperty().bindBidirectional(this.theViewModel.magnitudeErrorProperty());
		this.significanceErrorLabel.textProperty().bindBidirectional(this.theViewModel.significanceErrorProperty());
		this.distanceErrorLabel.textProperty().bindBidirectional(this.theViewModel.distanceErrorProperty());
		this.depthErrorLabel.textProperty().bindBidirectional(this.theViewModel.depthErrorProperty());

		this.errorLabel.textProperty().bindBidirectional(this.theViewModel.errorProperty());
		this.earthquakesListView.itemsProperty().bind(this.theViewModel.earthquakesProperty());
		this.summaryTextArea.textProperty().bind(this.theViewModel.summaryProperty());
	}

	private void setupListenerForStationsComboBox() {
		this.stationsComboBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldStation, newStation) -> {
					if (newStation != null) {
						this.selectedStation.set(newStation);
						this.earthquakePane.disableProperty().set(false);
						this.summaryPane.disableProperty().set(false);
					} else if (oldStation == null) {
						this.earthquakePane.disableProperty().set(true);
						this.summaryPane.disableProperty().set(true);
					}
					this.theViewModel.resetFormFields();
				});
	}

	@FXML
	private void onAddEarthquake() {
		this.theViewModel.addEarthquake();
	}

	@FXML
	private void onAddStation() {
		this.theViewModel.addStation();
	}

	@FXML
	private void onUpdateEarthquake() {
		this.theViewModel.updateEarthquake();
	}

	@FXML
	private void onDeleteEarthquake() {
		this.theViewModel.deleteEarthquake();
	}

	@FXML
	private void onGenerateReport() {
		this.clearSummaryReportErrorLabels();
		if (this.magnitudeRadioButton.isSelected()) {
			this.onGenerateMagnitudeReport();
		} else if (this.significanceRadioButton.isSelected()) {
			this.onGenerateSignificanceReport();
		} else {
			this.onGenerateLocationReport();
		}
		this.theViewModel.outdatedSummaryProperty().set(false);
	}

	private void onGenerateLocationReport() {
		String searchTerm = this.searchTermTextField.getText();
		if (searchTerm == null || searchTerm.isEmpty()) {
			this.searchTermErrorLabel.setText("Enter a search term.");
			return;
		}

		if (!this.allStationsCheckBox.isSelected()) {
			this.theViewModel.updateSeismicSummaryForStationByTerm(searchTerm);
		} else {
			this.theViewModel.updateSeismicSummaryForNetworkByTerm(searchTerm);
		}
	}

	private void onGenerateSignificanceReport() {
		RangesValidator validator = new RangesValidator();
		Integer significanceRange = validator.validateSignificanceRange(this.significanceRangeTextField.getText());
		this.significanceRangeErrorLabel.setText(validator.getSignificanceRangeError());

		if (!validator.foundSignificanceRangeError()) {
			if (!this.allStationsCheckBox.isSelected()) {
				this.theViewModel.updateSeismicSummaryForStationBySignificance(significanceRange);
			} else {
				this.theViewModel.updateSeismicSummaryForNetworkBySignificance(significanceRange);
			}
		}

	}

	private void onGenerateMagnitudeReport() {
		RangesValidator validator = new RangesValidator();
		Double magnitudeRange = validator.validateMagnitudeRange(this.magnitudeRangeTextField.getText());
		this.magnitudeRangeErrorLabel.setText(validator.getMagnitudeRangeError());

		if (!validator.foundMagnitudeRangeError()) {
			if (!this.allStationsCheckBox.isSelected()) {
				this.theViewModel.updateSeismicSummaryForStationByMagnitude(magnitudeRange);
			} else {
				this.theViewModel.updateSeismicSummaryForNetworkByMagnitude(magnitudeRange);
			}
		}
	}

	private void clearSummaryReportErrorLabels() {
		this.magnitudeRangeErrorLabel.setText("");
		this.significanceRangeErrorLabel.setText("");
		this.searchTermErrorLabel.setText("");
	}

	private void setFileFilters(FileChooser fileChooser) {
		ExtensionFilter filter = new ExtensionFilter("Earthquake Data", "*." + GeneralConstants.FILE_EXTENSION);
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter("All Files", "*.*");
		fileChooser.getExtensionFilters().add(filter);
	}

	@FXML
	private void onFileOpen() {
		FileChooser fileChooser = new FileChooser();
		this.setFileFilters(fileChooser);

		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(owner);

		if (selectedFile != null) {
			this.theViewModel.loadData(selectedFile);
		}
	}

	@FXML
	private void onFileSave() {
		FileChooser fileChooser = new FileChooser();
		this.setFileFilters(fileChooser);

		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showSaveDialog(owner);

		if (selectedFile != null) {
			this.theViewModel.saveData(selectedFile);
		}
	}

	@FXML
	private void onHelpAbout() {
		this.showAlert("CS2 Spring 2024", "NSS", "National Seismic System");
	}

	private void showAlert(String title, String header, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		Window owner = this.pane.getScene().getWindow();
		alert.initOwner(owner);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
