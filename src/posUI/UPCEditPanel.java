package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import posPD.Item;
import posPD.Store;
import posPD.UPC;


public class UPCEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	public UPCEditPanel(JFrame currentFrame, Store store, ListEditPanel panel, Item item, UPC upc, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblUpcEdit = new JLabel("UPC Edit");
		lblUpcEdit.setBounds(180, 11, 260, 14);
		add(lblUpcEdit);
		
		JLabel lblUpc = new JLabel("UPC: ");
		lblUpc.setBounds(50, 40, 46, 14);
		add(lblUpc);
		
		textField = new JTextField();
		textField.setBounds(106, 36, 86, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(upc.getUPC());
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upc.setUPC(textField.getText());
				upc.setItem(item);
//				store.getItems().get(textField_1.getText()).addUPC(upc);
				
				if(isAdd) {
					panel.getUPCListModel().addElement(upc);
					store.addUPC(upc);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(50, 266, 89, 23);
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
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(50, 65, 46, 14);
		add(lblItem);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 67, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("");
		// TODO Auto-generated constructor stub
	}

}
