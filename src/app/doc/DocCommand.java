package app.doc;

import app.command.Command;
import app.command.Commander;

public class DocCommand extends Command {

	public DocCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		commander.getRuntime().setCenter(new DocController().show())
							  .setTitle("Dokumentation")
							  .setCurrent(null);		
	}

}
