package app.waren;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Loadable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

class BestellungenView extends Loadable<AnchorPane> {

	private final String layout = "WarenBestellungen.fxml";
	
	@FXML
	private AnchorPane bestellungen;
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
	private TableColumn<WarenbestellungenRecord, String> bestellungen_einheit;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_bestelldatum;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_lieferdatum;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_mitarbeiter;
	@FXML
	private Button bestellungen_hinzufuegen;
	
	private WarenController controller;
	
	BestellungenView(WarenController controller) {
		this.controller = controller;
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
	    bestellungen_bestellnummer.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("bestellnummer"));
	    bestellungen_warennummer.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("warennummer"));
	    bestellungen_bezeichnung.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("bezeichnung"));
	    //bestellungen_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    //bestellungen_bezeichnung.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"bezeichnung");});
	    bestellungen_menge.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("menge"));
	    bestellungen_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_menge.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"menge",cell.getNewValue());});  
	    bestellungen_einheit.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("einheit"));
	    bestellungen_preis.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("preis"));
	    bestellungen_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_preis.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"preis",cell.getNewValue());});
	    bestellungen_waehrung.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("waehrung"));
	    bestellungen_lieferdatum.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("lieferdatum"));
	    bestellungen_lieferdatum.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_lieferdatum.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"lieferdatum",cell.getNewValue());});    
	    bestellungen_bestelldatum.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("bestelldatum"));
	    bestellungen_mitarbeiter.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("mitarbeiter"));
	    /**/
	    bestellungen_liste.setRowFactory(createRowListener());
	    /**/
	    bestellungen_hinzufuegen.setOnAction(event -> {controller.onBestellungAdd();});
	}
	
	private Callback<TableView<WarenbestellungenRecord>,TableRow<WarenbestellungenRecord>> createRowListener() {
		return new Callback<TableView<WarenbestellungenRecord>, TableRow<WarenbestellungenRecord>>(){
	        @Override
	        public TableRow<WarenbestellungenRecord> call(TableView<WarenbestellungenRecord> table) {
	            final TableRow<WarenbestellungenRecord> row = new TableRow<WarenbestellungenRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(createRowMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private ContextMenu createRowMenu(TableView<WarenbestellungenRecord> table, TableRow<WarenbestellungenRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem buchen = new MenuItem("Buchen");
        buchen.setOnAction(event -> {controller.onBestellungBuchen(row.getIndex());});	
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { controller.onBestellungDelete(row.getIndex());});
        menu.getItems().addAll(buchen,remove);
        return menu;
	}
	
	BestellungenView setItems(ArrayList<WarenbestellungenRecord> items) { 
		bestellungen_liste.setItems(FXCollections.observableList(items));
		return this;
	}
	
	protected AnchorPane onShow() { return bestellungen; }
	
	void onRefresh() {
		bestellungen_liste.refresh();
	}
}
