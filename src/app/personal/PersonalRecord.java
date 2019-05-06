package app.personal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonalRecord {

	private SimpleIntegerProperty index;
	private SimpleStringProperty personalnummer;
	private SimpleStringProperty benutzername;
    private SimpleStringProperty vorname;
    private SimpleStringProperty nachname;
    private SimpleStringProperty passwort;
    private SimpleStringProperty einstelldatum;

    public PersonalRecord(int index, String personalnummer, String benutzername, String vorname, String nachname, String passwort, String einstelldatum){
    	this.index = new SimpleIntegerProperty(index);
        this.personalnummer = new SimpleStringProperty(personalnummer);
        this.benutzername = new SimpleStringProperty(benutzername);
        this.vorname = new SimpleStringProperty(vorname);
        this.nachname = new SimpleStringProperty(nachname);
        this.passwort = new SimpleStringProperty(passwort);
        this.einstelldatum = new SimpleStringProperty(einstelldatum);
    }

    public PersonalRecord(int index, String[] record){
    	this.index = new SimpleIntegerProperty(index);
        this.personalnummer = new SimpleStringProperty(record[0]);
        this.benutzername = new SimpleStringProperty(record[1]);
        this.vorname = new SimpleStringProperty(record[2]);
        this.nachname = new SimpleStringProperty(record[3]);
        this.passwort = new SimpleStringProperty(record[4]);
        this.einstelldatum = new SimpleStringProperty(record[5]);
    }

    public int getIndex() { return this.index.get(); }

    public PersonalRecord setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
        return this;
    }
    
    public String getBenutzername() { return this.benutzername.get(); }
    
    public PersonalRecord setBenutzername(String benutzername) {
        this.benutzername = new SimpleStringProperty(benutzername);
        return this;
    }
    
    public String getPersonalnummer() { return this.personalnummer.get(); }
    
    public PersonalRecord setPersonalnummer(String personalnummer) {
        this.personalnummer = new SimpleStringProperty(personalnummer);
        return this;
    }

    public String getVorname() { return this.vorname.get(); }

    public PersonalRecord setVorname(String vorname) {
        this.vorname = new SimpleStringProperty(vorname);
        return this;
    }

    public String getNachname() { return this.nachname.get(); }

    public PersonalRecord setNachname(String nachname) {
        this.nachname = new SimpleStringProperty(nachname);
        return this;
    }

    public String getPasswort() { return this.passwort.get(); }
       
    public PersonalRecord setPasswort(String passwort) {
        this.passwort = new SimpleStringProperty(passwort);
        return this;
    }
    
    public String getEinstelldatum() { return this.einstelldatum.get(); }
    
    public PersonalRecord setEinstelldatum(String einstelldatum) {
        this.einstelldatum = new SimpleStringProperty(einstelldatum);
        return this;
    }
}
