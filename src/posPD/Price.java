package posPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The price of the item
 */
public class Price implements Comparable<Price>{

	/**
	 * The actual price of the item
	 */
	private BigDecimal price;
	/**
	 * the date
	 */
	private LocalDate effectiveDate;
	private Item item;

	/**
	 * default constructor
	 */
	public Price() {
		setPrice(new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING));
		setEffectiveDate(LocalDate.now());
		setItem(new Item());
	}

	/**
	 * constructor that allows you to set price and effectiveDate
	 * @param price
	 * @param effectiveDate
	 */
	public Price(String price, String effectiveDate) {
		this.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_CEILING));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		this.effectiveDate = LocalDate.parse(effectiveDate, formatter);
		setItem(new Item());
	}
	
	public Price(String price, String effectiveDate, Item item) {
		this.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_CEILING));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		this.effectiveDate = LocalDate.parse(effectiveDate, formatter);
		this.item = item;
		setItem(item);
	}

	/**
	 * evaluates effective date
	 * @param date
	 */
	public Boolean isEffective(LocalDate date) {
		if(date.isEqual(effectiveDate) || date.isAfter(effectiveDate)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * calculates the quantity amount
	 * @param quantity
	 */
	public BigDecimal calcAmountForQuantity(int quantity) {
		// TODO - implement Price.calcAmountForQuantity
		throw new UnsupportedOperationException();
	}

	/**
	 * compares prices "The BigDecimal variable...not the class"
	 * @param price
	 */
	@Override
	public int compareTo(Price price) {
		return -this.price.compareTo(price.getPrice().setScale(2, BigDecimal.ROUND_CEILING)); //negative so most in time is used and not just the first date that is effective
	}

	/**
	 * returns information about the price as a string
	 */
	public String toString() {
		// TODO - implement Price.toString
		return "Price: "+price.toString()+" EffectiveDate: "+effectiveDate.toString()+" Item: "+item.getName();
	}

	// set/getters
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Boolean isPromo() {
		return false;
	}

}