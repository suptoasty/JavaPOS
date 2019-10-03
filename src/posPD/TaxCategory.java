package posPD;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeSet;

/**
 * TaxCategory for an item
 */
public class TaxCategory {

	/**
	 * the tax category
	 */
	private String category;
	private TreeSet<TaxRate> taxRates;

	/**
	 * Constructor
	 */
	public TaxCategory() {
		category = "";
		taxRates = new TreeSet<TaxRate>();
	}
	
	public TaxCategory(TaxCategory tax) {
		this.category = tax.getTaxCategory();
		this.taxRates = tax.getTaxRates();
	}

	/**
	 * Constructor with category
	 * @param category
	 */
	public TaxCategory(String category) {
		this.category = category;
		taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * returns the tax rate
	 * @param date
	 */
	public BigDecimal getTaxRateforDate(LocalDate date) {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		for(TaxRate t : taxRates) {
			if(t.isEffective(date)) {
				value = t.getTaxRate();
			}
		}
		return value;
	}

	/**
	 * adds tax rate
	 * @param taxRate
	 */
	public void addTaxRate(TaxRate taxRate) {
		taxRates.add(taxRate);
	}

	/**
	 * returns information about tax category as string
	 */
	public String toString() {
		return category;//"Category: "+category;
	}

	/**
	 * removes tax Rate
	 * @param taxRate
	 */
	public void removeTaxRate(TaxRate taxRate) {
		taxRates.remove(taxRate);
	}
	
	// set/getters
	public void setTaxCategory(String category) {
		this.category = category;
	}
	
	public String getTaxCategory() {
		return category;
	}
	
	public void setTaxRates(TreeSet<TaxRate> rates) {
		taxRates = rates;
	}
	
	public TreeSet<TaxRate> getTaxRates() {
		return taxRates;
	}

}