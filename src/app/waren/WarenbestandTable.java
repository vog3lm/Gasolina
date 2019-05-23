package app.waren;

import java.util.ArrayList;

import app.Database;
import app.csv.CsvConnection;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class WarenbestandTable implements Database<WarenbestandRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.WAREN);
    private ArrayList<WarenbestandRecord> records = new ArrayList<WarenbestandRecord>();

    private int warennummern = -1;
    
    public WarenbestandTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){          
            WarenbestandRecord bestand = new WarenbestandRecord(i,data.get(i));
        	int warennummer = Integer.parseInt(bestand.getWarennummer());
        	if(warennummer > warennummern) {
        		warennummern = warennummer;
        	}
            this.records.add(bestand);
        }
    }

    @Override
    public WarenbestandRecord onRead(int index) { return this.records.get(index); }
    
    @Override
    public ArrayList<WarenbestandRecord> onRead() { return this.records; }

    @Override
    public int onCreate(WarenbestandRecord record){
        int index = this.records.size();
        this.records.add(record.setIndex(index).setWarennummer(++warennummern+""));
        return index;
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
            records.add(r.toArray());
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return CsvConnection.WAREN; }

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
