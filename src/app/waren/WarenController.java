package app.waren;

import app.controlling.AusgabenTable;

import app.waren.WarenbestellungenRecord;
import app.waren.WarenbestellungenTable;
import app.Lifecycle;

import javafx.scene.control.TableColumn;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class WarenController implements Lifecycle {
		
	private WarenbestandTable bestand = new WarenbestandTable();
	
	private WarenbestellungenTable bestellungen = new WarenbestellungenTable();
	
	private AusgabenTable ausgaben = new AusgabenTable();
	
	private WarenView view = new WarenView(this);
	
	public WarenController(int tab) {
		view.setIndex(tab);
		view.setBestand(bestand.onRead());
		view.setBestellungen(bestellungen.onRead());
	}
	
	void onBestandEdit(int index, String id, String value) {
		WarenbestandRecord record = bestand.onRead(index);
		if(id.equals("bezeichnung")) { record.setBezeichnung(value); }
		else if(id.equals("einheit")) { record.setEinheit(value); }
		else if(id.equals("menge")) { record.setMenge(value); }
		else if(id.equals("preis")) { record.setPreis(value); }
		else if(id.equals("kategorie")) { record.setKategorie(value); }
		view.onRefresh();
	}
	

	void onBestellungEdit(int index, String id, String value) {
		WarenbestellungenRecord record = bestellungen.onRead(index);
		if(id.equals("bezeichnung")) { record.setBezeichnung(value); }
		else if(id.equals("menge")) { record.setMenge(value); }
		else if(id.equals("preis")) { record.setPreis(value); }
		else if(id.equals("lieferdatum")) { record.setLieferdatum(value); }
		view.onRefresh();
	}
	
	void onBestellen(WarenbestellungenRecord record) {
		/* TODO: process result */
		// bestellungen.onCreate(r);
		// bestellungen_liste.refresh();
		view.setIndex(WarenView.BESTELLUNGEN);
		System.out.println("bestellen");
	}

	void onBuchen(WarenbestandRecord record) {
		view.setIndex(WarenView.BESTAND);
		System.out.println("buchen");
	}
	
	@Override
	public boolean destroy() {
		bestand.onCommit();
		bestellungen.onCommit();
		return true;
	}
}
