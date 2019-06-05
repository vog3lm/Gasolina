package app.kraftstoff;

import java.net.URL;
import java.util.ResourceBundle;

import app.Loadable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

class TankView extends Loadable<AnchorPane> {
	
	private final String layout = "Tanks.fxml";
	
	@FXML
	private AnchorPane tanks;
	
	TankView() {
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {}
	
	protected AnchorPane onShow() { return tanks; }
}
