package posUI;

import javax.swing.JPanel;

import posPD.Cashier;
import posPD.Payment;
import posPD.Register;
import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JTextField;

public class POSendsession extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private BigDecimal tot = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);


	/**
	 * Create the panel.
	 */
	public POSendsession(Window currentFrame, Store store, Session session, Cashier cashier, Register register) {
		setLayout(null);
		
		BigDecimal change = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
				
		JLabel lblEndSession = new JLabel("End Session");
		lblEndSession.setBounds(180, 11, 260, 14);
		add(lblEndSession);
		
		DefaultListModel<Sale> listModel = new DefaultListModel<Sale>();
		for(Sale s : session.getSales()) {
			listModel.addElement(s);
			change.add(s.calcChange());
		}

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				session.setCashier(cashier);
				session.setRegister(register);
				session.setEndDate(LocalDate.now().toString());
				session.setEdatetime(LocalDateTime.now());
				store.addSession(session);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ContentPane(currentFrame, store));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 300, 428, 219);
		add(scrollPane);
		
		JList<Sale> list = new JList<Sale>();
		scrollPane.setViewportView(list);
		list.setModel(listModel);
		btnSave.setBounds(10, 266, 89, 23);
		add(btnSave);
		list.setModel(listModel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ContentPane(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(351, 266, 89, 23);
		add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Cashier:");
		lblNewLabel.setBounds(36, 50, 46, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(125, 47, 86, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(cashier.getPerson().getName());
		
		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(36, 75, 46, 14);
		add(lblRegister);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(125, 72, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(cashier.getNumber());
		
		JLabel lblNumberSales = new JLabel("Number Sales:");
		lblNumberSales.setBounds(36, 100, 69, 14);
		add(lblNumberSales);
		
		textField_2 = new JTextField();
		textField_2.setBounds(125, 100, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		int quant = 0;

		for(Sale sale : session.getSales()) {
			quant++;
			tot = tot.add(sale.calcTotal());
		}
		textField_2.setText(""+quant);
		
		JLabel lblTotalSales = new JLabel("Total Sales:");
		lblTotalSales.setBounds(36, 125, 63, 14);
		add(lblTotalSales);
		
		JLabel lblEnterCash = new JLabel("Enter Cash:");
		lblEnterCash.setBounds(36, 156, 63, 14);
		add(lblEnterCash);
		
		textField_3 = new JTextField();
		textField_3.setBounds(125, 125, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(tot.toString());
		
		textField_4 = new JTextField();
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BigDecimal d = new BigDecimal(textField_4.getText()).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal f = session.calcCashCountDiff(d);
				textField_5.setText(f.toString());
			
			}
		});
		textField_4.setBounds(125, 153, 86, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblCashCountDiff = new JLabel("Cash Count Diff:");
		lblCashCountDiff.setBounds(36, 184, 80, 14);
		add(lblCashCountDiff);
		
		textField_5 = new JTextField();
		textField_5.setBounds(125, 181, 86, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
		
		register.getDrawer().addCash(tot);
		
		register.getDrawer().removeCash(change);

	}
}
