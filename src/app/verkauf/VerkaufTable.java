package app.verkauf;

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
public class VerkaufTable implements Database<VerkaufRecord> {

    private final CsvConnection database = new CsvConnection(CsvConnection.EINNAHMEN);
    private ArrayList<VerkaufRecord> records = new ArrayList<VerkaufRecord>();

    public VerkaufTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
            this.records.add(new VerkaufRecord(i,data.get(i)));
        }
    }

	@Override
	public VerkaufRecord onRead(int index) { return this.records.get(index); }

	@Override
	public ArrayList<VerkaufRecord> onRead() { return this.records; }

	@Override
	public int onCreate(VerkaufRecord record) {
        int belegnummer = this.records.size();
        this.records.add(record.setBelegnummer(belegnummer+""));
        return belegnummer;
	}

	@Override
	public void onUpdate(int index, VerkaufRecord record) {
        this.records.get(index);
        /* TODO: update verkauf record */
	}

	@Override
	public void onDelete(int index) {
        this.records.remove(index);
        for(int i=0; i < this.records.size(); i++){
            this.records.get(i).setBelegnummer(i+"");
        }
	}

	@Override
	public void onCommit() {
        ArrayList<String[]> records = new ArrayList<String[]>();
        for(VerkaufRecord r : this.records){
        	/* TODO: wirte table on commit */
            records.add(new String[]{});
        }
        //database.onWrite(records);
	}

	@Override
	public int getIndex(VerkaufRecord record) { return this.records.indexOf(record); }

	@Override
	public int getIndex(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount() { return this.records.size(); }

	@Override
	public String getUrl() { return CsvConnection.EINNAHMEN; }

}
