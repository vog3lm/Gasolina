package app.menu;

import app.Command;
import app.Commands;
import app.Zustand;
import app.personal.AnmeldenController;

public class MenuCommand extends Command {		
	@Override
	public void execute(String command) {
		zustand.getRoot().setTop(new MenuController().show());
		if(null == zustand.getBenutzer()){zustand.getCommander().execute(Commands.ANMELDEN);}
		else {zustand.getCommander().execute(Commands.SAEULE1);}
	}
}
