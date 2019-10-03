package posPD;
/**
 * the UPC
 */
public class UPC {

	/**
	 * actual upc name
	 */
	private String uPC;
	private Item item;

	/**
	 * default constructor
	 */
	public UPC() {
		// TODO - implement UPC.UPC
		uPC = "";
		item = new Item();
	}

	/**
	 * constructor that allows you to set the upc
	 * @param upc
	 */
	public UPC(String upc) {
		// TODO - implement UPC.UPC
		this.uPC = upc;
		item = new Item();
	}
	
	public UPC(String upc, Item item) {
		this.uPC = upc;
		this.item = item;
	}
	
	/**
	 * returns information about upc as string
	 */
	public String toString() {
		// TODO - implement UPC.toString
		return "UPC: "+uPC;//+" Item: "+item.toString();
	}
	
	
	// set/getters
	public void setUPC(String uPC) {
		this.uPC = uPC;
	}
	
	public String getUPC() {
		return uPC;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Item getItem() { 
		return this.item;
	}

}