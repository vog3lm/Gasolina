package app;

import java.util.ArrayList;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public interface Database<T> {

    public T onRead(int index);

    ArrayList<T> onRead();
    
    public int onCreate(T record);

    public void onUpdate(int index, T record);

    public void onDelete(int index);

    public void onCommit();
    
    public int getIndex(T record);
    
    public int getIndex(String id);

    public int getCount();

    public String getUrl();
}


