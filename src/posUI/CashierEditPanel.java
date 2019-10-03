package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import posPD.Cashier;
import posPD.Person;
import posPD.Store;


public class CashierEditPanel extends JPanel {
	private JTextField textField;
	private JTextField txtAddresstextfield;
	private JTextField txtPhonenumbertextfield;
	private JTextField txtSsntextfield;
	private JTextField txtNumbertextfield;
	private JTextField txtPasswordtextfield;

	/**
	 * Create the panel.
	 */
	public CashierEditPanel(Window currentFrame, Store store, CashierListPanel panel, Cashier cashier, Boolean isAdd) {
		addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent arg0) {
				store.getCashiers().replace(cashier.getNumber(), cashier);
			}
		});
		setLayout(null);
		
		JLabel lblCashierEdit = new JLabel("Cashier Edit");
		lblCashierEdit.setBounds(196, 5, 244, 14);
		add(lblCashierEdit);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement(cashier.getPerson().getName());
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(41, 41, 46, 14);
		add(lblName);
		
		textField = new JTextField();
		textField.setBounds(97, 38, 287, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(cashier.getPerson().getName());
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(41, 66, 46, 14);
		add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(41, 91, 46, 14);
		add(lblPhone);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(41, 116, 46, 14);
		add(lblSsn);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(41, 141, 46, 14);
		add(lblNumber);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(41, 166, 64, 14);
		add(lblPassword);
		
		txtAddresstextfield = new JTextField();
		txtAddresstextfield.setText(cashier.getPerson().getAddress());
		txtAddresstextfield.setBounds(97, 63, 287, 20);
		add(txtAddresstextfield);
		txtAddresstextfield.setColumns(10);
		
		txtPhonenumbertextfield = new JTextField();
		txtPhonenumbertextfield.setText(cashier.getPerson().getPhone());
		txtPhonenumbertextfield.setBounds(97, 88, 287, 20);
		add(txtPhonenumbertextfield);
		txtPhonenumbertextfield.setColumns(10);
		
		txtSsntextfield = new JTextField();
		txtSsntextfield.setText(cashier.getPerson().getsSN());
		txtSsntextfield.setBounds(97, 113, 287, 20);
		add(txtSsntextfield);
		txtSsntextfield.setColumns(10);
		
		txtNumbertextfield = new JTextField();
		txtNumbertextfield.setText(cashier.getNumber());
		txtNumbertextfield.setBounds(97, 138, 287, 20);
		add(txtNumbertextfield);
		txtNumbertextfield.setColumns(10);
		
		txtPasswordtextfield = new JTextField();
		txtPasswordtextfield.setText(cashier.getPassword());
		txtPasswordtextfield.setBounds(97, 163, 287, 20);
		add(txtPasswordtextfield);
		txtPasswordtextfield.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashier.setPerson(new Person(textField.getText(), txtAddresstextfield.getText(), txtPhonenumbertextfield.getText(), txtSsntextfield.getText(), null));
				cashier.setNumber(txtNumbertextfield.getText());
				cashier.setPassword(txtPasswordtextfield.getText());
				
				if(isAdd) {
					store.addCashier(cashier);
					panel.getListModel().addElement(cashier);
				}
				
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.repaint();
			}
		});
		btnSave.setBounds(41, 266, 89, 23);
		add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAdd) {
					
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.repaint();
			}
		});
		btnCancel.setBounds(295, 266, 89, 23);
		add(btnCancel);
	}
}
