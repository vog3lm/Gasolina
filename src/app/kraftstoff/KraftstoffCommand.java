package app.kraftstoff;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class KraftstoffCommand extends Command {

	public KraftstoffCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		KraftstoffController controller = new KraftstoffController();
		KraftstoffView view = controller.show();
		view.decorate(new BestandView(controller).show())
			.decorate(new BestellungenView(controller).show())
			.decorate(new TankView().show());
		if(Commands.KRAFTSTOFF_BESTAND.equals(command)) {view.setIndex(0);}
		else if(Commands.KRAFTSTOFF_BESTELLUNGEN.equals(command)) {view.setIndex(1);}
		else if(Commands.KRAFTSTOFF_TANKS.equals(command)) {view.setIndex(2);}
		commander.getRuntime().setCenter(view).setCurrent(controller);
	}
	
}
