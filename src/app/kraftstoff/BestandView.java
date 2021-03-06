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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

class BestandView extends Loadable<AnchorPane> {
	
	private final String layout = "KraftstoffBestand.fxml";

	@FXML
	private AnchorPane bestand;
	@FXML
    private TableView<KraftstoffbestandRecord> bestand_liste;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_warennummer;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_bezeichnung;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_einheit;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_menge;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_preis;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_waehrung;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_tank;
	@FXML
	private TableColumn<KraftstoffbestandRecord, String> bestand_kapazitaet;
	@FXML
	private Button bestand_bestellen;
	
	private KraftstoffController controller;
	
	private ArrayList<KraftstoffbestandRecord> items;
		
	BestandView(KraftstoffController controller,ArrayList<KraftstoffbestandRecord> items) {
		this.controller = controller;
		this.items = items;
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		bestand_warennummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("warennummer"));
	    bestand_bezeichnung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("bezeichnung"));
	    bestand_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_bezeichnung.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"bezeichnung",cell.getNewValue());});    
	    bestand_einheit.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("einheit"));
	    bestand_menge.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("menge"));
	    /* bestand_menge.setCellFactory(TextFieldTableCell.forTableColumn()); */
	    bestand_menge.setCellFactory(new Callback<TableColumn<KraftstoffbestandRecord,String>, TableCell<KraftstoffbestandRecord,String>>() {
	        public TableCell<KraftstoffbestandRecord,String> call(TableColumn<KraftstoffbestandRecord,String> column) {
	        	// TODO: crash on edit - not editable ! lt. anforderungskatalog keine explizite Edittierbarkeit gefordert!
	        	// return new TextFieldTableCell<KraftstoffbestandRecord, String>() {
	        	return new TableCell<KraftstoffbestandRecord, String>() {
	                @Override
	                public void updateItem(String value, boolean empty) {
	                    super.updateItem(value, empty);
	                    this.setText(value);

	            		TableRow<KraftstoffbestandRecord> row = this.getTableRow();
	            		if(!empty && !value.equals("") && null != row) {
	            			this.getStyleClass().add(controller.onBestandAmpel(row.getIndex(),value));
	                    }
	                }
	            };
	        }
	    });
	    bestand_menge.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"menge",cell.getNewValue());});
	    bestand_preis.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("preis"));
	    bestand_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_preis.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"preis",cell.getNewValue());});
	    bestand_waehrung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("waehrung"));
	    bestand_tank.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("tank"));
	    bestand_kapazitaet.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("kapazitaet"));	    
	    /**/
	    bestand_liste.setRowFactory(this.createRowListener());
	    bestand_liste.setItems(FXCollections.observableList(items)); 
	    bestand_liste.getSortOrder().addAll(bestand_bezeichnung);
	    // bestand_liste.setItems(FXCollections.observableList(controller.onData()));
	    /**/
	    bestand_bestellen.setOnAction(event -> {controller.onBestellungAdd();});
	    /**/
	}
	
	private Callback<TableView<KraftstoffbestandRecord>,TableRow<KraftstoffbestandRecord>> createRowListener() {
		return new Callback<TableView<KraftstoffbestandRecord>, TableRow<KraftstoffbestandRecord>>(){
	        @Override
	        public TableRow<KraftstoffbestandRecord> call(TableView<KraftstoffbestandRecord> table) {
	            final TableRow<KraftstoffbestandRecord> row = new TableRow<KraftstoffbestandRecord>();           
	            row.contextMenuProperty().bind(Bindings
            		.when(Bindings.isNotNull(row.itemProperty()))
            		.then(createRowMenu(table,row))
            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private ContextMenu createRowMenu(TableView<KraftstoffbestandRecord> table, TableRow<KraftstoffbestandRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem bestellen = new MenuItem("Bestellen");
        bestellen.setOnAction(event -> {controller.onBestandBestellen(row.getIndex());});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { controller.onBestandDelete(row.getIndex()); });
        menu.getItems().addAll(bestellen,remove);
        return menu;
	}
		
	protected AnchorPane onShow() { return bestand; }
	
	void onRefresh() {
		bestand_liste.refresh();
		bestand_liste.getSortOrder().addAll(bestand_bezeichnung);
	}
}
