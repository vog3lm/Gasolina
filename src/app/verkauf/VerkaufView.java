package app.verkauf;

import java.net.URL;
import java.util.ResourceBundle;

import app.fxml.Loader;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class VerkaufView implements Initializable {

	public static final int SAEULE1 = 0;
	public static final int SAEULE2 = 1;
	public static final int SAEULE3 = 2;
	public static final int JOURNAL = 3;
	
	@FXML
	private TabPane verkauf_tabs;
	
	private SaeuleView saeule1;
	
	private SaeuleView saeule2;
	
	private SaeuleView saeule3;
	
	private JournalView journal;
	
	VerkaufView(VerkaufController controller) {
		saeule1 = new SaeuleView(controller,"Säule 1");
		saeule2 = new SaeuleView(controller,"Säule 2");
		saeule3 = new SaeuleView(controller,"Säule 3");
		journal = new JournalView(controller);
		new Loader().onLoadBorderCenter(controller,Loader.VERKAUF,this);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		ObservableList<Tab> tabs = verkauf_tabs.getTabs();
		tabs.get(SAEULE1).setContent(saeule1.getView());
		tabs.get(SAEULE2).setContent(saeule2.getView());
		tabs.get(SAEULE3).setContent(saeule3.getView());
		tabs.get(JOURNAL).setContent(journal.getView());
	}
	
	VerkaufView setIndex(int tab) {
		verkauf_tabs.getSelectionModel().select(tab);
		return this;
	}
	
	int getCount() { return saeule1.getCount()+saeule2.getCount()+saeule3.getCount(); }
		
	void onRefresh() {
		saeule1.onRefresh();
		saeule2.onRefresh();
		saeule3.onRefresh();
		journal.onRefresh();
	}
}
