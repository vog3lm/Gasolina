package app.controlling;

import app.Controller;
import app.command.Commands;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class ControllingController implements Controller {
	
	private ControllingView view = new ControllingView()
			.onDecorate(new EinnahmenView().onShow())
			.onDecorate(new AusgabenView().onShow())
			.onDecorate(new ErgebnisView().onShow());
	
	@Override
	public void onStart(String command) {
		if(Commands.EINNAHMEN.equals(command)) {view.setIndex(0);}
		else if(Commands.AUSGABEN.equals(command)) {view.setIndex(1);}
		else if(Commands.ERGEBNIS.equals(command)) {view.setIndex(2);}
	}
	
	@Override
	public boolean onDestroy() {
		return true;
	}

	public ControllingView onShow() {return view;}
}
