package posPD;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * TaxRate for item
 */
public class TaxRate implements Comparable<TaxRate> {

	//private TaxCategory taxCategory;
	/**
	 * the actual tax rate
	 */
	private BigDecimal taxRate;
	/**
	 * the date of affect
	 */
	private LocalDate effectiveDate;

	/**
	 * Constructor
	 * @param effictiveDate
	 * @param rate
	 */
	public TaxRate(LocalDate effictiveDate, BigDecimal rate) {		
		this.effectiveDate = effictiveDate;
		this.taxRate = rate;
	}

	/**
	 * returns if it is the effective date
	 * @param date
	 */
	public boolean isEffective(LocalDate date) {
		if(date.isAfter(effectiveDate) || date.isEqual(effectiveDate)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * compares to another tax rate
	 * @param taxRate
	 */
	@Override
	public int compareTo(TaxRate taxRate) {
		return this.getEffectiveDate().compareTo(taxRate.getEffectiveDate());
	}

	/**
	 * returns information about the tax rate
	 */
	public String toString() {
		return "TaxRate: "+getTaxRate().toString()+" EffectiveDate: "+effectiveDate.toString();
	}
	
	// set/getters
	public void setTaxRate(BigDecimal rate) {
		this.taxRate = rate;
	}
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setEffectiveDate(LocalDate date) {
		effectiveDate = date;
	}
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

}