package app.personal;

import app.personal.PersonalTable;
import app.Lifecycle;

/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class PersonalController implements Lifecycle {
	
	private PersonalTable bestand = new PersonalTable();

	private PersonalView view = new PersonalView(this,bestand.onReadAll());
		
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
	public boolean destroy() {
		bestand.onCommit();
		return true;
	}
}
