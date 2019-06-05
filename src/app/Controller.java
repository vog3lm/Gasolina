package app;
/**
 * 
 * @author vog3lm
 * @version 1.0
 * @since   1.0
 */
public interface Controller<V>{
	/**
	 * */
	public void onStart(String command);
	/**
	 * */
	public V onShow();
	/**
	 * */
	public boolean onDestroy();
}
