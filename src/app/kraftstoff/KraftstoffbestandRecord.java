package app.kraftstoff;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class KraftstoffbestandRecord {

    private SimpleIntegerProperty index;
    private SimpleStringProperty warennummer;
    private SimpleStringProperty bezeichnung;
    private SimpleStringProperty einheit;
    private SimpleStringProperty menge;
    private SimpleStringProperty preis;
    private SimpleStringProperty waehrung;
    private SimpleStringProperty tank;
    private SimpleStringProperty kapazitaet;

    public KraftstoffbestandRecord(int index, String warennummer, String bezeichnung, String einheit, String menge, String preis, String waehrung, String tank, String kapazitaet){
    	this.index = new SimpleIntegerProperty(index);
    	this.warennummer = new SimpleStringProperty(warennummer);
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        this.einheit = new SimpleStringProperty(einheit);
        this.menge = new SimpleStringProperty(menge);
        this.preis = new SimpleStringProperty(preis);
        this.waehrung = new SimpleStringProperty(waehrung);
        this.tank = new SimpleStringProperty(tank);
        this.kapazitaet = new SimpleStringProperty(kapazitaet);
    }

    KraftstoffbestandRecord(int index, String[] record){
    	this.index = new SimpleIntegerProperty(index);
        this.warennummer = new SimpleStringProperty(record[0]);
        this.bezeichnung = new SimpleStringProperty(record[1]);
        this.einheit = new SimpleStringProperty(record[2]);
        this.menge = new SimpleStringProperty(record[3]);
        this.preis = new SimpleStringProperty(record[4]);
        this.waehrung = new SimpleStringProperty(record[5]);
        this.tank = new SimpleStringProperty(record[6]);
        this.kapazitaet = new SimpleStringProperty(record[7]);
    }

    public int getIndex() { return this.index.get(); }

    public KraftstoffbestandRecord setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
        return this;
    }
    
    public String getWarennummer() { return this.warennummer.get(); }

    public KraftstoffbestandRecord setWarennummer(String warennummer) {
        this.warennummer = new SimpleStringProperty(warennummer);
        return this;
    }

    public String getBezeichnung() { return this.bezeichnung.get(); }

    public KraftstoffbestandRecord setBezeichnung(String bezeichnung) {
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        return this;
    }

    public String getEinheit(){return this.einheit.get(); }

    public KraftstoffbestandRecord setEinheit(String einheit) {
        this.einheit = new SimpleStringProperty(einheit);
        return this;
    }

    public String getMenge(){return this.menge.get(); }

    public KraftstoffbestandRecord setMenge(String menge) {
        this.menge = new SimpleStringProperty(menge);
        return this;
    }

    public String getPreis(){return this.preis.get();}

    public KraftstoffbestandRecord setPreis(String preis) {
        this.preis = new SimpleStringProperty(preis);
        return this;
    }
    
    public String getWaehrung(){return this.waehrung.get();}

    public KraftstoffbestandRecord setWaehrung(String waehrung) {
        this.waehrung = new SimpleStringProperty(waehrung);
        return this;
    }
    
    public String getTank(){return this.tank.get();}

    public KraftstoffbestandRecord setTank(String tank) {
        this.tank = new SimpleStringProperty(tank);
        return this;
    }
    
    public String getKapazitaet(){return this.kapazitaet.get();}

    public KraftstoffbestandRecord setKapazitaet(String kapazitaet) {
        this.kapazitaet = new SimpleStringProperty(kapazitaet);
        return this;
    }
}
