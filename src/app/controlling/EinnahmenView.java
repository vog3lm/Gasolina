package app.controlling;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Loadable;
import app.verkauf.VerkaufRecord;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

class EinnahmenView extends Loadable<TableView<VerkaufRecord>> {
	
	private final String layout = "Einnahmen.fxml";
	
	@FXML
	private TableView<VerkaufRecord> einnahmen;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_id;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_warennummer;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_bezeichnung;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_preis;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_menge;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_einheit;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_summe;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_belegnummer;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_datum;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_uhrzeit;
	@FXML
	private TableColumn<VerkaufRecord, String> einnahmen_mitarbeiter;
	
	
	EinnahmenView() {
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		einnahmen_id.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("index"));
		einnahmen_warennummer.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("warennummer"));
		einnahmen_bezeichnung.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("bezeichnung"));
		einnahmen_preis.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("preis"));
		einnahmen_menge.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("menge"));
		einnahmen_menge.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("einheit"));
		einnahmen_summe.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("summe"));
		einnahmen_belegnummer.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("belegnummer"));
		einnahmen_datum.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("datum"));
		einnahmen_uhrzeit.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("uhrzeit"));
		einnahmen_mitarbeiter.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("mitarbeiter"));
	}
	
	void setItems(ArrayList<VerkaufRecord> items) {
		einnahmen.setItems(FXCollections.observableList(items));
	}
	
	@Override
	protected TableView<VerkaufRecord> show() { return einnahmen; }
}
