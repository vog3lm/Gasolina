package app.kraftstoff;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Zustand;
import app.fxml.Loader;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class BestellungenView implements Initializable {

	@FXML
	private AnchorPane bestellungen_wrapper;
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
	
	BestellungenView(KraftstoffController controller) {
		this.controller = controller;
		new Loader().onLoadInitializable(Loader.KRAFTSTOFF_BESTELLUNGEN,this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
	    bestellungen_bestellnummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bestellnummer"));
	    bestellungen_warennummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("warennummer"));
	    bestellungen_bezeichnung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bezeichnung"));
	    bestellungen_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_bezeichnung.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"bezeichnung",cell.getNewValue());});
	    bestellungen_menge.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("menge"));
	    bestellungen_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_menge.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"menge",cell.getNewValue());});
	    
	    bestellungen_einheit.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("einheit"));
	    bestellungen_einheit.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_einheit.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"menge",cell.getNewValue());});
	    
	    bestellungen_preis.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("preis"));
	    bestellungen_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_preis.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"preis",cell.getNewValue());});
	    bestellungen_waehrung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("waehrung"));
	    bestellungen_lieferdatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("lieferdatum"));
	    bestellungen_lieferdatum.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_lieferdatum.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"lieferdatum",cell.getNewValue());});
	    bestellungen_bestelldatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bestelldatum"));
	    bestellungen_mitarbeiter.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("mitarbeiter"));
	    /**/
	    bestellungen_liste.setRowFactory(this.createRowListener());
	    bestellungen_liste.getSortOrder().addAll(bestellungen_bezeichnung);
	    /**/
	    bestellungen_hinzufuegen.setOnAction(event -> {
	    	new KraftstoffDialoge().createBestellungAddDialog().showAndWait().ifPresent(record -> {
	    		controller.onBestellen(record);
			});
	    });
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
        buchen.setOnAction(event -> {new KraftstoffDialoge().createBuchenDialog(row.getItem()).showAndWait().ifPresent(r -> {
        	controller.onBuchen(r);
        });});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());}); // TODO : controller.onDelete()
        menu.getItems().addAll(buchen,remove);
        return menu;
	}
		
	void setItems(ArrayList<KraftstoffbestellungenRecord> items) {
		bestellungen_liste.setItems(FXCollections.observableList(items));
	}
	
	AnchorPane getView() { return bestellungen_wrapper; }
	
	void onRefresh() {
		bestellungen_liste.refresh();
		bestellungen_liste.getSortOrder().addAll(bestellungen_bezeichnung);
	}
}
