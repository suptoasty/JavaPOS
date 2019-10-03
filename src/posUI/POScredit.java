package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Credit;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;

public class POScredit extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private POScredit panel;

	/**
	 * Create the panel.
	 */
	public POScredit(POSpayment paymentPanel, String amount) {
		setLayout(null);
		
		panel = this;
		
		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setBounds(180, 11, 46, 14);
		add(lblCredit);
		
		JLabel label = new JLabel("Amount:");
		label.setBounds(10, 39, 46, 14);
		add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(66, 36, 86, 20);
		add(textField);
		
		JLabel label_1 = new JLabel("CardType:");
		label_1.setBounds(10, 64, 46, 14);
		add(label_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(66, 61, 86, 20);
		add(comboBox);
		
		JLabel label_2 = new JLabel("AccountNumber");
		label_2.setBounds(10, 92, 46, 14);
		add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(66, 89, 86, 20);
		add(textField_1);
		
		JLabel label_3 = new JLabel("Expire:");
		label_3.setBounds(10, 120, 46, 14);
		add(label_3);
		
		JLabel lblNewLabel = new JLabel("Number:");
		lblNewLabel.setBounds(10, 149, 46, 14);
		add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(66, 149, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(66, 118, 139, 20);
		add(datePicker);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//paymentPanel.getPayment().setAmtTendered(new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
				//paymentPanel.setAmount(new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
				paymentPanel.addPayment(new Credit(textField_2.getText(), textField_1.getText(), datePicker.getDate().toString(), amount, new BigDecimal(textField.getText()).setScale(2, BigDecimal.ROUND_HALF_UP)));
				paymentPanel.updateAmmount();
				paymentPanel.remove(panel);
				paymentPanel.repaint();	
			}
		});
		button.setBounds(10, 174, 89, 23);
		add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(109, 174, 89, 23);
		add(button_1);
	
	

	}
}
