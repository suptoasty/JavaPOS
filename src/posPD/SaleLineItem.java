package posPD;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The series of items being sold
 */
public class SaleLineItem {

	/**
	 * the amount of item being sold
	 */
	private int quantity;
	private Item item;
	private Sale sale;

	/**
	 * Default Constructor
	 */
	public SaleLineItem() {
		quantity = 0;
		item = new Item();
		sale = new Sale();
	}

	/**
	 * Constructor that sets itemNumber and quantity
	 * @param itemNumber
	 * @param quantity
	 */
	public SaleLineItem(String itemNumber, String quantity) {
		this.quantity = Integer.parseInt(quantity);
		item = new Item();
		item.setNumber(itemNumber);
		sale = new Sale();
	}
	
	/**
	 * Constructor that helps set sale
	 * @param itemNumber
	 * @param quantity
	 * @param sale
	 */
	public SaleLineItem(String itemNumber, String quantity, Sale sale) {
		this.sale = sale;
		this.quantity = Integer.parseInt(quantity);
		item = new Item();
		item.setNumber(itemNumber);
	}

	/**
	 * calculates the sub total
	 */
	public BigDecimal calcSubTotal() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		value = item.getPriceForDate(sale.getSaleDate()).multiply(new BigDecimal(quantity));
		return value;
	}
	
	public BigDecimal calcSubTotal(LocalDate date) {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		value = item.getPriceForDate(date).multiply(new BigDecimal(quantity));
		return value;
	}

	/**
	 * calculates the tax
	 */
	public BigDecimal calcTax() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		value = calcSubTotal().multiply(item.getTaxRateForDate(sale.getSaleDate()));
		return value;
	}
	
	public BigDecimal calcTax(LocalDate date) {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		value = calcSubTotal().multiply(item.getTaxRateForDate(date));
		return value;
	}

	/**
	 * returns infomation about the Sale Line Item
	 */
	public String toString() {
		return "SaleLineItem: "+item.getName()+" Quantity: "+quantity+ " SubTotal: "+calcSubTotal().toString()+" Tax: "+calcTax().setScale(2, BigDecimal.ROUND_CEILING).toString()+" Total: "+calcSubTotal().setScale(2, BigDecimal.ROUND_CEILING).add(calcTax()).setScale(2, BigDecimal.ROUND_CEILING).toString();
	}
	
	// set/getters
	public void addItemPrice(Price price) {
		item.addPrice(price);
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	public Sale getSale() {
		return sale;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int q) {
		this.quantity = q;
	}

}