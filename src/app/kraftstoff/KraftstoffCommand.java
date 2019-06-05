package app.kraftstoff;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class KraftstoffCommand extends Command {

	public KraftstoffCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		KraftstoffController controller = new KraftstoffController().onStart(command);
		commander.getRuntime().setCenter(controller.show())
							  .setTitle("Kraftstoffverwaltung")
							  .setCurrent(controller);
	}
	
}
