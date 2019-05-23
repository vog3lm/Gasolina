package app.waren;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.fxml.Loader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class WarenView implements Initializable {

	public static final int BESTAND = 0;
	public static final int BESTELLUNGEN = 1;
	
	@FXML
	private TabPane waren_tabs;
	
	private BestandView bestand;
	
	private BestellungenView bestellungen;
		
	WarenView(WarenController controller) {
		bestand = new BestandView(controller);
		bestellungen = new BestellungenView(controller);
		new Loader().onLoadBorderCenter(controller,Loader.WAREN,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		ObservableList<Tab> tabs = waren_tabs.getTabs();
		tabs.get(BESTAND).setContent(bestand.getView());
		tabs.get(BESTELLUNGEN).setContent(bestellungen.getView());
	}
	
	WarenView setIndex(int tab) {
		waren_tabs.getSelectionModel().select(tab);
		return this;
	}
	
	void setBestand(ArrayList<WarenbestandRecord> items) { bestand.setItems(items); }
	
	void setBestellungen(ArrayList<WarenbestellungenRecord> items) { bestellungen.setItems(items); }
	
	void onRefresh() {
		bestand.onRefresh();
		bestellungen.onRefresh();
	}
}
