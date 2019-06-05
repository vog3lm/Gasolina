package app.waren;

import app.Runtime;
import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class WarenCommand extends Command {

	public WarenCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Warenverwaltung".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{
			WarenController controller = new WarenController();
			controller.onStart(command);
			commander.getRuntime().setCenter(controller.onShow())
								  .setTitle("Warenverwaltung")
								  .setCurrent(controller);
		}
	}
}
