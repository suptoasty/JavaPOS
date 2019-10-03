package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import posPD.Store;
import posPD.TaxCategory;
import posPD.TaxRate;

public class TaxCategoryEditPanel extends JPanel {
	private JTextField textField;
	private TaxCategoryEditPanel thisPanel;
	private TreeSet<TaxRate> rates = new TreeSet<TaxRate>();
	private DefaultListModel<TaxRate> listModel = new DefaultListModel<TaxRate>();


	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(Window currentFrame, Store store, TaxCategoryListPanel panel, TaxCategory tax, Boolean isAdd) {
		setLayout(null);
		
		String taxCategory = tax.getTaxCategory();
		thisPanel = this;
		
		JLabel label = new JLabel("Edit Item");
		label.setBounds(177, 11, 46, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Category:");
		label_1.setBounds(28, 55, 60, 14);
		add(label_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		textField.setText(tax.getTaxCategory());
		textField.setColumns(10);
		textField.setBounds(81, 52, 112, 20);
		add(textField);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tax.setTaxCategory(textField.getText());
				tax.setTaxRates(rates);
				
				if(isAdd) {
					panel.getListModel().addElement(tax);
					store.addTaxCategory(tax);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		button.setBounds(71, 266, 89, 23);
		add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		button_1.setBounds(249, 266, 89, 23);
		add(button_1);
		
		JLabel label_2 = new JLabel("TaxRates:");
		label_2.setBounds(203, 55, 95, 14);
		add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(203, 80, 237, 127);
		add(scrollPane);
		
		JList<TaxRate> list = new JList<TaxRate>();
		scrollPane.setViewportView(list);
		
		for(TaxRate rate : tax.getTaxRates()) {
			listModel.addElement(rate);
			rates.add(rate);
		}
		list.setModel(listModel);
		
		JButton button_2 = new JButton("Add");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy"));
				LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yy"));
				
				TaxRate rate = new TaxRate(date, new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));
//				rates.add(rate);
//				listModel.addElement(rate);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, store, thisPanel, rate, tax.getTaxCategory(), true));
				currentFrame.getContentPane().revalidate();	
			}
		});
		button_2.setBounds(203, 218, 60, 23);
		add(button_2);
		
		JButton button_3 = new JButton("Remove");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					rates.remove(list.getSelectedValue());
					listModel.remove(list.getSelectedIndex());
				}
			}
		});
		button_3.setBounds(285, 218, 71, 23);
		add(button_3);
		
		JButton button_4 = new JButton("Edit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, store, thisPanel, list.getSelectedValue(), tax.getTaxCategory(), false));
					currentFrame.getContentPane().revalidate();					
				}
			}
		});
		button_4.setBounds(380, 218, 60, 23);
		add(button_4);
		
		button_3.setEnabled(false);
		button_4.setEnabled(false);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					button_3.setEnabled(true);
					button_4.setEnabled(true);
				} else {
					button_3.setEnabled(false);
					button_4.setEnabled(false);
				}
			}
		});
	}
	
	public TreeSet<TaxRate> getRates() {
		return rates;
	}
	
	public DefaultListModel<TaxRate> getListModel() {
		return listModel;
	}
}
