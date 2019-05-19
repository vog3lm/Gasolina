package app.doc;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import app.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class DocController implements Initializable {

	@FXML 
	private WebView webview;
	
	public DocController() {
		new Util().onLoadCenter(super.getClass().getResource("Documentation.fxml"),this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle arg) {
		File file = new File("src/app/dox/index.html");
		webview.getEngine().load(file.toURI().toString());
	}

}
