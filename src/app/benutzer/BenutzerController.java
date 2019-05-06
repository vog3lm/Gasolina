package app.benutzer;

import java.net.URL;
import java.util.ResourceBundle;

import app.Einstellungen;
import app.Lifecycle;
import app.personal.PersonalRecord;
import app.personal.PersonalTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BenutzerController implements Lifecycle {
	
	private PersonalTable bestand = new PersonalTable();
	
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

	@Override
	public void initialize(URL arg, ResourceBundle res) {
		PersonalRecord benutzer = Einstellungen.getInstance().getBenutzer();
		benutzer_personalnummer.setText(benutzer.getPersonalnummer());
		benutzer_benutzername.setText(benutzer.getBenutzername());
		benutzer_vorname.setText(benutzer.getVorname());
		benutzer_nachname.setText(benutzer.getNachname());
		benutzer_passwort.setText(benutzer.getPasswort());
		/**/
		benutzer_speichern.setOnAction(this::onSpeichern);
	}

	public void onSpeichern(ActionEvent event) {
		PersonalRecord benutzer = Einstellungen.getInstance().getBenutzer();
		benutzer.setBenutzername(benutzer_benutzername.getText());
		benutzer.setVorname(benutzer_vorname.getText());
		benutzer.setNachname(benutzer_nachname.getText());
		benutzer.setPasswort(benutzer_passwort.getText());
		bestand.onUpdate(bestand.getIndex(benutzer),benutzer);
	}
	
	@Override
	public boolean onDestroy() {
		bestand.onCommit();
		return true;
	}
}
