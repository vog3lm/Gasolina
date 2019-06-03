package app.personal;

import app.command.Command;
import app.command.Commander;

public class AnmeldenCommand extends Command {
			
	public AnmeldenCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		commander.getRuntime().setCenter(new AnmeldenController(commander).show());
	}
}
