package app.database;

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
public class DatabaseConnection{
	/**
	 * */
    private final String filename;
    /**
     * 
     * @param Path to database .csv file.
     */
    public DatabaseConnection(String filename){
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
