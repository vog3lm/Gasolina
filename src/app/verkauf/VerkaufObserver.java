package app.verkauf;

import java.util.ArrayList;

class VerkaufObserver {

	private ArrayList<VerkaufSimulation> simulations = new ArrayList<VerkaufSimulation>();
	
	void onRegister(VerkaufSimulation simulation) { simulations.add(simulation); }
	
	void onUpdate(int sid, String bezeichnung) {
		for(VerkaufSimulation s : simulations) {
			s.onUpdate(sid,bezeichnung);
		}
	}
	
	void onUnregister(VerkaufSimulation simulation) { simulations.remove(simulation); }
}
