package app.controlling;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class ControllingCommand extends Command {
	
	public ControllingCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		ControllingController controller = new ControllingController();
		ControllingView view = controller.show();
		view.decorate(new EinnahmenView().show())
			.decorate(new AusgabenView().show())
			.decorate(new ErgebnisView().show());
		if(Commands.EINNAHMEN.equals(command)) {view.setIndex(0);}
		else if(Commands.AUSGABEN.equals(command)) {view.setIndex(1);}
		else if(Commands.ERGEBNIS.equals(command)) {view.setIndex(2);}
		commander.getRuntime().setCenter(view).setCurrent(controller);
	}
}
