package app.personal;

import app.command.Command;
import app.command.Commander;

public class PersonalCommand extends Command {

	public PersonalCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		PersonalController controller = new PersonalController();
		commander.getRuntime().setCenter(controller.show()).setCurrent(controller);
	}

}
