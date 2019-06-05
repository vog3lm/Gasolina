package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public abstract class Loadable<R> implements Initializable {

	@Override
	public abstract void initialize(URL url, ResourceBundle res);
	
	protected abstract R show();
	
	/**
	 * @param Path to fxml layout
	 * @param Fxml layout view wrapper
	 */
	public void onLoad(String layout, Initializable view) {
		try {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(layout));
		    loader.setController(view);
		    loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}
