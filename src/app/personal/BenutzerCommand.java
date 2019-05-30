package app.personal;

import app.Command;

public class BenutzerCommand extends Command {
	
	@Override
	public void execute(String command) {
		BenutzerController controller = new BenutzerController();
		onLoadCurrent(controller);	
		zustand.getRoot().setCenter(controller.show());
	}

}
