package app.settings;

import app.Controller;
import javafx.scene.layout.AnchorPane;

public class SettingsController  implements Controller<AnchorPane> {
	
	private SettingsView view = new SettingsView(this);
	
	@Override
	public boolean destroy() {
		return true;
	}

	@Override
	public AnchorPane show() {return view.show();}
}
