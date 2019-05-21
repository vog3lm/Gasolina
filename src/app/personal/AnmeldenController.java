package app.personal;

import app.Zustand;
import app.verkauf.VerkaufController;
import app.verkauf.VerkaufView;
import javafx.event.ActionEvent;

/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class AnmeldenController {
	
	private Anmelden anmelden;
	
	private PersonalTable bestand = new PersonalTable();
	
	private AnmeldenView view = new AnmeldenView(this);
	
	public AnmeldenController(Anmelden anmelden) {
		this.anmelden = anmelden;
	}
		
	void onAnmelden(String benutzername, String passwort) {
		if(benutzername.equals("o$ter") && passwort.equals("ha$e")) {
			view.showEasterEgg();
		} else {
			int index = bestand.getIndex(benutzername);
			if(-1 != index) {
				PersonalRecord benutzer = bestand.onRead(index);
				if(passwort.equals(benutzer.getPasswort())) {
					Zustand.getInstance().setBenutzer(benutzer);
					this.anmelden.onAnmelden();
					new VerkaufController(VerkaufView.SAEULE1);				
				}else {
					view.showPasswortError();
				}
			}else {
				view.showBenutzerError();
			}			
		}
	}
}
