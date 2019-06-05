package app;

import java.util.ArrayList;

import app.personal.Anmelden;
import app.personal.PersonalRecord;
import app.settings.Settings;

public class Observer implements Anmelden {

	private ArrayList<Anmelden> anmelden = new ArrayList<Anmelden>();
	
	public void onRegister(Anmelden anmelden) {this.anmelden.add(anmelden);}

	@Override
	public void onAnmelden(PersonalRecord benutzer) {
		for(Anmelden anmelden : this.anmelden) {
			anmelden.onAnmelden(benutzer);
		}
	}

	@Override
	public void onAbmelden() {
		for(Anmelden anmelden : this.anmelden) {
			anmelden.onAbmelden();
		}
	}
	
}
