package app.verkauf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import app.Zustand;
import app.verkauf.VerkaufRecord;
import app.Controller;
import app.waren.WarenbestandRecord;
import app.waren.WarenbestandTable;
import app.kraftstoff.KraftstoffbestandRecord;
import app.kraftstoff.KraftstoffbestandTable;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	
	WarenbestandRecord hasWare(String bezeichnung) {
		int index = waren.getIndex(bezeichnung);
		if(-1 == index) { return null; }
		else { return waren.onRead(index); }
	}
	
	KraftstoffbestandRecord hasKraftstoff(String bezeichnung) {
		int index = kraftstoffe.getIndex(bezeichnung);
		if(-1 == index) { return null; }
		else { return kraftstoffe.onRead(index); }
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
