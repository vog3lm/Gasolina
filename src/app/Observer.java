package app;

import java.util.ArrayList;

import app.personal.Anmelden;
import app.personal.PersonalRecord;

public class Observer implements Anmelden {

	private ArrayList<Anmelden> anmelden = new ArrayList<Anmelden>();
	
	public void onRegister(Anmelden anmelden) {this.anmelden.add(anmelden);}

	@Override
	public void onAnmelden(PersonalRecord benutzer) {
		Settings settings = Settings.getInstance();
		settings.setBenutzer(benutzer);
		for(Anmelden anmelden : this.anmelden) {
			anmelden.onAnmelden(benutzer);
		}
	}

	@Override
	public void onAbmelden() {
		Settings settings = Settings.getInstance();
		settings.setBenutzer(null);
		for(Anmelden anmelden : this.anmelden) {
			anmelden.onAbmelden();
		}
	// TODO	settings.getCommander().onExecute(Commands.ANMELDEN);
	}
	
}
