package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import posPD.Item;
import posPD.Price;
import posPD.PromoPrice;
import posPD.Store;

public class PriceEditPanel extends JPanel {
	private JTextField txtPromoenddatetext;
	private JTextField textField;
	private JTextField textField_1;
	private Boolean isPromo = false;
	
	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, ListEditPanel panel, Store store, Item item, Price price, Boolean isAdd) {
		setLayout(null);
		
		isPromo = price.isPromo();
		
		JLabel lblPriceEdit = new JLabel("Price Edit");
		lblPriceEdit.setBounds(180, 11, 46, 14);
		add(lblPriceEdit);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(40, 62, 46, 14);
		add(lblPrice);
		
		JLabel lblEffectivedate = new JLabel("EffectiveDate:");
		lblEffectivedate.setBounds(40, 90, 70, 14);
		add(lblEffectivedate);
		
		JLabel lblPromoenddate = new JLabel("PromoEndDate:");
		lblPromoenddate.setBounds(40, 115, 80, 14);
		add(lblPromoenddate);
		
		txtPromoenddatetext = new JTextField();
		txtPromoenddatetext.setEnabled(isPromo);
		txtPromoenddatetext.setBounds(130, 112, 185, 20);
		add(txtPromoenddatetext);
		txtPromoenddatetext.setColumns(10);
		if(isPromo) {
			PromoPrice pr = (PromoPrice)price;
			txtPromoenddatetext.setText(pr.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		}
		
		textField = new JTextField();
		textField.setBounds(130, 87, 185, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(price.getEffectiveDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 59, 185, 20);
		add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(price.getPrice().toString());
		
		JLabel lblPromoprice = new JLabel("PromoPrice:");
		lblPromoprice.setBounds(40, 140, 70, 14);
		add(lblPromoprice);
		
		
		JCheckBox chckbxPromo = new JCheckBox("Promo");
		chckbxPromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isPromo = chckbxPromo.isSelected();
				if(isPromo) {
					txtPromoenddatetext.setEnabled(true);
				} else {
					txtPromoenddatetext.setEnabled(false);
				}
			}
		});
		chckbxPromo.setEnabled(isPromo);
		chckbxPromo.setSelected(isPromo);
//		if(isPromo) {
//			txtPromoenddatetext.setEnabled(true);
//		} else {
//			txtPromoenddatetext.setEnabled(false);
//		}

		chckbxPromo.setBounds(130, 139, 97, 23);
		add(chckbxPromo);
		
//		JToggleButton tglbtnTogglePromo = new JToggleButton("Toggle Promo");
//		tglbtnTogglePromo.setSelected(isPromo);
//		tglbtnTogglePromo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				isPromo = tglbtnTogglePromo.isSelected();
//
//			}
//		});
//		tglbtnTogglePromo.setBounds(130, 136, 121, 23);
//		add(tglbtnTogglePromo);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate eDate = LocalDate.parse(textField.getText(), DateTimeFormatter.ofPattern("MM/dd/yy"));
				BigDecimal pr = new BigDecimal(textField_1.getText()).setScale(2, BigDecimal.ROUND_HALF_UP);
				if(isPromo) {
					LocalDate pDate = LocalDate.parse(txtPromoenddatetext.getText(), DateTimeFormatter.ofPattern("MM/dd/yy"));
					item.getPrices().remove(price);
					PromoPrice p = new PromoPrice();
					p.setEffectiveDate(eDate);
					p.setEndDate(pDate);
					p.setPrice(pr);
					
					item.getPrices().add(p);
				} else {
					item.getPrices().remove(price);

					Price p = new Price();
					p.setEffectiveDate(eDate);
					p.setPrice(pr);

					
					item.getPrices().add(p);
				}
				
				if(isAdd) {
					if(isPromo) {
						LocalDate pDate = LocalDate.parse(txtPromoenddatetext.getText(), DateTimeFormatter.ofPattern("MM/dd/yy"));
						//item.getPrices().remove(price);
						PromoPrice p = new PromoPrice();
						p.setEffectiveDate(eDate);
						p.setEndDate(pDate);
						p.setPrice(pr);
						p.setItem(item);
						
						item.getPrices().add(p);
					} else {
						//item.getPrices().remove(price);

						Price p = new Price();
						p.setEffectiveDate(eDate);
						p.setPrice(pr);
						p.setItem(item);
						
						item.getPrices().add(p);
					}
				}
				panel.rebuildList(store);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
				
			}
		});
		btnSave.setBounds(40, 266, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(351, 266, 89, 23);
		add(btnCancel);
		

	}
}
