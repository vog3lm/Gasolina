package app.controlling;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.fxml.Loader;
import app.verkauf.VerkaufRecord;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ControllingView implements Initializable {

	public static final int EINNAHMEN = 0;
	public static final int AUSGABEN = 1;
	public static final int ERGEBNIS = 2;
	
	@FXML
	private TabPane controlling_tabs;
	
	private EinnahmenView einnahmen = new EinnahmenView();
	
	private AusgabenView ausgaben = new AusgabenView();
	
	private ErgebnisView ergebnis = new ErgebnisView();
		
	ControllingView(ControllingController controller) {
		new Loader().onLoadBorderCenter(controller,Loader.CONTROLLING,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		ObservableList<Tab> tabs = controlling_tabs.getTabs();
		tabs.get(EINNAHMEN).setContent(einnahmen.getView());
		tabs.get(AUSGABEN).setContent(ausgaben.getView());
		tabs.get(ERGEBNIS).setContent(ergebnis.getView());
	}
	
	void setEinnahmen(ArrayList<VerkaufRecord> items) { einnahmen.setItems(items); }
	
	void setAusgaben(ArrayList<AusgabenRecord> items) { ausgaben.setItems(items); }
	
	void setEinnahmen() {  }
	
	int getIndex() { return controlling_tabs.getSelectionModel().getSelectedIndex(); }
	
	ControllingView setIndex(int tab) {
		controlling_tabs.getSelectionModel().select(tab);
		return this;
	}
}
