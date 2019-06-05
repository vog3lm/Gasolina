package app.menu;

import app.command.Commander;
import app.command.Commands;
import app.personal.Anmelden;
import app.personal.PersonalRecord;
import javafx.scene.Node;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class MenuController implements Anmelden {
			
	private Commander commander;
	
	private MenuView view = new MenuView(this);
	
	public MenuController(Commander commander) {
		this.commander = commander;
		this.commander.getObserver().onRegister(this);
	}
		
	void onCall(String id) {
		if(id.equals("nav_saeule_1")){commander.onExecute(Commands.SAEULE1);}
		else if(id.equals("nav_saeule_2")){commander.onExecute(Commands.SAEULE2);}
		else if(id.equals("nav_saeule_3")){commander.onExecute(Commands.SAEULE3);}
		else if(id.equals("nav_journal")){commander.onExecute(Commands.JOURNAL);}
		else if(id.equals("nav_controlling_einnahmen")){commander.onExecute(Commands.EINNAHMEN);}
		else if(id.equals("nav_controlling_ausgaben")){commander.onExecute(Commands.AUSGABEN);}
		else if(id.equals("nav_controlling_betriebsergebnis")){commander.onExecute(Commands.ERGEBNIS);}
		else if(id.equals("nav_kraftstoff_bestand")){commander.onExecute(Commands.KRAFTSTOFF_BESTAND);}
		else if(id.equals("nav_kraftstoff_bestellungen")){commander.onExecute(Commands.KRAFTSTOFF_BESTELLUNGEN);}
		else if(id.equals("nav_kraftstoff_tanks")){commander.onExecute(Commands.KRAFTSTOFF_TANKS);}
		else if(id.equals("nav_waren_bestand")){commander.onExecute(Commands.WAREN_BESTAND);}
		else if(id.equals("nav_waren_bestellungen")){commander.onExecute(Commands.WAREN_BESTELLUNGEN);}
		else if(id.equals("nav_personal_benutzer")){commander.onExecute(Commands.BENUTZER);}
		else if(id.equals("nav_personal_verwaltung")){commander.onExecute(Commands.PERSONAL);}
		else if(id.equals("nav_personal_abmelden")){commander.getObserver().onAbmelden();}
		/* toggle design */
		else if(id.equals("nav_fenster_dark")){commander.onExecute(Commands.STYLE_DARK);}
		else if(id.equals("nav_fenster_light")){commander.onExecute(Commands.STYLE_LIGHT);}
		else if(id.equals("nav_fenster_doc")){commander.onExecute(Commands.DOC);}
		else if(id.equals("nav_settings")){commander.onExecute(Commands.SETTINGS);}
		else if(id.equals("nav_user")){commander.getObserver().onAbmelden();}
		else {System.out.println("no id found "+id);}
	}

	@Override
	public void onAnmelden(PersonalRecord benutzer) {
		view.showUser(benutzer.getVorname(),benutzer.getNachname());
	}

	@Override
	public void onAbmelden() {
		view.clearUser();
	}

	public Node show() {return view.show();}
}
