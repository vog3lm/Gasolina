package app.controlling;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.fxml.Loader;
import app.verkauf.VerkaufRecord;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AusgabenView implements Initializable {

	@FXML
	private TableView<AusgabenRecord> ausgaben_liste;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_id;
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
	private TableColumn<AusgabenRecord, String> ausgaben_belegnummer;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_datum;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_uhrzeit;
	@FXML
	private TableColumn<AusgabenRecord, String> ausgaben_mitarbeiter;
	
	AusgabenView() {
		new Loader().onLoadInitializable(Loader.AUSGABEN,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		ausgaben_id.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("index"));
		ausgaben_warennummer.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("warennummer"));
		ausgaben_bezeichnung.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("bezeichnung"));
		ausgaben_preis.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("preis"));
		ausgaben_menge.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("menge"));
		ausgaben_menge.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("einheit"));
		ausgaben_summe.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("summe"));
		ausgaben_belegnummer.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("belegnummer"));
		ausgaben_datum.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("datum"));
		ausgaben_uhrzeit.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("uhrzeit"));
		ausgaben_mitarbeiter.setCellValueFactory(new PropertyValueFactory<AusgabenRecord, String>("mitarbeiter"));
	}
	
	void setItems(ArrayList<AusgabenRecord> items) {
		ausgaben_liste.setItems(FXCollections.observableList(items));
	}
	
	TableView<AusgabenRecord> getView() { return ausgaben_liste; }
}
