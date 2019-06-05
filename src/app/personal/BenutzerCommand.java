package app.personal;

import app.Runtime;
import app.command.Command;
import app.command.Commander;

public class BenutzerCommand extends Command {
	
	public BenutzerCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Benutzervweraltung".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{	
			BenutzerController controller = new BenutzerController(runtime.getBenutzer());
			runtime.setCenter(controller.onShow())
				   .setTitle("Benutzervweraltung")
				   .setCurrent(controller);
		}
	}

}
