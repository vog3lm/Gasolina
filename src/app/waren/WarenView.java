package app.waren;

import app.Decorateable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class WarenView extends TabPane implements Decorateable<WarenView,Node> {
	
	@Override
	public WarenView onDecorate(Node node) {
		String id = node.getId();
		if("bestand".equals(id)) {addTab(id,node);}
		else if("bestellungen".equals(id)) {addTab(id,node);}
		else {System.err.println("id unknown ::" + id);}
		return this;
	}
	
	private void addTab(String id, Node node) {
		Tab tab = new Tab(id.substring(0, 1).toUpperCase()+id.substring(1));
		tab.setContent(node);
		getTabs().add(tab);
	}
	
	int getIndex() { return getSelectionModel().getSelectedIndex(); }
	
	WarenView setIndex(int tab) {
		getSelectionModel().select(tab);
		return this;
	}
}
