package app.settings;
/**
 * Current state holder object (singleton).
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class Settings {
	
	private SettingsTable table = new SettingsTable();
	/**
	 * Design getter.
	 * */
	public String getDesign() {return table.onRead(table.getIndex("design")).getValue();}
	/**
	 * Design setter.
	 * */
	public Settings setDesign(String design) {
		int index = table.getIndex("design");
		table.onUpdate(index,new SettingsRecord().setValue(design));
		return this;
	}
	/**
	 * Print path getter.
	 * */
	public String getLastView() {return table.onRead(table.getIndex("view")).getValue();}
	/**
	 * Print path setter.
	 * */
	public Settings setLastView(String view) {
		int index = table.getIndex("view");
		table.onUpdate(index,new SettingsRecord().setValue(view));
		return this;
	}
	/**
	 * Print path getter.
	 * */
	public String getPrintUrl() {return table.onRead(table.getIndex("print-url")).getValue();}
	/**
	 * Print path setter.
	 * */
	public Settings setPrintUrl(String printUrl) {
		int index = table.getIndex("print-url");
		table.onUpdate(index,new SettingsRecord().setValue(printUrl));
		return this;
	}
	
	public String getBenutzer() {return table.onRead(table.getIndex("user")).getValue();}
	
	public Settings setBenutzer(String benutzer) {
		int index = table.getIndex("user");
		table.onUpdate(index,new SettingsRecord().setValue(benutzer));
		return this;
	}
	/**
	 * Singleton instance.
	 * */
	private static Settings instance = new Settings();
	/**
	 * Singleton instance getter.
	 * */
	public static Settings getInstance() { return instance; }
}
