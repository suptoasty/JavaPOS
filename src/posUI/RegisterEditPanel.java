package posUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import posPD.Register;
import posPD.Store;


public class RegisterEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private BigDecimal drawerAmount;
	private Boolean badThing = false;

	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(Window currentFrame, Store store, RegisterListPanel panel, Register register, Boolean isAdd) {
		setLayout(null);
		
		drawerAmount = register.getDrawer().getAmount();
		
		JLabel lblEditRegister = new JLabel("Edit Register: ");
		lblEditRegister.setBounds(180, 11, 260, 14);
		add(lblEditRegister);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(50, 50, 56, 14);
		add(lblNumber);
		
		JLabel lblAlreadyUsed = new JLabel("Already Used");
		lblAlreadyUsed.setBackground(Color.RED);
		lblAlreadyUsed.setForeground(Color.RED);
		lblAlreadyUsed.setBounds(386, 50, 46, 14);
		add(lblAlreadyUsed);
		lblAlreadyUsed.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(116, 47, 260, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(register.getNumber());
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < panel.getListModel().size(); i++) {
					if(panel.getListModel().getElementAt(i).getNumber().equals(textField.getText())) {
						System.out.println("bad thing");
						badThing = true;
						lblAlreadyUsed.setVisible(true);
						
					}
				}
				
				if(!badThing) {
					
				register.getDrawer().setAmount(drawerAmount);
				
				register.setNumber(textField.getText());
				
				if(isAdd) {
					store.addRegister(register);
					panel.getListModel().addElement(register);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
				}
				badThing = false;
			}
		});
		btnSave.setBounds(50, 266, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(320, 266, 89, 23);
		add(btnCancel);
		
		JLabel lblDrawer = new JLabel("Drawer:");
		lblDrawer.setBounds(50, 85, 46, 14);
		add(lblDrawer);
		
		textField_1 = new JTextField();
		textField_1.setBounds(50, 136, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(50, 173, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAmmount = new JLabel("Amount:");
		lblAmmount.setBounds(60, 110, 316, 14);
		add(lblAmmount);
		lblAmmount.setText("Amount: "+drawerAmount.toString());

		
		JButton btnAddCash = new JButton("Add Cash");
		btnAddCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawerAmount = drawerAmount.add(new BigDecimal(textField_1.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
				lblAmmount.setText("Amount: "+drawerAmount.toString());
				
			}
		});
		btnAddCash.setBounds(162, 135, 124, 23);
		add(btnAddCash);
		
		JButton btnRemoveCash = new JButton("Remove Cash");
		btnRemoveCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawerAmount = drawerAmount.subtract(new BigDecimal(textField_2.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
				lblAmmount.setText("Amount: "+drawerAmount.toString());
			}
		});
		btnRemoveCash.setBounds(162, 172, 124, 23);
		add(btnRemoveCash);
	

	}
}
