package app;

import app.personal.PersonalRecord;

public class Einstellungen {

	private String databaseUrl = "src/app/database/";
	
	private String printUrl = "src/app/print/";
	
	public final static String LIGHT = "app/style/app.main.light.css";
	public final static String DARK = "app/style/app.main.dark.css";
	
	private String design = DARK;
	
	private PersonalRecord benutzer = null;
	
	public String getDesign() { return this.design; }
	
	public Einstellungen setDesign(String design) {
		this.design = design;
		return this;
	}
	
	public PersonalRecord getBenutzer() { return this.benutzer; }
	
	public Einstellungen setBenutzer(PersonalRecord benutzer) {
		this.benutzer = benutzer;
		return this;
	}
	
	public String getPrintUrl() { return this.printUrl; }
	
	public Einstellungen setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
		return this;
	}
	
	public String getDatabaseUrl() { return this.databaseUrl; }
	
	public Einstellungen setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
		return this;
	}
	
	private static Einstellungen instance = new Einstellungen();
	
	public static Einstellungen getInstance() { return instance; }
}
