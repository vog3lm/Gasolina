package app.personal;

import java.net.URL;
import java.util.ResourceBundle;

import app.Loadable;
import app.Zustand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

class BenutzerView extends Loadable<AnchorPane> {

	private final String layout = "Benutzer.fxml";
	
	@FXML
	private AnchorPane benutzer;
	
	@FXML
	private Label benutzer_personalnummer;
	@FXML
	private TextField benutzer_benutzername;
	@FXML
	private TextField benutzer_vorname;
	@FXML
	private TextField benutzer_nachname;
	@FXML
	private TextField benutzer_passwort;
	@FXML
	private Button benutzer_speichern;
	
	private BenutzerController controller;
	
	BenutzerView(BenutzerController controller) {
		this.controller = controller;
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		PersonalRecord benutzer = Zustand.getInstance().getBenutzer();
		/* set user data to view */
		benutzer_personalnummer.setText(benutzer.getPersonalnummer());
		benutzer_benutzername.setText(benutzer.getBenutzername());
		benutzer_vorname.setText(benutzer.getVorname());
		benutzer_nachname.setText(benutzer.getNachname());
		benutzer_passwort.setText(benutzer.getPasswort());
		/* process user data to controller */
		benutzer_speichern.setOnAction(event -> {controller.onSpeichern(benutzer_benutzername.getText()
																	   ,benutzer_vorname.getText()
																	   ,benutzer_nachname.getText()
																	   ,benutzer_passwort.getText());});
	}

	@Override
	protected AnchorPane getView() {return benutzer;}
	
}
