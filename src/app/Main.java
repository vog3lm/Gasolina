package app;

import app.command.Commander;
import app.command.Commands;
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
		/* create root layout */
		BorderPane root = new BorderPane();
		root.setPrefWidth(853.3);
		root.setPrefHeight(505);
		/**/
		Scene scene = new Scene(root);
		/**/
		Commander commander = new Commander(new Runtime(stage,scene,root));
		/* create custom exit strategy */
		Platform.setImplicitExit(false);
		stage.setOnCloseRequest(event -> {
			event.consume();
			commander.onExecute(Commands.EXIT);
		});
		stage.setScene(scene);
		stage.show();
		/**/
		/* load entry point view */
		commander.onExecute(Commands.START);
	}
	/**
	 * Application entry point.
	 * @param Application command line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
