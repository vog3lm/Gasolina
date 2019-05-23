package app.kraftstoff;

import java.util.ArrayList;

import app.Database;
import app.csv.CsvConnection;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class KraftstoffbestellungenTable implements Database<KraftstoffbestellungenRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.KRAFTSTOFF_BESTELLUNGEN);
    private ArrayList<KraftstoffbestellungenRecord> records = new ArrayList<KraftstoffbestellungenRecord>();

    private int bestellnummern = 0;
    
    public KraftstoffbestellungenTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
        	KraftstoffbestellungenRecord bestellung = new KraftstoffbestellungenRecord(i,data.get(i));
        	int bestellnummer = Integer.parseInt(bestellung.getBestellnummer());
        	if(bestellnummer > bestellnummern) {
        		bestellnummern = bestellnummer;
        	}
            this.records.add(bestellung);
        }
    }
    
    @Override
    public KraftstoffbestellungenRecord onRead(int index) { return this.records.get(index); }

    @Override
    public ArrayList<KraftstoffbestellungenRecord> onRead() { return this.records; }
    
    @Override
    public int onCreate(KraftstoffbestellungenRecord record){
        int index = this.records.size();
        this.records.add(record.setIndex(index).setBestellnummer(++bestellnummern+""));
        return index;
    }

    @Override
    public void onUpdate(int index, KraftstoffbestellungenRecord record){
        this.records.get(index)
        .setBestellnummer(record.getBestellnummer())
        .setWarennummer(record.getWarennummer())
        .setBezeichnung(record.getBezeichnung())
        .setMenge(record.getMenge())
        .setEinheit(record.getEinheit())
        .setPreis(record.getPreis())
        .setBestelldatum(record.getBestelldatum())
        .setLieferdatum(record.getLieferdatum())
        .setMitarbeiter(record.getMitarbeiter());
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
            records.add(new String[]{r.getBestellnummer(),r.getWarennummer(),r.getBezeichnung(),r.getPreis(),r.getWaehrung(),r.getMenge(),r.getEinheit(),r.getBestelldatum(),r.getLieferdatum(),r.getMitarbeiter()});
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return CsvConnection.KRAFTSTOFF_BESTELLUNGEN; }
    
	@Override
	public int getIndex(KraftstoffbestellungenRecord record) {
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
