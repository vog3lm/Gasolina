package app;

import app.personal.AnmeldenController;
import app.menu.MenuController;
import app.verkauf.VerkaufController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// TODO : https://javadoc-themer.firebaseapp.com/ jdoc themer

/**
 * Application entry point class. Wraps all startup/shutdown methods.
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class Main extends Application {
	/**
	 * JavaFX entry point. 
	 * Creates a scene and root objects to be stored in Zustand object.
	 * Applies a custom application shutdown strategy.
	 * Loads initial views. Anmelden if noone is logged in, Verkauf on autologin activated.
	 * @param JavaFX Stage object
	 */
	@Override
	public void start(Stage stage) {
		Zustand zustand = Zustand.getInstance();
		/* create root layout */
		BorderPane root = new BorderPane();
		root.setPrefWidth(853.3);
		root.setPrefHeight(505);
		/**/
		Scene scene = new Scene(root);
		scene.getStylesheets().add(zustand.getDesign());
		/* store stage and root layout to current state */
		zustand.setStage(stage).setScene(scene).setRoot(root);
		/* create custom exit strategy */
		Platform.setImplicitExit(false);
		stage.setOnCloseRequest(event -> {
			event.consume();
			this.destroy();
		});
		stage.setTitle("Tankstellenverwaltung"); // TODO : Main.java Title String
		stage.setScene(scene);
		stage.show();
		/* load entry point view */
		MenuController menu = new MenuController();
		if(null == zustand.getBenutzer()){new AnmeldenController(menu);}
		else {new VerkaufController(1);}
	}
	/**
	 * Application shutdown strategy. Makes sure all data is been written on shutdown.
	 */
	public void destroy() {
		boolean exit = true;
		Controller current = Zustand.getInstance().getCurrent();
		if(null != current) {exit = current.destroy();}
		if(exit) {
	        Platform.exit();
	        System.exit(0);
		}
	}
	/**
	 * Application entry point.
	 * @param Application command line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
