package app;

import app.personal.PersonalRecord;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Current state holder object (singleton).
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class Zustand {
	/**
	 * Path to light layout css file.
	 * */
	public final static String LIGHT = "app/style/app.main.light.css";
	/**
	 * Path to dark layout css file.
	 * */
	public final static String DARK = "app/style/app.main.dark.css";
	/**
	 * Used application stage.
	 * */
	private Stage stage;
	/**
	 * Application stage getter.
	 * */
	public Stage getStage() { return this.stage; }
	/**
	 * Application stage setter.
	 * */
	public Zustand setStage(Stage stage) {
		this.stage = stage;
		return this;
	}
	/**
	 * Used application scene.
	 * */
	private Scene scene;
	/**
	 * Application scene getter.
	 * */
	public Scene getScene() { return this.scene; }
	/**
	 * Application scene setter.
	 * */
	public Zustand setScene(Scene scene) {
		this.scene = scene;
		return this;
	}
	/**
	 * Used application root layout.
	 * */
	private BorderPane root;
	/**
	 * Application root layout getter.
	 * */
	public BorderPane getRoot() { return this.root; }
	/**
	 * Application root layout setter.
	 * */
	public Zustand setRoot(BorderPane root) {
		this.root = root;
		return this;
	}
	/**
	 * Visible view.
	 * */
	private Lifecycle current;
	/**
	 * Visible view getter.
	 * */
	public Lifecycle getCurrent() { return this.current; }
	/**
	 * Visible view setter.
	 * */
	public Zustand setCurrent(Lifecycle current) {
		this.current = current;
		return this;
	}
	/**
	 * Currently visible design.
	 * */
	private String design = DARK;
	/**
	 * Design getter.
	 * */
	public String getDesign() { return this.design; }
	/**
	 * Design setter.
	 * */
	public Zustand setDesign(String design) {
		this.design = design;
		return this;
	}
	/**
	 * Currently logged in user.
	 * */
	private PersonalRecord benutzer = null;
	/**
	 * User getter.
	 * */
	public PersonalRecord getBenutzer() { return this.benutzer; }
	/**
	 * User setter.
	 * */
	public Zustand setBenutzer(PersonalRecord benutzer) {
		this.benutzer = benutzer;
		return this;
	}
	/**
	 * Used application print path.
	 * */
	private String printUrl = "src/app/print/";
	/**
	 * Print path getter.
	 * */
	public String getPrintUrl() { return this.printUrl; }
	/**
	 * Print path setter.
	 * */
	public Zustand setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
		return this;
	}
	/**
	 * Used application database path.
	 * */
	private String databaseUrl = "src/app/sources/";
	/**
	 * Print path getter.
	 * */
	public String getDatabaseUrl() { return this.databaseUrl; }
	/**
	 * Print path setter.
	 * */
	public Zustand setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
		return this;
	}
	/**
	 * Singleton instance.
	 * */
	private static Zustand instance = new Zustand();
	/**
	 * Singleton instance getter.
	 * */
	public static Zustand getInstance() { return instance; }
}
