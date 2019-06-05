package app.command;

import app.menu.MenuController;
import app.settings.Settings;

public class Start extends Command {		

	public Start(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Settings settings = Settings.getInstance();
		if(null == commander.getRuntime()
				.setTop(new MenuController(commander).show())
				.setTitle("Tankstellenverwaltung")
				.setStyle(settings.getDesign())
				.getBenutzer()){commander.onExecute(Commands.ANMELDEN);}
		else {commander.onExecute(settings.getLastView());}
	}
}
