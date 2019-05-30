package app.doc;

import app.Command;

public class DocCommand extends Command {

	@Override
	public void execute(String command) {
		onLoadCurrent(null);	
		zustand.getRoot().setCenter(new DocController().getView());		
	}

}
