package posUI;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import posPD.Store;
import posPD.UPC;

/*
 * helper parent class for rebuilding list models in other classes
 * should probably be abstract
 */
public class ListEditPanel extends JPanel{
	public ListEditPanel() {
		
	}
	
	public void rebuildList(Store store) {
		
	}
	
	public void getListModel() {
		
	}
	
	public DefaultListModel<UPC> getUPCListModel() {
		return null;
	}
	
}
