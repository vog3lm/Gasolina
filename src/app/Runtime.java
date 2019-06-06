package app;

import app.personal.PersonalRecord;
import app.settings.Settings;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Runtime {

	private Stage stage;
	
	private Scene scene;
	
	private BorderPane layout;
	/**
	 * Currently logged in user.
	 * */
	private PersonalRecord benutzer;
	
	private Settings settings = Settings.getInstance();
	
	private Observer observer = new Observer();
	
	private Datapool datapool = new Datapool();
	
	private Controller current; 
	
	public Runtime(Stage stage, Scene scene, BorderPane layout) {
		this.stage = stage;
		this.scene = scene;
		this.layout = layout;
	}
		
	public Controller getCurrent() {return this.current;}
	/**
	 * @param The new Controller obejct
	 */
	public boolean setCurrent(Controller controller) {
		if(null != current) {
			if(!current.onDestroy()) {
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
	
	public String getTitle() {return this.stage.getTitle();}
	
	public Runtime setStyle(String style) {
		ObservableList<String> sheets = scene.getStylesheets();
		sheets.remove(settings.getDesign());
		sheets.add(style);
		settings.setDesign(style);
		return this;
	}
	
	/**/
	public Observer getObserver() {return observer;}
	
	public PersonalRecord getBenutzer() {return benutzer;}
	
	public Runtime setBenutzer(PersonalRecord benutzer) {
		this.benutzer = benutzer;
		return this;
	}
	
	public Datapool getDatapool() {return datapool;}
}
