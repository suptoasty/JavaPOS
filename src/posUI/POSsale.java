package posUI;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import posPD.Cashier;
import posPD.Item;
import posPD.Payment;
import posPD.Register;
import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;
import posPD.UPC;
import java.awt.Color;

public class POSsale extends JPanel {
	private JTextField txtItem;
	private JTextField txtQuantity;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Boolean taxFree;
	private Payment payment = new Payment();
	private POSsale panel;
	private Sale sale = new Sale();
	private JButton btnCompleteSale = new JButton("Complete Sale");
//	private TreeSet<Payment> payments = new TreeSet<Payment>();
	

	/**
	 * Create the panel.
	 */
	public POSsale(Window currentFrame, Store store, Session session, Cashier cashier, Register register) {
		setLayout(null);
		panel = this;
		
		taxFree = false;
		session.setSdatetime(LocalDateTime.now());
		
		sale = new Sale(Boolean.toString(taxFree));
		sale.setSaleDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		session.setStartDate(LocalDate.now().toString());
		
		JLabel lblSale = new JLabel("Sale");
		lblSale.setBounds(200, 11, 46, 14);
		add(lblSale);
		
		JLabel lblCashier = new JLabel("Cashier: ");
		lblCashier.setBounds(10, 11, 156, 14);
		add(lblCashier);
		lblCashier.setText("Cashier: "+cashier.getNumber()+" "+cashier.getPerson().getName());
		
		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(10, 36, 156, 14);
		add(lblRegister);
		lblRegister.setText("Register: "+register.getNumber());
		
		JCheckBox chckbxTaxfree = new JCheckBox("TaxFree");

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(162, 64, 46, 14);
		add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("Quantity");
		txtQuantity.setBounds(218, 61, 86, 20);
		add(txtQuantity);
		txtQuantity.setColumns(10);
		txtQuantity.setText(Integer.toString(1));
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(354, 89, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(354, 114, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(354, 139, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(354, 164, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(354, 189, 86, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_3.setText(sale.getPayment().getAmtTendered().toString());
		
		JLabel lblIncorrectUpc = new JLabel("Incorrect UPC");
		lblIncorrectUpc.setForeground(Color.RED);
		lblIncorrectUpc.setBounds(241, 235, 199, 14);
		add(lblIncorrectUpc);
		lblIncorrectUpc.setVisible(false);
		
		JLabel lblItem = new JLabel("Item: ");
		lblItem.setBounds(10, 61, 46, 14);
		add(lblItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 220, 115);
		add(scrollPane);
		
		JList<SaleLineItem> list = new JList<SaleLineItem>();
		list.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent arg0) {
			}
		});
		scrollPane.setViewportView(list);
		DefaultListModel<SaleLineItem> listModel = new DefaultListModel<SaleLineItem>();
		list.setModel(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
//				if(list.getSelectedIndex() >= 0) {
//					//update textFields
//					BigDecimal subTotal = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_UNNECESSARY);
//					for(int i = 0; i < listModel.size(); i++) {
//						subTotal.add(listModel.getElementAt(i).calcSubTotal(LocalDate.now()));
//					}
//					textField.setText(subTotal.toString());
//				}
			}
		});
		
		txtItem = new JTextField();
		txtItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean addItem = true;
				Item item = store.findItemForUPC(txtItem.getText());
				if(item == null) {
					System.out.println("Item: NULL");
					lblIncorrectUpc.setVisible(true);
					return;
				} 
				for(int i = 0; i < listModel.getSize(); i++) {
					if(item == listModel.getElementAt(i).getItem()) {
						addItem = false;
					}
				}
				lblIncorrectUpc.setVisible(false);
				if(addItem) {
					SaleLineItem lineItem = new SaleLineItem(item.getNumber(), txtQuantity.getText());
					lineItem.setSale(sale);
					lineItem.setItem(item);
					sale.addSaleLineItem(lineItem);
					listModel.addElement(lineItem);					
				} 

				updateAmounts();
			}
		});
		txtItem.setText("Item");
		txtItem.setBounds(66, 61, 86, 20);
		add(txtItem);
		txtItem.setColumns(10);
		
		chckbxTaxfree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxFree = chckbxTaxfree.isSelected();
				sale.setTaxfree(taxFree);
				updateAmounts();
			}
		});
		chckbxTaxfree.setBounds(310, 60, 86, 23);
		add(chckbxTaxfree);
		
		JLabel lblSubtotal = new JLabel("SubTotal:");
		lblSubtotal.setBounds(263, 92, 81, 14);
		add(lblSubtotal);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setBounds(263, 117, 46, 14);
		add(lblTax);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(263, 142, 46, 14);
		add(lblTotal);
		
		JLabel lblAmtTendered = new JLabel("Amt Tendered:");
		lblAmtTendered.setBounds(263, 167, 86, 14);
		add(lblAmtTendered);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(263, 192, 46, 14);
		add(lblChange);
		
		JButton btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSpayment(currentFrame, store, session, cashier, register, panel, sale, payment));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPayment.setBounds(10, 215, 89, 23);
		add(btnPayment);
		
		btnCompleteSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Session session = new Session(cashier, register);				
				btnCompleteSale.setEnabled(false);
				sale.setTime(LocalDateTime.now());
				sale.addPayment(payment);
				session.addSale(sale);
				
				sale = new Sale();
				payment = new Payment();
				listModel.clear();
				txtItem.setText("");
				txtQuantity.setText("1");
				taxFree = false;
				chckbxTaxfree.setSelected(false);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnCompleteSale.setBounds(109, 215, 89, 23);
		add(btnCompleteSale);
		btnCompleteSale.setEnabled(false);
		
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//removes sale info
				sale.getSaleLineItems().clear();
				sale.getPayments().clear();
				payment = new Payment();
				listModel.clear();
				txtItem.setText("");
				txtQuantity.setText("1");
				taxFree = false;
				chckbxTaxfree.setSelected(false);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnCancelSale.setBounds(10, 249, 89, 23);
		add(btnCancelSale);
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				//session.addSale(sale);
//				//store.addSession(session);
//				BigDecimal one = new BigDecimal(textField_3.getText()).setScale(2, BigDecimal.ROUND_HALF_UP);
//				BigDecimal two = new BigDecimal(textField_4.getText()).setScale(2, BigDecimal.ROUND_HALF_UP);
//				register.getDrawer().addCash(new BigDecimal(textField_3.getText()).setScale(2, BigDecimal.ROUND_HALF_UP));
////				register.getDrawer().addCash(one);
////				register.getDrawer().removeCash(two);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSendsession(currentFrame, store, session, cashier, register));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(109, 249, 89, 23);
		add(btnEndSession);
		
		txtQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				for(SaleLineItem i : (SaleLineItem[])listModel.toArray()) {
//					for(UPC upc : i.getItem().getuPCs().values()) {
//						if(txtItem.getText().equals(upc.getUPC())) {
//							i.setQuantity(Integer.parseInt(txtQuantity.getText()));
//						}
//					}
//				}
				for(int i = 0; i < listModel.getSize(); i++) {
					for(UPC upc : listModel.getElementAt(i).getItem().getuPCs().values()) {
						if(txtItem.getText().equals(upc.getUPC())) {
							listModel.getElementAt(i).setQuantity(Integer.parseInt(txtQuantity.getText()));
							list.updateUI();
							
							BigDecimal temp;
							temp = sale.calcSubTotal();
							textField.setText(temp.toString());
							if(chckbxTaxfree.isSelected() == false) {
								temp = sale.calcTax();
								textField_1.setText(temp.toString());					
							} else {
								textField_1.setText("taxFree");
							}
							temp = sale.calcTotal();
							textField_2.setText(temp.toString());
							
						}
					}
				}
			}
		});
	}
	
	public void updateAmounts() {
		BigDecimal temp;
		
		temp = sale.calcSubTotal();
		textField.setText(temp.toString());
		if(taxFree == false) {
			temp = sale.calcTax();
			textField_1.setText(temp.toString());					
		} else {
			textField_1.setText("taxFree");
		}
		temp = sale.calcTotal();
		textField_2.setText(temp.toString());
		
		temp = sale.calcAmountTendered();
		textField_3.setText(temp.toString());
		
			
		temp = sale.calcChange();
		BigDecimal z = new BigDecimal(0.0).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(temp.compareTo(z) <= 0) {
			textField_4.setText(temp.negate().toString());
		} else {
			textField_4.setText("0.00");
		}
		
		if(sale.calcTotal().compareTo(sale.calcAmountTendered()) <= 0) {
			btnCompleteSale.setEnabled(true);
		} else if(sale.calcTotal().compareTo(sale.calcAmountTendered()) > 0) {
			btnCompleteSale.setEnabled(false);
		}

	}
}
