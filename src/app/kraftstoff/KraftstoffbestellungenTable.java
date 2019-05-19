package app.kraftstoff;

import java.util.ArrayList;

import app.Zustand;
import app.database.Database;
import app.database.DatabaseConnection;

public class KraftstoffbestellungenTable implements Database<KraftstoffbestellungenRecord> {

    private final String filename = Zustand.getInstance().getDatabaseUrl()+"gs_kraftstoff_bestellungen.csv";
    private final DatabaseConnection database = new DatabaseConnection(filename);
    private ArrayList<KraftstoffbestellungenRecord> records = new ArrayList<KraftstoffbestellungenRecord>();

    public KraftstoffbestellungenTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
            this.records.add(new KraftstoffbestellungenRecord(i,data.get(i)));
        }
    }

    @Override
    public KraftstoffbestellungenRecord onRead(int index) { return this.records.get(index); }

    @Override
    public ArrayList<KraftstoffbestellungenRecord> onReadAll() { return this.records; }
    
    @Override
    public int onCreate(KraftstoffbestellungenRecord record){
        int index = this.records.size();
        this.records.add(record.setIndex(index));
        return index;
    }

    @Override
    public void onUpdate(int index, KraftstoffbestellungenRecord record){
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
        for(KraftstoffbestellungenRecord r : this.records){
            records.add(new String[]{r.getBestellnummer(),r.getWarennummer(),r.getBezeichnung(),r.getPreis(),r.getWaehrung(),r.getMenge(),r.getBestelldatum(),r.getLieferdatum()});
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return this.filename; }
    
	@Override
	public int getIndex(KraftstoffbestellungenRecord record) {
		return this.records.indexOf(record);
	}
	
	@Override
	public int getIndex(String bestellnummer) {
		/* TODO: search by ... */
		return -1;
	}
}
