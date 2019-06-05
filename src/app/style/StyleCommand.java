package app.style;

import app.command.Command;
import app.command.Commander;
import app.command.Commands;
import app.settings.Settings;
import app.settings.SettingsDefault;

public class StyleCommand extends Command {

	public StyleCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		if(Commands.STYLE_DARK.equals(command)) {commander.getRuntime().setStyle(SettingsDefault.DARK);} 
		else if(Commands.STYLE_DARK.equals(command)) {commander.getRuntime().setStyle(SettingsDefault.LIGHT);} 
	}
}
