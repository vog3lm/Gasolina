package app.kraftstoff;

import java.net.URL;
import java.util.ResourceBundle;

import app.Zustand;
import app.fxml.Loader;
import app.Lifecycle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class KraftstoffController implements Lifecycle, Initializable {

	public static final int BESTAND = 0;
	public static final int BESTELLUNGEN = 1;
	public static final int TANKS = 2;
	
	private int tab;
	
	private KraftstoffbestandTable bestand = new KraftstoffbestandTable();
	
	private KraftstoffbestellungenTable bestellungen = new KraftstoffbestellungenTable();
	
	@FXML
	private TabPane kraftstoff_tabs;
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
	private Button bestand_bestellen;
	@FXML
	private Button bestellungen_hinzufuegen;
	
	public KraftstoffController(int tab) {
		this.tab = tab;
		new Loader().onLoadBorderCenter(this,Loader.KRAFTSTOFF,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		kraftstoff_tabs.getSelectionModel().select(this.tab);
		kraftstoff_tabs.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
	        this.tab = kraftstoff_tabs.getSelectionModel().getSelectedIndex();
	    });
		/**/
		bestand_warennummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("warennummer"));
	    bestand_bezeichnung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("bezeichnung"));
	    bestand_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_bezeichnung.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"bezeichnung");});
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
	                    int kapazitaet = 1000;
	                    if(!empty && !value.equals("")) {
		                    float menge = Float.parseFloat(value);
		                    if(kapazitaet / 4 >= menge) {
		                    	this.getStyleClass().add("bestand-traffic-red");
		                    } else if(kapazitaet / 2 >= menge) {
		                    	this.getStyleClass().add("bestand-traffic-yellow");
		                    } else {
		                    	this.getStyleClass().add("bestand-traffic-green");
		                    }
	                    }
	                }
	            };
	        }
	    });
	    bestand_menge.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"menge");}); 
	    bestand_preis.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("preis"));
	    bestand_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_preis.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"preis");});
	    bestand_waehrung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("waehrung"));
	    bestand_tank.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("tank"));
	    bestand_kapazitaet.setCellValueFactory(new PropertyValueFactory<KraftstoffbestandRecord, String>("kapazitaet"));	    
	    /**/
	    bestand_liste.setItems(FXCollections.observableList(this.bestand.onReadAll()));
	    bestand_liste.setRowFactory(this.onCreateBestandListener());
	    bestand_liste.getSortOrder().addAll(bestand_bezeichnung);
	    	    
	    /**/
	    bestand_bestellen.setOnAction(this::onClickHinzufuegen);
	    /**/
	    bestellungen_bestellnummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bestellnummer"));
	    bestellungen_warennummer.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("warennummer"));
	    bestellungen_bezeichnung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bezeichnung"));
	    //bestellungen_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    //bestellungen_bezeichnung.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"bezeichnung");});
	    bestellungen_menge.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("menge"));
	    bestellungen_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_menge.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"menge");});
	    bestellungen_preis.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("preis"));
	    bestellungen_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_preis.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"preis");});
	    bestellungen_waehrung.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("waehrung"));
	    bestellungen_lieferdatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("lieferdatum"));
	    bestellungen_lieferdatum.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_lieferdatum.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"lieferdatum");});
	    bestellungen_bestelldatum.setCellValueFactory(new PropertyValueFactory<KraftstoffbestellungenRecord, String>("bestelldatum"));
	    /**/
	    bestellungen_liste.setItems(FXCollections.observableList(this.bestellungen.onReadAll()));
	    bestellungen_liste.setRowFactory(this.onCreateBestellungListener());
	    bestellungen_liste.getSortOrder().addAll(bestellungen_bezeichnung);
	    /**/
	    bestellungen_hinzufuegen.setOnAction(this::onClickHinzufuegen);
	}
	
	private Callback<TableView<KraftstoffbestandRecord>,TableRow<KraftstoffbestandRecord>> onCreateBestandListener() {
		return new Callback<TableView<KraftstoffbestandRecord>, TableRow<KraftstoffbestandRecord>>(){
	        @Override
	        public TableRow<KraftstoffbestandRecord> call(TableView<KraftstoffbestandRecord> table) {
	            final TableRow<KraftstoffbestandRecord> row = new TableRow<KraftstoffbestandRecord>();           
	            row.contextMenuProperty().bind(Bindings
            		.when(Bindings.isNotNull(row.itemProperty()))
            		.then(onClickBestandMenu(table,row))
            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private void onClickBestandEdit(TableColumn.CellEditEvent<KraftstoffbestandRecord, String> cell, String id) {
		KraftstoffbestandRecord row = cell.getTableView().getItems().get(cell.getTablePosition().getRow());
		if(id.equals("bezeichnung")) { row.setBezeichnung(cell.getNewValue()); }
		else if(id.equals("menge")) { row.setMenge(cell.getNewValue()); }
		else if(id.equals("preis")) { row.setPreis(cell.getNewValue()); }
	}
	
	private ContextMenu onClickBestandMenu(TableView<KraftstoffbestandRecord> table, TableRow<KraftstoffbestandRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem bestellen = new MenuItem("Bestellen");
        bestellen.setOnAction(event -> {onClickBestellen(row.getItem());});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(bestellen,remove);
        return menu;
	}
	
	private Callback<TableView<KraftstoffbestellungenRecord>,TableRow<KraftstoffbestellungenRecord>> onCreateBestellungListener() {
		return new Callback<TableView<KraftstoffbestellungenRecord>, TableRow<KraftstoffbestellungenRecord>>(){
	        @Override
	        public TableRow<KraftstoffbestellungenRecord> call(TableView<KraftstoffbestellungenRecord> table) {
	            final TableRow<KraftstoffbestellungenRecord> row = new TableRow<KraftstoffbestellungenRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(onClickBestellungMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private void onClickBestellungEdit(TableColumn.CellEditEvent<KraftstoffbestellungenRecord, String> cell, String id) {
		KraftstoffbestellungenRecord row = cell.getTableView().getItems().get(cell.getTablePosition().getRow());
		if(id.equals("bezeichnung")) { row.setBezeichnung(cell.getNewValue()); }
		else if(id.equals("menge")) { row.setMenge(cell.getNewValue()); }
		else if(id.equals("preis")) { row.setPreis(cell.getNewValue()); }
		else if(id.equals("lieferdatum")) { row.setLieferdatum(cell.getNewValue()); }
	}
	
	private ContextMenu onClickBestellungMenu(TableView<KraftstoffbestellungenRecord> table, TableRow<KraftstoffbestellungenRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem buchen = new MenuItem("Buchen");
        buchen.setOnAction(event -> {onClickBuchen(row.getItem());});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(buchen,remove);
        return menu;
	}

	private void onClickBestellen(KraftstoffbestandRecord record) {
		TextField warennummer = new TextField();
		warennummer.setText(record.getWarennummer());
		TextField bezeichnung = new TextField();
		bezeichnung.setText(record.getBezeichnung());
		bezeichnung.setEditable(false);
		TextField menge = new TextField();
		Platform.runLater(() -> menge.requestFocus());
		TextField einheit = new TextField();
		einheit.setEditable(false);
		einheit.setText(record.getEinheit());
		/* create and call dialog */
		onCreateBestellenDialog(warennummer,bezeichnung,menge,einheit).showAndWait().ifPresent(r -> {
			/* TODO: process result */
			// bestellungen.onCreate(r);
			// bestellungen_list.refresh();
			kraftstoff_tabs.getSelectionModel().select(1);
		});
	}
	
	private void onClickHinzufuegen(ActionEvent event) {
		kraftstoff_tabs.getSelectionModel().select(1);
		TextField warennummer = new TextField();
		TextField einheit = new TextField();
		TextField bezeichnung = new TextField();
		Platform.runLater(() -> bezeichnung.requestFocus());
		TextField menge = new TextField();
		/* create and call dialog */
		onCreateBestellenDialog(warennummer,bezeichnung,menge,einheit).showAndWait().ifPresent(r -> {
			/* TODO: process result */
			// bestellungen.onCreate(r);
			// bestellungen_list.refresh();
		});
	}
	
	private Dialog<KraftstoffbestellungenRecord> onCreateBestellenDialog(TextField warennummer, TextField bezeichnung, TextField menge, TextField einheit) {
		/* prepare dialog - one/two */
		Dialog<KraftstoffbestellungenRecord> dialog = new Dialog<KraftstoffbestellungenRecord>();
		dialog.setTitle("Kraftstoffbestellung");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(true);
		/* build dialog layout components */
		menge.setPromptText("Menge");
		menge.textProperty().addListener((ob, o, n) -> {
			if(!n.equals("")) {
			    try {Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){menge.setText(o);}
			}
		});
		TextField preis = new TextField();
		preis.setPromptText("Preis");
		preis.textProperty().addListener((ob, o, n) -> {
			if(!n.equals("")) {
			    try {Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){menge.setText(o);}
			}
		});
		warennummer.setPromptText("Warennummer");
		warennummer.setEditable(false);
		einheit.setPromptText("Einheit");
		TextField lieferdatum = new TextField();
		lieferdatum.setPromptText("Lieferdatum");
		bezeichnung.setPromptText("Bezeichnung");
		/* build dialog layout */
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Warennummer:"), 0, 0);
		grid.add(warennummer, 1, 0);
		grid.add(new Label("Bezeichnung:"), 0, 1);
		grid.add(bezeichnung, 1, 1);
		grid.add(new Label("Menge:"), 0, 2);
		grid.add(menge, 1, 2);
		grid.add(new Label("Einheit:"), 0, 3);
		grid.add(einheit, 1, 3);
		grid.add(new Label("Preis:"), 0, 4);
		grid.add(preis, 1, 4);
		grid.add(new Label("Lieferdatum:"), 0, 5);
		grid.add(lieferdatum, 1, 5);
		/* prepare dialog - two/two */
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
		    if (submit == ButtonType.OK) {
		        return new KraftstoffbestellungenRecord(-1,"","","","","","","","");
		    }
		    return null;
		});
		return dialog;
	}
	
	private void onClickBuchen(KraftstoffbestellungenRecord record) {
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
		        return new KraftstoffbestandRecord(-1,"","","","","","","","");
		    }
		    return null;
		});
		dialog.showAndWait().ifPresent(r -> {
			/* TODO: process result */
			kraftstoff_tabs.getSelectionModel().select(0);
		});
	}
	
	@Override
	public boolean destroy() {
		bestand.onCommit();
		bestellungen.onCommit();
		return true;
	}
}
