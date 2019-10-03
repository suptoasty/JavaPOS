package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import posPD.Store;

public class ContentPane extends JPanel {
	JPanel panel;

	/**
	 * Create the panel.
	 */
	public ContentPane(Window currentFrame, Store store) {
		JLabel lblThisIsA = new JLabel(store.getName());
		addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent arg0) {
				lblThisIsA.setText(store.getName());
			}
		});
		setLayout(null);
		
		panel = this;
		
		lblThisIsA.setBounds(180, 11, 243, 14);
		add(lblThisIsA);
		
		JButton btnSaveStoreData = new JButton("Save Store Data");
		btnSaveStoreData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Save Dialogue");
				//currentFrame.dm.saveData("StoreData2.csv", store);
				JFileChooser choose = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
				choose.setFileFilter(filter);
				int selection = choose.showSaveDialog(panel);
				if(selection == JFileChooser.APPROVE_OPTION) {
					try {
						currentFrame.getDataManager().saveData(choose.getSelectedFile().getAbsoluteFile().getName(), currentFrame.getStore());
						System.out.println("Saved: "+choose.getSelectedFile().getAbsoluteFile().getName());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			}
		});
		btnSaveStoreData.setBounds(310, 266, 130, 23);
		add(btnSaveStoreData);
		
		JButton btnOpenStoreData = new JButton("Open Store Data");
		btnOpenStoreData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Open Dialogue");
				JFileChooser choose = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
				choose.setFileFilter(filter);
				int selection = choose.showOpenDialog(currentFrame);
				if(selection == JFileChooser.APPROVE_OPTION) {
					try {
						currentFrame.getDataManager().loadData(choose.getSelectedFile().getName(), currentFrame.getStore());
						System.out.println("LOADED: "+choose.getSelectedFile().getAbsoluteFile().getName());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			}
		});
		btnOpenStoreData.setBounds(10, 266, 130, 23);
		add(btnOpenStoreData);
		
		
	}
}
