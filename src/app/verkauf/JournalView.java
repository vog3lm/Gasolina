package app.verkauf;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Loadable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

class JournalView extends Loadable<AnchorPane> {

	private final String layout = "Journal.fxml";
	
	@FXML
	private AnchorPane journal;
	@FXML
	private TableView<VerkaufRecord> journal_liste;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_belegnummer;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_datum;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_uhrzeit;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_warennummer;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_bezeichnung;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_preis;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_menge;
	@FXML
	private TableColumn<VerkaufRecord, String> journal_summe;
	@FXML
	private Label journal_total;
	
	VerkaufController controller;
	
	JournalView(VerkaufController controller) {
		this.controller = controller;
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		/**/
		journal_belegnummer.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("belegnummer"));
		journal_datum.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("datum"));
		journal_uhrzeit.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("uhrzeit"));
		journal_warennummer.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("warennummer"));
		journal_bezeichnung.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("bezeichnung"));
		journal_preis.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("preis"));
		journal_menge.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("menge"));
		journal_summe.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("summe"));
		/**/
		journal_liste.setItems(FXCollections.observableList(new ArrayList<VerkaufRecord>()));
		journal_liste.setRowFactory(this.createRowListener());
	}
	
	private Callback<TableView<VerkaufRecord>,TableRow<VerkaufRecord>> createRowListener() {
		return new Callback<TableView<VerkaufRecord>, TableRow<VerkaufRecord>>(){
	        @Override
	        public TableRow<VerkaufRecord> call(TableView<VerkaufRecord> table) {
	            final TableRow<VerkaufRecord> row = new TableRow<VerkaufRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(createRowMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private ContextMenu createRowMenu(TableView<VerkaufRecord> table, TableRow<VerkaufRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem remove = new MenuItem("Entfernen");
        /* TODO remove from journal */
     //	remove.setOnAction(event -> {controller.onJournalRemove(row.getIndex());});
     //	remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(remove);
        return menu;
	}
	
	@Override
	public AnchorPane show() { return journal; }
	
	void onRefresh() { journal_liste.refresh(); }
}
