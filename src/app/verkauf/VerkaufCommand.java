package app.verkauf;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class VerkaufCommand extends Command {
	
	public VerkaufCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		VerkaufController controller = new VerkaufController().onStart(command);
		commander.getRuntime().setCenter(controller.show())
							  .setTitle("Verkauf")
							  .setCurrent(controller);
	}
}
