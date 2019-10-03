package posUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import posPD.Cashier;
import posPD.Register;
import posPD.Session;
import posPD.Store;

public class LoginPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private Cashier cashier;
	private Register register;

	/**
	 * Create the panel.
	 */
	public LoginPanel(Window currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(180, 11, 208, 14);
		add(lblLogin);
		
		JLabel lblCashier = new JLabel("Cashier:");
		lblCashier.setBounds(50, 50, 46, 14);
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(50, 75, 46, 14);
		add(lblRegister);
		
		JLabel lblStartingcash = new JLabel("StartingCash:");
		lblStartingcash.setBounds(50, 100, 71, 14);
		add(lblStartingcash);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(50, 125, 63, 14);
		add(lblPassword);
		
		JComboBox<Cashier> comboBox = new JComboBox<Cashier>();
		DefaultComboBoxModel<Cashier> boxModel = new DefaultComboBoxModel<Cashier>();
		for(Cashier c : store.getCashiers().values()) {
			boxModel.addElement(c);
		}
		comboBox.setModel(boxModel);
		comboBox.setSelectedIndex(-1);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashier = (Cashier)comboBox.getSelectedItem();
			}
		});
		comboBox.setBounds(123, 50, 209, 20);
		add(comboBox);
		
		JComboBox<Register> comboBox_1 = new JComboBox<Register>();
		DefaultComboBoxModel<Register> boxModel_1 = new DefaultComboBoxModel<Register>();
		for(Register r : store.getRegisters().values()) {
			boxModel_1.addElement(r);
		}
		comboBox_1.setModel(boxModel_1);
		comboBox_1.setSelectedIndex(-1);
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register = (Register)comboBox_1.getSelectedItem();
			}
		});
		comboBox_1.setBounds(123, 75, 209, 20);
		add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(123, 100, 209, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(123, 125, 209, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblInvalid = new JLabel("Invalid");
		lblInvalid.setForeground(Color.RED);
		lblInvalid.setBounds(342, 47, 46, 14);
		add(lblInvalid);
		lblInvalid.setVisible(false);
		
		JLabel lblInvalid_1 = new JLabel("Invalid");
		lblInvalid_1.setForeground(Color.RED);
		lblInvalid_1.setBounds(342, 72, 46, 14);
		add(lblInvalid_1);
		lblInvalid_1.setVisible(false);
		
		JLabel lblInvalid_3 = new JLabel("Invalid");
		lblInvalid_3.setForeground(Color.RED);
		lblInvalid_3.setBounds(342, 100, 46, 14);
		add(lblInvalid_3);
		lblInvalid_3.setVisible(false);
		
		JLabel lblInvalid_2 = new JLabel("Invalid");
		lblInvalid_2.setForeground(Color.RED);
		lblInvalid_2.setBounds(343, 125, 46, 14);
		add(lblInvalid_2);
		lblInvalid_2.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cashier != null && register != null) {
					if(cashier.isAuthorized(textField_1.getText()) && !textField.getText().equals("")) {
						register.getDrawer().addCash(new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
						Session session = new Session(cashier, register);
						
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new POSsale(currentFrame, store, session, cashier, register));
						currentFrame.revalidate();
					} else {
						if(cashier.getPassword().compareTo(textField_1.getText()) != 0) {
							lblInvalid_2.setVisible(true);
						} else {
							lblInvalid_2.setVisible(false);
						}
						if(textField.getText().equals("")) {
							lblInvalid_3.setVisible(true);
						} else {
							lblInvalid_3.setVisible(false);
						}

					}
				} else {
					if(cashier == null) {
						lblInvalid.setVisible(true);
					} else {
						lblInvalid.setVisible(false);
					}
					if(register == null) {
						lblInvalid_1.setVisible(true);
					} else {
						lblInvalid_1.setVisible(false);
					}
				}
				
			}
		});
		btnLogin.setBounds(50, 266, 89, 23);
		add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ContentPane(currentFrame, store));
				currentFrame.revalidate();
			}
		});
		btnCancel.setBounds(300, 266, 89, 23);
		add(btnCancel);


	}
}
