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
	
	void onBestandEdit(int index, String id, String value) {
		KraftstoffbestandRecord record = bestand.onRead(index);
		if(id.equals("bezeichnung")) { record.setBezeichnung(value); }
		else if(id.equals("menge")) { record.setMenge(value); }
		else if(id.equals("preis")) { record.setPreis(value); }
		view.onRefresh();
	}
		
	void onBestellungEdit(int index, String id, String value) {
		KraftstoffbestellungenRecord record = bestellungen.onRead(index);
		if(id.equals("bezeichnung")) { record.setBezeichnung(value); }
		else if(id.equals("menge")) { record.setMenge(value); }
		else if(id.equals("preis")) { record.setPreis(value); }
		else if(id.equals("lieferdatum")) { record.setLieferdatum(value); }
		view.onRefresh();
	}
	
	void onBestellen(KraftstoffbestellungenRecord bestellung) {
		view.setIndex(KraftstoffView.BESTELLUNGEN);		
		bestellungen.onCreate(bestellung);
		view.onRefresh();
	}
	
	void onBuchen(KraftstoffbestellungenRecord lieferung) {		
		KraftstoffbestandRecord vorrat;
		int index = bestand.getIndex(lieferung.getBezeichnung());
		if(-1 == index) {
			// TODO new Bestand for new gas
			index = bestand.onCreate(new KraftstoffbestandRecord(-1
					,"warennummer"
					,lieferung.getBezeichnung()
					,"einheit"
					,"menge"
					,lieferung.getPreis()
					,lieferung.getWaehrung()
					,"tank"
					,"1000"));
		}
		vorrat = bestand.onRead(index);
		KraftstoffbestellungenRecord bestellung = bestellungen.onRead(lieferung.getIndex());
		try {
			float preis = Float.parseFloat(lieferung.getPreis());
			float bestandsmenge = Float.parseFloat(vorrat.getMenge());
			float liefermenge = Float.parseFloat(lieferung.getMenge());
			if("".equals(lieferung.getLieferdatum())) {
		    	bestandsmenge = bestandsmenge + liefermenge;
			} else {
		    	bestandsmenge = Float.parseFloat(vorrat.getMenge()) + liefermenge;
		     	bestellungen.onCreate(lieferung.setMenge((Float.parseFloat(bestellung.getMenge()) - liefermenge)+""));
		    }
			vorrat.setMenge(bestandsmenge+"");
			bestellungen.onDelete(lieferung.getIndex());
//		 	TODO : ausgaben.onCreate();
	    }catch (NumberFormatException | NullPointerException e){
	    	System.out.println("kraftstoff teilmenge buchen: oooops");
	    }
		view.onRefresh();
	}
	
	@Override
	public boolean destroy() {
		bestand.onCommit();
		bestellungen.onCommit();
		return true;
	}
}
