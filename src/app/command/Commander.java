package app.command;

import java.util.HashMap;

import app.Observer;
import app.Runtime;
import app.controlling.ControllingCommand;
import app.doc.DocCommand;
import app.kraftstoff.KraftstoffCommand;
import app.personal.AnmeldenCommand;
import app.personal.BenutzerCommand;
import app.personal.PersonalCommand;
import app.settings.SettingsCommand;
import app.style.StyleCommand;
import app.verkauf.VerkaufCommand;
import app.waren.WarenCommand;

public class Commander {

	private HashMap<String,Command> commands = new HashMap<String,Command>();
	
	private HashMap<String,String> mapping = new HashMap<String,String>();
	
	private Runtime runtime;
	
	/**
	 * @param The command id of the command to be executed.
	 */
	public Commander(Runtime runtime) {
		this.runtime = runtime;
		/**/
		commands.put(Commands.ANMELDEN,new AnmeldenCommand(this));
		commands.put(Commands.BENUTZER,new BenutzerCommand(this));
		commands.put(Commands.DOC,new DocCommand(this));
		commands.put("controlling",new ControllingCommand(this));
		commands.put("kraftstoff",new KraftstoffCommand(this));
		commands.put(Commands.START,new Start(this));
		commands.put(Commands.EXIT,new Exit(this));
		commands.put(Commands.PERSONAL,new PersonalCommand(this));
		commands.put(Commands.STYLE,new StyleCommand(this));
		commands.put(Commands.SETTINGS,new SettingsCommand(this));
		commands.put("verkauf",new VerkaufCommand(this));
		commands.put("waren",new WarenCommand(this));
		/**/
		mapping.put(Commands.ANMELDEN,Commands.ANMELDEN);
		mapping.put(Commands.START,Commands.START);
		mapping.put(Commands.EXIT,Commands.EXIT);
		mapping.put(Commands.BENUTZER,Commands.BENUTZER);
		mapping.put(Commands.EINNAHMEN,"controlling");
		mapping.put(Commands.AUSGABEN,"controlling");
		mapping.put(Commands.ERGEBNIS,"controlling");
		mapping.put(Commands.DOC,Commands.DOC);
		mapping.put(Commands.KRAFTSTOFF_BESTAND,"kraftstoff");
		mapping.put(Commands.KRAFTSTOFF_BESTELLUNGEN,"kraftstoff");
		mapping.put(Commands.KRAFTSTOFF_TANKS,"kraftstoff");
		mapping.put(Commands.PERSONAL,Commands.PERSONAL);
		mapping.put(Commands.STYLE,Commands.STYLE);
		mapping.put(Commands.STYLE_DARK,Commands.STYLE);
		mapping.put(Commands.STYLE_LIGHT,Commands.STYLE);
		mapping.put(Commands.SETTINGS,Commands.SETTINGS);
		mapping.put(Commands.SAEULE1,"verkauf");
		mapping.put(Commands.SAEULE2,"verkauf");
		mapping.put(Commands.SAEULE3,"verkauf");
		mapping.put(Commands.JOURNAL,"verkauf");
		mapping.put(Commands.WAREN_BESTAND,"waren");
		mapping.put(Commands.WAREN_BESTELLUNGEN,"waren");
	}
	
	/**
	 * @param The command id of the command to be executed.
	 */
	public void onExecute(String command) {
		Command cmd = commands.get(mapping.get(command));
		if(null != cmd){
			cmd.onExecute(command);
		}		
	}
	
	public Runtime getRuntime() {return runtime;}
	
	public Observer getObserver() {return runtime.getObserver();}

}
