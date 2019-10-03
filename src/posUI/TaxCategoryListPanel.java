package posUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import posPD.Store;
import posPD.TaxCategory;

public class TaxCategoryListPanel extends JPanel {
	private TaxCategoryListPanel panel;
	private DefaultListModel<TaxCategory> listModel = new DefaultListModel<TaxCategory>();
	/**
	 * Create the panel.
	 */
	public TaxCategoryListPanel(Window currentFrame, Store store) {
		setLayout(null);
		
		panel = this;
		
		JLabel lblTaxCategoriesList = new JLabel("Tax Categories List");
		lblTaxCategoriesList.setBounds(179, 5, 216, 14);
		add(lblTaxCategoriesList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 345, 200);
		add(scrollPane);
		
		JButton btnEdit = new JButton("Edit");
		JList<TaxCategory> list = new JList<TaxCategory>();
//	s	list.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent arg0) {
//				if(list.getSelectedIndex() < 0) {
//					btnEdit.setEnabled(false);
//					btnEdit.set
//				} else {
//					btnEdit.setEnabled(true);
//				}
//			}
//		});
		scrollPane.setViewportView(list);
		
		for(TaxCategory tax : store.getTaxCategories().values()) {
			listModel.addElement(tax);
		}
		list.setModel(listModel);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategory tax = new TaxCategory();
				tax.setTaxCategory(Integer.toString(store.getTaxCategories().size()-1));
				//store.addTaxCategory(tax);
				//listModel.addElement(store.getTaxCategory(tax));
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, panel, tax, true));
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(50, 266, 89, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeTaxCategory(list.getSelectedValue());
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnRemove.setBounds(180, 266, 89, 23);
		add(btnRemove);
		
//		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedIndex() >= 0) {
					currentFrame.getContentPane().removeAll();
					//currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, panel, store.getTaxCategory(list.getSelectedValue()), false));
					currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, panel, list.getSelectedValue(), false));
					currentFrame.revalidate();						
				}
			}
		});
		btnEdit.setBounds(306, 266, 89, 23);
		add(btnEdit);
		
		btnRemove.setEnabled(false);
		btnEdit.setEnabled(false);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					btnRemove.setEnabled(true);
					btnEdit.setEnabled(true);
				} else {
					btnRemove.setEnabled(false);
					btnEdit.setEnabled(false);
				}
			}
		});
	
	}
	
	public DefaultListModel<TaxCategory> getListModel() {
		return listModel;
	}
}
