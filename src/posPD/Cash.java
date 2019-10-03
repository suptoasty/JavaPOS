package posPD;

import java.math.BigDecimal;

/**
 * Representation of cash
 */
public class Cash extends Payment {

//	/**
//	 * Default Constructor
//	 */
//	public Cash() {
//		// TODO - implement Cash.Cash
//		throw new UnsupportedOperationException();
//	}

	/**
	 * Constructor that sets amount and amtTendered
	 * @param amount
	 * @param amTendered
	 */
	public Cash(String amount, BigDecimal amTendered) {
		setAmount(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_CEILING));
		setAmtTendered(amTendered);
	}

	/**
	 * calculates the change
	 */
	public BigDecimal calcChange() {
		return getAmtTendered().setScale(2, BigDecimal.ROUND_CEILING).subtract(getAmount()).setScale(2, BigDecimal.ROUND_CEILING);
		//return getAmount().subtract(getAmtTendered());
	}

	/**
	 * evaluates if is cash
	 */
	public Boolean countsAsCash() {
		return true;
	}

	/**
	 * returns infomation about cash
	 */
	public String toString() {
		return "Type: 'Cash'   Amount: "+getAmount().toString()+" AmountTendered: "+getAmtTendered().setScale(2, BigDecimal.ROUND_CEILING).toString()+" CountsAsCash: "+countsAsCash();//+" Change: ";
	}

}