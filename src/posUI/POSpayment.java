package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Cashier;
import posPD.Payment;
import posPD.Register;
import posPD.Sale;
import posPD.Session;
import posPD.Store;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeSet;
import java.awt.event.ActionEvent;

public class POSpayment extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private Payment payment = new Payment();
	private BigDecimal am = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_HALF_UP);
	private POSpayment curPanel;
	
	private POScash cashPanel =  null;
	private POScredit creditPanel = null;
	private POScheck checkPanel = null;
	private JButton btnCompletePaymnt = new JButton("Complete Payment");
	private Sale sale;
	private ArrayList<Payment> payments;



	/**
	 * Create the panel.
	 */
	public POSpayment(Window currentFrame, Store store, Session session, Cashier cashier, Register register, POSsale currentPanel, Sale sale, Payment prePayment) {
		setLayout(null);
		curPanel = this;
		this.sale = sale;
		payments = new ArrayList<Payment>();
		
		if(sale.getPayments() != null) {
//			payment = prePayment;
//			am = prePayment.getAmtTendered();
			for(Payment p : sale.getPayments()) {
				payments.add(p);
				am = am.add(p.getAmtTendered());
			}
		}
		
//		JPanel panel = new JPanel();
//		panel.setBounds(163, 48, 277, 195);
//		add(panel);
		
		
		JLabel lblPayments = new JLabel("Payments");
		lblPayments.setBounds(180, 11, 260, 14);
		add(lblPayments);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(10, 48, 46, 14);
		add(lblTotal);
		
		JLabel lblTendered = new JLabel("Tendered: ");
		lblTendered.setBounds(10, 73, 53, 14);
		add(lblTendered);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(67, 48, 86, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(sale.calcTotal().toString());
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(67, 70, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		//textField_1.setText("0.00");
		textField_1.setText(am.toString());
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPanel = new POScheck(curPanel, textField.getText());
				checkPanel.setBounds(163, 48, 277, 195);
				
				if(cashPanel != null) {
					remove(cashPanel);
					
				}
				if(creditPanel != null) {
					remove(creditPanel);
					
				}
				if(checkPanel != null) {
					remove(checkPanel);
					
				}
				
				add(checkPanel);
				repaint();
			}
		});
		btnCheck.setBounds(10, 115, 89, 23);
		add(btnCheck);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditPanel = new POScredit(curPanel, textField.getText());
				creditPanel.setBounds(163, 48, 277, 195);
				
				if(cashPanel != null) {
					remove(cashPanel);
					
				}
				if(creditPanel != null) {
					remove(creditPanel);
					
				}
				if(checkPanel != null) {
					remove(checkPanel);
					
				}
				
				add(creditPanel);
				repaint();
			}
		});
		btnCredit.setBounds(10, 149, 89, 23);
		add(btnCredit);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cashPanel = new POScash(curPanel, textField.getText());
				cashPanel.setBounds(163, 48, 277, 195);
				
				if(cashPanel != null) {
					remove(cashPanel);
					
				}
				if(creditPanel != null) {
					remove(creditPanel);
					
				}
				if(checkPanel != null) {
					remove(checkPanel);
					
				}
				
				//remove(cashPanel);
				add(cashPanel);
				repaint();
			}
		});
		btnCash.setBounds(10, 183, 89, 23);
		add(btnCash);
		
		JButton btnReturnToSale = new JButton("Return To Sale");
		btnReturnToSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnReturnToSale.setBounds(67, 266, 103, 23);
		add(btnReturnToSale);
		
		btnCompletePaymnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payment.setAmtTendered(am);
				//sale.setPayment(payment);
				sale.setPayments(payments);
				currentPanel.updateAmounts();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCompletePaymnt.setBounds(280, 266, 103, 23);
		add(btnCompletePaymnt);
		btnCompletePaymnt.setEnabled(false);
		updateAmmount();
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public BigDecimal getAmount() {
		return am;
	}
	
	public void setAmount(BigDecimal amount) {
		this.am = amount;
	}
	
	public void updateAmmount() {
//		textField_1.setText(am.toString());//payment.getAmtTendered().toString());
//		if(sale.calcTotal().compareTo(new BigDecimal(textField_1.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN)) <= 0) {
//			btnCompletePaymnt.setEnabled(true);
//		}
		BigDecimal amon = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		for(Payment p : payments) {
			amon = amon.add(p.getAmtTendered());
		}
		textField_1.setText(amon.toString());
		if(sale.calcTotal().compareTo(new BigDecimal(textField_1.getText()).setScale(2, BigDecimal.ROUND_HALF_EVEN)) <= 0) {
			btnCompletePaymnt.setEnabled(true);
		}
	}
	
	public void addPayment(Payment payment) {
		payments.add(payment);
	}
}
