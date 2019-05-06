package app.anmelden;

import app.personal.PersonalRecord;

public interface Anmelden {

	public void onAnmelden(PersonalRecord benutzername);
	public void onAbmelden();
	
}
