package app.controlling;

import app.Controller;
import app.verkauf.VerkaufTable;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class ControllingController implements Controller {

	private VerkaufTable einnahmen = new VerkaufTable();
	
	private AusgabenTable ausgaben = new AusgabenTable();

	private ControllingView view = new ControllingView(this);

	
	public ControllingController(int tab) {
		view.setIndex(tab);
		view.setEinnahmen(einnahmen.onRead());
		view.setAusgaben(ausgaben.onRead());
	}
	
	@Override
	public boolean destroy() {
		return true;
	}

}
