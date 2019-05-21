package app.menu;

import javafx.collections.ObservableList;
import app.Zustand;
import app.controlling.ControllingController;
import app.controlling.ControllingView;
import app.doc.DocController;
import app.kraftstoff.KraftstoffController;
import app.kraftstoff.KraftstoffView;
import app.personal.Anmelden;
import app.personal.AnmeldenController;
import app.personal.BenutzerController;
import app.personal.PersonalController;
import app.personal.PersonalRecord;
import app.verkauf.VerkaufController;
import app.verkauf.VerkaufView;
import app.waren.WarenController;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class MenuController implements Anmelden {
			
	private MenuView view = new MenuView(this);

	void onCall(String id) {
		if(null == Zustand.getInstance().getBenutzer()) {
			this.onAbmelden();
		}else {
			if(id.equals("nav_saeule_1")){new VerkaufController(VerkaufView.SAEULE1);}
			else if(id.equals("nav_saeule_2")){new VerkaufController(VerkaufView.SAEULE2);}
			else if(id.equals("nav_saeule_3")){new VerkaufController(VerkaufView.SAEULE3);}
			else if(id.equals("nav_journal")){new VerkaufController(VerkaufView.JOURNAL);}		
			else if(id.equals("nav_controlling_einnahmen")){new ControllingController(ControllingView.EINNAHMEN);}
			else if(id.equals("nav_controlling_ausgaben")){new ControllingController(ControllingView.AUSGABEN);}
			else if(id.equals("nav_controlling_betriebsergebnis")){new ControllingController(ControllingView.ERGEBNIS);}
			else if(id.equals("nav_kraftstoff_bestand")){new KraftstoffController(KraftstoffView.BESTAND);}
			else if(id.equals("nav_kraftstoff_bestellungen")){new KraftstoffController(KraftstoffView.BESTELLUNGEN);}
			else if(id.equals("nav_kraftstoff_tanks")){new KraftstoffController(KraftstoffView.TANKS);}
			else if(id.equals("nav_waren_bestand")){new WarenController(WarenController.BESTAND);}
			else if(id.equals("nav_waren_bestellungen")){new WarenController(WarenController.BESTELLUNGEN);}
			else if(id.equals("nav_personal_benutzer")){new BenutzerController();}
			else if(id.equals("nav_personal_verwaltung")){new PersonalController();}
			else if(id.equals("nav_personal_abmelden")){this.onAbmelden();}
			else if(id.equals("nav_fenster_dark")){
				Zustand zustand = Zustand.getInstance().setDesign(Zustand.DARK);
				ObservableList<String> sheets = zustand.getScene().getStylesheets();
				sheets.add(zustand.getDesign());
				sheets.remove(Zustand.LIGHT);
			}
			else if(id.equals("nav_fenster_light")){
				Zustand zustand = Zustand.getInstance().setDesign(Zustand.LIGHT);
				ObservableList<String> sheets = zustand.getScene().getStylesheets();
				sheets.add(zustand.getDesign());
				sheets.remove(Zustand.DARK);
			}else if(id.equals("nav_fenster_doc")){new DocController();
			}else if(id.equals("nav_user")){this.onAbmelden();}
			else {System.out.println("no id found "+id);}
		}
	}

	@Override
	public void onAnmelden() {
		PersonalRecord benutzer = Zustand.getInstance().getBenutzer();
		view.showUser(benutzer.getVorname(),benutzer.getNachname());
	}

	@Override
	public void onAbmelden() {
		view.clearUser();
		new AnmeldenController(this);
	}
}
