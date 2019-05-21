package app.kraftstoff;

import java.net.URL;
import java.util.ResourceBundle;

import app.fxml.Loader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class TankView implements Initializable {
	
	TankView() {
		new Loader().onLoadInitializable(Loader.KRAFTSTOFF_BESTELLUNGEN,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		
	}
	
	AnchorPane getView() { return null; }
}
