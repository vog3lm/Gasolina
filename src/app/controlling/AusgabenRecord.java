package app.controlling;

import app.verkauf.VerkaufRecord;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AusgabenRecord {
	
	private SimpleIntegerProperty index;
	private SimpleStringProperty warennummer;
	private SimpleStringProperty bezeichnung;
	private SimpleStringProperty preis;
	private SimpleStringProperty menge;
	private SimpleStringProperty einheit;
	private SimpleStringProperty summe;
	private SimpleStringProperty belegnummer;
	private SimpleStringProperty datum;
	private SimpleStringProperty uhrzeit;
	private SimpleStringProperty mitarbeiter;
	
    public AusgabenRecord(int index, String warennummer, String bezeichnung, String preis, String menge, String einheit, String summe){
    	this.index = new SimpleIntegerProperty(index);
        this.warennummer = new SimpleStringProperty(warennummer);
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        this.preis = new SimpleStringProperty(preis);
        this.menge = new SimpleStringProperty(menge);
        this.einheit = new SimpleStringProperty(einheit);
        this.summe = new SimpleStringProperty(summe);
    }

    public AusgabenRecord(int index, String[] record){
    	this.index = new SimpleIntegerProperty(index);
    	/* kasse values */
        this.warennummer = new SimpleStringProperty(record[0]);
        this.bezeichnung = new SimpleStringProperty(record[1]);
        this.preis = new SimpleStringProperty(record[2]);
        this.menge = new SimpleStringProperty(record[3]);
        this.einheit = new SimpleStringProperty(record[4]);
        this.summe = new SimpleStringProperty(record[5]);
        /* journal values */
        this.belegnummer = new SimpleStringProperty(record[6]);
        this.datum = new SimpleStringProperty(record[7]);
        this.uhrzeit = new SimpleStringProperty(record[8]);
        this.mitarbeiter = new SimpleStringProperty(record[9]);
    }
	
    public int getIndex() { return this.index.get(); }

    public AusgabenRecord setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
        return this;
    }
    
    public String getWarennummer() { return this.warennummer.get(); }
    
    public AusgabenRecord setWarennummer(String warennummer) {
        this.warennummer = new SimpleStringProperty(warennummer);
        return this;
    }
	
    public String getBezeichnung() { return this.bezeichnung.get(); }
    
    public AusgabenRecord setBezeichnung(String bezeichnung) {
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        return this;
    }
    
    public String getPreis() { return this.preis.get(); }
    
    public AusgabenRecord setPreis(String preis) {
        this.preis = new SimpleStringProperty(preis);
        return this;
    }
    
    public String getMenge() { return this.menge.get(); }
    
    public AusgabenRecord setMenge(String menge) {
        this.menge = new SimpleStringProperty(menge);
        return this;
    }
    
    public String getEinheit() { return this.einheit.get(); }
    
    public AusgabenRecord setEinheit(String einheit) {
        this.einheit = new SimpleStringProperty(einheit);
        return this;
    }
    
    public String getSumme() { return this.summe.get(); }
    
    public AusgabenRecord setSumme(String summe) {
        this.summe = new SimpleStringProperty(summe);
        return this;
    }
    
    public String getBelegnummer() { return this.belegnummer.get(); }
    
    public AusgabenRecord setBelegnummer(String belegnummer) {
        this.belegnummer = new SimpleStringProperty(belegnummer);
        return this;
    }
    
    public String getDatum() { return this.datum.get(); }
    
    public AusgabenRecord setDatum(String datum) {
        this.datum = new SimpleStringProperty(datum);
        return this;
    }
    
    public String getUhrzeit() { return this.uhrzeit.get(); }
    
    public AusgabenRecord setUhrzeit(String uhrzeit) {
        this.uhrzeit = new SimpleStringProperty(uhrzeit);
        return this;
    }
    
    public String getMitarbeiter() { return this.mitarbeiter.get(); }
    
    public AusgabenRecord setMitarbeiter(String mitarbeiter) {
        this.mitarbeiter = new SimpleStringProperty(mitarbeiter);
        return this;
    }
}
