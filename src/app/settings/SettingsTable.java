package app.settings;

import java.util.ArrayList;

import app.Database;
import app.csv.CsvConnection;

public class SettingsTable implements Database<SettingsRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.SETTINGS);
    private ArrayList<SettingsRecord> records = new ArrayList<SettingsRecord>();
	
    public SettingsTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
        	this.records.add(new SettingsRecord(i,data.get(i)));
        }
    }

	@Override
	public SettingsRecord onRead(int index) {return this.records.get(index);}

	@Override
	public ArrayList<SettingsRecord> onRead() {return records;}

	@Override
	public int onCreate(SettingsRecord record) {
		/* no new settings createable */
		return -1;
	}

	@Override
	public void onUpdate(int index, SettingsRecord record) {
		this.records.get(index).setValue(record.getValue());
	}

	@Override
	public void onDelete(int index) {
        for(SettingsRecord r : this.records){
            r.setValue(r.getDefault());
        }
	}

	@Override
	public void onCommit() {
        ArrayList<String[]> records = new ArrayList<String[]>();
        for(SettingsRecord r : this.records){
            records.add(r.toArray());
        }
        database.onWrite(records);
	}

	@Override
	public int getIndex(SettingsRecord record) {return this.records.indexOf(record);}

	@Override
	public int getIndex(String key) {
		for(int i=0; i<records.size(); i++) {
			if(key.equals(records.get(i).getKey())) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getCount() {return records.size();}

	@Override
	public String getUrl() {return CsvConnection.SETTINGS;}
}
