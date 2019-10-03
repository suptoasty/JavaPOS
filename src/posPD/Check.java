package posPD;

import java.math.BigDecimal;

/**
 * Represents a check
 */
public class Check extends AuthorizedPayment {

	/**
	 * the routing number for account
	 */
	private String routingNumber;
	/**
	 * the account number
	 */
	private String accountNumber;
	/**
	 * the check number
	 */
	private String checkNumber;

//	/**
//	 * Default Constructor
//	 */
//	public Check() {
//		routingNumber = "";
//		accountNumber = "";
//		checkNumber = "";
//	}

	/**
	 * The Constructor that helps set attributes
	 * @param amount
	 * @param accountNumber
	 * @param checkNumber
	 */
	public Check(String amount, String accountNumber, String checkNumber) {
		setAmtTendered(new BigDecimal(0));
		setAmount(new BigDecimal(amount));
		this.accountNumber = accountNumber;
		this.checkNumber = checkNumber;
		
	}
	
	public Check(String amount, String accountNumber, String checkNumber, BigDecimal AmtTend) {
		setAmtTendered(AmtTend);
		setAmount(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP));
		this.accountNumber = accountNumber;
		this.checkNumber = checkNumber;
		
	}

	/**
	 * returns if payment was authorized
	 */
	public Boolean isAuthorized() {
		return true;
	}

	/**
	 * calculates change
	 */
	public BigDecimal calcChange() {
		return getAmtTendered().subtract(getAmount());
		//return getAmount().subtract(getAmtTendered());
	}

	/**
	 * returns check infomration as a string
	 */
	public String toString() {
		return "Type: 'Check'   Amount: "+getAmount()+" AccountNumber: "+accountNumber+" CheckNumber: "+checkNumber+ " CountsAsCash: "+countsAsCash()+" Authorized: "+isAuthorized();//+" Change: ";
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

}