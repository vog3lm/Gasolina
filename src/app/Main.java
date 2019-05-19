package app;

import app.anmelden.AnmeldenController;
import app.menu.MenuController;
import app.personal.PersonalRecord;
import app.verkauf.VerkaufController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

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
		stage.setTitle("Tankstellenverwaltung");
		stage.setScene(scene);
		stage.show();
		/* load entry point view */
		MenuController menu = new MenuController();
		if(null == zustand.getBenutzer()){new AnmeldenController(menu);}
		else {new VerkaufController(1);}
	}
	
	public void destroy() {
		boolean exit = true;
		Lifecycle current = Zustand.getInstance().getCurrent();
		if(null != current) {exit = current.destroy();}
		if(exit) {
	        Platform.exit();
	        System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
