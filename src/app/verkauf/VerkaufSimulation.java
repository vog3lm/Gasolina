package app.verkauf;

import javafx.scene.control.TableView;

public class VerkaufSimulation extends Thread {
  
	private VerkaufRecord row;
	private TableView<VerkaufRecord> table;
	private boolean running = true;
	
	VerkaufSimulation(VerkaufRecord row, TableView<VerkaufRecord> table){
		this.row = row;
		this.table = table;
	}
	
	@Override
    public void run() {
		int menge = 0;
		try {
			while(this.running) {
				this.row.setMenge(menge+++"");
				this.table.refresh();
				super.sleep(1000);
			}
		}catch(InterruptedException e) {}
	}
}