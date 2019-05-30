package app.personal;

import app.Command;

public class AnmeldenCommand extends Command {
	
	@Override
	public void execute(String command) {
		onLoadCurrent(null);	
		zustand.getRoot().setCenter(new AnmeldenController().show());
	}

}
