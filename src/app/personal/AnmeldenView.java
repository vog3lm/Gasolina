package app.personal;

import java.net.URL;
import java.util.ResourceBundle;

import app.Loadable;
import app.settings.Settings;
import app.settings.SettingsDefault;
import javafx.application.Platform;
import javafx.fxml.FXML;
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

class AnmeldenView extends Loadable<AnchorPane> {

	private static final String layout = "Anmelden.fxml";
	
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
	
	private AnmeldenController controller;
	
	AnmeldenView(AnmeldenController controller) {
		this.controller = controller;
		onLoad(layout,this);
	}

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		anmelden_submit.setOnAction(event -> {controller.onAnmelden(anmelden_benutzername.getText().toLowerCase(),anmelden_passwort.getText().toLowerCase());});
		anmelden_passwort.setText("geheim");
		Platform.runLater(() -> anmelden_benutzername.requestFocus());
	}
		
	void showEasterEgg() {
		double width = anmelden_pane.getWidth();
		double height = anmelden_pane.getHeight();
		String url = "app/style/egg.dark.jpg";
		if(Settings.getInstance().getDesign().equals(SettingsDefault.LIGHT)) {
			url = "app/style/egg.light.jpg";
		}
		BackgroundSize size = new BackgroundSize(width, height, false, false, false, false);
		BackgroundImage egg = new BackgroundImage(new Image(url,width,height,false,true),
		        BackgroundRepeat.NO_REPEAT, 
		        BackgroundRepeat.NO_REPEAT, 
		        BackgroundPosition.CENTER,
		        size);
		anmelden_pane.setBackground(new Background(egg));
	}
	
	void showBenutzerError() {
		anmelden_benutzername.getStyleClass().add("anmelden-input-error");
		anmelden_benutzername_lbl.getStyleClass().add("anmelden-input-error");
	}
	
	void showPasswortError() {
		anmelden_passwort.getStyleClass().add("anmelden-input-error");
		anmelden_passwort_lbl.getStyleClass().add("anmelden-input-error");
	}

	@Override
	protected AnchorPane onShow() {return anmelden_pane;}
}
