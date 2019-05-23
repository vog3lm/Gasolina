package app.controlling;

import java.util.ArrayList;

import app.Database;
import app.csv.CsvConnection;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class AusgabenTable implements Database<AusgabenRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.AUSGABEN);
    private ArrayList<AusgabenRecord> records = new ArrayList<AusgabenRecord>();

    private int buchungsnummern = -1;
    
    public AusgabenTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){           
            AusgabenRecord bestand = new AusgabenRecord(i,data.get(i));
        	int buchungsnummer = Integer.parseInt(bestand.getBuchungsnummer());
        	if(buchungsnummer > buchungsnummern) {
        		buchungsnummern = buchungsnummer;
        	}
            this.records.add(bestand);
        }
    }

	@Override
	public AusgabenRecord onRead(int index) { return this.records.get(index); }

	@Override
	public ArrayList<AusgabenRecord> onRead() { return this.records; }

	@Override
	public int onCreate(AusgabenRecord record) {
        int index = this.records.size();
        this.records.add(record.setIndex(index).setBuchungsnummer(++buchungsnummern+""));
        return index;
	}

	@Override
	public void onUpdate(int index, AusgabenRecord record) {
		AusgabenRecord r = this.records.get(index);
		r.setBuchungsnummer(record.getBuchungsnummer());
		r.setWarennummer(record.getWarennummer());
		r.setBezeichnung(record.getBezeichnung());
		r.setPreis(record.getPreis());
		r.setMenge(record.getMenge());
		r.setEinheit(record.getEinheit());
		r.setSumme(record.getSumme());
		r.setDatum(record.getDatum());
		r.setUhrzeit(record.getUhrzeit());
		r.setMitarbeiter(record.getMitarbeiter());
	}

	@Override
	public void onDelete(int index) {
        this.records.remove(index);
        for(int i=0; i < this.records.size(); i++){
            this.records.get(i).setWarennummer(i+"");
        }
	}
	
	@Override
	public void onCommit() {
        ArrayList<String[]> records = new ArrayList<String[]>();
        for(AusgabenRecord r : this.records){
            records.add(r.toArray());
        }
        database.onWrite(records);
	}

	@Override
	public int getIndex(AusgabenRecord record) { return this.records.indexOf(record); }

	@Override
	public int getIndex(String buchungsnummer) {
		for(int i=0; i<records.size(); i++) {
			if(buchungsnummer.equals(records.get(i).getBuchungsnummer())) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getCount() { return this.records.size(); }

	@Override
	public String getUrl() { return CsvConnection.AUSGABEN; }

}
