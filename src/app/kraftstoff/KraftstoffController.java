package app.kraftstoff;

import app.controlling.AusgabenRecord;
import app.controlling.AusgabenTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.Controller;
import app.Datapool;
import app.command.Commands;
import app.settings.Settings;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class KraftstoffController implements Controller {
		
	private KraftstoffbestandTable bestand;
	
	private KraftstoffbestellungenTable bestellungen;
	
	private AusgabenTable ausgaben;
	
	private KraftstoffView view;
	
	KraftstoffController(Datapool pool){
		
		this.bestand = pool.aquireKraftstoffbestand();
		this.bestellungen = pool.aquireKraftstoffbestellungen();
		this.ausgaben = pool.aquireAusgaben();
		this.view = new KraftstoffView()
			.onDecorate(new BestandView(this,bestand.onRead()).onShow())
			.onDecorate(new BestellungenView(this,bestellungen.onRead()).onShow())
			.onDecorate(new TankView().onShow());
	}
	
	public void onStart(String command) {
		if(Commands.KRAFTSTOFF_BESTAND.equals(command)) {view.setIndex(0);}
		else if(Commands.KRAFTSTOFF_BESTELLUNGEN.equals(command)) {view.setIndex(1);}
		else if(Commands.KRAFTSTOFF_TANKS.equals(command)) {view.setIndex(2);}
	}
	
	void onBestandEdit(int index, String id, String value) {
		KraftstoffbestandRecord record = bestand.onRead(index);
		if(id.equals("bezeichnung")) { record.setBezeichnung(value); }
		else if(id.equals("menge")) { record.setMenge(value); }
		else if(id.equals("preis")) { record.setPreis(value); }
	//	view.onRefresh();
	}
	
	String onBestandAmpel(int index, String value) {
		KraftstoffbestandRecord item = bestand.onRead(index);
    	float kapazitaet = Float.parseFloat(item.getKapazitaet());
        float menge = Float.parseFloat(value);
        if(kapazitaet / 4 >= menge) {
        	return "bestand-traffic-red";
        } else if(kapazitaet / 2 >= menge) {
        	return "bestand-traffic-yellow";
        } else {
        	return "bestand-traffic-green";
        }
	}
	
	void onBestandDelete(int index) {
		bestand.onDelete(index);
	//	view.onRefresh();
	}
	
	void onBestandBestellen(int index) {
		KraftstoffbestandRecord record = bestand.onRead(index);
       	new KraftstoffDialoge().createBestandBestellenDialog(record).showAndWait().ifPresent(result -> {
    	//	view.setIndex(KraftstoffView.BESTELLUNGEN);		
    		bestellungen.onCreate(result);
    	//	view.onRefresh();
		});
	}
	
	void onBestellungAdd() {
    	new KraftstoffDialoge().createBestellungAddDialog().showAndWait().ifPresent(result -> {
    	//	view.setIndex(KraftstoffView.BESTELLUNGEN);		
    		bestellungen.onCreate(result);
    	//	view.onRefresh();
		});
	}
	
	void onBestellungEdit(int index, String id, String value) {
		KraftstoffbestellungenRecord record = bestellungen.onRead(index);
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
		KraftstoffbestellungenRecord bestellung = bestellungen.onRead(index);
		new KraftstoffDialoge().createBuchenDialog(bestellung).showAndWait().ifPresent(lieferung -> {
			KraftstoffbestandRecord vorrat;
			int bidx = bestand.getIndex(lieferung.getBezeichnung());
			if(-1 == bidx) {
				bidx = bestand.onCreate(new KraftstoffbestandRecord(-1
						,"warennummer"
						,lieferung.getBezeichnung()
						,"einheit"
						,"menge"
						,lieferung.getPreis()
						,lieferung.getWaehrung()
						,"tank"
						,"1000"));
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
						,Settings.getInstance().getBenutzer()));
		    }catch (NumberFormatException | NullPointerException e){
		    	System.out.println("kraftstoff teilmenge buchen: oooops");
		    }
			//view.onRefresh();
		});
	}
	
	@Override
	public boolean onDestroy() {
		bestand.onCommit();
		bestellungen.onCommit();
		ausgaben.onCommit();
		return true;
	}

	public KraftstoffView onShow() {return view;}
}
