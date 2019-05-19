package app.menu;
	
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import app.Util;
import app.Zustand;
import app.controlling.ControllingController;
import app.doc.DocController;
import app.kraftstoff.KraftstoffController;
import app.personal.Anmelden;
import app.personal.AnmeldenController;
import app.personal.BenutzerController;
import app.personal.PersonalController;
import app.personal.PersonalRecord;
import app.verkauf.VerkaufController;
import app.waren.WarenController;


public class MenuController implements Initializable, Anmelden {
			
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
	private MenuItem nav_fenster_doc;
	
	@FXML
	private Button nav_user;

	
	public MenuController() {
		new Util().onLoadTop(super.getClass().getResource("Menu.fxml"),this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		nav_bar.setDisable(true);
		/**/
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
        nav_fenster_doc.setOnAction(this::onClickNavMenuItem);
        /**/
        nav_user.setOnAction(this::onClickNavButton);
	}


	private void onClickNavButton(ActionEvent event) {
		if(null == Zustand.getInstance().getBenutzer()) {
			this.onAbmelden();
		}else {
			String id = ((Button)event.getSource()).getId();
			if(id.equals("nav_user")){this.onAbmelden();}
			else {System.out.println("no id found "+id);}
		}
	}
	
	private void onClickNavMenuItem(ActionEvent event) {
		if(null == Zustand.getInstance().getBenutzer()) {
			this.onAbmelden();
		}else {
			String id = ((MenuItem)event.getSource()).getId();
			if(id.equals("nav_saeule_1")){new VerkaufController(VerkaufController.SAEULE1);}
			else if(id.equals("nav_saeule_2")){new VerkaufController(VerkaufController.SAEULE2);}
			else if(id.equals("nav_saeule_3")){new VerkaufController(VerkaufController.SAEULE3);}
			else if(id.equals("nav_journal")){new VerkaufController(VerkaufController.JOURNAL);}		
			else if(id.equals("nav_controlling_einnahmen")){new ControllingController(ControllingController.EINNAHMEN);}
			else if(id.equals("nav_controlling_ausgaben")){new ControllingController(ControllingController.AUSGABEN);}
			else if(id.equals("nav_controlling_betriebsergebnis")){new ControllingController(ControllingController.ERGEBNIS);}
			else if(id.equals("nav_kraftstoff_bestand")){new KraftstoffController(KraftstoffController.BESTAND);}
			else if(id.equals("nav_kraftstoff_bestellungen")){new KraftstoffController(KraftstoffController.BESTELLUNGEN);}
			else if(id.equals("nav_kraftstoff_tanks")){new KraftstoffController(KraftstoffController.TANKS);}
			else if(id.equals("nav_waren_bestand")){new WarenController(WarenController.BESTAND);}
			else if(id.equals("nav_waren_bestellungen")){new WarenController(WarenController.BESTELLUNGEN);}
			else if(id.equals("nav_personal_benutzer")){new BenutzerController();}
			else if(id.equals("nav_personal_verwaltung")){new PersonalController();}
			else if(id.equals("nav_personal_abmelden")){this.onAbmelden();}
			else if(id.equals("nav_fenster_dark")){
				Zustand zustand = Zustand.getInstance().setDesign(Zustand.DARK);
				ObservableList<String> sheets = zustand.getScene().getStylesheets();
				sheets.add(zustand.getDesign());
				sheets.remove(Zustand.LIGHT);
			}
			else if(id.equals("nav_fenster_light")){
				Zustand zustand = Zustand.getInstance().setDesign(Zustand.LIGHT);
				ObservableList<String> sheets = zustand.getScene().getStylesheets();
				sheets.add(zustand.getDesign());
				sheets.remove(Zustand.DARK);
			}else if(id.equals("nav_fenster_doc")){new DocController();}
			else {System.out.println("no id found "+id);}
		}
		
	}

	@Override
	public void onAnmelden() {
		PersonalRecord benutzer = Zustand.getInstance().getBenutzer();
		this.nav_user.setText(benutzer.getVorname()+" "+benutzer.getNachname());
		this.nav_bar.setDisable(false);
	}

	@Override
	public void onAbmelden() {
		this.nav_user.setText("");
		this.nav_bar.setDisable(true);
		new AnmeldenController(this);
	}
}
