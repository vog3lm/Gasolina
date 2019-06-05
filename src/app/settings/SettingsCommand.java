package app.settings;

import app.command.Command;
import app.command.Commander;

public class SettingsCommand extends Command {

	public SettingsCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		SettingsController controller = new SettingsController();
		commander.getRuntime().setCenter(controller.show())
							  .setTitle("Einstellungen")
							  .setCurrent(controller);
	}

}
