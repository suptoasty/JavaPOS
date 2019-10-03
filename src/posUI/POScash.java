package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Cash;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class POScash extends JPanel {
	private JTextField textField;
	private POScash panel;
	/**
	 * Create the panel.
	 */
	public POScash(POSpayment paymentPanel, String amount) {
		setLayout(null);
		
		panel = this;
		
		JLabel lblEnterCash = new JLabel("Enter Cash");
		lblEnterCash.setBounds(180, 11, 260, 14);
		add(lblEnterCash);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(95, 59, 46, 14);
		add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(180, 56, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//paymentPanel.getPayment().setAmtTendered(new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
//				paymentPanel.setAmount(new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
				if(!textField.getText().equals("")) {
					paymentPanel.addPayment(new Cash(amount, new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP)));
					paymentPanel.updateAmmount();
					paymentPanel.remove(panel);
					paymentPanel.repaint();	
				} else {
					System.out.println("invalid");
				}
				 
			}
		});
		btnSave.setBounds(78, 84, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnCancel.setBounds(177, 84, 89, 23);
		add(btnCancel);

	}

}
