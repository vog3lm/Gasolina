package app.personal;

import javafx.scene.Node;
import app.Controller;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class BenutzerController implements Controller<Node> {
	
	private PersonalRecord benutzer;
	
	private PersonalTable bestand = new PersonalTable();
	
	private BenutzerView view;

	BenutzerController(PersonalRecord benutzer){
		this.benutzer = benutzer;
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

	@Override
	public Node onShow() {return view.onShow();}
	
}
