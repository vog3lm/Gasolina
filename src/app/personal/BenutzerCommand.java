package app.personal;

import app.command.Command;
import app.command.Commander;

public class BenutzerCommand extends Command {
	
	public BenutzerCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		BenutzerController controller = new BenutzerController();
		commander.getRuntime().setCenter(controller.show()).setCurrent(controller);
	}

}
