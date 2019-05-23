package app.waren;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.Zustand;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

class WarenDialoge {

	Dialog<WarenbestellungenRecord> createBuchenDialog(WarenbestellungenRecord record) {
		/* prepare dialog - one/two */
		Dialog<WarenbestellungenRecord> dialog = new Dialog<WarenbestellungenRecord>();
		dialog.setTitle("Waren Einbuchen");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(false);
		/* build dialog layout components */
		TextField bestellnummer = new TextField();
		bestellnummer.setEditable(false);
		bestellnummer.setText(record.getBestellnummer());
		TextField bezeichnung = new TextField();
		bezeichnung.setEditable(false);
		bezeichnung.setText(record.getBezeichnung());
		TextField restmenge = new TextField();
		restmenge.setPromptText("Restmenge");
		restmenge.setEditable(false);
		restmenge.setVisible(false);
		Label restmenge_label = new Label("Restmenge:");
		restmenge_label.setVisible(false);
		TextField lieferdatum = new TextField();
		lieferdatum.setPromptText("Lieferdatum");
		lieferdatum.setVisible(false);
		lieferdatum.textProperty().addListener((ob, o, n) -> {
			if("".equals(lieferdatum.getText())) {
				pane.lookupButton(ButtonType.OK).setDisable(true);
			}else {
				pane.lookupButton(ButtonType.OK).setDisable(false);
			}
		});
		Label lieferdatum_label = new Label("Lieferdatum:");
		lieferdatum_label.setVisible(false);
		TextField menge = new TextField();
		menge.setPromptText("Menge");
		menge.setText(record.getMenge());
		menge.textProperty().addListener((ob, o, n) -> {
			float liefermenge = 0;
			if(!n.equals("")) {
			    try {liefermenge = Float.parseFloat(n);} 
			    catch (NumberFormatException | NullPointerException e){
			    	menge.setText(o);
			    	return;
			    }
			}
			if(!n.equals(record.getMenge())) {
				restmenge.setText((Float.parseFloat(record.getMenge())-liefermenge)+"");
				restmenge.setVisible(true);
				restmenge_label.setVisible(true);
				lieferdatum.setVisible(true);
				lieferdatum_label.setVisible(true);
				pane.lookupButton(ButtonType.OK).setDisable(true);
			}else {
				restmenge.setText("");
				restmenge.setVisible(false);
				restmenge_label.setVisible(false);
				lieferdatum.setVisible(false);
				lieferdatum_label.setVisible(false);
				pane.lookupButton(ButtonType.OK).setDisable(false);
			}
		});
		/* build dialog layout */
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Bestellnummer:"), 0, 0);
		grid.add(bestellnummer, 1, 0);
		grid.add(new Label("Bezeichnung:"), 0, 1);
		grid.add(bezeichnung, 1, 1);
		grid.add(new Label("Menge:"), 0, 2);
		grid.add(menge, 1, 2);
		grid.add(restmenge_label, 0, 3);
		grid.add(restmenge, 1, 3);
		grid.add(lieferdatum_label, 0, 4);
		grid.add(lieferdatum, 1, 4);
		/* prepare dialog - two/two */
		pane.setContent(grid);
		dialog.setResultConverter(submit -> {
		    if (submit == ButtonType.OK) {
		    	return new WarenbestellungenRecord(record.getIndex()
		    			,record.getBestellnummer()
		    			,record.getWarennummer()
		    			,record.getBezeichnung()
		    			,menge.getText()			/* gelieferte menge */
		    			,record.getEinheit()
		    			,record.getPreis()
		    			,record.getWaehrung()		    			
		    			,record.getBestelldatum()
		    			,lieferdatum.getText()	/* lieferdatum restmenge */
		    			,record.getMitarbeiter());
		    }
		    return null;
		});
		return dialog;
	}
	
	Dialog<WarenbestellungenRecord> createBestandBestellenDialog(WarenbestandRecord record) {
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

	Dialog<WarenbestellungenRecord> createBestellungAddDialog() {
		TextField warennummer = new TextField();
		TextField bezeichnung = new TextField();
		Platform.runLater(() -> bezeichnung.requestFocus());
		TextField menge = new TextField();
		TextField einheit = new TextField();
		/* create and call dialog */
		return onCreateBestellenDialog(warennummer,bezeichnung,menge,einheit);
	}
	
	private Dialog<WarenbestellungenRecord> onCreateBestellenDialog(TextField warennummer, TextField bezeichnung, TextField menge, TextField einheit) {
		/* prepare dialog - one/two */
		Dialog<WarenbestellungenRecord> dialog = new Dialog<WarenbestellungenRecord>();
		dialog.setTitle("Warenbestellung");
		dialog.setHeaderText(null);
		DialogPane pane = dialog.getDialogPane();
		pane.getStylesheets().add(Zustand.getInstance().getDesign());
		// Set the icon (must be included in the project).
		// dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
		pane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		pane.lookupButton(ButtonType.OK).setDisable(true);
		/* build dialog layout components */
		TextField preis = new TextField();
		TextField lieferdatum = new TextField();
		menge.setPromptText("Menge");
		menge.textProperty().addListener((ob, o, n) -> {
			if(!n.equals("")) {
			    try {
			    	Float.parseFloat(n);
					if("".equals(menge.getText()) || "".equals(preis.getText()) || "".equals(lieferdatum.getText())) {
						pane.lookupButton(ButtonType.OK).setDisable(true);
					}else {
						pane.lookupButton(ButtonType.OK).setDisable(false);
					}	
			    } 
			    catch (NumberFormatException | NullPointerException e){menge.setText(o);}
			}
		});
		
		preis.setPromptText("Preis");
		preis.textProperty().addListener((ob, o, n) -> {
			if(!n.equals("")) {
			    try {
			    	Float.parseFloat(n);
					if("".equals(menge.getText()) || "".equals(preis.getText()) || "".equals(lieferdatum.getText())) {
						pane.lookupButton(ButtonType.OK).setDisable(true);
					}else {
						pane.lookupButton(ButtonType.OK).setDisable(false);
					}		
			    } 
			    catch (NumberFormatException | NullPointerException e){menge.setText(o);}
			}
		});
		warennummer.setPromptText("Warennummer");
		warennummer.setEditable(false);
		einheit.setPromptText("Einheit");
		
		lieferdatum.setPromptText("Lieferdatum");
		lieferdatum.textProperty().addListener((ob, o, n) -> {
			if("".equals(menge.getText()) || "".equals(preis.getText()) || "".equals(lieferdatum.getText())) {
				pane.lookupButton(ButtonType.OK).setDisable(true);
			}else {
				pane.lookupButton(ButtonType.OK).setDisable(false);
			}
		});
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
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		        return new WarenbestellungenRecord(-1,""
		        		,warennummer.getText()
		        		,bezeichnung.getText()
		        		,menge.getText()
		        		,einheit.getText()
		        		,preis.getText()
		        		,"EUR"
		        		,dateFormat.format(new Date())
		        		,lieferdatum.getText()
		        		,Zustand.getInstance().getBenutzer().getBenutzername());
		    }
		    return null;
		});
		return dialog;
	}
}
