package app.verkauf;

import app.kraftstoff.KraftstoffbestandRecord;
import javafx.scene.control.TableView;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class VerkaufSimulation extends Thread {
  
	private VerkaufRecord row;
	private TableView<VerkaufRecord> table;
	private boolean running = true;
	private KraftstoffbestandRecord ware;
	private Float compare;
	private Float preis;
	
	VerkaufSimulation(KraftstoffbestandRecord ware, VerkaufRecord row, TableView<VerkaufRecord> table){
		this.row = row;
		this.table = table;
		this.ware = ware;
		try {
			compare = Float.parseFloat(this.ware.getMenge());
			preis = Float.parseFloat(this.ware.getPreis());
		} catch (NumberFormatException | NullPointerException e){
			compare = 0f;
			preis = 0f;
		}
		
	}
	
	@Override
    public void run() {
		table.getItems().add(row);
		int menge = 0;
		try {
			while(this.running) {
				this.row.setMenge(menge+++"");
				Double summe = Math.round(preis*menge * Math.pow(10, 2)) / Math.pow(10, 2);
				this.row.setSumme(summe+"");
				this.table.refresh();
				if(compare < menge) {
					/* kill selling if tank got empty */
					this.interrupt();
				}
				super.sleep(1000);
			}
		}catch(InterruptedException e) {}
	}
}