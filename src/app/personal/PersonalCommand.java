package app.personal;

import app.Runtime;
import app.command.Command;
import app.command.Commander;

public class PersonalCommand extends Command {

	public PersonalCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Personalverwaltung".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{	
			PersonalController controller = new PersonalController();
			runtime.setCenter(controller.onShow())
			  	   .setTitle("Personalverwaltung")
	  			   .setCurrent(controller);
		}
	}

}
