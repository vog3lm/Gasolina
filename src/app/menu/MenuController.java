package app.menu;

import app.Commander;
import app.Commands;
import app.Zustand;
import app.personal.Anmelden;
import app.personal.PersonalRecord;
import javafx.collections.ObservableList;
import javafx.scene.Node;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class MenuController implements Anmelden {
			
	private MenuView view = new MenuView(this);
	
	public MenuController() {
		Zustand zustand = Zustand.getInstance();
		zustand.getObserver().onRegister(this);
	}
		
	void onCall(String id) {
		Zustand zustand = Zustand.getInstance();
		if(null == zustand.getBenutzer()) {zustand.getObserver().onAbmelden();}
		else {
			Commander commander = zustand.getCommander(); 
			if(id.equals("nav_saeule_1")){commander.execute(Commands.SAEULE1);}
			else if(id.equals("nav_saeule_2")){commander.execute(Commands.SAEULE2);}
			else if(id.equals("nav_saeule_3")){commander.execute(Commands.SAEULE3);}
			else if(id.equals("nav_journal")){commander.execute(Commands.JOURNAL);}
			else if(id.equals("nav_controlling_einnahmen")){commander.execute(Commands.EINNAHMEN);}
			else if(id.equals("nav_controlling_ausgaben")){commander.execute(Commands.AUSGABEN);}
			else if(id.equals("nav_controlling_betriebsergebnis")){commander.execute(Commands.ERGEBNIS);}
			else if(id.equals("nav_kraftstoff_bestand")){commander.execute(Commands.KRAFTSTOFF_BESTAND);}
			else if(id.equals("nav_kraftstoff_bestellungen")){commander.execute(Commands.KRAFTSTOFF_BESTELLUNGEN);}
			else if(id.equals("nav_kraftstoff_tanks")){commander.execute(Commands.KRAFTSTOFF_TANKS);}
			else if(id.equals("nav_waren_bestand")){commander.execute(Commands.WAREN_BESTAND);}
			else if(id.equals("nav_waren_bestellungen")){commander.execute(Commands.WAREN_BESTELLUNGEN);}
			else if(id.equals("nav_personal_benutzer")){commander.execute(Commands.BENUTZER);}
			else if(id.equals("nav_personal_verwaltung")){commander.execute(Commands.PERSONAL);}
			else if(id.equals("nav_personal_abmelden")){zustand.getObserver().onAbmelden();}
			else if(id.equals("nav_fenster_dark")){
				zustand.setDesign(Zustand.DARK);
				ObservableList<String> sheets = zustand.getScene().getStylesheets();
				sheets.add(zustand.getDesign());
				sheets.remove(Zustand.LIGHT);
			}else if(id.equals("nav_fenster_light")){
				zustand.setDesign(Zustand.LIGHT);
				ObservableList<String> sheets = zustand.getScene().getStylesheets();
				sheets.add(zustand.getDesign());
				sheets.remove(Zustand.DARK);
			}else if(id.equals("nav_fenster_doc")){commander.execute(Commands.DOC);}
			else if(id.equals("nav_user")){zustand.getObserver().onAbmelden();}
			else {System.out.println("no id found "+id);}
		}
	}

	@Override
	public void onAnmelden(PersonalRecord benutzer) {
		view.showUser(benutzer.getVorname(),benutzer.getNachname());
	}

	@Override
	public void onAbmelden() {
		view.clearUser();
	}

	Node show() {return view.getView();}
}
