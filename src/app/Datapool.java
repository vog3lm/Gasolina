package app;

import app.controlling.AusgabenTable;
import app.csv.CsvConnection;
import app.kraftstoff.KraftstoffbestandTable;
import app.kraftstoff.KraftstoffbestellungenTable;
import app.personal.PersonalTable;
import app.settings.SettingsTable;
import app.verkauf.VerkaufTable;
import app.waren.WarenbestandTable;
import app.waren.WarenbestellungenTable;

public class Datapool {

	private AusgabenTable ausgaben;
	
	private VerkaufTable einnahmen;
	
	private KraftstoffbestandTable kraftstoffBestand;
	
	private KraftstoffbestellungenTable kraftstoffbestellungen;
	
	private PersonalTable personalBestand;
	
	private SettingsTable settings;
	
	private WarenbestandTable warenBestand;
	
	private WarenbestellungenTable warenBestellungen;
		
	private void onAquire(String id){
		if(CsvConnection.AUSGABEN.equals(id)) {ausgaben = new AusgabenTable();} 
		else if(CsvConnection.EINNAHMEN.equals(id)) {einnahmen = new VerkaufTable();}
		else if(CsvConnection.KRAFTSTOFF.equals(id)) {kraftstoffBestand = new KraftstoffbestandTable();}
		else if(CsvConnection.KRAFTSTOFF_BESTELLUNGEN.equals(id)) {kraftstoffbestellungen = new KraftstoffbestellungenTable();}
		else if(CsvConnection.PERSONAL.equals(id)) {personalBestand = new PersonalTable();}
		else if(CsvConnection.SETTINGS.equals(id)) {settings = new SettingsTable();}
		else if(CsvConnection.WAREN.equals(id)) {warenBestand = new WarenbestandTable();}
		else if(CsvConnection.WAREN_BESTELLUNGEN.equals(id)) {warenBestellungen = new WarenbestellungenTable();}
	}
	
	public AusgabenTable aquireAusgaben() {
		if(null == ausgaben) {onAquire(CsvConnection.AUSGABEN);}
		return ausgaben;
	}
	
	public VerkaufTable aquireVerkauf() {
		if(null == einnahmen) {onAquire(CsvConnection.EINNAHMEN);}
		return einnahmen;
	}
	
	public KraftstoffbestandTable aquireKraftstoffbestand() {
		if(null == kraftstoffBestand) {onAquire(CsvConnection.KRAFTSTOFF);}
		return kraftstoffBestand;
	}
	
	public KraftstoffbestellungenTable aquireKraftstoffbestellungen() {
		if(null == kraftstoffbestellungen) {onAquire(CsvConnection.KRAFTSTOFF_BESTELLUNGEN);}
		return kraftstoffbestellungen;
	}
	
	public PersonalTable aquirePersonal() {
		if(null == personalBestand) {onAquire(CsvConnection.PERSONAL);}
		return personalBestand;
	}
	
	public SettingsTable aquireSettings() {
		if(null == settings) {onAquire(CsvConnection.SETTINGS);}
		return settings;
	}
	
	public WarenbestandTable aquireWarenbestand() {
		if(null == warenBestand) {onAquire(CsvConnection.WAREN);}
		return warenBestand;
	}
	
	public WarenbestellungenTable aquireWarenbestellungen() {
		if(null == warenBestellungen) {onAquire(CsvConnection.WAREN_BESTELLUNGEN);}
		return warenBestellungen;
	}
	
}
