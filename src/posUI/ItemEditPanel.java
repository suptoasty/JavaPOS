package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import posPD.Item;
import posPD.Price;
import posPD.Store;
import posPD.TaxCategory;
import posPD.UPC;

//use currentPanel in parent and here to go back to previous panel to save information entered but not submitted
//user repaint and not revalidate
public class ItemEditPanel extends ListEditPanel {
	private JTextField txtDefault;
	private JTextField textField;
	private JEditorPane editorPane = new JEditorPane();
	private TreeSet<Price> prices = new TreeSet<Price>();
	private TreeMap<String, UPC> upcs = new TreeMap<String, UPC>();
	private ListEditPanel panel;
	private TaxCategory tax = new TaxCategory();
	private DefaultListModel<Price> listModel = new DefaultListModel<Price>(); //neded here to rebuild list
	private Item it; //used to update price list on changinge promo-to-price or price-to-promo
	private DefaultListModel<UPC> listModel_1 = new DefaultListModel<UPC>();

	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, ItemListPanel currentPanel, Store store, Item item, Boolean isAdd) {
		setLayout(null);
		
		panel = this;
		it = item;
				
		JLabel lblEditItem = new JLabel("Edit Item: "+item.getName());
		lblEditItem.setBounds(161, 11, 250, 14);
		add(lblEditItem);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(20, 64, 46, 14);
		add(lblName);
		
		txtDefault = new JTextField();
		txtDefault.setText(item.getName());
		txtDefault.setBounds(74, 61, 122, 20);
		add(txtDefault);
		txtDefault.setColumns(10);
		
		JLabel lblTaxrates = new JLabel("Prices:");
		lblTaxrates.setBounds(205, 36, 95, 14);
		add(lblTaxrates);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(205, 61, 205, 53);
		add(scrollPane);
		
		JList<Price> list = new JList<Price>();
		scrollPane.setViewportView(list);
	
		
		//DefaultListModel<Price> listModel = new DefaultListModel<Price>();
		for(Price price : item.getPrices()) {
			listModel.addElement(price);
			prices.add(price);
		}
		list.setModel(listModel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Price price = new Price("0.00", LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy")).toString());
				//prices.add(price);
				//listModel.addElement(price);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, panel, store, it, price, true));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		btnAdd.setBounds(205, 125, 60, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedIndex() >= 0) {
					prices.remove(list.getSelectedValue());
					listModel.removeElement(list.getSelectedValue());	
				}
			}
		});
		btnRemove.setBounds(275, 125, 71, 23);
		add(btnRemove);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, panel, store, item, list.getSelectedValue(), false));
					currentFrame.getContentPane().revalidate();
				}
			}
		});
		btnEdit.setBounds(350, 125, 60, 23);
		add(btnEdit);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(20, 97, 46, 14);
		add(lblNumber);
		
		textField = new JTextField();
		textField.setBounds(74, 94, 122, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(item.getNumber());
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				item.setTaxCategory(tax);
				item.setName(txtDefault.getText());
				item.setNumber(textField.getText());
				item.setDescription(editorPane.getText());
				item.setPrices(prices);
				item.setuPCs(upcs);
				
				if(isAdd) {
					store.addItem(item);
					currentPanel.getListModel().addElement(item);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(74, 266, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAdd) {
					store.getItems().remove(item.getNumber());
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(252, 266, 89, 23);
		add(btnCancel);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(74, 159, 95, 14);
		add(lblDescription);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 184, 176, 80);
		add(scrollPane_1);
		
		scrollPane_1.setViewportView(editorPane);
		editorPane.setText(item.getDescription());
		
		JLabel lblTaxcategory = new JLabel("TaxCategory:");
		lblTaxcategory.setBounds(20, 129, 71, 14);
		add(lblTaxcategory);
		
		JComboBox<TaxCategory> comboBox = new JComboBox<TaxCategory>();
		DefaultComboBoxModel<TaxCategory> boxModel = new DefaultComboBoxModel<TaxCategory>();
		for(TaxCategory t : store.getTaxCategories().values()) {
			boxModel.addElement(t);
		}
		comboBox.setModel(boxModel);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//listModel.removeAllElements();
				//rates.clear();

				tax = (TaxCategory)comboBox.getSelectedItem();
				//for(TaxRate rate : tax.getTaxRates()) {
				//	listModel.addElement(rate);
				//}
			}
		});
		comboBox.setBounds(101, 126, 95, 20);
		add(comboBox);
		
		JLabel lblUpcs = new JLabel("UPCs:");
		lblUpcs.setBounds(205, 159, 60, 14);
		add(lblUpcs);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(205, 184, 205, 53);
		add(scrollPane_2);
		
		JList<UPC> list_1 = new JList<UPC>();
		scrollPane_2.setViewportView(list_1);

		for(UPC upc : item.getuPCs().values()) {
			listModel_1.addElement(upc);
			upcs.put(upc.getUPC(), upc);
		}
		list_1.setModel(listModel_1);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int value = rand.nextInt(Integer.MAX_VALUE);
				UPC upC = new UPC(Integer.toString(value));
				upC.setItem(item);
				upcs.put(upC.getUPC(), upC);
//				store.getUPCs().put(upC.getUPC(), upC);
//				listModel_1.addElement(upC);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, store, panel, item, upC, true));
				currentFrame.revalidate();
			}
		});
		btnAdd_1.setBounds(205, 241, 60, 23);
		add(btnAdd_1);
		
		JButton btnRemove_1 = new JButton("Remove");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getSelectedIndex() >= 0) {
					upcs.remove(list_1.getSelectedValue().getUPC());
					listModel_1.removeElement(list_1.getSelectedValue());					
				}
			}
		});
		btnRemove_1.setBounds(275, 241, 71, 23);
		add(btnRemove_1);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getSelectedIndex() >= 0) {
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, store, panel, it, list_1.getSelectedValue(), false));
					currentFrame.revalidate();
				}
			}
		});
		btnEdit_1.setBounds(350, 241, 60, 23);
		add(btnEdit_1);
		
		btnEdit.setEnabled(false);
		btnRemove.setEnabled(false);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					btnEdit.setEnabled(true);
					btnRemove.setEnabled(true);
				} else {
					btnEdit.setEnabled(false);
					btnRemove.setEnabled(false);
				}
			}
		});
		
		btnEdit_1.setEnabled(false);
		btnRemove_1.setEnabled(false);
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list_1.getSelectedIndex() >= 0) {
					btnEdit_1.setEnabled(true);
					btnRemove_1.setEnabled(true);
				} else {
					btnEdit_1.setEnabled(false);
					btnRemove_1.setEnabled(false);
				}
			}
		});
		
		
		for(int i = 0; i < boxModel.getSize(); i++) {
			if(comboBox.getItemAt(i).getTaxCategory() == item.getTaxCategory().getTaxCategory()) {
				comboBox.setSelectedIndex(i);
			}
		}
	}
	
	@Override
	public void rebuildList(Store store) {
		listModel.clear();
		for(Price price : it.getPrices()) {
			listModel.addElement(price);
			prices.add(price);
		}
	}
	
	@Override
	public DefaultListModel<UPC> getUPCListModel() {
		return listModel_1;
	}
}
