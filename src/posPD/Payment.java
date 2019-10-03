package posPD;

import java.math.BigDecimal;

/**
 * The payment method
 */
public class Payment {

	/**
	 * the amount for payment
	 */
	private BigDecimal amount;
	/**
	 * the amount tendered
	 */
	private BigDecimal amtTendered;

	/**
	 * calculates the change
	 */
	public BigDecimal calcChange() {
		return amtTendered.subtract(amount).setScale(2, BigDecimal.ROUND_CEILING);
		//return amount.subtract(amtTendered);
	}

	/**
	 * determins if payment method works
	 */
	public Boolean countsAsCash() {
		// TODO - implement Payment.countsAsCash
		throw new UnsupportedOperationException();
	}

	/**
	 * Default Constructor
	 */
	public Payment() {
		amount = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		amtTendered = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
	}
	
	/**
	 * 
	 * @param amount
	 * @param amountTendered
	 */
	public Payment(String amount, String amountTendered) {
		this.amount = new BigDecimal(amount);
		this.amtTendered = new BigDecimal(amountTendered);
	}
	
	public String toString() {
		return "Amount: "+amount.setScale(2, BigDecimal.ROUND_CEILING).toString()+" AmountTendered: "+amtTendered.setScale(2, BigDecimal.ROUND_CEILING).toString()+" Change: "+calcChange().toString()+" ";
	}

	public BigDecimal getAmount() {
		return amount.setScale(2, BigDecimal.ROUND_CEILING);
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount.setScale(2, BigDecimal.ROUND_CEILING);
	}

	public BigDecimal getAmtTendered() {
		return amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered) {
		this.amtTendered = amtTendered.setScale(2, BigDecimal.ROUND_CEILING);
	}

}