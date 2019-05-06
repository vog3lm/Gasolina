package app.waren;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WarenbestandRecord {

	private SimpleIntegerProperty index;
    private SimpleStringProperty warennummer;
    private SimpleStringProperty bezeichnung;
    private SimpleStringProperty einheit;
    private SimpleStringProperty menge;
    private SimpleStringProperty preis;
    private SimpleStringProperty kategorie;
    private SimpleStringProperty waehrung;

    WarenbestandRecord(int index, String warennummer, String bezeichnung, String einheit, String menge, String preis, String waehrung, String kategorie){
    	this.index = new SimpleIntegerProperty(index);
    	this.warennummer = new SimpleStringProperty(warennummer);
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        this.einheit = new SimpleStringProperty(einheit);
        this.menge = new SimpleStringProperty(menge);
        this.preis = new SimpleStringProperty(preis);
        this.waehrung = new SimpleStringProperty(kategorie);
        this.kategorie = new SimpleStringProperty(kategorie);
    }

    WarenbestandRecord(int index, String[] record){
    	this.index = new SimpleIntegerProperty(index);
        this.warennummer = new SimpleStringProperty(record[0]);
        this.bezeichnung = new SimpleStringProperty(record[1]);
        this.einheit = new SimpleStringProperty(record[2]);
        this.menge = new SimpleStringProperty(record[3]);
        this.preis = new SimpleStringProperty(record[4]);
        this.waehrung = new SimpleStringProperty(record[5]);
        this.kategorie = new SimpleStringProperty(record[6]);
    }

    public int getIndex() { return this.index.get(); }

    public WarenbestandRecord setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
        return this;
    }
    
    public String getWarennummer() { return this.warennummer.get(); }

    public WarenbestandRecord setWarennummer(String warennummer) {
        this.warennummer = new SimpleStringProperty(warennummer);
        return this;
    }

    public String getBezeichnung() { return this.bezeichnung.get(); }

    public WarenbestandRecord setBezeichnung(String bezeichnung) {
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        return this;
    }

    public String getEinheit(){return this.einheit.get(); }

    public WarenbestandRecord setEinheit(String einheit) {
        this.einheit = new SimpleStringProperty(einheit);
        return this;
    }

    public String getMenge(){return this.menge.get(); }

    public WarenbestandRecord setMenge(String menge) {
        this.menge = new SimpleStringProperty(menge);
        return this;
    }

    public String getPreis(){return this.preis.get(); }

    public WarenbestandRecord setPreis(String preis) {
        this.preis = new SimpleStringProperty(preis);
        return this;
    }
    
    public String getWaehrung(){return this.waehrung.get();}

    public WarenbestandRecord setWaehrung(String waehrung) {
        this.waehrung = new SimpleStringProperty(waehrung);
        return this;
    }

    public String getKategorie(){return this.kategorie.get(); }

    public WarenbestandRecord setKategorie(String kategorie) {
        this.kategorie = new SimpleStringProperty(kategorie);
        return this;
    }
}
