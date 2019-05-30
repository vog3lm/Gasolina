package app.verkauf;

import app.Command;
import app.Commands;

public class VerkaufCommand extends Command {
	@Override
	public void execute(String command) {
		VerkaufController controller = new VerkaufController();
		onLoadCurrent(controller);	
		VerkaufView view = controller.show();
		view.decorate(new SaeuleView(controller,"Säule 1").getView())
			.decorate(new SaeuleView(controller,"Säule 2").getView())
			.decorate(new SaeuleView(controller,"Säule 3").getView())
			.decorate(new JournalView(controller).getView());
		if(Commands.SAEULE1.equals(command)) {view.setIndex(0);}
		else if(Commands.SAEULE2.equals(command)) {view.setIndex(1);}
		else if(Commands.SAEULE3.equals(command)) {view.setIndex(2);}
		else if(Commands.JOURNAL.equals(command)) {view.setIndex(3);}
		zustand.getRoot().setCenter(view);
	}
}
