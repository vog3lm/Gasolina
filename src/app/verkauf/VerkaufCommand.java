package app.verkauf;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class VerkaufCommand extends Command {
	
	public VerkaufCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		VerkaufController controller = new VerkaufController();
		VerkaufView view = controller.show();
		view.decorate(new SaeuleView(controller,"Säule 1").show())
			.decorate(new SaeuleView(controller,"Säule 2").show())
			.decorate(new SaeuleView(controller,"Säule 3").show())
			.decorate(new JournalView(controller).show());
		if(Commands.SAEULE1.equals(command)) {view.setIndex(0);}
		else if(Commands.SAEULE2.equals(command)) {view.setIndex(1);}
		else if(Commands.SAEULE3.equals(command)) {view.setIndex(2);}
		else if(Commands.JOURNAL.equals(command)) {view.setIndex(3);}
		commander.getRuntime().setCenter(view).setCurrent(controller);
	}
}
