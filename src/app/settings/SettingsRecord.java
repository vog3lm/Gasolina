package app.settings;

import app.Model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SettingsRecord implements Model {

	private SimpleIntegerProperty index;
	private SimpleStringProperty key;
	private SimpleStringProperty val;
	private SimpleStringProperty def;
	
    public SettingsRecord(){}
	
    public SettingsRecord(int index, String[] record){
    	this.index = new SimpleIntegerProperty(index);
    	this.key = new SimpleStringProperty(record[0]);
    	this.val = new SimpleStringProperty(record[1]);
    	this.def = new SimpleStringProperty(record[2]);
    }
	
    public int getIndex() { return this.index.get(); }
    
    public String getKey() { return this.key.get(); }
    
    public SettingsRecord setKey(String key) {
        this.key = new SimpleStringProperty(key);
        return this;
    }
    
    public String getValue() { return this.val.get(); }
    
    public SettingsRecord setValue(String val) {
        this.val = new SimpleStringProperty(val);
        return this;
    }
    
    public String getDefault() { return this.def.get(); }
    
    public SettingsRecord setDefault(String def) {
        this.def = new SimpleStringProperty(def);
        return this;
    }

	@Override
	public String toString() { 
		return "idx:" + index.get() + ","
			 + "key:" + key.get() + ","
			 + "value:" + val.get() + ","
			 + "default:" + def.get();
	}
    
	@Override
	public String[] toArray() {
		return new String[]{
			 key.get()
			,val.get()
			,def.get()
		};
	}
}
