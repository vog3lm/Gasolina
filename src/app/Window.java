package app;
	
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import app.anmelden.Anmelden;
import app.anmelden.AnmeldenController;
import app.benutzer.BenutzerController;
import app.controlling.ControllingController;
import app.kraftstoff.KraftstoffController;
import app.personal.PersonalController;
import app.personal.PersonalRecord;
import app.verkauf.VerkaufController;
import app.waren.WarenController;

public class Window extends Application implements Lifecycle, Anmelden {
		
	private Stage stage;
	
	private Scene scene;
	
	private BorderPane window;
	
	private Lifecycle state;
	
	@FXML
	private MenuBar nav_bar;
	@FXML
	private MenuItem nav_saeule_1;
	@FXML
	private MenuItem nav_saeule_2;
	@FXML
	private MenuItem nav_saeule_3;
	@FXML
	private MenuItem nav_journal;
	@FXML
	private MenuItem nav_controlling_ausgaben;
	@FXML
	private MenuItem nav_controlling_einnahmen;
	@FXML
	private MenuItem nav_controlling_betriebsergebnis;
	@FXML
	private MenuItem nav_waren_bestand;
	@FXML
	private MenuItem nav_waren_bestellungen;
	@FXML
	private MenuItem nav_kraftstoff_bestand;
	@FXML
	private MenuItem nav_kraftstoff_bestellungen;
	@FXML
	private MenuItem nav_kraftstoff_tanks;
	@FXML
	private MenuItem nav_personal_benutzer;
	@FXML
	private MenuItem nav_personal_verwaltung;
	@FXML
	private MenuItem nav_personal_abmelden;
	@FXML
	private MenuItem nav_fenster_dark;
	@FXML
	private MenuItem nav_fenster_light;
	
	@FXML
	private Button nav_user;
	
