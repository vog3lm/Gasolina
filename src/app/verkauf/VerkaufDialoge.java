package app.verkauf;

import app.Settings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VerkaufDialoge {
	
	Alert createNurZahlen() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().getStylesheets().add(Settings.getInstance().getDesign());
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Numerisches Feld!");
		alert.setContentText("Ausschließlich numerische Eingaben erlaubt!");
		return alert;
	}
	
	Alert createVorratNichtAusreichend(String bezeichnung) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().getStylesheets().add(Settings.getInstance().getDesign());
		alert.setTitle("Error Dialog");
		alert.setHeaderText(bezeichnung+" Vorrat nicht ausreichend!");
		alert.setContentText(bezeichnung+" bestellen oder kleinere Menge eingeben.");
		return alert;
	}
	
	Alert createVorratVerbraucht(String bezeichnung){
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().getStylesheets().add(Settings.getInstance().getDesign());
		alert.setTitle("Error Dialog");
		alert.setHeaderText(bezeichnung+" Vorrat verbraucht!");
		alert.setContentText(bezeichnung+" bestellen.");
		return alert;
	}
	
	Alert createOffeneVorgaenge() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.getDialogPane().getStylesheets().add(Settings.getInstance().getDesign());
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Offene Kassenvorgänge!");
		alert.setContentText("Buchen Sie alle Kassenvorgänge,\nbevor Sie das Programm beenden!");
		return alert;
	}
}
