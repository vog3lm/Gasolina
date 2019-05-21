package app.controlling;

import java.net.URL;
import java.util.ResourceBundle;

import app.fxml.Loader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ErgebnisView  implements Initializable {

	@FXML
	private AnchorPane controlling_ergebnis;
	
	ErgebnisView() {
		new Loader().onLoadInitializable(Loader.ERGEBNIS,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {

	}
	
	AnchorPane getView() { return controlling_ergebnis; }
	
}