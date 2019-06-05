package app.kraftstoff;

import app.Runtime;
import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class KraftstoffCommand extends Command {

	public KraftstoffCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Kraftstoffverwaltung".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{	
			KraftstoffController controller = new KraftstoffController();
			controller.onStart(command);
			runtime.setCenter(controller.onShow())
				   .setTitle("Kraftstoffverwaltung")
				   .setCurrent(controller);
		}
	}
	
}
