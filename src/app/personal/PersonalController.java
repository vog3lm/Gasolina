package app.personal;

import app.personal.PersonalTable;
import javafx.scene.layout.AnchorPane;
import app.Controller;

/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class PersonalController implements Controller<AnchorPane> {
	
	private PersonalTable bestand = new PersonalTable();
	
	private PersonalView view = new PersonalView(this).onData(bestand.onRead());
		
	@Override
	public void onStart(String command) {}
	
	void onAdd(String bn, String vn, String nn, String pw, String d) {
		bestand.onCreate(new PersonalRecord(-1,"-1",bn,vn,nn,pw,d));
		view.onRefresh();
	}
	
	void onEdit(int index, String id, String value) {
		PersonalRecord record = bestand.onRead(index);
		if(id.equals("benutzername")) { record.setBenutzername(value); }
		else if(id.equals("vorname")) { record.setVorname(value); }
		else if(id.equals("nachname")) { record.setNachname(value); }
		else if(id.equals("menge")) { record.setPasswort(value); }
		view.onRefresh();
	}
	
	void onRemove(int index) {
		bestand.onDelete(index);
		view.onRefresh();
	}
		
	@Override
	public boolean onDestroy() {
		bestand.onCommit();
		return true;
	}

	@Override
	public AnchorPane onShow() {return view.onShow();}
}
