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

import posPD.Cashier;
import posPD.Person;
import posPD.Store;

public class CashierListPanel extends JPanel {
	CashierListPanel panel;
	JList<Cashier> list = new JList<Cashier>();
	DefaultListModel<Cashier> listModel = new DefaultListModel<Cashier>();

	/**
	 * Create the panel.
	 */
	public CashierListPanel(Window currentFrame, Store store) {
		setLayout(null);
		
		panel = this;
		
		
		JLabel lblCashiers = new JLabel("Cashiers");
		lblCashiers.setBounds(200, 11, 240, 14);
		add(lblCashiers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 345, 200);
		add(scrollPane);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier cashier = new Cashier();
				cashier.setPerson(new Person());
//				cashier.setPerson(new Person("cashier"+(store.getCashiers().size()-1),"","","",cashier));
//				Random rand = new Random();
//				int number = rand.nextInt(Integer.MAX_VALUE);
//				cashier.setNumber(Integer.toString(number));
//				store.addCashier(cashier);
//				listModel.addElement(cashier);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, panel, cashier, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(50, 266, 89, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeCashier(list.getSelectedValue());
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnRemove.setBounds(180, 266, 89, 23);
		add(btnRemove);
		btnRemove.setEnabled(false);
		
		JButton btnDetails = new JButton("Edit");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, panel, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDetails.setBounds(306, 266, 89, 23);
		add(btnDetails);
		btnDetails.setEnabled(false);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					btnDetails.setEnabled(true);
					btnRemove.setEnabled(true);
				} else {
					btnDetails.setEnabled(false);
					btnRemove.setEnabled(false);
				}
			}
		});
		scrollPane.setViewportView(list);
		

		for(Cashier cashier : store.getCashiers().values()) {
			listModel.addElement(cashier);
		}
		list.setModel(listModel);

	}
	
	public DefaultListModel<Cashier> getListModel() {
		return listModel;
	}
}
