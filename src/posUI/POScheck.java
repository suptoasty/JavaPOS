package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Check;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class POScheck extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private POScheck panel;

	/**
	 * Create the panel.
	 */
	public POScheck(POSpayment paymentPanel, String amount) {
		setLayout(null);
		
		panel = this;
		
		JLabel lblCheck = new JLabel("Check");
		lblCheck.setBounds(180, 11, 46, 14);
		add(lblCheck);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(30, 30, 46, 14);
		add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(86, 27, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAccountnumber = new JLabel("AccountNumber");
		lblAccountnumber.setBounds(30, 58, 46, 14);
		add(lblAccountnumber);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 55, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//paymentPanel.getPayment().setAmtTendered(new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
				//paymentPanel.setAmount(new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
				paymentPanel.addPayment(new Check(amount, textField_2.getText(), textField_3.getText(), new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP)));
				paymentPanel.updateAmmount();
				paymentPanel.remove(panel);
				paymentPanel.repaint();	
			}
		});
		btnSave.setBounds(30, 133, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(129, 133, 89, 23);
		add(btnCancel);
		
		JLabel lblRouting = new JLabel("Routing");
		lblRouting.setBounds(30, 83, 46, 14);
		add(lblRouting);
		
		textField_2 = new JTextField();
		textField_2.setBounds(86, 80, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblChecknumber = new JLabel("CheckNumber");
		lblChecknumber.setBounds(30, 108, 46, 14);
		add(lblChecknumber);
		
		textField_3 = new JTextField();
		textField_3.setBounds(86, 105, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);

	}
}
