package app.controlling;

import app.Controller;
import app.command.Commands;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class ControllingController implements Controller<ControllingView> {
	
	private ControllingView view = new ControllingView()
			.decorate(new EinnahmenView().show())
			.decorate(new AusgabenView().show())
			.decorate(new ErgebnisView().show());
	
	ControllingController onStart(String command) {
		if(Commands.EINNAHMEN.equals(command)) {view.setIndex(0);}
		else if(Commands.AUSGABEN.equals(command)) {view.setIndex(1);}
		else if(Commands.ERGEBNIS.equals(command)) {view.setIndex(2);}
		return this;
	}
	
	@Override
	public boolean destroy() {
		return true;
	}

	@Override
	public ControllingView show() {return view;}
}
