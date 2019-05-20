package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public interface Lifecycle extends Initializable {
	/**
	 * 
	 * */
	public void initialize(URL url, ResourceBundle arg);
	/**
	 * 
	 * */
	public boolean destroy();
}
