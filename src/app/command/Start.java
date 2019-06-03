package app.command;

import app.Settings;
import app.menu.MenuController;

public class Start extends Command {		

	public Start(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		if(null == commander.getRuntime()
				.setTop(new MenuController(commander).show())
				.setTitle("Tankstellenverwaltung")
				.setStyle(Settings.DARK)
				.getBenutzer()){commander.onExecute(Commands.ANMELDEN);}
		else {commander.onExecute(Commands.SAEULE1);}
	}
}
