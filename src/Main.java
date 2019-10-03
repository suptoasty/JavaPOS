import java.awt.EventQueue;
import java.io.FileNotFoundException;

import posPD.Store;
import posUI.Window;

public class Main {
/*
//	public static void main(String[] args) throws FileNotFoundException {
////		Store store = new Store("456544bc", "awesome shop");
////		
////		
////		Test mytest = new Test();
////		mytest.test1(store);
////		
////		System.out.println("");
////		System.out.println("======================================================");
////		
////		mytest.test2(store);
////		
////		System.out.println("");
////		System.out.println("======================================================");
////		
////		mytest.test3(store);
////		
////		System.out.println("");
////		System.out.println("======================================================");
////		
////		mytest.test4(store);
//		
//		Store store = new Store();
//		
//		StoreDM test = new StoreDM();
//		test.loadData("StoreData.csv", store);
//		
//		System.out.println(store.toString());
//		
//		Iterator it = store.getCashiers().entrySet().iterator();
//		
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//		
//		System.out.println("");
//		it = store.getSessions().iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//		
//	}
*/
	
	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Store store = new Store();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window(store);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
/*		
		Store store = new Store();	
		//read data from file and load into store
		StoreDM test = new StoreDM();
		test.loadData("StoreData.csv", store);
		
		System.out.println(store.toString()); //print out store name
		
		//print out the store info
		Iterator it = store.getSessions().iterator();

		while(it.hasNext()) {
			System.out.println(it.next());
		}
*/
		
	}

}
