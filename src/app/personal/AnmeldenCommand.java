package app.personal;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class AnmeldenCommand extends Command implements Anmelden {
			
	public AnmeldenCommand(Commander commander) {
		super(commander);
		commander.getObserver().onRegister(this);
	}

	@Override
	public void onExecute(String command) {
		commander.getRuntime().setCenter(new AnmeldenController(commander).onShow())
							  .setTitle("Tankstellenverwaltung")
					  		  .setCurrent(null);
	}

	@Override
	public void onAnmelden(PersonalRecord benutzer) {
		commander.onExecute(Commands.SAEULE1);	
	}

	@Override
	public void onAbmelden() {
		commander.getRuntime().setCenter(new AnmeldenController(commander).onShow())
		  					  .setTitle("Tankstellenverwaltung")
  					  		  .setCurrent(null);
	}
}
