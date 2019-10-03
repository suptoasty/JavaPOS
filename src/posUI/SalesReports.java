package posUI;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import posPD.Sale;
import posPD.Session;
import posPD.Store;
import javax.swing.JScrollPane;

public class SalesReports extends JPanel {

	/**
	 * Create the panel.
	 */
	public SalesReports(Window currentFrame, Store store) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 26, 345, 218);
		add(scrollPane);
		
		JList<Sale> list = new JList<Sale>();
		scrollPane.setViewportView(list);
		
		DefaultListModel<Sale> listModel = new DefaultListModel<Sale>();		
		for(Session session : store.getSessions()) {
			for(Sale sale : session.getSales()) {
				listModel.addElement(sale);
			}
		}
		list.setModel(listModel);
	}

}
