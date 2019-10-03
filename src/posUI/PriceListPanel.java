package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import posPD.Item;
import posPD.Price;
import posPD.Store;

public class PriceListPanel extends ListEditPanel {

	private ListEditPanel panel;
	private DefaultListModel<Price> listModel = new DefaultListModel<Price>();

	/**
	 * Create the panel.
	 */
	public PriceListPanel(Window currentFrame, Store store) {
		panel = this;
		setLayout(null);
		
		JLabel lblPriceList = new JLabel("Price List");
		lblPriceList.setBounds(180, 11, 46, 14);
		add(lblPriceList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 345, 200);
		add(scrollPane);
		
		JList<Price> list = new JList<Price>();
		scrollPane.setViewportView(list);
		
		//DefaultListModel<Price> listModel = new DefaultListModel<Price>();
		for(Item item : store.getItems().values()) {
			for(Price price : item.getPrices())  {
				listModel.addElement(price);
			}
		}
		list.setModel(listModel);
		
		JButton button_2 = new JButton("Edit");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, panel, store, store.getItem(list.getSelectedValue().getItem()), list.getSelectedValue(), false));
					currentFrame.revalidate();					
				}
			}
		});
		button_2.setBounds(180, 261, 89, 23);
		add(button_2);
	}
	
	@Override
	public void rebuildList(Store store) {
		listModel.clear();
		for(Item item : store.getItems().values()) {
			for(Price price : item.getPrices())  {
				listModel.addElement(price);
			}
		}
	}
}
