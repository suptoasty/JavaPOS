package posPD;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * The store that the system is keeping track of.
 */
public class Store {

	/**
	 * store number
	 */
	private String number;
	/**
	 * the name of the store
	 */
	private String name;
	/**
	 * the taxcategory for sales
	 */
	private TreeMap<String, TaxCategory> taxCategories;
	private TreeMap<String, Cashier> cashiers;
	private TreeMap<String, Register> registers;
	private TreeMap<String, Item> items;
	private ArrayList<Session> sessions;
	private TreeMap<String, UPC> upcs;

	/**
	 * Default constructor
	 */
	public Store() {
		number = "";
		name = "";
		taxCategories = new TreeMap<String, TaxCategory>();
		cashiers = new TreeMap<String, Cashier>();
		registers = new TreeMap<String, Register>();
		items = new TreeMap<String, Item>();
		sessions = new ArrayList<Session>();
		upcs = new TreeMap<String, UPC>();
	}

	/**
	 * Constructor that sets attributes based on paramerters
	 * @param number
	 * @param name
	 */
	public Store(String number, String name) {
		// TODO - implement Store.Store
		this.number = number;
		this.name = name;
		taxCategories = new TreeMap<String, TaxCategory>();
		cashiers = new TreeMap<String, Cashier>();
		registers = new TreeMap<String, Register>();
		items = new TreeMap<String, Item>();
		sessions = new ArrayList<Session>();
		upcs = new TreeMap<String, UPC>();
	}
	
	public void openStore() {
		
	}

	/**
	 * Adds item to item list
	 * @param item
	 */
	public void addItem(Item item) {
		items.put(item.getNumber(), item);
	}

	/**
	 * Removes item from item list
	 * @param item
	 */
	public void removeItem(Item item) {
		items.remove(item.getNumber());
	}
	
	/**
	 * Gets a single item
	 * @param item
	 */
	public Item getItem(Item item) {
		return items.get(item.getNumber());
	}

	/**
	 * Adds UPC
	 * @param upc
	 */
	public void addUPC(UPC upc) {
		upcs.put(upc.getUPC(), upc);
	}

	/**
	 * Removes UPC
	 * @param upc
	 */
	public void removeUPC(UPC upc) {
		upcs.remove(upc.getUPC());
	}

	/**
	 * Adds Cashier to cashier list
	 * @param cashier
	 */
	public void addCashier(Cashier cashier) {
		cashiers.put(cashier.getNumber(), cashier);
	}

	/**
	 * removes cashier from cashier list
	 * @param cashier
	 */
	public void removeCashier(Cashier cashier) {
		cashiers.remove(cashier.getNumber());
	}
	
	/**
	 * gets a single cashier
	 * @param cashier
	 */
	public Cashier getCashier(Cashier cashier) {
		return cashiers.get(cashier.getNumber());
	}
	
	/**
	 * adds register to list of registers
	 * @param register
	 */
	public void addRegister(Register register) {
		registers.put(register.getNumber(), register);
	}
	
	/**
	 * removes register from list of registers
	 * @param register
	 */
	public void removeRegister(Register register) {
		registers.remove(register.getNumber());
	}

	/**
	 * adds Session
	 * @param session
	 */
	public void addSession(Session session) {
		sessions.add(session);
	}

	/**
	 * removes Session
	 * @param session
	 */
	public void removeSession(Session session) {
		sessions.remove(session);
	}
	
	/**
	 * adds a tax category to list
	 * @param taxCategory
	 */
	public void addTaxCategory(TaxCategory taxCategory) {
		taxCategories.put(taxCategory.getTaxCategory(), taxCategory);
	}

	/**
	 * removes tax category from list
	 * @param taxCategory
	 */
	public void removeTaxCategory(TaxCategory taxCategory) {
		taxCategories.remove(taxCategory.getTaxCategory());
	}
	
	/**
	 * gets an instance of TaxCategory from taxCategories collection
	 * @param TaxCategory
	 */
	public TaxCategory getTaxCategory(TaxCategory taxCategory) {
		return taxCategories.get(taxCategory.getTaxCategory());
	}

	/**
	 * finds item for upc
	 * @param upc
	 */
	public Item findItemForUPC(String upc) {
		Item item = null;
		for(Item i : items.values()) {
			for(UPC u : i.getuPCs().values()) {
				if(u.getUPC().equals(upc)) {
					item = i;
				}
			}
		}
		return item;
	}

	/**
	 * finds item for nubmer
	 * @param number
	 */
	public Item findItemForNumber(String number) {
		Item item = null;
		for(Item i : items.values()) {
			if(i.getNumber().equals(number)) {
				item = i;
			}
		}
		return item;
	}

	/**
	 * finds cashier
	 * @param number
	 */
	public Cashier findCashierForNumber(String number) {
		for(Cashier c : cashiers.values()) {
			if(c.getNumber().equals(number)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * returns a string that contains information about the store
	 */
	public String toString() {
		//build up session strings to print store number
		return ("StoreName: '"+name);//+"'StoreNumber: '"+number+"'");//+"\n");
	}
	
	// set/getters
	public TreeMap<String, Cashier> getCashiers() {
		return cashiers;
	}

	public TreeMap<String, Item> getItems() {
		return items;
	}
	
	public TreeMap<String, Register> getRegisters() {
		return registers;
	}
	
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public TaxCategory getCategoryForTax(String description) {
		return taxCategories.get(description);
	}
	
	public  TreeMap<String, TaxCategory> getTaxCategories() {
		return taxCategories;
	}
	
	public TreeMap<String, UPC> getUPCs() {
		return upcs;
	}
	
	public void setUPCs(TreeMap<String, UPC> upcs) {
		this.upcs = upcs;
	}
}