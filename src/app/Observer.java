package app;

import java.util.ArrayList;

import app.command.Commands;
import app.personal.Anmelden;
import app.personal.PersonalRecord;
import app.style.Styleable;

public class Observer implements Anmelden, Styleable {

	private ArrayList<Anmelden> anmelden = new ArrayList<Anmelden>();
	private ArrayList<Styleable> styleables = new ArrayList<Styleable>();
	
	public void onRegister(Anmelden anmelden) {this.anmelden.add(anmelden);}
	
	public void onRegister(Styleable styleable) {this.styleables.add(styleable);}

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
		settings.getCommander().onExecute(Commands.ANMELDEN);
	}

	@Override
	public void onStyle(String design) {
		for(Styleable styleable : this.styleables) {
			styleable.onStyle(design);
		}
	}
	
}
