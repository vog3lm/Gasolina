package app.kraftstoff;

import app.Command;
import app.Commands;

public class KraftstoffCommand extends Command {

	@Override
	public void execute(String command) {
		KraftstoffController controller = new KraftstoffController();
		onLoadCurrent(controller);	
		KraftstoffView view = controller.show();
		view.decorate(new BestandView(controller).getView())
			.decorate(new BestellungenView(controller).getView())
			.decorate(new TankView().getView());
		if(Commands.KRAFTSTOFF_BESTAND.equals(command)) {view.setIndex(0);}
		else if(Commands.KRAFTSTOFF_BESTELLUNGEN.equals(command)) {view.setIndex(1);}
		else if(Commands.KRAFTSTOFF_TANKS.equals(command)) {view.setIndex(1);}
		zustand.getRoot().setCenter(view);
	}
	
}
