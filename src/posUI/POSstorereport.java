package posUI;

import javax.swing.JPanel;

import posPD.Cash;
import posPD.Check;
import posPD.Credit;
import posPD.Payment;
import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class POSstorereport extends JPanel {

	/**
	 * Create the panel.
	 */
	public POSstorereport(Window currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblStoreReport = new JLabel("Store Report");
		lblStoreReport.setBounds(180, 11, 260, 14);
		add(lblStoreReport);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(50, 40, 46, 14);
		add(lblDate);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(106, 25, 166, 31);
		add(datePicker);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 430, 209);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(datePicker.getDate() != null) {
						textArea.setText("");
						BigDecimal cash = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal credit = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal check = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
						int items = 0;
						BigDecimal amount = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
						for(Session s : store.getSessions()) {	
						if(s.getEndDate().isEqual(datePicker.getDate())) {
							Sale tempSale = null;
							for(Sale sale : s.getSales()) {
								for(Payment p : sale.getPayments()) {
									if(p instanceof Check) {
										check = check.add(p.getAmount());
									} else if(p instanceof Credit) {
										credit = credit.add(p.getAmount());
									} else if(p instanceof Cash) {
										cash = cash.add(p.getAmount());
									}
								}
								for(SaleLineItem i : sale.getSaleLineItems()) {
									items += i.getQuantity();
								}
								amount = amount.add(sale.calcTotal());
							}
							//textArea.append("Report for:"+s.getSDateTime().toString()+"\n");
							textArea.append("Cashier: "+s.getCashier().getPerson().getName()+"\tRegister: "+s.getRegister().getNumber()+"\n");
//							textArea.append("Sales\tItems\tCash\tCheck\tCredit\tTimeS\tTimeE\tTimeSale\n");
							textArea.append("Sales\tItems\tCash\tCheck\tCredit\tTimeSale\n");
							for(Sale sal : s.getSales()) {
								textArea.append(amount+"\t"+items+"\t"+cash+"\t"+check+"\t"+credit+"\t"+sal.getTimie().getHour()+":"+sal.getTimie().getMinute()+"\n\n");
							}
							//textArea.append("EndSession: "+s.getEDatetime()+"\n\n");
						} else { 
							System.out.println("No Data");
							System.out.println(s.getEndDate().toString());
							System.out.println(datePicker.getDate());
							//textArea.append("No Data for Session");
						}
					}
				}
			}
		});
		btnGenerate.setBounds(10, 271, 89, 23);
		add(btnGenerate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ContentPane(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(351, 271, 89, 23);
		add(btnCancel);

	}
}
