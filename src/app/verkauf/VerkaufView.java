package app.verkauf;

import app.Decorateable;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class VerkaufView extends TabPane implements Decorateable<VerkaufView,Node> {
	
	private int saeulen = 1;
	
	@Override
	public VerkaufView decorate(Node node) {
		String id = node.getId();
		if("saeule".equals(id)) {addSaeule(node);}
		else if("journal".equals(id)) {addTab(id,node);}
		else {System.err.println("id unknown ::" + id);}
		return this;
	}
	
	private void addSaeule(Node node) {
		Tab tab = new Tab("Säule "+saeulen++);
		tab.setContent(node);
		getTabs().add(tab);
	}
	
	private void addTab(String id, Node node) {
		Tab tab = new Tab(id.substring(0, 1).toUpperCase()+id.substring(1));
		tab.setContent(node);
		getTabs().add(tab);
	}
	
	VerkaufView setIndex(int tab) {
		getSelectionModel().select(tab);
		return this;
	}
		
	void onRefresh() {
	//	saeule1.onRefresh();
	//	saeule2.onRefresh();
	//	saeule3.onRefresh();
	//	journal.onRefresh();
	}
}
