package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import posPD.Store;
import posPD.TaxRate;


public class TaxRateEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public TaxRateEditPanel(Window currentFrame, Store store, TaxCategoryEditPanel panel, TaxRate rate, String categoryName, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblEditTaxrateFor = new JLabel("Edit TaxRate for: "+categoryName);
		lblEditTaxrateFor.setBounds(70, 11, 370, 14);
		add(lblEditTaxrateFor);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(70, 83, 73, 14);
		add(lblEffectiveDate);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(149, 80, 86, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(rate.getEffectiveDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		
		JLabel lblRate = new JLabel("Rate:");
		lblRate.setBounds(70, 127, 46, 14);
		add(lblRate);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField_1.setBounds(149, 124, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(rate.getTaxRate().toString());
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal tax = new BigDecimal(textField_1.getText()).setScale(2, BigDecimal.ROUND_HALF_UP);
				LocalDate date = LocalDate.parse(textField.getText(), DateTimeFormatter.ofPattern("MM/dd/yy"));
				if(tax.compareTo(new BigDecimal(0)) < 0) {
					return;
				}
				rate.setTaxRate(tax);
				rate.setEffectiveDate(date);
				
				if(isAdd) {
					panel.getRates().add(rate);
					panel.getListModel().addElement(rate);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(70, 266, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(300, 266, 89, 23);
		add(btnCancel);

	}
}
