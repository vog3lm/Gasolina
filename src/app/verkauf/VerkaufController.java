package app.verkauf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import app.settings.Settings;
import app.verkauf.VerkaufRecord;
import app.Controller;
import app.Datapool;
import app.command.Commands;
import app.waren.WarenbestandRecord;
import app.waren.WarenbestandTable;
import app.kraftstoff.KraftstoffbestandRecord;
import app.kraftstoff.KraftstoffbestandTable;

import javafx.collections.ObservableList;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class VerkaufController implements Controller {
		
	private KraftstoffbestandTable kraftstoffe;
	
	private WarenbestandTable waren;
	
	private VerkaufObserver observer = new VerkaufObserver();
	
	private VerkaufView view;
	
	VerkaufController(Datapool pool) {
		this.kraftstoffe = pool.aquireKraftstoffbestand();
		this.waren = pool.aquireWarenbestand();
		this.view = new VerkaufView()
			.onDecorate(new SaeuleView(this,"Säule 1").onShow())
			.onDecorate(new SaeuleView(this,"Säule 2").onShow())
			.onDecorate(new SaeuleView(this,"Säule 3").onShow())
			.onDecorate(new JournalView(this).onShow());
	}
	
	public void onStart(String command) {
		if(Commands.SAEULE1.equals(command)) {view.setIndex(0);}
		else if(Commands.SAEULE2.equals(command)) {view.setIndex(1);}
		else if(Commands.SAEULE3.equals(command)) {view.setIndex(2);}
		else if(Commands.JOURNAL.equals(command)) {view.setIndex(3);}
	}
	
	VerkaufSimulation onSimulate(SaeuleView view, VerkaufRecord verkauf) {
		int index = kraftstoffe.getIndex(verkauf.getBezeichnung());
		if(-1 == index) { return null; }
		else { 
			KraftstoffbestandRecord bestand = kraftstoffe.onRead(index);
			if(0 == Float.parseFloat(bestand.getMenge())) {
				return null;
			} else {
				return new VerkaufSimulation(bestand,observer,verkauf,view);
			}
		}
	}
	
	WarenbestandRecord serveWare(String bezeichnung) {
		int index = waren.getIndex(bezeichnung);
		if(-1 == index) { return null; }
		else { return waren.onRead(index); }
	}
	
	void commitWare(VerkaufRecord verkauf) {
		int index = waren.getIndex(verkauf.getBezeichnung());
		if(-1 != index) {
			WarenbestandRecord bestand = waren.onRead(index);
			bestand.setMenge((Float.parseFloat(bestand.getMenge())-Float.parseFloat(verkauf.getMenge()))+"");
			waren.onUpdate(index,bestand);
		}
	}
	
	KraftstoffbestandRecord serveKraftstoff(String bezeichnung) {
		int index = kraftstoffe.getIndex(bezeichnung);
		if(-1 == index) { return null; }
		else { return kraftstoffe.onRead(index); }
	}
	
	void commitKraftstoff(VerkaufRecord verkauf) {
		int index = kraftstoffe.getIndex(verkauf.getBezeichnung());
		if(-1 != index) {
			KraftstoffbestandRecord bestand = kraftstoffe.onRead(index);
			bestand.setMenge((Float.parseFloat(bestand.getMenge())-Float.parseFloat(verkauf.getMenge()))+"");
			kraftstoffe.onUpdate(index,bestand);
		}
	}
		
	void onVerkaufDrucken(String message) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Settings.getInstance().getPrintUrl()+"beleg.txt"));
	        writer.write(message);
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void onVerkaufBuchen(ObservableList<VerkaufRecord> records) {
		/* TODO
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

	void onJournalDelete(int index) {
		/* TODO */
	//	view.onRefresh();
	}
	
	@Override
	public boolean onDestroy() {
		/*	TODO journal.onCommit() */
	//	if(0 != view.getCount()) {
	//		new VerkaufDialoge().createOffeneVorgaenge();
	//		return false;
	//	} 
		return true;
	}
	
	public VerkaufView onShow() {return view;}
}
