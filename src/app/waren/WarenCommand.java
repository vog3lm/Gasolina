package app.waren;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class WarenCommand extends Command {

	public WarenCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		WarenController controller = new WarenController().onStart(command);
		commander.getRuntime().setCenter(controller.show())
							  .setTitle("Warenverwaltung")
							  .setCurrent(controller);
	}
}
