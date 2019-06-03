package app.doc;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import app.Loadable;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class DocController extends Loadable<WebView> {

	private final String layout = "Documentation.fxml";
	
	@FXML 
	private WebView webview;
	
	public DocController() {
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle arg) {
		File file = new File("src/app/dox/index.html");
		webview.getEngine().load(file.toURI().toString());
	}

	@Override
	protected WebView show() {return webview;}

}
