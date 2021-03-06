package app.kraftstoff;

import app.Model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class KraftstoffbestellungenRecord implements Model {

    private SimpleIntegerProperty index;
    private SimpleStringProperty bestellnummer;
    private SimpleStringProperty warennummer;
    private SimpleStringProperty bezeichnung;
    private SimpleStringProperty preis;
    private SimpleStringProperty waehrung;
    private SimpleStringProperty menge;
    private SimpleStringProperty einheit;
    private SimpleStringProperty bestelldatum;
    private SimpleStringProperty lieferdatum;
    private SimpleStringProperty mitarbeiter;
    private SimpleStringProperty status;
    
    public KraftstoffbestellungenRecord(int index, String bestellnummer, String warennummer, String bezeichnung, String preis, String waehrung, String menge, String einheit, String bestelldatum, String lieferdatum, String mitarbeiter, String status){
        this.index = new SimpleIntegerProperty(index);
    	this.bestellnummer = new SimpleStringProperty(bestellnummer);
        this.warennummer = new SimpleStringProperty(warennummer);
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        this.menge = new SimpleStringProperty(menge);
        this.einheit = new SimpleStringProperty(einheit);
        this.preis = new SimpleStringProperty(preis);
        this.waehrung = new SimpleStringProperty(waehrung);
        this.bestelldatum = new SimpleStringProperty(bestelldatum);
        this.lieferdatum = new SimpleStringProperty(lieferdatum);
        this.mitarbeiter = new SimpleStringProperty(mitarbeiter);
        this.status = new SimpleStringProperty(status);
    }

    KraftstoffbestellungenRecord(int index, String[] record){
    	this.index = new SimpleIntegerProperty(index);
        this.bestellnummer = new SimpleStringProperty(record[0]);
        this.warennummer = new SimpleStringProperty(record[1]);
        this.bezeichnung = new SimpleStringProperty(record[2]);
        this.preis = new SimpleStringProperty(record[3]);
        this.waehrung = new SimpleStringProperty(record[4]);
        this.menge = new SimpleStringProperty(record[5]);
        this.einheit = new SimpleStringProperty(record[6]);
        this.bestelldatum = new SimpleStringProperty(record[7]);
        this.lieferdatum = new SimpleStringProperty(record[8]);
        this.mitarbeiter = new SimpleStringProperty(record[9]);
        this.mitarbeiter = new SimpleStringProperty(record[10]);
    }

    int getIndex() { return this.index.get(); }

    KraftstoffbestellungenRecord setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
        return this;
    }

    public String getBestellnummer() { return this.bestellnummer.get(); }

    public KraftstoffbestellungenRecord setBestellnummer(String bestellnummer) {
        this.bestellnummer = new SimpleStringProperty(bestellnummer);
        return this;
    }

    public String getWarennummer() { return this.warennummer.get(); }

    public KraftstoffbestellungenRecord setWarennummer(String warennummer) {
        this.warennummer = new SimpleStringProperty(warennummer);
        return this;
    }

    public String getBezeichnung() { return this.bezeichnung.get(); }

    public KraftstoffbestellungenRecord setBezeichnung(String bezeichnung) {
        this.bezeichnung = new SimpleStringProperty(bezeichnung);
        return this;
    }

    public String getMenge(){return this.menge.get(); }

    public KraftstoffbestellungenRecord setMenge(String menge) {
        this.menge = new SimpleStringProperty(menge);
        return this;
    }

    public String getEinheit(){return this.einheit.get();}

    public KraftstoffbestellungenRecord setEinheit(String einheit) {
        this.einheit = new SimpleStringProperty(einheit);
        return this;
    }
    
    public String getPreis(){return this.preis.get();}

    public KraftstoffbestellungenRecord setPreis(String preis) {
        this.preis = new SimpleStringProperty(preis);
        return this;
    }
    
    public String getWaehrung(){return this.waehrung.get();}

    public KraftstoffbestellungenRecord setWaehrung(String waehrung) {
        this.waehrung = new SimpleStringProperty(waehrung);
        return this;
    }

    public String getBestelldatum(){return this.bestelldatum.get(); }

    public KraftstoffbestellungenRecord setBestelldatum(String bestelldatum) {
        this.bestelldatum = new SimpleStringProperty(bestelldatum);
        return this;
    }

    public String getLieferdatum(){ return this.lieferdatum.get();}

    public KraftstoffbestellungenRecord setLieferdatum(String lieferdatum) {
        this.lieferdatum = new SimpleStringProperty(lieferdatum);
        return this;
    }
    
    public String getMitarbeiter(){ return this.mitarbeiter.get();}

    public KraftstoffbestellungenRecord setMitarbeiter(String mitarbeiter) {
        this.mitarbeiter = new SimpleStringProperty(mitarbeiter);
        return this;
    }
    
    public String getStatus(){ return this.status.get();}

    public KraftstoffbestellungenRecord setStatus(String status) {
        this.status = new SimpleStringProperty(status);
        return this;
    }
       
	@Override
	public String toString() { 
		return "idx:" + index.get() + ","
			 + "bn:" + bestellnummer.get() + ","
			 + "wn:" + warennummer.get() + ","
			 + "bez:" + bezeichnung.get() + ","
			 + "preis:" + preis.get() + ","
			 + "cur:" + waehrung.get() + ","
			 + "menge:" + menge.get() + ","
			 + "e:" + einheit.get() + ","
			 + "b-date:" + bestelldatum.get() + ","
			 + "l-date:" + lieferdatum.get() + ","
			 + "ma:" + mitarbeiter.get() + ","
			 + "state:" + status.get();
	}
    
	@Override
	public String[] toArray() {
		return new String[]{
			 bestellnummer.get()
			,warennummer.get()
			,bezeichnung.get()
			,preis.get()
			,waehrung.get()
			,menge.get()
			,einheit.get()
			,bestelldatum.get()
			,lieferdatum.get()
			,mitarbeiter.get()
			,status.get()
		};
	}
}
