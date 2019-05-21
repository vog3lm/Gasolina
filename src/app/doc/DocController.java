package app.doc;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import app.fxml.Loader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class DocController implements Initializable {

	@FXML 
	private WebView webview;
	
	public DocController() {
		new Loader().onLoadBorderCenter(null,Loader.DOCUMENTATION,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle arg) {
		File file = new File("src/app/dox/index.html");
		webview.getEngine().load(file.toURI().toString());
	}

}
