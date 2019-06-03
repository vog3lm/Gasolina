package app.command;

public abstract class Command {
	
	protected Commander commander;
	
	protected Command(Commander commander){
		this.commander = commander;
	}
	
	public abstract void onExecute(String command);	

}