	@Override
	public void start(Stage stage) {	
		try {		
			FXMLLoader loader = new FXMLLoader(super.getClass().getResource("Window.fxml"));
		    loader.setController(this);
		    this.window = (BorderPane) loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Einstellungen einstellungen = Einstellungen.getInstance();
		this.scene = new Scene(this.window);		
		this.scene.getStylesheets().add(einstellungen.getDesign());
		this.stage = stage;
		this.stage.setTitle("Tankstellenverwaltung");
		this.stage.setScene(this.scene);
		this.stage.show();
		/* apply custom exit strategy */
		Platform.setImplicitExit(false);
		this.stage.setOnCloseRequest(event -> {
			event.consume();
			this.onDestroy();
		});
		/* call login view if no user is logged in */
		PersonalRecord benutzer = einstellungen.getBenutzer();
		if(null != benutzer) {this.onAnmelden(benutzer);}
		else {this.onAbmelden();}
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {		
		nav_saeule_1.setOnAction(this::onClickNavMenuItem);
		nav_saeule_2.setOnAction(this::onClickNavMenuItem);
		nav_saeule_3.setOnAction(this::onClickNavMenuItem);
        nav_journal.setOnAction(this::onClickNavMenuItem);
    	nav_controlling_ausgaben.setOnAction(this::onClickNavMenuItem);
    	nav_controlling_einnahmen.setOnAction(this::onClickNavMenuItem);
    	nav_controlling_betriebsergebnis.setOnAction(this::onClickNavMenuItem);
        nav_waren_bestand.setOnAction(this::onClickNavMenuItem);
        nav_waren_bestellungen.setOnAction(this::onClickNavMenuItem);
        nav_kraftstoff_bestand.setOnAction(this::onClickNavMenuItem);
        nav_kraftstoff_bestellungen.setOnAction(this::onClickNavMenuItem);
        nav_kraftstoff_tanks.setOnAction(this::onClickNavMenuItem);
        nav_personal_benutzer.setOnAction(this::onClickNavMenuItem); 
        nav_personal_verwaltung.setOnAction(this::onClickNavMenuItem); 
        nav_personal_abmelden.setOnAction(this::onClickNavMenuItem);
        nav_fenster_dark.setOnAction(this::onClickNavMenuItem);
        nav_fenster_light.setOnAction(this::onClickNavMenuItem);
        /**/
        nav_user.setOnAction(this::onClickNavButton);
	}
	
	private void onController(String title, String url, Initializable controller) {	
		try {		
			FXMLLoader loader = new FXMLLoader(super.getClass().getResource(url));
		    loader.setController(controller);
		    Parent layout = loader.load();
			if(null != layout) {
				this.window.setCenter(layout);
				this.stage.setTitle(title);
			}
			this.state = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void onLifecycle(String title, String url, Lifecycle lifecycle) {
		if(null != this.state) {
			this.state.onDestroy();
		}
		this.onController(title,url,lifecycle);
		this.state = lifecycle;
	}
	
	private void onClickNavButton(ActionEvent event) {
		/* eher unnötig */
		if(null == Einstellungen.getInstance().getBenutzer()) {
			this.onAbmelden();
		}else {
			String id = ((Button)event.getSource()).getId();
			if(id.equals("nav_user")){this.onAbmelden();}
			else {System.out.println("no id found "+id);}
		}
	}
	
	private void onClickNavMenuItem(ActionEvent event) {
		/* eher unnötig */
		if(null == Einstellungen.getInstance().getBenutzer()) {
			this.onAbmelden();
		}else {
			String id = ((MenuItem)event.getSource()).getId();
			if(id.equals("nav_saeule_1")){this.onLifecycle("Kasse","verkauf/Verkauf.fxml",new VerkaufController(VerkaufController.SAEULE1));}
			else if(id.equals("nav_saeule_2")){this.onLifecycle("Kasse","verkauf/Verkauf.fxml",new VerkaufController(VerkaufController.SAEULE2));}
			else if(id.equals("nav_saeule_3")){this.onLifecycle("Kasse","verkauf/Verkauf.fxml",new VerkaufController(VerkaufController.SAEULE3));}
			else if(id.equals("nav_journal")){this.onLifecycle("Journal","verkauf/Verkauf.fxml",new VerkaufController(VerkaufController.JOURNAL));}		
			else if(id.equals("nav_controlling_einnahmen")){this.onLifecycle("Einnahmen","controlling/Controlling.fxml",new ControllingController(ControllingController.EINNAHMEN));}
			else if(id.equals("nav_controlling_ausgaben")){this.onLifecycle("Ausgaben","controlling/Controlling.fxml",new ControllingController(ControllingController.AUSGABEN));}
			else if(id.equals("nav_controlling_betriebsergebnis")){this.onLifecycle("Betriebsergebnis","controlling/Controlling.fxml",new ControllingController(ControllingController.ERGEBNIS));}
			else if(id.equals("nav_kraftstoff_bestand")){this.onLifecycle("Kraftstoffbestand","kraftstoff/Kraftstoff.fxml",new KraftstoffController(KraftstoffController.BESTAND));}
			else if(id.equals("nav_kraftstoff_bestellungen")){this.onLifecycle("Kraftstoffbestellungen","kraftstoff/Kraftstoff.fxml",new KraftstoffController(KraftstoffController.BESTELLUNGEN));}
			else if(id.equals("nav_kraftstoff_tanks")){this.onLifecycle("Kraftstofftanks","kraftstoff/Kraftstoff.fxml",new KraftstoffController(KraftstoffController.TANKS));}
			else if(id.equals("nav_waren_bestand")){this.onLifecycle("Warenbestand","waren/Waren.fxml",new WarenController(WarenController.BESTAND));}
			else if(id.equals("nav_waren_bestellungen")){this.onLifecycle("Warenbestellungen","waren/Waren.fxml",new WarenController(WarenController.BESTELLUNGEN));}
			else if(id.equals("nav_personal_benutzer")){this.onLifecycle("Benutzer","benutzer/Benutzer.fxml",new BenutzerController());}
			else if(id.equals("nav_personal_verwaltung")){this.onLifecycle("Personalverwaltung","personal/Personal.fxml",new PersonalController());}
			else if(id.equals("nav_personal_abmelden")){this.onAbmelden();}
			else if(id.equals("nav_fenster_dark")){
				Einstellungen einstellungen = Einstellungen.getInstance().setDesign(Einstellungen.DARK);
				ObservableList<String> sheets = this.scene.getStylesheets();
				sheets.add(einstellungen.getDesign());
				sheets.remove(Einstellungen.LIGHT);
			}
			else if(id.equals("nav_fenster_light")){
				Einstellungen einstellungen = Einstellungen.getInstance().setDesign(Einstellungen.LIGHT);
				ObservableList<String> sheets = this.scene.getStylesheets();
				sheets.add(einstellungen.getDesign());
				sheets.remove(Einstellungen.DARK);
			}
			else {System.out.println("no id found "+id);}
		}
	}

	@Override
	public void onAnmelden(PersonalRecord benutzer) {
		Einstellungen.getInstance().setBenutzer(benutzer);
		this.nav_user.setText(benutzer.getVorname()+" "+benutzer.getNachname());
		this.nav_bar.setDisable(false);
		this.onLifecycle("Verkauf","verkauf/Verkauf.fxml",new VerkaufController(VerkaufController.SAEULE1));
	}

	@Override
	public void onAbmelden() {
		this.nav_user.setText("");
		this.nav_bar.setDisable(true);
		this.state = null;
		this.onController("Anmelden","anmelden/Anmelden.fxml",new AnmeldenController(this));
	}
	
	@Override
	public boolean onDestroy() {
		boolean exit = true;
		if(null != this.state) {exit = this.state.onDestroy();}
		if(exit) {
	        Platform.exit();
	        System.exit(0);
		}
        return exit;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
