package app.personal;

import java.net.URL;
import java.util.ResourceBundle;

import app.Util;
import app.Zustand;
import app.verkauf.VerkaufController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class AnmeldenController implements Initializable {
	
	private Anmelden anmelden;
	
	private PersonalTable bestand = new PersonalTable();
	
	@FXML
	private AnchorPane anmelden_pane;
	@FXML
	private Button anmelden_submit;
	@FXML
	private TextField anmelden_benutzername;
	@FXML
	private Label anmelden_benutzername_lbl;
	@FXML
	private TextField anmelden_passwort;
	@FXML
	private Label anmelden_passwort_lbl;
	
	public AnmeldenController(Anmelden anmelden) {
		this.anmelden = anmelden;
		new Util().onLoadCenter(super.getClass().getResource("Anmelden.fxml"),this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		anmelden_submit.setOnAction(this::onAnmelden);
		Platform.runLater(() -> anmelden_benutzername.requestFocus());
	}
	
	void onAnmelden(ActionEvent event) {
		String benutzername = anmelden_benutzername.getText().toLowerCase();
		String passwort = anmelden_passwort.getText().toLowerCase();
		double width = anmelden_pane.getWidth();
		double height = anmelden_pane.getHeight();
		if(benutzername.equals("o$ter") && passwort.equals("ha$e")) {
			String url = "app/style/egg.dark.jpg";
			if(Zustand.getInstance().getDesign().equals(Zustand.LIGHT)) {
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
			int index = bestand.getIndex(benutzername);
			if(-1 != index) {
				PersonalRecord benutzer = bestand.onRead(index);
				if(passwort.equals(benutzer.getPasswort())) {
					Zustand.getInstance().setBenutzer(benutzer);
					this.anmelden.onAnmelden();
					new VerkaufController(VerkaufController.SAEULE1);				
					return;
				}
				anmelden_passwort.getStyleClass().add("anmelden-input-error");
				anmelden_passwort_lbl.getStyleClass().add("anmelden-input-error");
			}else {
				anmelden_benutzername.getStyleClass().add("anmelden-input-error");
				anmelden_benutzername_lbl.getStyleClass().add("anmelden-input-error");
			}			
		}
	}
}
