package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public interface Lifecycle extends Initializable {
	public void initialize(URL arg, ResourceBundle res);
	public boolean destroy();
}
