package app.waren;

import java.util.ArrayList;

import app.database.DatabaseConnection;
import app.Einstellungen;
import app.database.Database;

public class WarenbestandTable implements Database<WarenbestandRecord> {

    private final String filename = Einstellungen.getInstance().getDatabaseUrl()+"gs_waren.csv";
    private final DatabaseConnection database = new DatabaseConnection(filename);
    private ArrayList<WarenbestandRecord> records = new ArrayList<WarenbestandRecord>();

    public WarenbestandTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
            this.records.add(new WarenbestandRecord(i,data.get(i)));
        }
    }

    @Override
    public WarenbestandRecord onRead(int index) { return this.records.get(index); }
    
    @Override
    public ArrayList<WarenbestandRecord> onReadAll() { return this.records; }

    @Override
    public int onCreate(WarenbestandRecord record){
        int warennummer = this.records.size();
        this.records.add(record.setWarennummer(warennummer+""));
        return warennummer;
    }

    @Override
    public void onUpdate(int index, WarenbestandRecord record){
        WarenbestandRecord r = this.records.get(index);
        r.setBezeichnung(record.getBezeichnung());
        r.setEinheit(record.getEinheit());
        r.setMenge(record.getMenge());
        r.setPreis(record.getPreis());
        r.setKategorie(record.getKategorie());
    }

    @Override
    public void onDelete(int index){
        this.records.remove(index);
        for(int i=0; i < this.records.size(); i++){
            this.records.get(i).setWarennummer(i+"");
        }
    }

    @Override
    public void onCommit(){
        ArrayList<String[]> records = new ArrayList<String[]>();
        for(WarenbestandRecord r : this.records){
            records.add(new String[]{r.getWarennummer(),r.getBezeichnung(),r.getEinheit(),r.getMenge(),r.getPreis(),r.getWaehrung(),r.getKategorie()});
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return this.filename; }

	@Override
	public int getIndex(WarenbestandRecord record) {
		return this.records.indexOf(record);
	}
	
	@Override
	public int getIndex(String bezeichnung) {
		for(int i=0; i<records.size(); i++) {
			if(bezeichnung.equals(records.get(i).getBezeichnung())) {
				return i;
			}
		}
		return -1;
	}
}
