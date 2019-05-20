package app.controlling;

import java.net.URL;
import java.util.ResourceBundle;

import app.Lifecycle;
import app.Util;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class ControllingController implements Lifecycle {

	public static final int EINNAHMEN = 0;
	public static final int AUSGABEN = 1;
	public static final int ERGEBNIS = 2;
	
	private int tab;
	
	// journal
	// kraftstoff
	// waren
	
	@FXML
	private TabPane controlling_tabs;
	
	public ControllingController(int tab) {
		this.tab = tab;
		new Util().onLoadCenter(this,super.getClass().getResource("Controlling.fxml"),this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		controlling_tabs.getSelectionModel().select(this.tab);
		controlling_tabs.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
	        this.tab = controlling_tabs.getSelectionModel().getSelectedIndex();
	    });
	}

	@Override
	public boolean destroy() {
		return true;
	}

}
