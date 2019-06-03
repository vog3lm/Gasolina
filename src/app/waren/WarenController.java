package app.waren;

import app.controlling.AusgabenRecord;
import app.controlling.AusgabenTable;
import app.waren.WarenbestellungenRecord;
import app.waren.WarenbestellungenTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.Controller;
import app.Settings;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class WarenController implements Controller<WarenView> {
		
	private WarenbestandTable bestand = new WarenbestandTable();
	
	private WarenbestellungenTable bestellungen = new WarenbestellungenTable();
	
	private AusgabenTable ausgaben = new AusgabenTable();
	
	private WarenView view = new WarenView();
		
	void onBestandEdit(int index, String id, String value) {
		WarenbestandRecord record = bestand.onRead(index);
		if(id.equals("bezeichnung")) { record.setBezeichnung(value); }
		else if(id.equals("einheit")) { record.setEinheit(value); }
		else if(id.equals("menge")) { record.setMenge(value); }
		else if(id.equals("preis")) { record.setPreis(value); }
		else if(id.equals("kategorie")) { record.setKategorie(value); }
	//	view.onRefresh();
	}
	
	void onBestandDelete(int index) {
		bestand.onDelete(index);
		//	view.onRefresh();
	}
	
	void onBestandBestellen(int index) {
		WarenbestandRecord record = bestand.onRead(index);
    	new WarenDialoge().createBestandBestellenDialog(record).showAndWait().ifPresent(bestellung -> {
    		//	view.setIndex(WarenView.BESTELLUNGEN);
    		bestellungen.onCreate(bestellung);
    		//		view.onRefresh();
		});
	}
	
	void onBestellungAdd() {
    	new WarenDialoge().createBestellungAddDialog().showAndWait().ifPresent(bestellung -> {
    		//	view.setIndex(WarenView.BESTELLUNGEN);
    		bestellungen.onCreate(bestellung);
    		//	view.onRefresh();
		});
	}
	
	void onBestellungEdit(int index, String id, String value) {
		WarenbestellungenRecord record = bestellungen.onRead(index);
		if(id.equals("bezeichnung")) { record.setBezeichnung(value); }
		else if(id.equals("menge")) { record.setMenge(value); }
		else if(id.equals("preis")) { record.setPreis(value); }
		else if(id.equals("lieferdatum")) { record.setLieferdatum(value); }
		//view.onRefresh();
	}
	
	void onBestellungDelete(int index) {
		bestellungen.onDelete(index);
		//view.onRefresh();
	}

	void onBestellungBuchen(int index) {
		WarenbestellungenRecord bestellung = bestellungen.onRead(index);
		new WarenDialoge().createBuchenDialog(bestellung).showAndWait().ifPresent(lieferung -> {
			//	view.setIndex(WarenView.BESTAND);
			WarenbestandRecord vorrat;
			int bidx = bestand.getIndex(lieferung.getBezeichnung());
			if(-1 == bidx) {
				bidx = bestand.onCreate(new WarenbestandRecord(-1
						,"warennummer"
						,lieferung.getBezeichnung()
						,"einheit"
						,"menge"
						,lieferung.getPreis()
						,lieferung.getWaehrung()
						,"kategorie"));
			}
			vorrat = bestand.onRead(bidx);
			try {
				float preis = Float.parseFloat(lieferung.getPreis());
				float bestandsmenge = Float.parseFloat(vorrat.getMenge());
				float liefermenge = Float.parseFloat(lieferung.getMenge());
				bestellungen.onDelete(lieferung.getIndex());
				if("".equals(lieferung.getLieferdatum())) {
			    	bestandsmenge = bestandsmenge + liefermenge;
				} else {
			    	bestandsmenge = Float.parseFloat(vorrat.getMenge()) + liefermenge;
			     	bestellungen.onCreate(lieferung.setMenge((Float.parseFloat(bestellung.getMenge()) - liefermenge)+""));
				}
				vorrat.setMenge(bestandsmenge+"");	
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				DateFormat timeFormat = new SimpleDateFormat("hh:mm");
				Date date = new Date();
				ausgaben.onCreate(new AusgabenRecord(-1,""
						,vorrat.getWarennummer()
						,vorrat.getBezeichnung()
						,lieferung.getPreis()
						,lieferung.getMenge()
						,vorrat.getEinheit()
						,(preis*liefermenge)+""
						,dateFormat.format(date)
						,timeFormat.format(date)
						,Settings.getInstance().getBenutzer().getBenutzername()));
			}catch (NumberFormatException | NullPointerException e){
		    	System.out.println("kraftstoff teilmenge buchen: oooops");
		    }
			//view.onRefresh();
        });
	}
	
	@Override
	public boolean destroy() {
		bestand.onCommit();
		bestellungen.onCommit();
		ausgaben.onCommit();
		return true;
	}

	@Override
	public WarenView show() {return view;}
}
