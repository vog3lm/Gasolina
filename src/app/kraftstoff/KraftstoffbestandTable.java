package app.kraftstoff;

import java.util.ArrayList;

import app.Database;
import app.Zustand;
import app.csv.CsvConnection;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class KraftstoffbestandTable implements Database<KraftstoffbestandRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.KRAFTSTOFF);
    private ArrayList<KraftstoffbestandRecord> records = new ArrayList<KraftstoffbestandRecord>();
    /**/
    private ArrayList<String> einheiten = new ArrayList<String>(){{
        add("Liter");add("Kubikmeter");add("Kilowattstunden");
    }};

    public KraftstoffbestandTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
            this.records.add(new KraftstoffbestandRecord(i,data.get(i)));
        }
    }

    @Override
    public KraftstoffbestandRecord onRead(int index) { return this.records.get(index); }

    @Override
    public ArrayList<KraftstoffbestandRecord> onRead() { return this.records; }
    
    @Override
    public int onCreate(KraftstoffbestandRecord record){
        int warennummer = this.records.size();
        this.records.add(record.setWarennummer(warennummer+""));
        return warennummer;
    }

    @Override
    public void onUpdate(int index, KraftstoffbestandRecord record){
        this.records.get(index)
        .setBezeichnung(record.getBezeichnung())
        .setEinheit(record.getEinheit())
        .setMenge(record.getMenge())
        .setPreis(record.getPreis());
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
        for(KraftstoffbestandRecord r : this.records){
            records.add(new String[]{r.getWarennummer(),r.getBezeichnung(),r.getEinheit(),r.getMenge(),r.getPreis(),r.getWaehrung(),r.getTank(),r.getKapazitaet()});
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return CsvConnection.KRAFTSTOFF; }
    
	@Override
	public int getIndex(KraftstoffbestandRecord record) {
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
