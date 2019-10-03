package posUI;

import javax.swing.JPanel;

import posPD.Cashier;
import posPD.Item;
import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class POSitemreport extends JPanel {

	/**
	 * Create the panel.
	 */
	public POSitemreport(Window currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblItemReports = new JLabel("Item Reports");
		lblItemReports.setBounds(180, 11, 260, 14);
		add(lblItemReports);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(70, 45, 46, 14);
		add(lblDate);
		
		DatePicker datePicker = new DatePicker();
		datePicker.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		datePicker.setBounds(126, 36, 165, 30);
		add(datePicker);
		
		DefaultListModel<Item> listModel = new DefaultListModel<Item>();
		for(Item i : store.getItems().values()) {
			listModel.addElement(i);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 76, 420, 190);
		add(scrollPane);
		//list.setModel(listModel);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		//time,totalsales,
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(datePicker.getDate() != null) {
					int count = 0;
					textArea.setText("");
					for(Session s : store.getSessions()) {
						BigDecimal amount = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
						if(s.getEndDate().isEqual(datePicker.getDate())) {
							textArea.append("Session: "+count+"\n");
							count++;
							textArea.append("Cashier Report for:"+datePicker.getDate().toString()+"\n"
									+"Number\tName\t\tCount\tAmount\n");
							System.out.println(s.getCashier());
							for(Sale sale : s.getSales()) {
								for(SaleLineItem i : sale.getSaleLineItems()) {									
									textArea.append(i.getItem().getNumber()+"\t"+i.getItem().getName());
									textArea.append(" \t"+i.getQuantity()+" \t"+sale.calcTotal()+"\n");
									amount = amount.add(sale.calcTotal());
								}
							}
							textArea.append("Total: \t\t\t\t"+amount.toString()+"\n");
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
		
		btnGenerate.setBounds(20, 271, 89, 23);
		add(btnGenerate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ContentPane(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(351, 271, 89, 23);
		add(btnClose);

	}
}
