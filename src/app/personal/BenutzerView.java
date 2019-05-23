package app.personal;

import java.net.URL;
import java.util.ResourceBundle;

import app.fxml.Loader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

class BenutzerView implements Initializable {

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
	
	private PersonalRecord benutzer;
	
	BenutzerView(BenutzerController controller, PersonalRecord benutzer) {
		this.controller = controller;
		this.benutzer = benutzer;
		new Loader().onLoadBorderCenter(controller,Loader.BENUTZER,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
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
	
}
