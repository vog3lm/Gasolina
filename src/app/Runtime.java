package app;

import app.personal.PersonalRecord;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Runtime {

	private Stage stage;
	
	private Scene scene;
	
	private BorderPane layout;
	
	private Settings settings;
	
	private Controller<?> current; 
	
	public Runtime(Stage stage, Scene scene, BorderPane layout, Settings settings) {
		this.stage = stage;
		this.scene = scene;
		this.layout = layout;
		this.settings = settings;
	}
		
	/**
	 * @param The new Controller obejct
	 */
	public boolean setCurrent(Controller<?> controller) {
		if(null != current) {
			if(!current.destroy()) {
				return false;
			}
		}
		current = controller;
		return true;
	}
	
	public Runtime setCenter(Node node) {
		this.layout.setCenter(node);
		return this;
	}
	
	public Runtime setTop(Node node) {
		this.layout.setTop(node);
		return this;
	}
	
	public Runtime setTitle(String title) {
		this.stage.setTitle(title);
		return this;
	}
	
	public Runtime setStyle(String style) {
		ObservableList<String> sheets = scene.getStylesheets();
		sheets.remove(settings.getDesign());
		sheets.add(style);
		settings.setDesign(style);
		return this;
	}
	
	/**/
	public PersonalRecord getBenutzer() {return settings.getBenutzer();}
	
}
