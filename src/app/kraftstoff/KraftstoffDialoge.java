package app.kraftstoff;

import app.Zustand;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class KraftstoffDialoge {

	Dialog<KraftstoffbestellungenRecord> createBestandBestellenDialog(KraftstoffbestandRecord record) {
		TextField warennummer = new TextField();
		warennummer.setText(record.getWarennummer());
		TextField bezeichnung = new TextField();
		bezeichnung.setText(record.getBezeichnung());
		bezeichnung.setEditable(false);
		TextField menge = new TextField();
		Platform.runLater(() -> menge.requestFocus());
		TextField einheit = new TextField();
		einheit.setEditable(false);
		einheit.setText(record.getEinheit());
		/* create and call dialog */
		return onCreateBestellenDialog(warennummer,bezeichnung,menge,einheit);
	}
	
	Dialog<KraftstoffbestellungenRecord> createBestellungAddDialog() {
		TextField warennummer = new TextField();
		TextField einheit = new TextField();
		TextField bezeichnung = new TextField();
		Platform.runLater(() -> bezeichnung.requestFocus());
		TextField menge = new TextField();
		/* create and call dialog */
		return onCreateBestellenDialog(warennummer,bezeichnung,menge,einheit);
	}
	
	private Dialog<KraftstoffbestellungenRecord> onCreateBestellenDialog(TextField warennummer, TextField bezeichnung, TextField menge, TextField einheit) {
		/* prepare dialog - one/two */
		Dialog<KraftstoffbestellungenRecord> dialog = new Dialog<KraftstoffbestellungenRecord>();
		dialog.setTitle("Kraftstoffbestellung");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(true);
		/* build dialog layout components */
		menge.setPromptText("Menge");
		menge.textProperty().addListener((ob, o, n) -> {
			if(!n.equals("")) {
			    try {Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){menge.setText(o);}
			}
		});
		TextField preis = new TextField();
		preis.setPromptText("Preis");
		preis.textProperty().addListener((ob, o, n) -> {
			if(!n.equals("")) {
			    try {Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){menge.setText(o);}
			}
		});
		warennummer.setPromptText("Warennummer");
		warennummer.setEditable(false);
		einheit.setPromptText("Einheit");
		TextField lieferdatum = new TextField();
		lieferdatum.setPromptText("Lieferdatum");
		bezeichnung.setPromptText("Bezeichnung");
		/* build dialog layout */
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Warennummer:"), 0, 0);
		grid.add(warennummer, 1, 0);
		grid.add(new Label("Bezeichnung:"), 0, 1);
		grid.add(bezeichnung, 1, 1);
		grid.add(new Label("Menge:"), 0, 2);
		grid.add(menge, 1, 2);
		grid.add(new Label("Einheit:"), 0, 3);
		grid.add(einheit, 1, 3);
		grid.add(new Label("Preis:"), 0, 4);
		grid.add(preis, 1, 4);
		grid.add(new Label("Lieferdatum:"), 0, 5);
		grid.add(lieferdatum, 1, 5);
		/* prepare dialog - two/two */
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
		    if (submit == ButtonType.OK) {
		    	// TODO :: 
		        return new KraftstoffbestellungenRecord(-1,"","","","","","","","");
		    }
		    return null;
		});
		return dialog;
	}
}
