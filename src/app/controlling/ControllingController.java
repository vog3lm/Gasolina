package app.controlling;

import app.Lifecycle;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class ControllingController implements Lifecycle {

	public static final int EINNAHMEN = 0;
	public static final int AUSGABEN = 1;
	public static final int ERGEBNIS = 2;
	
	// journal
	// kraftstoff
	// waren
	
	private ControllingView view = new ControllingView(this);

	
	public ControllingController(int tab) {
		view.setIndex(tab);
	}
	
	@Override
	public boolean destroy() {
		return true;
	}

}
