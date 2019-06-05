package app.controlling;

import app.Decorateable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ControllingView extends TabPane implements Decorateable<ControllingView,Node> {
			
	@Override
	public ControllingView onDecorate(Node node) {
		String id = node.getId();
		if("einnahmen".equals(id)) {addTab(id,node);}
		else if("ausgaben".equals(id)) {addTab(id,node);}
		else if("ergebnis".equals(id)) {addTab(id,node);}
		else {System.err.println("id unknown ::" + id);}
		return this;
	}
	
	private void addTab(String id, Node node) {
		Tab tab = new Tab(id.substring(0, 1).toUpperCase()+id.substring(1));
		tab.setContent(node);
		getTabs().add(tab);
	}
		
	int getIndex() { return getSelectionModel().getSelectedIndex(); }
	
	ControllingView setIndex(int tab) {
		getSelectionModel().select(tab);
		return this;
	}


}
