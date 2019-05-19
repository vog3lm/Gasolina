package app.waren;

import java.net.URL;
import java.util.ResourceBundle;

import app.Zustand;
import app.waren.WarenbestellungenRecord;
import app.waren.WarenbestellungenTable;
import app.Lifecycle;
import app.Util;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class WarenController implements Lifecycle {

	public static final int BESTAND = 0;
	public static final int BESTELLUNGEN = 1;
	
	private int tab;
	
	@FXML
	private TabPane waren_tabs;
	
	private WarenbestandTable bestand = new WarenbestandTable();
	
	private WarenbestellungenTable bestellungen = new WarenbestellungenTable();
	
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
	private TableColumn<WarenbestellungenRecord, String> bestellungen_bestelldatum;
	@FXML
	private TableColumn<WarenbestellungenRecord, String> bestellungen_lieferdatum;
	
	@FXML
	private Button bestand_bestellen;
	@FXML
	private Button bestellungen_hinzufuegen;
	
	public WarenController(int tab) {
		this.tab = tab;
		new Util().onLoadCenter(super.getClass().getResource("Waren.fxml"),this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		waren_tabs.getSelectionModel().select(this.tab);
		waren_tabs.getSelectionModel().select(this.tab);
		waren_tabs.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
	        this.tab = waren_tabs.getSelectionModel().getSelectedIndex();
	    });
		/**/
		bestand_warennummer.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("warennummer"));
	    bestand_bezeichnung.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("bezeichnung"));
	    bestand_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_bezeichnung.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"bezeichnung");});
	    bestand_einheit.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("einheit"));
	    bestand_einheit.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_einheit.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"einheit");});
	    bestand_menge.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("menge"));
	    bestand_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_menge.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"menge");});
	    bestand_preis.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("preis"));
	    bestand_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_preis.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"preis");});
	    bestand_waehrung.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("waehrung"));
	    bestand_kategorie.setCellValueFactory(new PropertyValueFactory<WarenbestandRecord, String>("kategorie"));
	    bestand_kategorie.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestand_kategorie.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"kategorie");});
	    /**/
	    bestand_liste.setItems(FXCollections.observableList(this.bestand.onReadAll()));
	    bestand_liste.setRowFactory(this.onCreateBestandListener());
	    bestand_liste.getSortOrder().addAll(bestand_bezeichnung);
	    /**/
	    bestand_bestellen.setOnAction(this::onClickHinzufuegen);
	    /**/
	    bestellungen_bestellnummer.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("bestellnummer"));
	    bestellungen_warennummer.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("warennummer"));
	    bestellungen_bezeichnung.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("bezeichnung"));
	    //bestellungen_bezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
	    //bestellungen_bezeichnung.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"bezeichnung");});
	    bestellungen_menge.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("menge"));
	    bestellungen_menge.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_menge.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"menge");});
	    bestellungen_preis.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("preis"));
	    bestellungen_preis.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_preis.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"preis");});
	    bestellungen_waehrung.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("waehrung"));
	    bestellungen_lieferdatum.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("lieferdatum"));
	    bestellungen_lieferdatum.setCellFactory(TextFieldTableCell.forTableColumn());
	    bestellungen_lieferdatum.setOnEditCommit((cell) -> {onClickBestellungEdit(cell,"lieferdatum");});
	    bestellungen_bestelldatum.setCellValueFactory(new PropertyValueFactory<WarenbestellungenRecord, String>("bestelldatum"));
	    /**/
	    bestellungen_liste.setItems(FXCollections.observableList(this.bestellungen.onReadAll()));
	    bestellungen_liste.setRowFactory(this.onCreateBestellungListener());
	    bestellungen_liste.getSortOrder().addAll(bestellungen_bezeichnung);
	    /**/
	    bestellungen_hinzufuegen.setOnAction(this::onClickHinzufuegen);
	}
	
	private Callback<TableView<WarenbestandRecord>,TableRow<WarenbestandRecord>> onCreateBestandListener() {
		return new Callback<TableView<WarenbestandRecord>, TableRow<WarenbestandRecord>>(){
	        @Override
	        public TableRow<WarenbestandRecord> call(TableView<WarenbestandRecord> table) {
	            final TableRow<WarenbestandRecord> row = new TableRow<WarenbestandRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(onClickBestandMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private void onClickBestandEdit(TableColumn.CellEditEvent<WarenbestandRecord, String> cell, String id) {
		WarenbestandRecord row = cell.getTableView().getItems().get(cell.getTablePosition().getRow());	
		if(id.equals("bezeichnung")) { row.setBezeichnung(cell.getNewValue()); }
		else if(id.equals("einheit")) { row.setEinheit(cell.getNewValue()); }
		else if(id.equals("menge")) { row.setMenge(cell.getNewValue()); }
		else if(id.equals("preis")) { row.setPreis(cell.getNewValue()); }
		else if(id.equals("kategorie")) { row.setKategorie(cell.getNewValue()); }
	}
	
	private ContextMenu onClickBestandMenu(TableView<WarenbestandRecord> table, TableRow<WarenbestandRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem bestellen = new MenuItem("Bestellen");
        bestellen.setOnAction(event -> {onClickBestellen(row.getItem());});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(bestellen,remove);
        return menu;
	}
	
	private Callback<TableView<WarenbestellungenRecord>,TableRow<WarenbestellungenRecord>> onCreateBestellungListener() {
		return new Callback<TableView<WarenbestellungenRecord>, TableRow<WarenbestellungenRecord>>(){
	        @Override
	        public TableRow<WarenbestellungenRecord> call(TableView<WarenbestellungenRecord> table) {
	            final TableRow<WarenbestellungenRecord> row = new TableRow<WarenbestellungenRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(onClickBestellungMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private void onClickBestellungEdit(TableColumn.CellEditEvent<WarenbestellungenRecord, String> cell, String id) {
		WarenbestellungenRecord row = cell.getTableView().getItems().get(cell.getTablePosition().getRow());	
		if(id.equals("bezeichnung")) { row.setBezeichnung(cell.getNewValue()); }
		else if(id.equals("menge")) { row.setMenge(cell.getNewValue()); }
		else if(id.equals("preis")) { row.setPreis(cell.getNewValue()); }
		else if(id.equals("lieferdatum")) { row.setLieferdatum(cell.getNewValue()); }
	}
	
	private ContextMenu onClickBestellungMenu(TableView<WarenbestellungenRecord> table, TableRow<WarenbestellungenRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem buchen = new MenuItem("Buchen");
        buchen.setOnAction(event -> {onClickBuchen(row.getItem());});
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(buchen,remove);
        return menu;
	}

	private void onClickBestellen(WarenbestandRecord record) {
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
			// bestellungen_liste.refresh();
			waren_tabs.getSelectionModel().select(1);
		});
		
	}

	private void onClickHinzufuegen(ActionEvent event) {
		waren_tabs.getSelectionModel().select(1);
		TextField warennummer = new TextField();
		TextField bezeichnung = new TextField();
		Platform.runLater(() -> bezeichnung.requestFocus());
		TextField menge = new TextField();
		TextField einheit = new TextField();
		/* create and call dialog */
		onCreateBestellenDialog(warennummer,bezeichnung,menge,einheit).showAndWait().ifPresent(r -> {
			/* TODO: process result */
			// bestellungen.onCreate(r);
			// bestellungen_liste.refresh();
		});
	}
	
	private Dialog<WarenbestellungenRecord> onCreateBestellenDialog(TextField warennummer, TextField bezeichnung, TextField menge, TextField einheit) {
		/* prepare dialog - one/two */
		Dialog<WarenbestellungenRecord> dialog = new Dialog<WarenbestellungenRecord>();
		dialog.setTitle("Warenbestellung");
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
		        return new WarenbestellungenRecord(-1,"","","","","","","","");
		    }
		    return null;
		});
		return dialog;
	}
	
	private void onClickBuchen(WarenbestellungenRecord record) {
		/* prepare dialog - one/two */
		Dialog<WarenbestandRecord> dialog = new Dialog<WarenbestandRecord>();
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
		        return new WarenbestandRecord(-1,"","","","","","","");
		    }
		    return null;
		});
		dialog.showAndWait().ifPresent(r -> {
			waren_tabs.getSelectionModel().select(0);
		});
	}
	
	@Override
	public boolean destroy() {
		bestand.onCommit();
		bestellungen.onCommit();
		return true;
	}
}
