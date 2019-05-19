package app;

import app.personal.PersonalRecord;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Zustand {

	/**/
	public final static String LIGHT = "app/style/app.main.light.css";
	public final static String DARK = "app/style/app.main.dark.css";
	
	
	/**/
	private Stage stage;
	
	public Stage getStage() { return this.stage; }
	
	public Zustand setStage(Stage stage) {
		this.stage = stage;
		return this;
	}
	
	
	/**/
	private Scene scene;
	
	public Scene getScene() { return this.scene; }
	
	public Zustand setScene(Scene scene) {
		this.scene = scene;
		return this;
	}
	
	
	/**/
	private BorderPane root;
	
	public BorderPane getRoot() { return this.root; }
	
	public Zustand setRoot(BorderPane root) {
		this.root = root;
		return this;
	}
	
	
	/**/
	private Lifecycle current;
	
	public Lifecycle getCurrent() { return this.current; }
	
	public Zustand setCurrent(Lifecycle current) {
		this.current = current;
		return this;
	}
	
	
	/**/
	private String design = DARK;
	
	public String getDesign() { return this.design; }
	
	public Zustand setDesign(String design) {
		this.design = design;
		return this;
	}
	
	
	/**/
	private PersonalRecord benutzer = null;
	
	public PersonalRecord getBenutzer() { return this.benutzer; }
	
	public Zustand setBenutzer(PersonalRecord benutzer) {
		this.benutzer = benutzer;
		return this;
	}
	
	
	/**/
	private String printUrl = "src/app/print/";
	
	public String getPrintUrl() { return this.printUrl; }
	
	public Zustand setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
		return this;
	}
	
	
	/**/
	private String databaseUrl = "src/app/database/";
	
	public String getDatabaseUrl() { return this.databaseUrl; }
	
	public Zustand setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
		return this;
	}
	
	
	/* singleton object access */
	private static Zustand instance = new Zustand();
	
	public static Zustand getInstance() { return instance; }
}
