package app.kraftstoff;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.fxml.Loader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class KraftstoffView implements Initializable {
	
	public static final int BESTAND = 0;
	public static final int BESTELLUNGEN = 1;
	public static final int TANKS = 2;
	
	@FXML
	private TabPane kraftstoff_tabs;
	
	private BestandView bestand;
	
	private BestellungenView bestellungen;
	
	private TankView tanks = new TankView();
	
	KraftstoffView(KraftstoffController controller) {
		bestand = new BestandView(controller);
		bestellungen = new BestellungenView(controller);
		new Loader().onLoadBorderCenter(controller,Loader.KRAFTSTOFF,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		ObservableList<Tab> tabs = kraftstoff_tabs.getTabs();
		tabs.get(BESTAND).setContent(bestand.getView());
		tabs.get(BESTELLUNGEN).setContent(bestellungen.getView());
		tabs.get(TANKS).setContent(tanks.getView());
	}
	
	void setBestand(ArrayList<KraftstoffbestandRecord> items) { bestand.setItems(items); }
	
	void setBestellungen(ArrayList<KraftstoffbestellungenRecord> items) { bestellungen.setItems(items); }
	
	void setEinnahmen() {  }
	
	int getIndex() { return kraftstoff_tabs.getSelectionModel().getSelectedIndex(); }
	
	KraftstoffView setIndex(int tab) {
		kraftstoff_tabs.getSelectionModel().select(tab);
	//	controlling_tabs.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
	//        this.tab = controlling_tabs.getSelectionModel().getSelectedIndex();
	//    });
		return this;
	}
}
