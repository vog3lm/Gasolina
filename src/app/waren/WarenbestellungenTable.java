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
public class WarenbestellungenTable implements Database<WarenbestellungenRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.WAREN_BESTELLUNGEN);
    private ArrayList<WarenbestellungenRecord> records = new ArrayList<WarenbestellungenRecord>();

    private int bestellnummern = -1;
    
    public WarenbestellungenTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
        	WarenbestellungenRecord bestellung = new WarenbestellungenRecord(i,data.get(i));
        	int bestellnummer = Integer.parseInt(bestellung.getBestellnummer());
        	if(bestellnummer > bestellnummern) {
        		bestellnummern = bestellnummer;
        	}
            this.records.add(bestellung);
        }
    }
    
    @Override
    public WarenbestellungenRecord onRead(int index) { return this.records.get(index); }

    public ArrayList<WarenbestellungenRecord> onRead() { return this.records; }
    
    @Override
    public int onCreate(WarenbestellungenRecord record){
        int index = this.records.size();
        this.records.add(record.setIndex(index).setBestellnummer(++bestellnummern+""));
        return index;
    }

    @Override
    public void onUpdate(int index, WarenbestellungenRecord record){
        this.records.get(index)
                .setBestellnummer(record.getBestellnummer())
                .setWarennummer(record.getWarennummer())
                .setBezeichnung(record.getBezeichnung())
                .setMenge(record.getMenge())
                .setEinheit(record.getEinheit())
                .setPreis(record.getPreis())
                .setBestelldatum(record.getBestelldatum())
                .setLieferdatum(record.getLieferdatum())
                .setMitarbeiter(record.getMitarbeiter())
                .setStatus(record.getStatus());
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
            records.add(new String[]{r.getBestellnummer()
            		,r.getWarennummer()
            		,r.getBezeichnung()
            		,r.getPreis()
            		,r.getWaehrung()
            		,r.getMenge()
            		,r.getEinheit()
            		,r.getBestelldatum()
            		,r.getLieferdatum()
            		,r.getMitarbeiter()
            		,r.getStatus()
            	});
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return CsvConnection.WAREN_BESTELLUNGEN; }
    
	@Override
	public int getIndex(WarenbestellungenRecord record) {
		return this.records.indexOf(record);
	}
	
	@Override
	public int getIndex(String bestellnummer) {
		for(int i=0; i<records.size(); i++) {
			if(bestellnummer.equals(records.get(i).getBezeichnung())) {
				return i;
			}
		}
		return -1;
	}
}
