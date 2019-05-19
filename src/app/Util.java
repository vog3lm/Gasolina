package app;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Util {

	public void onLoadTop(URL url, Initializable controller) {
		Zustand.getInstance().getRoot().setTop(onLoad(url,controller));
	}
	
	public void onLoadCenter(URL url, Initializable controller) {
		Zustand.getInstance().getRoot().setCenter(onLoad(url,controller));
	}
	
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
