package app.verkauf;

import app.Runtime;
import app.command.Command;
import app.command.Commander;

public class VerkaufCommand extends Command {
	
	public VerkaufCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Verkauf".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{
			VerkaufController controller = new VerkaufController(runtime.getDatapool());
			controller.onStart(command);
			runtime.setCenter(controller.onShow())
				   .setTitle("Verkauf")
				   .setCurrent(controller);
		}
	}
}
