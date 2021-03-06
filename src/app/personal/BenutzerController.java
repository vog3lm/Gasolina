package app.personal;

import javafx.scene.Node;

import app.Controller;
import app.Datapool;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class BenutzerController implements Controller {
	
	private PersonalRecord benutzer;
	
	private PersonalTable bestand;
	
	private BenutzerView view;

	BenutzerController(Datapool pool, PersonalRecord benutzer){
		this.benutzer = benutzer;
		this.bestand = pool.aquirePersonal();
		view = new BenutzerView(this,benutzer);
	}
	
	@Override
	public void onStart(String command) {}
	
	public void onSpeichern(String un, String vn, String nn, String pw) {
		PersonalRecord benutzer = bestand.onRead(this.benutzer.getIndex());
		benutzer.setBenutzername(un);
		benutzer.setVorname(vn);
		benutzer.setNachname(nn);
		benutzer.setPasswort(pw);
		bestand.onUpdate(benutzer.getIndex(),benutzer);
	}
	
	@Override
	public boolean onDestroy() {
		bestand.onCommit();
		return true;
	}

	public Node onShow() {return view.onShow();}
	
}
