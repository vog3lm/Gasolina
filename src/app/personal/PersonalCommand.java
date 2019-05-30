package app.personal;

import app.Command;

public class PersonalCommand extends Command {

	@Override
	public void execute(String command) {
		PersonalController controller = new PersonalController();
		onLoadCurrent(controller);
		zustand.getRoot().setCenter(controller.show());
	}

}
