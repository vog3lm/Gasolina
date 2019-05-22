package app.waren;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.fxml.Loader;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class BestandView implements Initializable {

	@FXML
	private AnchorPane bestand_wrapper;
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
	
	private WarenController controller;
	
	BestandView(WarenController controller) {
		this.controller = controller;
		new Loader().onLoadInitializable(Loader.WAREN_BESTAND,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		/**/
		bestand_warennummer.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("warennummer"));
	    bestand_bezeichnung.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("bezeichnung"));
	    bestand_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	//    bestand_bezeichnung.setOnEditCommit((cell) -> {controller.onBestandEdit(cell,"bezeichnung");});
	    bestand_einheit.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("einheit"));
	    bestand_einheit.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_einheit.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"einheit",cell.getNewValue());});
	    bestand_menge.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("menge"));
	    bestand_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_menge.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"menge",cell.getNewValue());});
	    bestand_preis.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("preis"));
	    bestand_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_preis.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"preis",cell.getNewValue());});
	    bestand_waehrung.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("waehrung"));
	    bestand_kategorie.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("kategorie"));
	    bestand_kategorie.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_kategorie.setOnEditCommit(cell -> {controller.onBestandEdit(cell.getTablePosition().getRow(),"kategorie",cell.getNewValue());});
	    /**/
	    bestand_liste.setRowFactory(createRowListener());
	    bestand_liste.getSortOrder().addAll(bestand_bezeichnung);
	    /**/
//	    bestand_bestellen.setOnAction(this::onClickHinzufuegen);
	    bestand_bestellen.setOnAction(event -> {
	    	new WarenDialoge().createBestellungAddDialog().showAndWait().ifPresent(record -> {
	    		controller.onBestellen(record);
			});
	    });
	}
	
	private Callback<TableView<WarenbestandRecord>,TableRow<WarenbestandRecord>> createRowListener() {
		return new Callback<TableView<WarenbestandRecord>, TableRow<WarenbestandRecord>>(){
	        @Override
	        public TableRow<WarenbestandRecord> call(TableView<WarenbestandRecord> table) {
	            final TableRow<WarenbestandRecord> row = new TableRow<WarenbestandRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(createRowMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private ContextMenu createRowMenu(TableView<WarenbestandRecord> table, TableRow<WarenbestandRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem bestellen = new MenuItem("Bestellen");
//        bestellen.setOnAction(event -> {onClickBestellen(row.getItem());});
        bestellen.setOnAction(event -> {
        	new WarenDialoge().createBestandBestellenDialog(row.getItem()).showAndWait().ifPresent(record -> {
	    		controller.onBestellen(record);
			});
        });
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(bestellen,remove);
        return menu;
	}

	void setItems(ArrayList<WarenbestandRecord> items) { bestand_liste.setItems(FXCollections.observableList(items)); }
	
	AnchorPane getView() { return bestand_wrapper; }
	
	void onRefresh() {
		bestand_liste.refresh();
		bestand_liste.getSortOrder().addAll(bestand_bezeichnung);
	}
}