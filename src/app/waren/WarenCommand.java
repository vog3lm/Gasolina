package app.waren;

import app.Command;
import app.Commands;

public class WarenCommand extends Command {

	@Override
	public void execute(String command) {
		WarenController controller = new WarenController();
		onLoadCurrent(controller);	
		WarenView view = controller.show();
		view.decorate(new BestandView(controller).getView())
			.decorate(new BestellungenView(controller).getView());
		if(Commands.WAREN_BESTAND.equals(command)) {view.setIndex(0);}
		else if(Commands.WAREN_BESTELLUNGEN.equals(command)) {view.setIndex(1);}
		zustand.getRoot().setCenter(view);
	}
}
