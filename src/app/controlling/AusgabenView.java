package app.controlling;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Loadable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AusgabenView extends Loadable<TableView<AusgabenRecord>> {

	private final String layout = "Ausgaben.fxml";
	
	@FXML
	private TableView<AusgabenRecord> ausgaben;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_buchungsnummer;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_warennummer;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_bezeichnung;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_preis;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_menge;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_einheit;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_summe;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_datum;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_uhrzeit;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_mitarbeiter;
	
	AusgabenView() {
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		ausgaben_buchungsnummer.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("buchungsnummer"));
		ausgaben_warennummer.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("warennummer"));
		ausgaben_bezeichnung.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("bezeichnung"));
		ausgaben_preis.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("preis"));
		ausgaben_menge.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("menge"));
		ausgaben_einheit.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("einheit"));
		ausgaben_summe.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("summe"));
		ausgaben_datum.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("datum"));
		ausgaben_uhrzeit.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("uhrzeit"));
		ausgaben_mitarbeiter.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("mitarbeiter"));
	}
	
	void setItems(ArrayList<AusgabenRecord> items) { ausgaben.setItems(FXCollections.observableList(items)); }
	
	@Override
	protected TableView<AusgabenRecord> show() { return ausgaben; }

}
