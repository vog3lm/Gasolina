package app.waren;

import app.controlling.AusgabenTable;
import app.waren.WarenbestellungenRecord;
import app.waren.WarenbestellungenTable;
import app.Lifecycle;

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
	
	void onBestandDelete(int index) {
		bestand.onDelete(index);
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
	
	void onBestellungDelete(int index) {
		bestellungen.onDelete(index);
		view.onRefresh();
	}
	
	void onBestellen(WarenbestellungenRecord bestellung) {
		view.setIndex(WarenView.BESTELLUNGEN);
		bestellungen.onCreate(bestellung);
		view.onRefresh();
	}

	void onBuchen(WarenbestellungenRecord lieferung) {
		view.setIndex(WarenView.BESTAND);
		WarenbestandRecord vorrat;
		int index = bestand.getIndex(lieferung.getBezeichnung());
		if(-1 == index) {
			index = bestand.onCreate(new WarenbestandRecord(-1
					,"warennummer"
					,lieferung.getBezeichnung()
					,"einheit"
					,"menge"
					,lieferung.getPreis()
					,lieferung.getWaehrung()
					,"kategorie"));
		}
		vorrat = bestand.onRead(index);
		WarenbestellungenRecord bestellung = bestellungen.onRead(lieferung.getIndex());
		try {
			float preis = Float.parseFloat(lieferung.getPreis());
			float bestandsmenge = Float.parseFloat(vorrat.getMenge());
			float liefermenge = Float.parseFloat(lieferung.getMenge());
			
			
			System.out.println("delete: "+lieferung.getIndex());
			for(WarenbestellungenRecord r : bestellungen.onRead()) {
				System.out.println(r.getIndex()+" "+r.getBezeichnung());
			}
			
			
			bestellungen.onDelete(lieferung.getIndex());
			if("".equals(lieferung.getLieferdatum())) {
		    	bestandsmenge = bestandsmenge + liefermenge;
			} else {
		    	bestandsmenge = Float.parseFloat(vorrat.getMenge()) + liefermenge;
		     	int i = bestellungen.onCreate(lieferung.setMenge((Float.parseFloat(bestellung.getMenge()) - liefermenge)+""));
			}
			vorrat.setMenge(bestandsmenge+"");
//		 	TODO : ausgaben.onCreate();			
		}catch (NumberFormatException | NullPointerException e){
	    	System.out.println("kraftstoff teilmenge buchen: oooops");
	    }
		view.onRefresh();
	}
	
	@Override
	public boolean destroy() {
	//	bestand.onCommit();
	//	bestellungen.onCommit();
		return true;
	}
}
