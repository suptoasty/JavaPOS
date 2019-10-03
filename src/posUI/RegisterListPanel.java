package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import posPD.Register;
import posPD.Store;

public class RegisterListPanel extends JPanel {
	private RegisterListPanel panel;
	DefaultListModel<Register> listModel = new DefaultListModel<Register>();
	JList<Register> list = new JList<Register>();


	/**
	 * Create the panel.
	 */
	public RegisterListPanel(Window currentFrame, Store store) {
		setLayout(null);
		
		panel = this;
		
		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setBounds(200, 11, 195, 14);
		add(lblRegisters);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 50, 345, 200);
		add(scrollPane);
		
		//JList<Register> list = new JList<Register>();
		scrollPane.setViewportView(list);
		
		for(Register register : store.getRegisters().values()) {
			listModel.addElement(register);
		}
		list.setModel(listModel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setNumber(String.valueOf(store.getRegisters().size()+1));
//				store.addRegister(register);
//				listModel.addElement(register);
//			
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, panel, register, true));
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(50, 266, 89, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeRegister(list.getSelectedValue());
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnRemove.setBounds(180, 266, 89, 23);
		add(btnRemove);
		
		JButton btnDetails = new JButton("Edit");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, panel, list.getSelectedValue(), false));
					currentFrame.revalidate();
				}
			}
		});
		btnDetails.setBounds(306, 266, 89, 23);
		add(btnDetails);
		
		btnDetails.setEnabled(false);
		btnRemove.setEnabled(false);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() >= 0) {
					btnDetails.setEnabled(true);
					btnRemove.setEnabled(true);
				} else {
					btnDetails.setEnabled(false);
					btnRemove.setEnabled(false);
				}
			}
		});
		

	}
	
	public DefaultListModel<Register> getListModel() {
		return listModel;
	}
	
	public JList<Register> getList() {
		return list;
	}
}
