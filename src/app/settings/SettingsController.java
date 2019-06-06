package app.settings;

import app.Controller;
import javafx.scene.layout.AnchorPane;

public class SettingsController  implements Controller {
	
	private SettingsView view = new SettingsView(this);
	
	@Override
	public void onStart(String command) {}
	
	@Override
	public boolean onDestroy() {
		return true;
	}

	public AnchorPane onShow() {return view.onShow();}


}
