package app.controlling;

import app.Controller;
import app.verkauf.VerkaufTable;
import javafx.scene.Node;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class ControllingController implements Controller {
	
	private ControllingView view = new ControllingView();
	
	public ControllingController() {

	}
	
	@Override
	public boolean destroy() {
		return true;
	}

	@Override
	public ControllingView show() {return view;}
}
