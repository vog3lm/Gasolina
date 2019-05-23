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
import app.fxml.Loader;
import app.personal.PersonalRecord;
import app.verkauf.VerkaufRecord;
import app.Controller;
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
import javafx.fxml.Initializable;
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
public class VerkaufController implements Controller {
		
	private KraftstoffbestandTable kraftstoffe = new KraftstoffbestandTable();
	
	private WarenbestandTable waren = new WarenbestandTable();
	
	private VerkaufView view = new VerkaufView(this);
	
	public VerkaufController(int tab) {
		view.setIndex(tab);
	}
	
	void onSimulate(VerkaufRecord record) {
	//	verkauf_liste.getItems().add(record);
	//	KraftstoffbestandRecord bestand = kraftstoffe.onRead(kraftstoffe.getIndex(record.getBezeichnung()));
	//	VerkaufSimulation simulation = new VerkaufSimulation(bestand,record,this.verkauf_liste);
	//	verkauf_kraftstoff.setText("Stop");
		/* TODO: delete click row listener */
	//	verkauf_kraftstoff.setOnAction(event -> {
	//		simulation.interrupt();
			/* TODO: set click row listener
			 * item.onCreateRowListener() */
	//		verkauf_kraftstoff.setText("Kraftstoff");
	//		verkauf_kraftstoff.setOnAction(e -> {onCreateKraftstoffVerkauf();});
	//	});
	//	simulation.start();
	}
	
	void onDrucken(String message) {
		try {
			String path = Zustand.getInstance().getPrintUrl();
			BufferedWriter writer = new BufferedWriter(new FileWriter(path+"beleg.txt"));
	        writer.write(message);
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void onBuchen(ObservableList<VerkaufRecord> records) {
		/*
		ObservableList<VerkaufRecord> journal = this.journal_liste.getItems();
		float umsatz = Float.parseFloat(journal_total.getText());
		for(VerkaufRecord record : posten) {
			record.setBelegnummer(belegnummer);
			record.setDatum(datum);
			record.setUhrzeit(uhrzeit);
			record.setMitarbeiter(benutzer.getPersonalnummer());
			journal.add(record);
			umsatz = umsatz + Float.parseFloat(record.getSumme());
		}
		verkauf_total.setText("0.00");
		journal_total.setText(Math.round(umsatz * Math.pow(10, 2)) / Math.pow(10, 2)+"");
		this.verkauf_liste.setItems(FXCollections.observableList(new ArrayList<VerkaufRecord>()));
		*/
	}


	
	@Override
	public boolean destroy() {
		/*	journal.onCommit() */
		if(0 != view.getCount()) {
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
