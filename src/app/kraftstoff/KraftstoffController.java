package app.kraftstoff;

import app.controlling.AusgabenTable;
import app.Lifecycle;

import javafx.scene.control.TableColumn;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class KraftstoffController implements Lifecycle {
		
	private KraftstoffbestandTable bestand = new KraftstoffbestandTable();
	
	private KraftstoffbestellungenTable bestellungen = new KraftstoffbestellungenTable();
	
	private AusgabenTable ausgaben = new AusgabenTable();
	
	private KraftstoffView view = new KraftstoffView(this);

	public KraftstoffController(int tab) {
		view.setIndex(tab);
		view.setBestand(bestand.onRead());
		view.setBestellungen(bestellungen.onRead());
	}
	
	void onBestandEdit(TableColumn.CellEditEvent<KraftstoffbestandRecord, String> cell, String id) {
		KraftstoffbestandRecord row = cell.getTableView().getItems().get(cell.getTablePosition().getRow());
		if(id.equals("bezeichnung")) { row.setBezeichnung(cell.getNewValue()); }
		else if(id.equals("menge")) { row.setMenge(cell.getNewValue()); }
		else if(id.equals("preis")) { row.setPreis(cell.getNewValue()); }
	}
		
	void onBestellungEdit(TableColumn.CellEditEvent<KraftstoffbestellungenRecord, String> cell, String id) {
		KraftstoffbestellungenRecord row = cell.getTableView().getItems().get(cell.getTablePosition().getRow());
		if(id.equals("bezeichnung")) { row.setBezeichnung(cell.getNewValue()); }
		else if(id.equals("menge")) { row.setMenge(cell.getNewValue()); }
		else if(id.equals("preis")) { row.setPreis(cell.getNewValue()); }
		else if(id.equals("lieferdatum")) { row.setLieferdatum(cell.getNewValue()); }
	}
	
	void onBestellen(KraftstoffbestellungenRecord bestellung) {
		view.setIndex(KraftstoffView.BESTELLUNGEN);
		System.out.println("hello bestellen");
		/* TODO: process result */
		// bestellungen.onCreate(r);
		// bestellungen_list.refresh();
	}
	
	@Override
	public boolean destroy() {
		bestand.onCommit();
		bestellungen.onCommit();
		return true;
	}
}
