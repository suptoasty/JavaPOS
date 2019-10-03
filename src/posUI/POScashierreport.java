package posUI;

import javax.swing.JPanel;
import javax.swing.ListModel;

import posPD.Sale;
import posPD.Session;
import posPD.Store;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class POScashierreport extends JPanel {

	/**
	 * Create the panel.
	 */
	public POScashierreport(Window currentFrame, Store store) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 430, 185);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		JLabel lblCashierReports = new JLabel("Cashier Reports");
		lblCashierReports.setBounds(180, 11, 260, 14);
		add(lblCashierReports);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(55, 45, 46, 14);
		add(lblDate);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(111, 36, 155, 27);
		add(datePicker);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(datePicker.getDate() != null) {
					textArea.setText("");
					textArea.append("Cashier Report for:"+datePicker.getDate().toString()+"\n"
							+"Number\tName\t\tCount\tAmount\n");
					BigDecimal amount = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
					for(Session s : store.getSessions()) {	
						if(s.getEndDate().isEqual(datePicker.getDate())) {
							System.out.println(s.getCashier());
							textArea.append(s.getCashier().getNumber()+"\t"+s.getCashier().getPerson().getName());
							textArea.append(" \t\t"+s.calcTotalSalesAmount()+" \t"+s.calcTotalSalesAmount()+"\n");
							amount = amount.add(s.calcTotalSalesAmount());
						} else {
							System.out.println("No Data");
							System.out.println(s.getEndDate().toString());
							System.out.println(datePicker.getDate());
							//textArea.append("No Data for Session");
						}
					}
					textArea.append("Total: \t\t\t\t"+amount.toString()+"\n");
				}
			}
		});
		btnGenerate.setBounds(10, 266, 89, 23);
		add(btnGenerate);
		
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

	}
}
