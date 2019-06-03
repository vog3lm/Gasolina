package app.personal;

import app.Settings;
import app.command.Commands;
import javafx.scene.layout.AnchorPane;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class AnmeldenController {
		
	private PersonalTable bestand = new PersonalTable();
	
	private AnmeldenView view = new AnmeldenView(this);
			
	void onAnmelden(String benutzername, String passwort) {
		if(benutzername.equals("o$ter") && passwort.equals("ha$e")) {
			view.showEasterEgg();
		} else {
			int index = bestand.getIndex(benutzername);
			if(-1 != index) {
				PersonalRecord benutzer = bestand.onRead(index);
				if(passwort.equals(benutzer.getPasswort())) {
					Settings zustand = Settings.getInstance();
					zustand.getObserver().onAnmelden(benutzer);
					zustand.getCommander().onExecute(Commands.SAEULE1);		
				}else {
					view.showPasswortError();
				}
			}else {
				view.showBenutzerError();
			}			
		}
	}
	
	AnchorPane show() {return view.show();}
}
