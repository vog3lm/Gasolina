package app.controlling;

import java.net.URL;
import java.util.ResourceBundle;

import app.Loadable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

class ErgebnisView extends Loadable<AnchorPane> {

	private final String layout = "Ergebnis.fxml";
	
	@FXML
	private AnchorPane ergebnis;
	
	ErgebnisView() {
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {

	}
	
	@Override
	protected AnchorPane getView() { return ergebnis; }
	
}