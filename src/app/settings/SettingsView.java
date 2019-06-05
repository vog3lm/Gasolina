package app.settings;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Decorateable;
import app.Loadable;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class SettingsView extends Loadable<AnchorPane> {

	private final String layout = "Settings.fxml";
	
	@FXML
	private AnchorPane settings;
	@FXML
    private TableView<SettingsRecord> settings_liste;
	@FXML
	private TableColumn<SettingsRecord, String> settings_key;
	@FXML
	private TableColumn<SettingsRecord, String> settings_value;
	@FXML
	private TableColumn<SettingsRecord, String> settings_default;
	@FXML
	private Button settings_persist;
	
	private SettingsController controller;
	
	SettingsView(SettingsController controller){
		this.controller = controller;
		onLoad(layout,this);
	}
	
	public SettingsView onData(ArrayList<SettingsRecord> data) {
		settings_liste.setItems(FXCollections.observableList(data));
		return this;
	}

	@Override
	public void initialize(URL url, ResourceBundle res) {
		settings_key.setCellValueFactory(new PropertyValueFactory<SettingsRecord, String>("key"));
		settings_value.setCellValueFactory(new PropertyValueFactory<SettingsRecord, String>("value"));
		settings_default.setCellValueFactory(new PropertyValueFactory<SettingsRecord, String>("default"));
		/**/
		settings_persist.setOnAction(event -> {/*createAddDialog();*/System.out.println("speichern,...");});
	}

	@Override
	protected AnchorPane show() {return settings;}

}
