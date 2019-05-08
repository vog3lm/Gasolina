package app.verkauf;

import app.kraftstoff.KraftstoffbestandRecord;
import javafx.scene.control.TableView;

public class VerkaufSimulation extends Thread {
  
	private VerkaufRecord row;
	private TableView<VerkaufRecord> table;
	private boolean running = true;
	private KraftstoffbestandRecord bestand; 
	
	VerkaufSimulation(KraftstoffbestandRecord bestand, VerkaufRecord row, TableView<VerkaufRecord> table){
		this.row = row;
		this.table = table;
		this.bestand = bestand;
	}
	
	@Override
    public void run() {
		int menge = 0;
		float compare = Float.parseFloat(this.bestand.getMenge());
		try {
			while(this.running) {
				this.row.setMenge(menge+++"");
				this.table.refresh();
				if(compare < menge) {
					/* kill selling if tank got empty */
					return;
				}
				super.sleep(1000);
			}
		}catch(InterruptedException e) {}
	}
}