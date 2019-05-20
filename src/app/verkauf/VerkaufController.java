package app.verkauf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import app.Zustand;
import app.personal.PersonalRecord;
import app.verkauf.VerkaufRecord;
import app.Lifecycle;
import app.Util;
import app.waren.WarenbestandRecord;
import app.waren.WarenbestandTable;
import app.kraftstoff.KraftstoffbestandRecord;
import app.kraftstoff.KraftstoffbestandTable;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Callback;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class VerkaufController implements Lifecycle {

	public static final int SAEULE1 = 0;
	public static final int SAEULE2 = 1;
	public static final int SAEULE3 = 2;
	public static final int JOURNAL= 3;
	
	private int tab;
	
	private KraftstoffbestandTable kraftstoffe = new KraftstoffbestandTable();
	
	private WarenbestandTable waren = new WarenbestandTable();
	
	@FXML
	private TabPane verkauf_tabs;
	
	@FXML
	private TableView<VerkaufRecord> verkauf_liste;
	@FXML
	private TableColumn<VerkaufRecord, String> verkauf_warennummer;
	@FXML
	private TableColumn<VerkaufRecord, String> verkauf_bezeichnung;
	@FXML
	private TableColumn<VerkaufRecord, String> verkauf_preis;
	@FXML
	private TableColumn<VerkaufRecord, String> verkauf_menge;
	@FXML
	private TableColumn<VerkaufRecord, String> verkauf_einheit;
	@FXML
	private TableColumn<VerkaufRecord, String> verkauf_summe;
	@FXML
	private Label verkauf_total;
	
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
	
	@FXML
	private Button verkauf_kraftstoff;
	@FXML
	private Button verkauf_waren;
	@FXML
	private Button verkauf_buchen;
		
	public VerkaufController(int tab) {
		this.tab = tab;
		new Util().onLoadCenter(super.getClass().getResource("Verkauf.fxml"),this);

	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		verkauf_tabs.getSelectionModel().select(this.tab);
		verkauf_tabs.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
	        this.tab = verkauf_tabs.getSelectionModel().getSelectedIndex();
	    });
		/**/
		verkauf_warennummer.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("warennummer"));
		verkauf_bezeichnung.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("bezeichnung"));
		verkauf_preis.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("preis"));
		verkauf_menge.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("menge"));
		verkauf_menge.setCellFactory(TextFieldTableCell.forTableColumn());
		verkauf_menge.setOnEditCommit((cell) -> {onClickCellEdit(cell,"menge");});
		verkauf_einheit.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("einheit"));
		verkauf_summe.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("summe"));
		/**/
		verkauf_liste.setRowFactory(this.onCreateRowListener());
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
		journal_liste.setRowFactory(this.onCreateRowListener());
		/**/
		verkauf_kraftstoff.setOnAction(event -> {onCreateKraftstoffVerkauf();});
		verkauf_waren.setOnAction(event -> {onCreateWarenVerkauf();});
		verkauf_buchen.setOnAction(this::onClickVerkaufBuchen);	
	}
	
	private Callback<TableView<VerkaufRecord>,TableRow<VerkaufRecord>> onCreateRowListener() {
		return new Callback<TableView<VerkaufRecord>, TableRow<VerkaufRecord>>(){
	        @Override
	        public TableRow<VerkaufRecord> call(TableView<VerkaufRecord> table) {
	            final TableRow<VerkaufRecord> row = new TableRow<VerkaufRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(onClickRowMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
				
	private void onClickVerkaufBuchen(ActionEvent event) {
		ObservableList<VerkaufRecord> posten = this.verkauf_liste.getItems();
		/**/
		String belegnummer = "0";
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String datum = dateFormat.format(date);
		String uhrzeit = timeFormat.format(date);
		PersonalRecord benutzer = Zustand.getInstance().getBenutzer();
		String message = "Max Maier Tankstelle\n\n" + 
			"Belegnummer: "+belegnummer+"\n" + 
			"Datum: "+datum+" "+uhrzeit+"\n" + 
			"Mitarbeiter: "+benutzer.getVorname()+" "+benutzer.getNachname()+"\n\n";
		float summe = 0;
		for(VerkaufRecord p : posten) {
			String s = p.getSumme();
			message = message + p.getBezeichnung()+"-"+p.getMenge()+" "+p.getEinheit()+"-"+p.getPreis()+" EUR-"+s+" EUR\n";
			summe = summe + Float.parseFloat(s);
		}
		message = message + "\nGesamtbetrag: "+summe+" EUR";
		/* must be final for print */
		final String msg = message;
		/**/
		Alert alert = new Alert(AlertType.INFORMATION);
		DialogPane pane = alert.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		pane.setMinHeight(Region.USE_PREF_SIZE);
		alert.setTitle("Print Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.getButtonTypes().setAll(new ButtonType("Drucken",ButtonData.YES), ButtonType.OK, ButtonType.CANCEL);	
		alert.setResultConverter(submit -> {
		    if (submit.getButtonData() == ButtonData.YES) {
				try {
					String path = Zustand.getInstance().getPrintUrl();
					BufferedWriter writer = new BufferedWriter(new FileWriter(path+"beleg.txt"));
			        writer.write(msg);
			        writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    return null;
		});
		alert.showAndWait();
		
		/**/
		ObservableList<VerkaufRecord> journal = this.journal_liste.getItems();
		float umsatz = Float.parseFloat(journal_total.getText());
		for(VerkaufRecord record : posten) {
			record.setBelegnummer(belegnummer);
			record.setDatum(datum);
			record.setUhrzeit(uhrzeit);
			record.setMitarbeiter(benutzer.getPersonalnummer());
			journal.add(record);
			/**/
			umsatz = umsatz + Float.parseFloat(record.getSumme());
		}
		verkauf_total.setText("0.00");
		journal_total.setText(Math.round(umsatz * Math.pow(10, 2)) / Math.pow(10, 2)+"");
		this.verkauf_liste.setItems(FXCollections.observableList(new ArrayList<VerkaufRecord>()));
	}

	private void onClickCellEdit(TableColumn.CellEditEvent<VerkaufRecord, String> cell, String id) {
		VerkaufRecord row = cell.getTableView().getItems().get(cell.getTablePosition().getRow());
		if(id.equals("menge")) { row.setMenge(cell.getNewValue()); }
	}
	
	private ContextMenu onClickRowMenu(TableView<VerkaufRecord> table, TableRow<VerkaufRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem remove = new MenuItem("Entfernen");
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(remove);
        return menu;
	}
	
	private void onCreateKraftstoffVerkauf() {
		/* https://code.makery.ch/blog/javafx-dialogs-official/ */
		Dialog<VerkaufRecord> dialog = new Dialog<VerkaufRecord>();
		dialog.setTitle("Kraftstoffverkauf");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		ButtonType button = new ButtonType("Simulieren",ButtonData.YES);
		pane.getButtonTypes().addAll(ButtonType.CANCEL,button);
		pane.lookupButton(button).setDisable(true);
		/**/
		TextField bezeichnung = new TextField();
		TextField zapfsaeule = new TextField();
		zapfsaeule.setPromptText("Zapfsäule");
		zapfsaeule.setText(verkauf_tabs.getSelectionModel().getSelectedItem().getText()); 
		zapfsaeule.setEditable(false);
		TextField preis = new TextField();
		preis.setPromptText("Preis");
		preis.setEditable(false);
		TextField warennummer = new TextField();
		warennummer.setPromptText("Warennummer");
		warennummer.setEditable(false);
		TextField einheit = new TextField();
		einheit.setPromptText("Einheit");
		einheit.setEditable(false);
		bezeichnung.setPromptText("Bezeichnung");
		bezeichnung.textProperty().addListener((ob, o, n) -> {
			int index = kraftstoffe.getIndex(n);
			if(-1 != index) {
				KraftstoffbestandRecord record = kraftstoffe.onRead(index);
				warennummer.setText(record.getWarennummer());
				preis.setText(record.getPreis());
				einheit.setText(record.getEinheit());
				if(!bezeichnung.getText().equals("")) {
					pane.lookupButton(button).setDisable(false);
				}
			}else {
				warennummer.setText("");
				preis.setText("");
				einheit.setText("");
				pane.lookupButton(button).setDisable(true);
			}
		});
		Platform.runLater(() -> bezeichnung.requestFocus());
		/**/
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Warennummer:"), 0, 0);
		grid.add(warennummer, 1, 0);
		grid.add(new Label("Bezeichnung:"), 0, 1);
		grid.add(bezeichnung, 1, 1);
		grid.add(new Label("Zapfsäule:"), 0, 2);
		grid.add(zapfsaeule, 1, 2);
		grid.add(new Label("Einheit:"), 0, 3);
		grid.add(einheit, 1, 3);
		grid.add(new Label("Preis:"), 0, 4);
		grid.add(preis, 1, 4);
		/**/
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
		    if (submit.getButtonData() == ButtonData.YES) {
		    	return new VerkaufRecord(-1,warennummer.getText(),bezeichnung.getText(),preis.getText(),"0",einheit.getText(),"0");
		    }
		    return null;
		});
		dialog.showAndWait().ifPresent(record -> {
			this.verkauf_liste.getItems().add(record);
			KraftstoffbestandRecord bestand = kraftstoffe.onRead(kraftstoffe.getIndex(record.getBezeichnung()));
			VerkaufSimulation simulation = new VerkaufSimulation(bestand,record,this.verkauf_liste);
			this.verkauf_kraftstoff.setText("Stop");
			/* TODO: delete click row listener */
			this.verkauf_kraftstoff.setOnAction(event -> {
				simulation.interrupt();
				/* TODO: set click row listener
				 * item.onCreateRowListener() */
				this.verkauf_kraftstoff.setText("Kraftstoff");
				this.verkauf_kraftstoff.setOnAction(e -> {onCreateKraftstoffVerkauf();});
			});
			simulation.start();
		});
	}
	
	private void onCreateWarenVerkauf() {
		Dialog<VerkaufRecord> dialog = new Dialog<VerkaufRecord>();
		dialog.setTitle("Warenverkauf");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(true);
		/**/
		TextField bezeichnung = new TextField();
		TextField menge = new TextField();
		menge.setPromptText("Menge");
		menge.textProperty().addListener((ob, o, n) -> {
			if(!n.equals("")) {
				
			    try {Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){
			    	menge.setText(o);
			    	return;
			    }
			}
			if(!menge.getText().equals("") && !bezeichnung.getText().equals("")) {
				int index = waren.getIndex(bezeichnung.getText());
				if(-1 != index) {
					WarenbestandRecord record = waren.onRead(index);
					pane.lookupButton(ButtonType.OK).setDisable(false);
					/* TODO: check available quantity */
					// String bestand = record.getMenge();
					// if(bestand < menge) {
					// 	pane.lookupButton(ButtonType.OK).setDisable(true);
					// }else {
					//	pane.lookupButton(ButtonType.OK).setDisable(false);
					// }
				}
			}else {
				pane.lookupButton(ButtonType.OK).setDisable(true);
			}
		});
		TextField preis = new TextField();
		preis.setPromptText("Preis");
		preis.setEditable(false);
		TextField warennummer = new TextField();
		warennummer.setPromptText("Warennummer");
		warennummer.setEditable(false);
		TextField einheit = new TextField();
		einheit.setPromptText("Einheit");
		einheit.setEditable(false);
		bezeichnung.setPromptText("Bezeichnung");
		bezeichnung.textProperty().addListener((ob, o, n) -> {
			int index = waren.getIndex(n);
			if(-1 != index) {
				WarenbestandRecord record = waren.onRead(index);
				warennummer.setText(record.getWarennummer());
				preis.setText(record.getPreis());
				einheit.setText(record.getEinheit());
				
				if(!menge.getText().equals("") && !bezeichnung.getText().equals("")) {
					pane.lookupButton(ButtonType.OK).setDisable(false);
					/* TODO: check available quantity */
					// String bestand = record.getMenge();
					// if(bestand < menge) {
					// 	pane.lookupButton(ButtonType.OK).setDisable(true);
					// }else {
					//	pane.lookupButton(ButtonType.OK).setDisable(false);
					// }
				}
			}else {
				warennummer.setText("");
				preis.setText("");
				einheit.setText("");
				pane.lookupButton(ButtonType.OK).setDisable(true);
			}
		});
		Platform.runLater(() -> bezeichnung.requestFocus());
		/**/
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
		/**/
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
			String bez_string = bezeichnung.getText();
			String men_string = menge.getText();
		    if (submit == ButtonType.OK) {
		    	Double summe = Double.parseDouble(men_string)*Float.parseFloat(preis.getText());
		    	summe = Math.round(summe * Math.pow(10, 2)) / Math.pow(10, 2);
		    	verkauf_total.setText((Float.parseFloat(verkauf_total.getText())+summe)+"");
		        return new VerkaufRecord(-1,warennummer.getText(),bez_string,preis.getText(),men_string,einheit.getText(),summe+"");
		    }
		    return null;
		});
		dialog.showAndWait().ifPresent(record -> {this.verkauf_liste.getItems().add(record);});
		
		
		
		/* TODO: create waren verkauf dialog
		WarenbestandRecord record = waren.onRead(index);
		warennummer.setText(record.getWarennummer());
		preis.setText(record.getPreis());
		einheit.setText(record.getEinheit());
		 */
	}
	
	@Override
	public boolean destroy() {
		//	journal.onCommit();
		if(0 != verkauf_liste.getItems().size()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.getDialogPane().getStylesheets().add(Zustand.getInstance().getDesign());
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Offene Kassenvorgänge!");
			alert.setContentText("Buchen Sie alle Kassenvorgänge,\nbevor Sie das Programm beenden!");
			alert.showAndWait();
			return false;
		}
		return true;
	}
}
