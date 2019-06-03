package app.command;

import javafx.application.Platform;

public class Exit extends Command {

	public Exit(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		boolean exit = commander.getRuntime().setCurrent(null);
		if(exit) {
	        Platform.exit();
	        System.exit(0);
		}
	}
	
}
