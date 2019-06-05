package app.settings;

import app.Runtime;
import app.command.Command;
import app.command.Commander;

public class SettingsCommand extends Command {

	public SettingsCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		Runtime runtime = commander.getRuntime();
		if("Einstellungen".equals(runtime.getTitle())){
			runtime.getCurrent().onStart(command);
		}else{
			SettingsController controller = new SettingsController();
			runtime.setCenter(controller.onShow())
				   .setTitle("Einstellungen")
				   .setCurrent(controller);
		}
	}

}
