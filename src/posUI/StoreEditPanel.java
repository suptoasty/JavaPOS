package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import posPD.Store;

public class StoreEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StoreEditPanel(Window currentFrame, Store store, JPanel panel) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Store");
		lblNewLabel.setBounds(155, 11, 225, 23);
		add(lblNewLabel);
		
		JLabel lblThisIsJfjfjfj = new JLabel("StoreName: ");
		lblThisIsJfjfjfj.setBounds(57, 80, 77, 23);
		add(lblThisIsJfjfjfj);
		
		textField = new JTextField();
		textField.setBounds(121, 81, 86, 20);
		add(textField);
		textField.setColumns(10);
		textField.setText(store.getName());
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				store.setName(textField.getText());
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton.setBounds(57, 266, 89, 23);
		add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(panel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(291, 266, 89, 23);
		add(btnCancel);

	}
}
