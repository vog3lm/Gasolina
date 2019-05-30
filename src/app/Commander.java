package app;

import java.util.HashMap;

import app.controlling.ControllingCommand;
import app.doc.DocCommand;
import app.kraftstoff.KraftstoffCommand;
import app.menu.MenuCommand;
import app.personal.AnmeldenCommand;
import app.personal.BenutzerCommand;
import app.personal.PersonalCommand;
import app.verkauf.VerkaufCommand;
import app.waren.WarenCommand;

public class Commander {

	private HashMap<String,Command> commands = new HashMap<String,Command>();
	private HashMap<String,String> mapping = new HashMap<String,String>();
	
	Commander() {
		commands.put(Commands.ANMELDEN,new AnmeldenCommand());
		commands.put(Commands.BENUTZER,new BenutzerCommand());
		commands.put(Commands.DOC,new DocCommand());
		commands.put("controlling",new ControllingCommand());
		commands.put("kraftstoff",new KraftstoffCommand());
		commands.put(Commands.MENU,new MenuCommand());
		commands.put(Commands.PERSONAL,new PersonalCommand());
		commands.put("verkauf",new VerkaufCommand());
		commands.put("waren",new WarenCommand());
		/**/
		mapping.put(Commands.ANMELDEN,Commands.ANMELDEN);
		mapping.put(Commands.BENUTZER,Commands.BENUTZER);
		
		mapping.put(Commands.EINNAHMEN,"controlling");
		mapping.put(Commands.AUSGABEN,"controlling");
		mapping.put(Commands.ERGEBNIS,"controlling");
		
		mapping.put(Commands.DOC,Commands.DOC);
		
		mapping.put(Commands.KRAFTSTOFF_BESTAND,"kraftstoff");
		mapping.put(Commands.KRAFTSTOFF_BESTELLUNGEN,"kraftstoff");
		mapping.put(Commands.KRAFTSTOFF_TANKS,"kraftstoff");
		
		mapping.put(Commands.MENU,Commands.MENU);
		
		mapping.put(Commands.PERSONAL,Commands.PERSONAL);
		
		mapping.put(Commands.SAEULE1,"verkauf");
		mapping.put(Commands.SAEULE2,"verkauf");
		mapping.put(Commands.SAEULE3,"verkauf");
		mapping.put(Commands.JOURNAL,"verkauf");
		
		mapping.put(Commands.WAREN_BESTAND,"waren");
		mapping.put(Commands.WAREN_BESTELLUNGEN,"waren");
	}
	
	public void execute(String command) {
		Command cmd = commands.get(mapping.get(command));
		if(null != cmd){
			cmd.execute(command);
		}		
	}
}
