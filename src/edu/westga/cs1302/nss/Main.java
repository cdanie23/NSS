package edu.westga.cs1302.nss;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Class Main - entry point into the National Seismic System (NSS)
 * application. Data source:
 * https://corgis-edu.github.io/corgis/csv/earthquakes/
 * 
 * @author CS1302
 * @version Spring 2024
 */
public class Main extends Application {

	private static final String GUI_FXML = "view/NSSGui.fxml";
	private static final String TITLE = "Project 1 by Colby Daniel";

	/**
	 * Constructs a new Application object for the (National Seismic System) NSS
	 * program.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public Main() {
		super();
	}

	/**
	 * Starts the application and sets the main stage.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param primaryStage The main stage to set for the application.
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane pane = this.loadGui();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle(TITLE);

			primaryStage.show();
		} catch (IllegalStateException | IOException anException) {
			anException.printStackTrace();
		}
	}

	private Pane loadGui() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(GUI_FXML));
		return (Pane) loader.load();
	}

	/**
	 * Launches the application.
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}