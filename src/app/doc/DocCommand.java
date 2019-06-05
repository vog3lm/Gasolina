package app.doc;

import app.Runtime;
import app.command.Command;
import app.command.Commander;

public class DocCommand extends Command {

	public DocCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Dokumentation".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{	
			runtime.setCenter(new DocController().onShow())
				   .setTitle("Dokumentation")
				   .setCurrent(null);	
		}
	}

}
