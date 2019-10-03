package posPD;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The item being sold
 */
public class Item {

	/**
	 * the item number
	 */
	private String number;
	
	/**
	 * the item name
	 */
	private String name;
	
	/**
	 * the item description
	 */
	private String description;
	private ArrayList<SaleLineItem> saleLineItems;
	private TreeSet<Price> prices;
	private TaxCategory taxCategory;
	private TreeMap<String, UPC> uPCs;

	/**
	 * default constructor
	 */
	public Item() {
		number = "";
		description = "";
		saleLineItems = new ArrayList<SaleLineItem>();
		prices = new TreeSet<Price>();
		taxCategory = new TaxCategory();
		uPCs = new TreeMap<String, UPC>();
	}
	
	public Item(Item item) {
		this.number = item.getNumber();
		this.name = item.getName();
		this.description = item.getDescription();
		this.prices = item.getPrices();
		this.taxCategory = item.getTaxCategory();
		this.uPCs = item.getuPCs();
	}

	/**
	 * item that helps set attributes
	 * @param number
	 * @param description
	 */
	public Item(String number, String description) {
		this.number = number;
		this.description = description;
		saleLineItems = new ArrayList<SaleLineItem>();
		prices = new TreeSet<Price>();
		taxCategory = new TaxCategory();
		uPCs = new TreeMap<String, UPC>();
	}

	/**
	 * add price
	 * @param price
	 */
	public void addPrice(Price price) {
		prices.add(price);
	}

	/**
	 * removes price
	 * @param price
	 */
	public void removePrice(Price price) {
		prices.remove(price);
	}

	/**
	 * adds upcs
	 * @param upc
	 */
	public void addUPC(UPC upc) {
		uPCs.put(upc.getUPC(), upc);
	}

	/**
	 * removes upcs
	 * @param upc
	 */
	public void removeUPC(UPC upc) {
		uPCs.remove(upc.getUPC(), upc);
	}

	/**
	 * returns the price for date
	 * @param date
	 */
	public BigDecimal getPriceForDate(LocalDate date) {			
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		for(Price price: prices) {
			if(price.isEffective(date)) {
				value = price.getPrice();
			}
		}
		return value;
	}

	/**
	 * returns the tax rate for the date
	 * @param date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date) {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		value = taxCategory.getTaxRateforDate(date);
		return value;
	}

	/**
	 * calculate amount for date and the amount of itesm
	 * @param date
	 * @param quantity
	 */
	public BigDecimal calcAmountForDateQuantity(LocalDate date, int quantity) {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		for(SaleLineItem i: saleLineItems) {
			value.add(i.calcSubTotal(date));
		}
		value.multiply(new BigDecimal(quantity));
		return value;
	}

	/**
	 * returns information about the item
	 */
	public String toString() {
		return getName()+" (Item Number: "+getNumber()+")";//"ItemName: "+getName()+"	ItemNumber: "+getNumber();//"ItemNumber: "+getNumber()+" Name: "+getName()+" Description: "+getDescription()+" EffectivePrice: "+getPriceForDate(LocalDate.now()).toString()+" "+taxCategory.toString();//+" "+getuPCs().get("JFKSLDJFL");			
	}

	
	// set/getters
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<SaleLineItem> getSaleLineItems() {
		return saleLineItems;
	}

	public void setSaleLineItems(ArrayList<SaleLineItem> saleLineItems) {
		this.saleLineItems = saleLineItems;
	}

	public TreeSet<Price> getItem() {
		return prices;
	}

	public void setItem(TreeSet<Price> prices) {
		this.prices = prices;
	}

	public TreeMap<String, UPC> getuPCs() {
		return uPCs;
	}

	public void setuPCs(TreeMap<String, UPC> uPCs) {
		this.uPCs = uPCs;
	}

	public TaxCategory getTaxCategory() {
		return taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory) {
		this.taxCategory = taxCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public TreeSet<Price> getPrices() {
		return prices;
	}
	
	public void setPrices(TreeSet<Price> prices) {
		this.prices = prices;
	}

}