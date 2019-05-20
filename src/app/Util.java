package app;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
/**
 * JavaFX fxml layout loader Factory.
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class Util {
	/**
	 * BoderPane top section fxml layout loader.
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	public void onLoadTop(URL url, Initializable controller) {
		Zustand.getInstance().getRoot().setTop(onLoad(url,controller));
	}
	/**
	 * BoderPane center section fxml layout loader.
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	public void onLoadCenter(URL url, Initializable controller) {
		Zustand.getInstance().getRoot().setCenter(onLoad(url,controller));
	}
	/**
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	private Parent onLoad(URL url, Initializable controller) {
		try {		
			FXMLLoader loader = new FXMLLoader(url);
		    loader.setController(controller);
		    return loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
