package app.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public class CsvConnection {
	/** */
	private static final String path = "src/app/csv/";
	/** */
	public static final String EINNAHMEN = path+"gs_verkauf.csv";
	/** */
	public static final String AUSGABEN = path+"gs_ausgaben.csv";
	/** */
	public static final String KRAFTSTOFF = path+"gs_kraftstoff.csv";
	/** */
	public static final String KRAFTSTOFF_BESTELLUNGEN = path+"gs_kraftstoff_bestellungen.csv";
	/** */
	public static final String WAREN = path+"gs_waren.csv";
	/** */
	public static final String WAREN_BESTELLUNGEN = path+"gs_waren_bestellungen.csv";
	/** */
	public static final String PERSONAL = path+"gs_personal.csv";
	/** */
	public static final String SETTINGS = path+"gs_settings.csv";
	/** */
    private final String filename;
    /**
     * 
     * @param Path to database .csv file.
     */
    public CsvConnection(String filename){
        this.filename = filename;
    }
	/**
	 * @return List of read lines as String array.
	 * */
    public ArrayList<String[]> onRead(){
        ArrayList<String[]> records = new ArrayList<String[]>();
        try {
            FileReader file = new FileReader(this.filename);
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            CSVReader reader = new CSVReaderBuilder(file)
            //        .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();
            for(String[] row : reader.readAll()){
                records.add(row);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
    /**
     * 
     * @param List of lines to be written as String array.
     */
    public void onWrite(ArrayList<String[]> records){
        try {
            FileWriter file = new FileWriter(this.filename);
            CSVWriter writer = new CSVWriter(file,';');
            writer.writeAll(records);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
