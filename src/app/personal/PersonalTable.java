package app.personal;

import java.util.ArrayList;

import app.Database;
import app.csv.CsvConnection;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class PersonalTable implements Database<PersonalRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.PERSONAL);
    private ArrayList<PersonalRecord> records = new ArrayList<PersonalRecord>();

    private int personalnummern = -1;
    
    public PersonalTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){           
            PersonalRecord record = new PersonalRecord(i,data.get(i));
        	int personalnummer = Integer.parseInt(record.getPersonalnummer());
        	if(personalnummer > personalnummern) {
        		personalnummern = personalnummer;
        	}
            this.records.add(record);
        }
    }

    @Override
    public PersonalRecord onRead(int index) { return this.records.get(index); }

    @Override
    public ArrayList<PersonalRecord> onRead() { return this.records; }
    
    @Override
    public int onCreate(PersonalRecord record){
        int index = this.records.size();
        this.records.add(record.setIndex(index).setPersonalnummer(++personalnummern+""));
        return index;
    }

    @Override
    public void onUpdate(int index, PersonalRecord record){
        this.records.get(index)
        .setVorname(record.getVorname())
        .setNachname(record.getNachname())
        .setPasswort(record.getPasswort());
    }

    @Override
    public void onDelete(int index){
        this.records.remove(index);
        for(int i=0; i < this.records.size(); i++){
            this.records.get(i).setPersonalnummer(i+"");
        }
    }

    @Override
    public void onCommit(){
        ArrayList<String[]> records = new ArrayList<String[]>();
        for(PersonalRecord r : this.records){
            records.add(r.toArray());
        }
        database.onWrite(records);
    }

    @Override
    public int getCount(){ return this.records.size(); }

    @Override
    public String getUrl() { return CsvConnection.PERSONAL; }
    
	@Override
	public int getIndex(PersonalRecord record) { return this.records.indexOf(record); }
	
	@Override
	public int getIndex(String benutzername) {
		for(int i=0; i<records.size(); i++) {
			if(benutzername.equals(records.get(i).getBenutzername())) {
				return i;
			}
		}
		return -1;
	}
}
