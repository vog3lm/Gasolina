package app.anmelden;

import java.net.URL;
import java.util.ResourceBundle;

import app.Einstellungen;
import app.personal.PersonalRecord;
import app.personal.PersonalTable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class AnmeldenController implements Initializable {
	
	private Anmelden listener;
	
	private PersonalTable bestand = new PersonalTable();
	
	@FXML
	private AnchorPane anmelden_pane;
	@FXML
	private Button anmelden_submit;
	@FXML
	private TextField anmelden_benutzername;
	@FXML
	private TextField anmelden_passwort;
	
	public AnmeldenController(Anmelden listener) {
		this.listener = listener;
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		anmelden_submit.setOnAction(this::onAnmelden);
		Platform.runLater(() -> anmelden_benutzername.requestFocus());
	}
	
	private void onAnmelden(ActionEvent event) {
		String benutzername = anmelden_benutzername.getText().toLowerCase();
		String passwort = anmelden_passwort.getText().toLowerCase();
		double width = anmelden_pane.getWidth();
		double height = anmelden_pane.getHeight();
		if(benutzername.equals("o$ter") && passwort.equals("ha$e")) {
			String url = "app/style/egg.dark.jpg";
			if(Einstellungen.getInstance().getDesign().equals(Einstellungen.LIGHT)) {
				url = "app/style/egg.light.jpg";
			}
			BackgroundSize size = new BackgroundSize(width, height, false, false, false, false);
			BackgroundImage egg = new BackgroundImage(new Image(url,width,height,false,true),
			        BackgroundRepeat.NO_REPEAT, 
			        BackgroundRepeat.NO_REPEAT, 
			        BackgroundPosition.CENTER,
			        size);
			anmelden_pane.setBackground(new Background(egg));
		} else {
			for(PersonalRecord benutzer : bestand.onReadAll()) {
				if(benutzername.equals(benutzer.getBenutzername()) && passwort.equals(benutzer.getPasswort())) {
					this.listener.onAnmelden(benutzer);
				}
			}			
		}
	}
}
