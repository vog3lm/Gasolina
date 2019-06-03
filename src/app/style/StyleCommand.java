package app.style;

import app.Settings;
import app.command.Command;
import app.command.Commander;
import app.command.Commands;

public class StyleCommand extends Command {

	public StyleCommand(Commander commander) {
		super(commander);
	}

	@Override
	public void onExecute(String command) {
		if(Commands.STYLE_DARK.equals(command)) {commander.getRuntime().setStyle(Settings.DARK);} 
		else if(Commands.STYLE_DARK.equals(command)) {commander.getRuntime().setStyle(Settings.LIGHT);} 
	}
}
