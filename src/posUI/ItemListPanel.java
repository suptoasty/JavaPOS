package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import posPD.Item;
import posPD.Store;

public class ItemListPanel extends JPanel {
	private ItemListPanel itemListPanel;
	private JList<Item> list = new JList<Item>();
	private DefaultListModel<Item> listModel = new DefaultListModel<Item>();
	private Boolean isAdd = false;

	/**
	 * Create the panel.
	 */
	public ItemListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		itemListPanel = this;
			
		JLabel lblItemList = new JLabel("Item List");
		lblItemList.setBounds(180, 11, 260, 14);
		add(lblItemList);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = new Item();
				Random rand = new Random();
				int number = rand.nextInt(Integer.MAX_VALUE);
				item.setNumber(Integer.toString(number));
//				store.addItem(item);
//				listModel.addElement(item);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, itemListPanel, store, item, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(50, 266, 89, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					store.removeItem(list.getSelectedValue());
					listModel.removeElementAt(list.getSelectedIndex());;					
				}
			}
		});
		btnRemove.setBounds(180, 266, 89, 23);
		add(btnRemove);
		btnRemove.setEnabled(false);
		
		JButton btnDetails = new JButton("Edit");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, itemListPanel, store, list.getSelectedValue(), false));
					currentFrame.getContentPane().revalidate();
				}
			}
		});
		btnDetails.setBounds(306, 266, 89, 23);
		add(btnDetails);
		btnDetails.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 345, 200);
		add(scrollPane);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(list.getSelectedIndex() >= 0) {
					btnDetails.setEnabled(true);
					btnRemove.setEnabled(true);
				} else {
					btnDetails.setEnabled(false);
					btnRemove.setEnabled(false);
				}
			}
		});
		
		list.addFocusListener(new FocusAdapter() {
		
		});
		scrollPane.setViewportView(list);
		
		for(Item item : store.getItems().values()) {
			listModel.addElement(item);
		}
		list.setModel(listModel);

	}
	
	public  DefaultListModel<Item> getListModel() {
		return listModel;
	}

}
