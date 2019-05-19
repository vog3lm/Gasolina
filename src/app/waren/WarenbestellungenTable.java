package app.waren;

import java.util.ArrayList;

import app.database.DatabaseConnection;
import app.kraftstoff.KraftstoffbestellungenRecord;
import app.Zustand;
import app.database.Database;

public class WarenbestellungenTable implements Database<WarenbestellungenRecord> {

    private final String filename = Zustand.getInstance().getDatabaseUrl()+"gs_waren_bestellungen.csv";
    private final DatabaseConnection database = new DatabaseConnection(filename);
    private ArrayList<WarenbestellungenRecord> records = new ArrayList<WarenbestellungenRecord>();

    WarenbestellungenTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
            this.records.add(new WarenbestellungenRecord(i,data.get(i)));
        }
    }

    @Override
    public WarenbestellungenRecord onRead(int index) { return this.records.get(index); }

    public ArrayList<WarenbestellungenRecord> onReadAll() { return this.records; }
    
    @Override
    public int onCreate(WarenbestellungenRecord record){
        int index = this.records.size();
        this.records.add(record.setIndex(index));
        return index;
    }

    @Override
    public void onUpdate(int index, WarenbestellungenRecord record){
        this.records.get(index)
                .setBestellnummer(record.getBestellnummer())
                .setWarennummer(record.getWarennummer())
                .setBezeichnung(record.getBezeichnung())
                .setMenge(record.getMenge())
                .setPreis(record.getPreis())
                .setBestelldatum(record.getBestelldatum())
                .setLieferdatum(record.getLieferdatum());
    }

    @Override
    public void onDelete(int index) {
        this.records.remove(index);
        for(int i=0; i < this.records.size(); i++){
            this.records.get(i).setIndex(i);
        }
    }

    @Override
    public void onCommit() {
        ArrayList<String[]> records = new ArrayList<String[]>();
        for(WarenbestellungenRecord r : this.records){
            records.add(new String[]{r.getBestellnummer(),r.getWarennummer(),r.getBezeichnung(),r.getPreis(),r.getWaehrung(),r.getMenge(),r.getBestelldatum(),r.getLieferdatum()});
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return this.filename; }
    
	@Override
	public int getIndex(WarenbestellungenRecord record) {
		return this.records.indexOf(record);
	}
	
	@Override
	public int getIndex(String warennummer) {
		/* TODO: search by ... */
		return -1;
	}
}
