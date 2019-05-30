package app;

public abstract class Command {
	
	protected Zustand zustand = Zustand.getInstance();
	
	public abstract void execute(String command);
	
	/**
	 * @param The new Controller obejct
	 */
	protected void onLoadCurrent(Controller controller) {
		Controller current = zustand.getCurrent();
		if(null != current) {
			if(!zustand.getCurrent().destroy()) {
				return;
			}
		}
		zustand.setCurrent(controller);
	}	
}
