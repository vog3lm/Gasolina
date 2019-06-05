package app.controlling;

import app.Runtime;
import app.command.Command;
import app.command.Commander;

public class ControllingCommand extends Command {
	
	public ControllingCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Controlling".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{		
			ControllingController controller = new ControllingController();
			controller.onStart(command);
			runtime.setCenter(controller.onShow())
				   .setTitle("Controlling")
				   .setCurrent(controller);
		}
	}
}
