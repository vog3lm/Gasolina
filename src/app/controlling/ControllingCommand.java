package app.controlling;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class ControllingCommand extends Command {
	
	public ControllingCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		ControllingController controller = new ControllingController().onStart(command);
		commander.getRuntime().setCenter(controller.show())
							  .setTitle("Controlling")
							  .setCurrent(controller);
	}
}
