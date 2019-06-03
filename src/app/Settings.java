package app;

import app.command.Commander;
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
public class Settings {
	/**
	 * Path to light layout css file.
	 * */
	public final static String LIGHT = "app/style/app.main.light.css";
	/**
	 * Path to dark layout css file.
	 * */
	public final static String DARK = "app/style/app.main.dark.css";
	
	/**
	 * 
	 * */
	private Observer observer;
	/**
	 * 
	 * */
	public Observer getObserver() { return this.observer; }
	/**
	 * 
	 * */
	public Settings setObserver(Observer observer) {
		this.observer = observer;
		return this;
	}
	/**
	 * 
	 * */
	private Commander commander;
	/**
	 * 
	 * */
	public Commander getCommander() { return this.commander; }
	/**
	 * 
	 * */
	public Settings setCommander(Commander commander) {
		this.commander = commander;
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
	public Settings setDesign(String design) {
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
	public Settings setBenutzer(PersonalRecord benutzer) {
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
	public Settings setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
		return this;
	}
	/**
	 * Singleton instance.
	 * */
	private static Settings instance = new Settings();
	/**
	 * Singleton instance getter.
	 * */
	public static Settings getInstance() { return instance; }
}
