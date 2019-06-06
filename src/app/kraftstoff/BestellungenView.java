package app.kraftstoff;

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
	
	private final String layout = "KraftstoffBestellungen.fxml";

	@FXML
	private AnchorPane bestellungen;
	@FXML
    private TableView<KraftstoffbestellungenRecord> bestellungen_liste;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_bestellnummer;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_warennummer;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_bezeichnung;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_preis;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_waehrung;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_menge;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_einheit;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_bestelldatum;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_lieferdatum;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_mitarbeiter;
	@FXML
	private Button bestellungen_hinzufuegen;
		
	private KraftstoffController controller;
	
	private ArrayList<KraftstoffbestellungenRecord> items;
		
	BestellungenView(KraftstoffController controller,ArrayList<KraftstoffbestellungenRecord> items) {
		this.controller = controller;
		this.items = items;
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
	    bestellungen_bestellnummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bestellnummer"));
	    bestellungen_warennummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("warennummer"));
	    bestellungen_bezeichnung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bezeichnung"));
	    bestellungen_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_bezeichnung.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"bezeichnung",cell.getNewValue());});
	    bestellungen_menge.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("menge"));
	    bestellungen_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_menge.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"menge",cell.getNewValue());});
	    bestellungen_einheit.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("einheit"));
	    bestellungen_einheit.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_einheit.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"menge",cell.getNewValue());});
	    bestellungen_preis.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("preis"));
	    bestellungen_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_preis.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"preis",cell.getNewValue());});
	    bestellungen_waehrung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("waehrung"));
	    bestellungen_lieferdatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("lieferdatum"));
	    bestellungen_lieferdatum.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_lieferdatum.setOnEditCommit(cell -> {controller.onBestellungEdit(cell.getTablePosition().getRow(),"lieferdatum",cell.getNewValue());});
	    bestellungen_bestelldatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bestelldatum"));
	    bestellungen_mitarbeiter.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("mitarbeiter"));
	    /**/
	    bestellungen_liste.setRowFactory(this.createRowListener());
	    bestellungen_liste.setItems(FXCollections.observableList(items));
	    /**/
	    bestellungen_hinzufuegen.setOnAction(event -> {controller.onBestellungAdd();});
	}
	
	private Callback<TableView<KraftstoffbestellungenRecord>,TableRow<KraftstoffbestellungenRecord>> createRowListener() {
		return new Callback<TableView<KraftstoffbestellungenRecord>, TableRow<KraftstoffbestellungenRecord>>(){
	        @Override
	        public TableRow<KraftstoffbestellungenRecord> call(TableView<KraftstoffbestellungenRecord> table) {
	            final TableRow<KraftstoffbestellungenRecord> row = new TableRow<KraftstoffbestellungenRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(createRowMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private ContextMenu createRowMenu(TableView<KraftstoffbestellungenRecord> table, TableRow<KraftstoffbestellungenRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem buchen = new MenuItem("Buchen");
        buchen.setOnAction(event -> {controller.onBestellungBuchen(row.getIndex());});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { controller.onBestellungDelete(row.getIndex());});
        menu.getItems().addAll(buchen,remove);
        return menu;
	}
			
	protected AnchorPane onShow() { return bestellungen; }
	
	void onRefresh() {
		bestellungen_liste.refresh();
	}
}
