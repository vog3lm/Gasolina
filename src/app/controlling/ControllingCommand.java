package app.controlling;

import app.Command;
import app.Commands;

public class ControllingCommand extends Command {
	@Override
	public void execute(String command) {
		ControllingController controller = new ControllingController();
		onLoadCurrent(controller);	
		ControllingView view = controller.show();
		view.decorate(new EinnahmenView().getView())
			.decorate(new AusgabenView().getView())
			.decorate(new ErgebnisView().getView());
		if(Commands.EINNAHMEN.equals(command)) {view.setIndex(0);}
		else if(Commands.AUSGABEN.equals(command)) {view.setIndex(1);}
		else if(Commands.ERGEBNIS.equals(command)) {view.setIndex(2);}
		zustand.getRoot().setCenter(view);
	}
}
