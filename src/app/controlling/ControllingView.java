package app.controlling;

import java.net.URL;
import java.util.ResourceBundle;

import app.fxml.Loader;
import app.waren.WarenbestandRecord;
import app.waren.WarenbestellungenRecord;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllingView implements Initializable {

	@FXML
	private TabPane controlling_tabs;
	
	
	@FXML
    private TableView<WarenbestandRecord> bestand_liste;
	@FXML
	private TableColumn<WarenbestandRecord, String> bestand_warennummer;
	@FXML
	private TableColumn<WarenbestandRecord, String> bestand_bezeichnung;
	@FXML
	private TableColumn<WarenbestandRecord, String> bestand_einheit;
	@FXML
	private TableColumn<WarenbestandRecord, String> bestand_menge;
	@FXML
	private TableColumn<WarenbestandRecord, String> bestand_preis;
	@FXML
	private TableColumn<WarenbestandRecord, String> bestand_waehrung;
	@FXML
	private TableColumn<WarenbestandRecord, String> bestand_kategorie;
	@FXML
	private Button bestand_bestellen;
	
	@FXML
    private TableView<WarenbestellungenRecord> bestellungen_liste;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_bestellnummer;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_warennummer;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_bezeichnung;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_preis;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_waehrung;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_menge;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_bestelldatum;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_lieferdatum;
	@FXML
	private Button bestellungen_hinzufuegen;
	
	
	
	private ControllingController controller;
	
	ControllingView(ControllingController controller) {
		this.controller = controller;
		new Loader().onLoadBorderCenter(controller,Loader.CONTROLLING,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {

	}
	
	int getIndex() { return controlling_tabs.getSelectionModel().getSelectedIndex(); }
	
	ControllingView setIndex(int tab) {
		controlling_tabs.getSelectionModel().select(tab);
	//	controlling_tabs.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
	//        this.tab = controlling_tabs.getSelectionModel().getSelectedIndex();
	//    });
		return this;
	}
}
