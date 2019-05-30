package app.verkauf;

import java.util.Random;

import app.kraftstoff.KraftstoffbestandRecord;
import javafx.application.Platform;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class VerkaufSimulation extends Thread {
  
	private final int SID = new Random().nextInt();
	
	private VerkaufObserver observer;
	private VerkaufRecord row;
	private SaeuleView table;
	private boolean running = true;
	private Float compare;
	private int external = 0;
	private Float preis;
	private String bezeichnung;
	
	VerkaufSimulation(KraftstoffbestandRecord ware, VerkaufObserver observer, VerkaufRecord row, SaeuleView table){
		this.row = row;
		this.table = table;
		this.observer = observer;
		this.bezeichnung = ware.getBezeichnung();
		this.observer.onRegister(this);
		try {
			compare = Float.parseFloat(ware.getMenge());
			preis = Float.parseFloat(ware.getPreis());
		} catch (NumberFormatException | NullPointerException e){
			compare = 0f;
			preis = 0f;
		}
	}
	
	@Override
    public void run() {
		int menge = 0;
		try {
			while(this.running) {
				this.observer.onUpdate(SID,bezeichnung);
				this.row.setMenge(++menge+"");
				Double summe = Math.round(preis*menge * Math.pow(10, 2)) / Math.pow(10, 2);
				this.row.setSumme(summe+"");
				this.table.onRefresh();
				if(compare <= menge + external) { 
					new VerkaufDialoge().createVorratVerbraucht(bezeichnung);
					this.interrupt(); 
				}
				super.sleep(1000);
			}
		}catch(InterruptedException e) {}
		this.observer.onUnregister(this);
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
            	table.destroyKraftstoffVerkauf(row);
            }
        });
		
	}
	
	void onUpdate(int sid, String bezeichnung) {
		if(this.bezeichnung.equals(bezeichnung) && this.SID != sid) {
			external++;
		}
	}
	
	int getSid() { return this.SID; }
	
	String getBezeichnung() { return this.bezeichnung; }
	
	VerkaufRecord getRow() { return this.row; }
}