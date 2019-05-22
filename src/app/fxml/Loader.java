package app.fxml;

import app.Lifecycle;
import app.Zustand;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
/**
 * JavaFX fxml layout loader Factory.
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class Loader {
	/** */
	public static final String ANMELDEN = "Anmelden.fxml";
	/** */
	public static final String BENUTZER = "Benutzer.fxml";
	/** */
	public static final String CONTROLLING = "Controlling.fxml";
	public static final String EINNAHMEN = "Einnahmen.fxml";
	public static final String AUSGABEN = "Ausgaben.fxml";
	public static final String ERGEBNIS = "Ergebnis.fxml";
	
	/** */
	public static final String DOCUMENTATION = "Documentation.fxml";
	/** */
	public static final String KRAFTSTOFF = "Kraftstoff.fxml";
	public static final String KRAFTSTOFF_BESTAND = "KraftstoffBestand.fxml";
	public static final String KRAFTSTOFF_BESTELLUNGEN = "KraftstoffBestellungen.fxml";
	public static final String TANKS = "Tanks.fxml";
	/** */
	public static final String MENU = "Menu.fxml";
	/** */
	public static final String PERSONAL = "Personal.fxml";
	/** */
	public static final String VERKAUF = "Verkauf.fxml";
	/** */
	public static final String WAREN = "Waren.fxml";
	public static final String WAREN_BESTAND = "WarenBestand.fxml";
	public static final String WAREN_BESTELLUNGEN = "WarenBestellungen.fxml";
	/**
	 * BoderPane top section fxml layout loader.
	 * @param 
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	public void onLoadBorderTop(Lifecycle lc, String layout, Initializable view) {
		Zustand zustand = Zustand.getInstance();
		Lifecycle current = zustand.getCurrent();
		if(null != current) {
			zustand.getCurrent().destroy();
		}
		zustand.setCurrent(lc);
		zustand.getRoot().setTop(onLoadInitializable(layout,view));
	}
	/**
	 * BoderPane center section fxml layout loader.
	 * @param 
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	public void onLoadBorderCenter(Lifecycle lc, String layout, Initializable view) {
		Zustand zustand = Zustand.getInstance();
		Lifecycle current = zustand.getCurrent();
		if(null != current) {
			zustand.getCurrent().destroy();
		}
		zustand.setCurrent(lc);
		zustand.getRoot().setCenter(onLoadInitializable(layout,view));
	}
	/**
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	public Parent onLoadInitializable(String layout, Initializable view) {
		try {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(layout));
		    loader.setController(view);
		    return loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
