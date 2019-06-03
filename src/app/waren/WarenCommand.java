package app.waren;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class WarenCommand extends Command {

	public WarenCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		WarenController controller = new WarenController();
		WarenView view = controller.show();
		view.decorate(new BestandView(controller).show())
			.decorate(new BestellungenView(controller).show());
		if(Commands.WAREN_BESTAND.equals(command)) {view.setIndex(0);}
		else if(Commands.WAREN_BESTELLUNGEN.equals(command)) {view.setIndex(1);}
		commander.getRuntime().setCenter(view).setCurrent(controller);
	}
}
