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
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_bestelldatum;
	@FXML
	private TableColumn<KraftstoffbestellungenRecord, String> bestellungen_lieferdatum;
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
	    //bestellungen_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    //bestellungen_bezeichnung.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"bezeichnung");});
	    bestellungen_menge.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("menge"));
	    bestellungen_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_menge.setOnEditCommit((cell) -> {controller.onBestellungEdit(cell,"menge");});
	    bestellungen_preis.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("preis"));
	    bestellungen_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_preis.setOnEditCommit((cell) -> {controller.onBestellungEdit(cell,"preis");});
	    bestellungen_waehrung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("waehrung"));
	    bestellungen_lieferdatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("lieferdatum"));
	    bestellungen_lieferdatum.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_lieferdatum.setOnEditCommit((cell) -> {controller.onBestellungEdit(cell,"lieferdatum");});
	    bestellungen_bestelldatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bestelldatum"));
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
        buchen.setOnAction(event -> {showBuchenDialog(row.getItem());});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());}); // TODO : controller.onDelete()
        menu.getItems().addAll(buchen,remove);
        return menu;
	}
	
	private void showBuchenDialog(KraftstoffbestellungenRecord record) {
		/* prepare dialog - one/two */
		Dialog<KraftstoffbestandRecord> dialog = new Dialog<KraftstoffbestandRecord>();
		dialog.setTitle("Waren Einbuchen");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(true);
		/* build dialog layout components */
		TextField bestellnummer = new TextField();
		bestellnummer.setEditable(false);
		bestellnummer.setText(record.getBestellnummer());
		TextField bezeichnung = new TextField();
		bezeichnung.setEditable(false);
		bezeichnung.setText(record.getBezeichnung());
		TextField restmenge = new TextField();
		restmenge.setPromptText("Restmenge");
		restmenge.setEditable(false);
		restmenge.setVisible(false);
		Label restmenge_label = new Label("Restmenge:");
		restmenge_label.setVisible(false);
		TextField lieferdatum = new TextField();
		lieferdatum.setPromptText("Lieferdatum");
		lieferdatum.setVisible(false);
		Label lieferdatum_label = new Label("Lieferdatum:");
		lieferdatum_label.setVisible(false);
		TextField menge = new TextField();
		menge.setPromptText("Menge");
		menge.setText(record.getMenge());
		menge.textProperty().addListener((ob, o, n) -> {
			float liefermenge = 0;
			if(!n.equals("")) {
			    try {liefermenge = Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){
			    	menge.setText(o);
			    	return;
			    }
			}
			if(!n.equals(record.getMenge())) {
				restmenge.setText((Float.parseFloat(record.getMenge())-liefermenge)+"");
				restmenge.setVisible(true);
				restmenge_label.setVisible(true);
				lieferdatum.setVisible(true);
				lieferdatum_label.setVisible(true);
			}else {
				restmenge.setText("");
				restmenge.setVisible(false);
				restmenge_label.setVisible(false);
				lieferdatum.setVisible(false);
				lieferdatum_label.setVisible(false);
			}
		});
		/* build dialog layout */
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Bestellnummer:"), 0, 0);
		grid.add(bestellnummer, 1, 0);
		grid.add(new Label("Bezeichnung:"), 0, 1);
		grid.add(bezeichnung, 1, 1);
		grid.add(new Label("Menge:"), 0, 2);
		grid.add(menge, 1, 2);
		grid.add(restmenge_label, 0, 3);
		grid.add(restmenge, 1, 3);
		grid.add(lieferdatum_label, 0, 4);
		grid.add(lieferdatum, 1, 4);
		/* prepare dialog - two/two */
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
		    if (submit == ButtonType.OK) {
		    	// TODO : call controller onBuchen
		    	// view.setIndex(KraftstoffView.BESTAND);
		        return new KraftstoffbestandRecord(-1,"","","","","","","","");
		    }
		    return null;
		});
		dialog.showAndWait();
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
