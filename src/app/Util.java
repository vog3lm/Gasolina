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
	public void onLoadTop(Lifecycle lc, URL url, Initializable view) {
		Zustand zustand = Zustand.getInstance();
		Lifecycle current = zustand.getCurrent();
		if(null != current) {
			zustand.getCurrent().destroy();
		}
		zustand.setCurrent(lc);
		zustand.getRoot().setTop(onLoad(url,view));
	}
	/**
	 * BoderPane center section fxml layout loader.
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	public void onLoadCenter(Lifecycle lc, URL url, Initializable view) {
		Zustand zustand = Zustand.getInstance();
		Lifecycle current = zustand.getCurrent();
		if(null != current) {
			zustand.getCurrent().destroy();
		}
		zustand.setCurrent(lc);
		zustand.getRoot().setCenter(onLoad(url,view));
	}
	/**
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	private Parent onLoad(URL url, Initializable view) {
		try {		
			FXMLLoader loader = new FXMLLoader(url);
		    loader.setController(view);
		    return loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
