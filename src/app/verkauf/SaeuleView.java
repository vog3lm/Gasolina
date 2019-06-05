package app.verkauf;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import app.Loadable;
import app.settings.Settings;
import app.kraftstoff.KraftstoffbestandRecord;
import app.personal.PersonalRecord;
import app.waren.WarenbestandRecord;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
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
import javafx.scene.layout.Region;
import javafx.util.Callback;

public class SaeuleView extends Loadable<AnchorPane> {
	
	private final String id;
	
	private final String layout = "Saeule.fxml";
	
	@FXML
	private AnchorPane saeule;
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
		onLoad(layout,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		/**/
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
		verkauf_liste.setRowFactory(createRowListener());
		/**/
		verkauf_kraftstoff.setOnAction(event -> {createKraftstoffVerkauf();});
		verkauf_waren.setOnAction(event -> {createWarenVerkauf();});
		verkauf_buchen.setOnAction(event -> {createVerkaufBuchen();});	
	}
	
	private Callback<TableView<VerkaufRecord>,TableRow<VerkaufRecord>> createRowListener() {
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
        remove.setOnAction(event -> {table.getItems().remove(row.getIndex());});
        menu.getItems().addAll(remove);
        return menu;
	}
	

	
	private void createWarenVerkauf() {
		Dialog<VerkaufRecord> dialog = new Dialog<VerkaufRecord>();
		dialog.setTitle("Warenverkauf");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Settings.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(true);
		/**/
		TextField bezeichnung = new TextField();
		TextField bestand = new TextField();
		bestand.setEditable(false);
		bestand.setPromptText("Bestand");
		TextField menge = new TextField();
		menge.setDisable(true);
		menge.setPromptText("Menge");
		menge.textProperty().addListener((ob, o, n) -> {

			if(!n.equals("")) {
			    try {Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){
			    	menge.setText(o);
			    	new VerkaufDialoge().createNurZahlen().showAndWait();
			    	return;
			    }
			}
			String bez = bezeichnung.getText();
			if(!menge.getText().equals("") && !bez.equals("")) {
				WarenbestandRecord ware = controller.serveWare(bez);
				try {
					float b = Float.parseFloat(ware.getMenge());
					if(b < Float.parseFloat(menge.getText())) {
						pane.lookupButton(ButtonType.OK).setDisable(true);
						new VerkaufDialoge().createVorratNichtAusreichend(bez).showAndWait();
					} else if(b == 0) {
						pane.lookupButton(ButtonType.OK).setDisable(true);
						new VerkaufDialoge().createVorratVerbraucht(bez).showAndWait();
					} else {
						pane.lookupButton(ButtonType.OK).setDisable(false);
					}
				} catch (NumberFormatException | NullPointerException e){
					pane.lookupButton(ButtonType.OK).setDisable(true);
					new VerkaufDialoge().createNurZahlen().showAndWait();
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
			WarenbestandRecord ware = controller.serveWare(n);
			if(null == ware) {
				warennummer.setText("");
				preis.setText("");
				einheit.setText("");
				bestand.setText("");
				/* menge.setText(""); */
				pane.lookupButton(ButtonType.OK).setDisable(true);
				menge.setDisable(true);
			} else {
				warennummer.setText(ware.getWarennummer());
				preis.setText(ware.getPreis());
				einheit.setText(ware.getEinheit());
				menge.setDisable(false);
				bestand.setText(ware.getMenge());
				if(!menge.getText().equals("") && !bezeichnung.getText().equals("")) {
					try {
						Float b = Float.parseFloat(ware.getMenge());
						if(b < Float.parseFloat(menge.getText())) {
							pane.lookupButton(ButtonType.OK).setDisable(true);
							new VerkaufDialoge().createVorratNichtAusreichend(bezeichnung.getText()).showAndWait();
						} else if(b == 0) {
							pane.lookupButton(ButtonType.OK).setDisable(true);
							new VerkaufDialoge().createVorratVerbraucht(bezeichnung.getText()).showAndWait();
						} else {
							pane.lookupButton(ButtonType.OK).setDisable(false);
						}
					} catch (NumberFormatException | NullPointerException e){
						pane.lookupButton(ButtonType.OK).setDisable(true);
						new VerkaufDialoge().createNurZahlen().showAndWait();
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
		grid.add(new Label("Bestand:"), 0, 3);
		grid.add(bestand, 1, 3);
		grid.add(new Label("Einheit:"), 0, 4);
		grid.add(einheit, 1, 4);
		grid.add(new Label("Preis:"), 0, 5);
		grid.add(preis, 1, 5);
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
		    	controller.commitWare(record);
		    }
		    return null;
		});
		dialog.showAndWait();
	}
	
	private void createKraftstoffVerkauf() {
		/* https://code.makery.ch/blog/javafx-dialogs-official/ */
		Dialog<VerkaufSimulation> dialog = new Dialog<VerkaufSimulation>();
		dialog.setTitle("Kraftstoffverkauf");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Settings.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		ButtonType button = new ButtonType("Simulieren",ButtonData.YES);
		pane.getButtonTypes().addAll(ButtonType.CANCEL,button);
		pane.lookupButton(button).setDisable(true);
		/**/
		TextField bezeichnung = new TextField();
		TextField bestand = new TextField();
		bestand.setEditable(true);
		bestand.setPromptText("Bestand");
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
			KraftstoffbestandRecord ware = controller.serveKraftstoff(n);
			if(null == ware) {
				warennummer.setText("");
				preis.setText("");
				einheit.setText("");
				bestand.setText("");
				pane.lookupButton(button).setDisable(true);
			} else {
				warennummer.setText(ware.getWarennummer());
				preis.setText(ware.getPreis());
				einheit.setText(ware.getEinheit());
				bestand.setText(ware.getMenge());
				if("".equals(bezeichnung.getText())) {
					pane.lookupButton(button).setDisable(true);
				} else if("0.0".equals(bestand.getText()) || "0".equals(bestand.getText())) {
					pane.lookupButton(button).setDisable(true);
					new VerkaufDialoge().createVorratVerbraucht(bezeichnung.getText()).showAndWait();
				} else {
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
		grid.add(new Label("Bestand:"), 0, 3);
		grid.add(bestand, 1, 3);
		grid.add(new Label("Einheit:"), 0, 4);
		grid.add(einheit, 1, 4);
		grid.add(new Label("Preis:"), 0, 5);
		grid.add(preis, 1, 5);
		/**/
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
		    if (submit.getButtonData() == ButtonData.YES) {
		    	VerkaufRecord record =  new VerkaufRecord(-1
		    			,warennummer.getText()
		    			,bezeichnung.getText()
		    			,preis.getText()
		    			,"EUR"
		    			,"0"
		    			,einheit.getText()
		    			,"0");
		    	return controller.onSimulate(this,record);
		    }
		    return null;
		});
		dialog.showAndWait().ifPresent(s -> {
			verkauf_liste.getItems().add(s.getRow());
			verkauf_kraftstoff.setText("Stop");
			/* TODO: delete click row listener */
			verkauf_kraftstoff.setOnAction(event -> { s.interrupt(); });
			s.start();
		});
	}

	void destroyKraftstoffVerkauf(VerkaufRecord record) {
		/* TODO: set click row listener
		 * item.onCreateRowListener() */
		verkauf_kraftstoff.setText("Kraftstoff");
		verkauf_kraftstoff.setOnAction(e -> {createKraftstoffVerkauf();});
		Double summe = Double.parseDouble(record.getSumme());
		summe = summe + Double.parseDouble(verkauf_total.getText());
		summe = Math.round(summe * Math.pow(10, 2)) / Math.pow(10, 2);
		verkauf_total.setText(summe+"");
		controller.commitKraftstoff(record);
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
		String benutzer = Settings.getInstance().getBenutzer();
		String message = "Max Maier Tankstelle\n\n" + 
			"Belegnummer: "+belegnummer+"\n" + 
			"Datum: "+datum+" "+uhrzeit+"\n" + 
			"Mitarbeiter: "+benutzer+"\n\n";
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
		pane.getStylesheets().add(Settings.getInstance().getDesign());
		pane.setMinHeight(Region.USE_PREF_SIZE);
		alert.setTitle("Print Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.getButtonTypes().setAll(new ButtonType("Drucken",ButtonData.YES), ButtonType.OK, ButtonType.CANCEL);	
		alert.setResultConverter(submit -> {
		    if(submit.getButtonData() == ButtonData.YES){controller.onVerkaufDrucken(msg);}
		    controller.onVerkaufBuchen(posten);
		    return null;
		});
		alert.showAndWait();
	}
	
	@Override
	protected AnchorPane show() {return saeule;}
	
	String getTitle() {return id;}
	
	void onRefresh() { verkauf_liste.refresh(); }
	
	int getCount() { return verkauf_liste.getItems().size(); }
}
