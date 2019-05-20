package app.menu;

import java.net.URL;
import java.util.ResourceBundle;

import app.Util;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuView implements Initializable {

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
	
	private MenuController controller;
	
	public MenuView(MenuController controller) {
		this.controller = controller;
		new Util().onLoadTop(null,super.getClass().getResource("Menu.fxml"),this);
	}
	
	@Override
	public void initialize(URL arg, ResourceBundle res) {
		nav_bar.setDisable(true);
		/**/
		nav_saeule_1.setOnAction(event -> {controller.onCall(nav_saeule_1.getId());});
		nav_saeule_2.setOnAction(event -> {controller.onCall(nav_saeule_2.getId());});
		nav_saeule_3.setOnAction(event -> {controller.onCall(nav_saeule_3.getId());});
		nav_journal.setOnAction(event -> {controller.onCall(nav_journal.getId());});
		/**/
		nav_controlling_ausgaben.setOnAction(event -> {controller.onCall(nav_controlling_ausgaben.getId());});
		nav_controlling_einnahmen.setOnAction(event -> {controller.onCall(nav_controlling_einnahmen.getId());});
		nav_controlling_betriebsergebnis.setOnAction(event -> {controller.onCall(nav_controlling_betriebsergebnis.getId());});
		/**/
		nav_waren_bestand.setOnAction(event -> {controller.onCall(nav_waren_bestand.getId());});
		nav_waren_bestellungen.setOnAction(event -> {controller.onCall(nav_waren_bestellungen.getId());});
		/**/
		nav_kraftstoff_bestand.setOnAction(event -> {controller.onCall(nav_kraftstoff_bestand.getId());});
		nav_kraftstoff_bestellungen.setOnAction(event -> {controller.onCall(nav_kraftstoff_bestellungen.getId());});
		nav_kraftstoff_tanks.setOnAction(event -> {controller.onCall(nav_kraftstoff_tanks.getId());});
		/**/
		nav_personal_benutzer.setOnAction(event -> {controller.onCall(nav_personal_benutzer.getId());});
		nav_personal_verwaltung.setOnAction(event -> {controller.onCall(nav_personal_verwaltung.getId());});
		nav_personal_abmelden.setOnAction(event -> {controller.onCall(nav_personal_abmelden.getId());});
		/**/
		nav_fenster_dark.setOnAction(event -> {controller.onCall(nav_fenster_dark.getId());});
		nav_fenster_light.setOnAction(event -> {controller.onCall(nav_fenster_light.getId());});
		nav_fenster_doc.setOnAction(event -> {controller.onCall(nav_fenster_doc.getId());});
		/**/
		nav_user.setOnAction(event -> {controller.onCall(nav_user.getId());});
	}
	
	void showUser(String vn, String nn) {
		this.nav_user.setText(vn+" "+nn);
		this.nav_bar.setDisable(false);
	}
	
	void clearUser() {
		this.nav_user.setText("");
		this.nav_bar.setDisable(true);
	}
}
