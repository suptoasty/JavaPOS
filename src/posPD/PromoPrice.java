package posPD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Promotional Price
 */
public class PromoPrice extends Price {

	/**
	 * The end date of the promotion
	 */
	private LocalDate endDate;

	/**
	 * The promotional price
	 */
	public PromoPrice() {
		super();
		endDate = LocalDate.now();
	}

	/**
	 * Constructor that allows you to set base and current class attributes
	 * @param price
	 * @param effectiveDate
	 * @param endDate
	 */
	public PromoPrice(String price, String effectiveDate, String endDate) {
		super(price, effectiveDate);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		this.endDate = LocalDate.parse(effectiveDate, formatter);
	}
	
	public PromoPrice(String price, String effectiveDate, String endDate, Item item) {
		super(price, effectiveDate, item);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
		this.endDate = LocalDate.parse(effectiveDate, formatter);
	}

	/**
	 * evaluates the effective date
	 * @param date the date
	 */
	public Boolean isEffective(LocalDate date) {
		if(date.isAfter(super.getEffectiveDate()) || date.isBefore(endDate) || date.isEqual(super.getEffectiveDate())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * compares prices
	 * @param price
	 */
	@Override
	public int compareTo(Price price) {
		return this.getEffectiveDate().compareTo(price.getEffectiveDate());
	}

	/**
	 * returns information about the promo price
	 */
	public String toString() {
		return "PromoPrice: "+getPrice().toString()+" EffectiveDate: "+getEffectiveDate().toString()+" EndDate: "+endDate.toString()+" Item: "+getItem().getName();
	}
	
	
	@Override
	public Boolean isPromo() {
		return true;
	}
	
	public void setEndDate(LocalDate date) {
		this.endDate = date;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

}