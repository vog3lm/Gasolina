package app.controlling;

import java.util.ArrayList;

import app.Zustand;
import app.database.Database;
import app.database.DatabaseConnection;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class AusgabenTable implements Database<AusgabenRecord> {

    private final DatabaseConnection database = new DatabaseConnection(DatabaseConnection.AUSGABEN);
    private ArrayList<AusgabenRecord> records = new ArrayList<AusgabenRecord>();

    public AusgabenTable(){
        ArrayList<String[]> data = database.onRead();
        for(int i=0; i<data.size(); i++){
        	/* TODO: build ausgaben table */
        //    this.records.add(new AusgabenRecord(i,data.get(i)));
        }
    }

	@Override
	public AusgabenRecord onRead(int index) { return this.records.get(index); }

	@Override
	public ArrayList<AusgabenRecord> onReadAll() { return this.records; }

	@Override
	public int onCreate(AusgabenRecord record) {
		/* TODO: delete ausgaben record */
		return -1;
	}

	@Override
	public void onUpdate(int index, AusgabenRecord record) {
        this.records.get(index);
        /* TODO: update ausgaben record */
	}

	@Override
	public void onDelete(int index) {
		/* TODO: delete ausgaben record */
	}

	@Override
	public void onCommit() {
        ArrayList<String[]> records = new ArrayList<String[]>();
        for(AusgabenRecord r : this.records){
        	/* TODO: wirte table on commit */
            records.add(new String[]{});
        }
        //database.onWrite(records);
	}

	@Override
	public int getIndex(AusgabenRecord record) { return this.records.indexOf(record); }

	@Override
	public int getIndex(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount() { return this.records.size(); }

	@Override
	public String getUrl() { return DatabaseConnection.AUSGABEN; }

}
