package app.verkauf;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

public class VerkaufView implements Initializable {

	public static final int SAEULE1 = 0;
	public static final int SAEULE2 = 1;
	public static final int SAEULE3 = 2;
	public static final int JOURNAL = 3;
	
	@FXML
	private TabPane verkauf_tabs;
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
	//	verkauf_tabs.getSelectionModel().select(this.tab);
	//	verkauf_tabs.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
	//        this.tab = verkauf_tabs.getSelectionModel().getSelectedIndex();
	//    });
	}
}
