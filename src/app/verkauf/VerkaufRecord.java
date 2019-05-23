package app.verkauf;

import app.Model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class VerkaufRecord implements Model {

	private SimpleIntegerProperty index;
	private SimpleStringProperty warennummer;
	private SimpleStringProperty bezeichnung;
	private SimpleStringProperty preis;
	private SimpleStringProperty waehrung;
	private SimpleStringProperty menge;
	private SimpleStringProperty einheit;
	private SimpleStringProperty summe;
	private SimpleStringProperty belegnummer;
	private SimpleStringProperty datum;
	private SimpleStringProperty uhrzeit;
	private SimpleStringProperty mitarbeiter;
	
    public VerkaufRecord(int index, String warennummer, String bezeichnung, String preis, String waehrung, String menge, String einheit, String summe){
    	this.index = new SimpleIntegerProperty(index);
        this.warennummer = new SimpleStringProperty(warennummer);
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        this.preis = new SimpleStringProperty(preis);
        this.waehrung = new SimpleStringProperty(waehrung);
        this.menge = new SimpleStringProperty(menge);
        this.einheit = new SimpleStringProperty(einheit);
        this.summe = new SimpleStringProperty(summe);
    }

    public VerkaufRecord(int index, String[] record){
    	this.index = new SimpleIntegerProperty(index);
        this.warennummer = new SimpleStringProperty(record[0]);
        this.bezeichnung = new SimpleStringProperty(record[1]);
        this.preis = new SimpleStringProperty(record[2]);
        this.waehrung = new SimpleStringProperty(record[3]);
        this.menge = new SimpleStringProperty(record[4]);
        this.einheit = new SimpleStringProperty(record[5]);
        this.summe = new SimpleStringProperty(record[6]);
        this.belegnummer = new SimpleStringProperty(record[7]);
        this.datum = new SimpleStringProperty(record[8]);
        this.uhrzeit = new SimpleStringProperty(record[9]);
        this.mitarbeiter = new SimpleStringProperty(record[10]);
    }
	
    public int getIndex() { return this.index.get(); }

    public VerkaufRecord setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
        return this;
    }
    
    public String getWarennummer() { return this.warennummer.get(); }
    
    public VerkaufRecord setWarennummer(String warennummer) {
        this.warennummer = new SimpleStringProperty(warennummer);
        return this;
    }
	
    public String getBezeichnung() { return this.bezeichnung.get(); }
    
    public VerkaufRecord setBezeichnung(String bezeichnung) {
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        return this;
    }
    
    public String getPreis() { return this.preis.get(); }
    
    public VerkaufRecord setPreis(String preis) {
        this.preis = new SimpleStringProperty(preis);
        return this;
    }
    
    public String getMenge() { return this.menge.get(); }
    
    public VerkaufRecord setMenge(String menge) {
        this.menge = new SimpleStringProperty(menge);
        return this;
    }
    
    public String getEinheit() { return this.einheit.get(); }
    
    public VerkaufRecord setEinheit(String einheit) {
        this.einheit = new SimpleStringProperty(einheit);
        return this;
    }
    
    public String getSumme() { return this.summe.get(); }
    
    public VerkaufRecord setSumme(String summe) {
        this.summe = new SimpleStringProperty(summe);
        return this;
    }
    
    public String getBelegnummer() { return this.belegnummer.get(); }
    
    public VerkaufRecord setBelegnummer(String belegnummer) {
        this.belegnummer = new SimpleStringProperty(belegnummer);
        return this;
    }
    
    public String getDatum() { return this.datum.get(); }
    
    public VerkaufRecord setDatum(String datum) {
        this.datum = new SimpleStringProperty(datum);
        return this;
    }
    
    public String getUhrzeit() { return this.uhrzeit.get(); }
    
    public VerkaufRecord setUhrzeit(String uhrzeit) {
        this.uhrzeit = new SimpleStringProperty(uhrzeit);
        return this;
    }
    
    public String getMitarbeiter() { return this.mitarbeiter.get(); }
    
    public VerkaufRecord setMitarbeiter(String mitarbeiter) {
        this.mitarbeiter = new SimpleStringProperty(mitarbeiter);
        return this;
    }
        
	@Override
	public String toString() { 
		return "idx:" + index.get() + ","
			 + "wn:" + warennummer.get() + ","
			 + "bez:" + bezeichnung.get() + ","
			 + "preis:" + preis.get() + ","
			 + "cur:" + waehrung.get() + ","
			 + "menge:" + menge.get() + ","
			 + "e:" + einheit.get() + ","
			 + "sum:" + summe.get() + ","
			 + "bn:" + belegnummer.get() + ","
			 + "date:" + datum.get() + ","
			 + "time:" + uhrzeit.get() + ","
			 + "ma:" + mitarbeiter.get();
	}
    
	@Override
	public String[] toArray() {
		return new String[]{
			 warennummer.get()
			,bezeichnung.get()
			,preis.get()
			,waehrung.get()
			,menge.get()
			,einheit.get()
			,summe.get()
			,belegnummer.get()
			,datum.get()
			,uhrzeit.get()
			,mitarbeiter.get()
		};
	}
}
