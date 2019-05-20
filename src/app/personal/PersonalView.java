package app.personal;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.Util;
import app.Zustand;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class PersonalView implements Initializable {

	@FXML
    private TableView<PersonalRecord> personal_liste;
	@FXML
	private TableColumn<PersonalRecord, String> personal_nummer;
	@FXML
	private TableColumn<PersonalRecord, String> personal_benutzername;
	@FXML
	private TableColumn<PersonalRecord, String> personal_vorname;
	@FXML
	private TableColumn<PersonalRecord, String> personal_nachname;
	@FXML
	private TableColumn<PersonalRecord, String> personal_passwort;
	@FXML
	private TableColumn<PersonalRecord, String> personal_einstelldatum;
	@FXML
	private Button personal_hinzufuegen;
	
	private PersonalController controller;
	
	private ObservableList<PersonalRecord> data;
	
	PersonalView(PersonalController controller, ArrayList<PersonalRecord> data){
		this.controller = controller;
		this.data = FXCollections.observableList(data);
		new Util().onLoadCenter(controller,super.getClass().getResource("Personal.fxml"),this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {	
	    personal_nummer.setCellValueFactory(new PropertyValueFactory<PersonalRecord, String>("personalnummer"));
	    personal_benutzername.setCellValueFactory(new PropertyValueFactory<PersonalRecord, String>("benutzername"));
	    personal_benutzername.setCellFactory(TextFieldTableCell.forTableColumn());
	    personal_benutzername.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"benutzername");});
	    personal_vorname.setCellValueFactory(new PropertyValueFactory<PersonalRecord, String>("vorname"));
	    personal_vorname.setCellFactory(TextFieldTableCell.forTableColumn());
	    personal_vorname.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"vorname");});
	    personal_nachname.setCellValueFactory(new PropertyValueFactory<PersonalRecord, String>("nachname"));
	    personal_nachname.setCellFactory(TextFieldTableCell.forTableColumn());
	    personal_nachname.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"nachname");});
	    personal_passwort.setCellValueFactory(new PropertyValueFactory<PersonalRecord, String>("passwort"));
	    personal_passwort.setCellFactory(TextFieldTableCell.forTableColumn());
	    personal_passwort.setOnEditCommit((cell) -> {onClickBestandEdit(cell,"passwort");});
	    personal_einstelldatum.setCellValueFactory(new PropertyValueFactory<PersonalRecord, String>("einstelldatum"));
	    /**/
	    personal_liste.setItems(data);
	    personal_liste.setRowFactory(this.onCreatePersonalListener());
	    personal_liste.getSortOrder().addAll(personal_nachname);
	    /**/
	    personal_hinzufuegen.setOnAction(event -> {showAddDialog();});
	}
	
	private Callback<TableView<PersonalRecord>,TableRow<PersonalRecord>> onCreatePersonalListener() {
		return new Callback<TableView<PersonalRecord>, TableRow<PersonalRecord>>(){
	        @Override
	        public TableRow<PersonalRecord> call(TableView<PersonalRecord> table) {
	            final TableRow<PersonalRecord> row = new TableRow<PersonalRecord>();           
	            row.contextMenuProperty().bind(Bindings
	            		.when(Bindings.isNotNull(row.itemProperty()))
	            		.then(createRightClickMenu(table,row))
	            		.otherwise((ContextMenu)null));
	            return row;
		    }
		};
	}
	
	private ContextMenu createRightClickMenu(TableView<PersonalRecord> table, TableRow<PersonalRecord> row) {
        ContextMenu menu = new ContextMenu();
        MenuItem remove = new MenuItem("Entfernen");
     //   remove.setOnAction(event -> {table.getItems().remove(row.getIndex());});
        remove.setOnAction(event -> {controller.onRemove(row.getIndex());});
        menu.getItems().addAll(remove);
        return menu;
	}
	
	private void showAddDialog() {
		/* prepare dialog - one/two */
		Dialog<PersonalRecord> dialog = new Dialog<PersonalRecord>();
		dialog.setTitle("Kraftstoffbestellung");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(true);
		/* build dialog layout components */
		TextField benutzername = new TextField();
		benutzername.setPromptText("Benutzername");
		TextField vorname = new TextField();
		vorname.setPromptText("Vorname");
		TextField nachname = new TextField();
		nachname.setPromptText("Nachname");
		TextField passwort = new TextField();
		passwort.setPromptText("Passwort");
		TextField einstelldatum = new TextField();
		einstelldatum.setPromptText("Einstelldatum");
		/**/
		ChangeListener<String> cb = new ChangeListener<String>() {
	        @Override 
	        public void changed(ObservableValue<? extends String> ob, String o, String n) {
	        	if(!benutzername.getText().equals("") 
	        	&& !vorname.getText().equals("")
	        	&& !nachname.getText().equals("")
	        	&& !passwort.getText().equals("")
	        	&& !einstelldatum.getText().equals("")) {
	        		pane.lookupButton(ButtonType.OK).setDisable(false);
	        	} else {
	        		pane.lookupButton(ButtonType.OK).setDisable(true);
	        	}
	        }
	    };
		benutzername.textProperty().addListener(cb);
		vorname.textProperty().addListener(cb);
		nachname.textProperty().addListener(cb);
		passwort.textProperty().addListener(cb);
		einstelldatum.textProperty().addListener(cb);
		/* build dialog layout */
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Benutzername:"), 0, 0);
		grid.add(benutzername, 1, 0);
		grid.add(new Label("Vorname:"), 0, 1);
		grid.add(vorname, 1, 1);
		grid.add(new Label("Nachname:"), 0, 2);
		grid.add(nachname, 1, 2);
		grid.add(new Label("Passwort:"), 0, 3);
		grid.add(passwort, 1, 3);
		grid.add(new Label("Einstelldatum:"), 0, 4);
		grid.add(einstelldatum, 1, 4);
		/* prepare dialog - two/two */
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
		    if(submit == ButtonType.OK){controller.onAdd(benutzername.getText(),vorname.getText(),nachname.getText(),passwort.getText(),einstelldatum.getText());}
		    return null;
		});
		dialog.showAndWait();
	}
	
	private void onClickBestandEdit(TableColumn.CellEditEvent<PersonalRecord, String> cell, String id) {
		/* PersonalRecord record = cell.getTableView().getItems().get(cell.getTablePosition().getRow()); */
		controller.onEdit(cell.getTablePosition().getRow(),id,cell.getNewValue());
	}
	
	void onRefresh() {
		personal_liste.refresh();
		personal_liste.getSortOrder().addAll(personal_nachname);
	}
}
