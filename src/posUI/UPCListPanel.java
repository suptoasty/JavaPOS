package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import posPD.Item;
import posPD.Store;
import posPD.UPC;

public class UPCListPanel extends ListEditPanel {
	private ListEditPanel panel;
	private DefaultListModel<UPC> listModel = new DefaultListModel<UPC>();

	/**
	 * Create the panel.
	 */
	public UPCListPanel(Window currentFrame, Store store) {
		setLayout(null);
		
		panel = this;
		
		JLabel lblUpcs = new JLabel("UPCs");
		lblUpcs.setBounds(200, 11, 46, 14);
		add(lblUpcs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 345, 200);
		add(scrollPane);
		
		JList<UPC> list = new JList<UPC>();
		scrollPane.setViewportView(list);
		
//		for(Item item : store.getItems().values()) {
//			for(UPC upc : item.getuPCs().values()) {
//				listModel.addElement(upc);
//			}
//		}
		for(UPC upc : store.getUPCs().values()) {
			listModel.addElement(upc);
		}
		list.setModel(listModel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int value = rand.nextInt(Integer.MAX_VALUE);
				UPC upc = new UPC(String.valueOf(value));
//				store.addUPC(upc);
//				listModel.addElement(upc);
//				Item item = new Item();
//				value = rand.nextInt(Integer.MAX_VALUE);
//				item.setNumber(String.valueOf(value));
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, store, panel, null, upc, true));
				currentFrame.getContentPane().revalidate();
			
			}
		});
		btnAdd.setBounds(50, 266, 89, 23);
		add(btnAdd);
		
		JButton btnDetails = new JButton("Edit");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					Random rand = new Random();
					int value = rand.nextInt(Integer.MAX_VALUE);
					UPC upc = new UPC(String.valueOf(value));
//					store.addUPC(upc);
//					listModel.addElement(upc);
					Item item = new Item();
					value = rand.nextInt(Integer.MAX_VALUE);
					item.setNumber(String.valueOf(value));
					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, store, panel, null, list.getSelectedValue(), false));
					currentFrame.getContentPane().revalidate();
				}
			}
		});
		btnDetails.setBounds(306, 266, 89, 23);
		add(btnDetails);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					store.removeUPC(list.getSelectedValue());
					listModel.removeElement(list.getSelectedValue());
				}
			}
		});
		btnRemove.setBounds(180, 266, 89, 23);
		add(btnRemove);
		
		btnRemove.setEnabled(false);
		btnDetails.setEnabled(false);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					btnRemove.setEnabled(true);
					btnDetails.setEnabled(true);
				} else {
					btnRemove.setEnabled(false);
					btnDetails.setEnabled(false);
				}
			}
		});

	}
	
	@Override
	public DefaultListModel<UPC> getUPCListModel() {
		return listModel;
	}

}
