package posUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import posDM.StoreDM;
import posPD.Store;

public class Window extends JFrame {

	private Store store = new Store();
	private StoreDM dm = new StoreDM();
	private Window currentFrame = this;
	private JPanel panel;
	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public Window(Store store) throws FileNotFoundException {
		dm.loadData("StoreData.csv", store);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("POS - Jason Lonsinger");
		setAlwaysOnTop(true);
		setBounds(100, 100, 450+(450/10), 300+(300/3));
		
		panel = new ContentPane(currentFrame, store);
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().revalidate();
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu Maintenance = new JMenu("Maintenance");
		Maintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Maintenance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				Maintenance.setPopupMenuVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() >= 2) {
					getContentPane().removeAll();
					getContentPane().add(new ContentPane(currentFrame, store));
					getContentPane().revalidate();
				}
			}
		});
		menuBar.add(Maintenance);
		
		JMenuItem itmTaxCategory = new JMenuItem("TaxCategory");
		itmTaxCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		itmTaxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		
		JMenuItem itmRegister = new JMenuItem("Registers");
		itmRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new RegisterListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		itmRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new StoreEditPanel(currentFrame, store, panel));
				getContentPane().revalidate();
			}
		});
		Maintenance.add(mntmStore);
		
		JMenuItem mnItem = new JMenuItem("Item");
		mnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		Maintenance.add(mnItem);
		
		JMenuItem mntmCashier = new JMenuItem("Cashier");
		mntmCashier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		
		JMenuItem mntmPrices = new JMenuItem("Prices");
		mntmPrices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new PriceListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		Maintenance.add(mntmPrices);
		Maintenance.add(mntmCashier);
		Maintenance.add(itmRegister);
		Maintenance.add(itmTaxCategory);
		
		JMenu mnPos = new JMenu("POS");
		menuBar.add(mnPos);
		
		JMenuItem mntmSales = new JMenuItem("Sales");
		mntmSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LoginPanel(currentFrame, store));
				currentFrame.revalidate();
			}
		});
		mnPos.add(mntmSales);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmSalesReport = new JMenuItem("Sales Report");
		mntmSalesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new SalesReports(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmSalesReport);
		
		JMenuItem mntmStoreReport = new JMenuItem("Store Report");
		mntmStoreReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new POSstorereport(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmStoreReport);
		
		JMenuItem mntmItemReport = new JMenuItem("Item Report");
		mntmItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new POSitemreport(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmItemReport);
		
		JMenuItem mntmCashierReport = new JMenuItem("Cashier Report");
		mntmCashierReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new POScashierreport(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmCashierReport);
		Maintenance.add(itmTaxCategory);
		
		JMenuItem mntmUpc = new JMenuItem("UPC");
		mntmUpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new UPCListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		Maintenance.add(mntmUpc);
	}
	
	// set/getters
	public void setStore(Store store) {
		this.store = store;
	}
	
	public Store getStore() {
		return store;
	}
	
	public StoreDM getDataManager() {
		return dm;
	}
}
