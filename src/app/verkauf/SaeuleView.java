package app.verkauf;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import app.Zustand;
import app.fxml.Loader;
import app.kraftstoff.KraftstoffbestandRecord;
import app.personal.PersonalRecord;
import app.waren.WarenbestandRecord;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Callback;

public class SaeuleView implements Initializable {
	
	private String id = "no id";
	
	@FXML
	private AnchorPane verkauf_wrapper;
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
	private Button verkauf_kraftstoff;
	@FXML
	private Button verkauf_waren;
	@FXML
	private Button verkauf_buchen;
	
	private VerkaufController controller;
	
	SaeuleView(VerkaufController controller, String id) {
		this.controller = controller;
		this.id = id;
		new Loader().onLoadInitializable(Loader.SAEULE,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		verkauf_warennummer.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("warennummer"));
		verkauf_bezeichnung.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("bezeichnung"));
		verkauf_preis.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("preis"));
		verkauf_menge.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("menge"));
		verkauf_menge.setCellFactory(TextFieldTableCell.forTableColumn());
		verkauf_menge.setOnEditCommit((cell) -> {
			cell.getTableView().getItems().get(cell.getTablePosition().getRow()).setMenge(cell.getNewValue());
		});
		verkauf_einheit.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("einheit"));
		verkauf_summe.setCellValueFactory(new PropertyValueFactory<VerkaufRecord, String>("summe"));
		/**/
		verkauf_liste.setRowFactory(this.createRowListener());
		/**/
		verkauf_kraftstoff.setOnAction(event -> {createKraftstoffVerkauf();});
		verkauf_waren.setOnAction(event -> {createWarenVerkauf();});
		verkauf_buchen.setOnAction(event -> {createVerkaufBuchen();});	
	}
	
	public Callback<TableView<VerkaufRecord>,TableRow<VerkaufRecord>> createRowListener() {
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
        remove.setOnAction(event -> { table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(remove);
        return menu;
	}
	
	private void createWarenVerkauf() {
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
		menge.setDisable(true);
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
				WarenbestandRecord ware = controller.hasWare(bezeichnung.getText());
				try {
					if(Float.parseFloat(ware.getMenge()) < Float.parseFloat(menge.getText())) {
						pane.lookupButton(ButtonType.OK).setDisable(true);
					} else {
						pane.lookupButton(ButtonType.OK).setDisable(false);
					}
				} catch (NumberFormatException | NullPointerException e){
					pane.lookupButton(ButtonType.OK).setDisable(true);
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
			WarenbestandRecord ware = controller.hasWare(n);
			if(null == ware) {
				warennummer.setText("");
				preis.setText("");
				einheit.setText("");
				/* menge.setText(""); */
				pane.lookupButton(ButtonType.OK).setDisable(true);
				menge.setDisable(true);
			} else {
				warennummer.setText(ware.getWarennummer());
				preis.setText(ware.getPreis());
				einheit.setText(ware.getEinheit());
				menge.setDisable(false);
				if(!menge.getText().equals("") && !bezeichnung.getText().equals("")) {
					try {
						if(Float.parseFloat(ware.getMenge()) < Float.parseFloat(menge.getText())) {
							pane.lookupButton(ButtonType.OK).setDisable(true);
						} else {
							pane.lookupButton(ButtonType.OK).setDisable(false);
						}
					} catch (NumberFormatException | NullPointerException e){
						pane.lookupButton(ButtonType.OK).setDisable(true);
					}
				}
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
		    	summe = summe + Double.parseDouble(verkauf_total.getText());
		    	summe = Math.round(summe * Math.pow(10, 2)) / Math.pow(10, 2);
		    	verkauf_total.setText(summe+"");
		    	VerkaufRecord record = new VerkaufRecord(-1,warennummer.getText(),bez_string,preis.getText(),"EUR",men_string,einheit.getText(),summe+"");
		    	verkauf_liste.getItems().add(record);
		    }
		    return null;
		});
		dialog.showAndWait();
	}
	
	private void createKraftstoffVerkauf() {
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
		zapfsaeule.setText(id); 
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
			KraftstoffbestandRecord ware = controller.hasKraftstoff(n);
			if(null == ware) {
				warennummer.setText("");
				preis.setText("");
				einheit.setText("");
				pane.lookupButton(button).setDisable(true);
			} else {
				warennummer.setText(ware.getWarennummer());
				preis.setText(ware.getPreis());
				einheit.setText(ware.getEinheit());
				if(!bezeichnung.getText().equals("")) {
					pane.lookupButton(button).setDisable(false);
				}
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
		    	VerkaufRecord record = new VerkaufRecord(-1
		    			,warennummer.getText()
		    			,bezeichnung.getText()
		    			,preis.getText()
		    			,"EUR"
		    			,"0"
		    			,einheit.getText()
		    			,"0");
		    	KraftstoffbestandRecord ware = controller.hasKraftstoff(bezeichnung.getText());
				VerkaufSimulation simulation = new VerkaufSimulation(ware,record,verkauf_liste);
				verkauf_kraftstoff.setText("Stop");
				/* TODO: delete click row listener */
				verkauf_kraftstoff.setOnAction(event -> {
					simulation.interrupt();
					/* TODO: set click row listener
					 * item.onCreateRowListener() */
					verkauf_kraftstoff.setText("Kraftstoff");
					verkauf_kraftstoff.setOnAction(e -> {createKraftstoffVerkauf();});
					Double summe = Double.parseDouble(record.getSumme());
					summe = summe + Double.parseDouble(verkauf_total.getText());
			    	summe = Math.round(summe * Math.pow(10, 2)) / Math.pow(10, 2);
			    	verkauf_total.setText(summe+"");
				});
				simulation.start();
		    }
		    return null;
		});
		dialog.showAndWait();
	}

	private void createVerkaufBuchen() {
		ObservableList<VerkaufRecord> posten = this.verkauf_liste.getItems();
		if(0 == posten.size()) { return; }
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
		/* must be final to be accessalbe in result converter */
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
		    if(submit.getButtonData() == ButtonData.YES){controller.onDrucken(msg);}
		    controller.onBuchen(posten);
		    return null;
		});
		alert.showAndWait();
	}
	
	AnchorPane getView() { return verkauf_wrapper; }
	
	void onRefresh() { verkauf_liste.refresh(); }
	
	int getCount() { return verkauf_liste.getItems().size(); }
}
