package app.personal;

import app.Zustand;
import app.Controller;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class BenutzerController implements Controller {
	
	private PersonalTable bestand = new PersonalTable();
	
	private PersonalRecord benutzer = Zustand.getInstance().getBenutzer();
	
	private BenutzerView view = new BenutzerView(this,benutzer);

	public void onSpeichern(String un, String vn, String nn, String pw) {
		benutzer.setBenutzername(un);
		benutzer.setVorname(vn);
		benutzer.setNachname(nn);
		benutzer.setPasswort(pw);
		bestand.onUpdate(benutzer.getIndex(),benutzer);
	}
	
	@Override
	public boolean destroy() {
		bestand.onCommit();
		return true;
	}
}
