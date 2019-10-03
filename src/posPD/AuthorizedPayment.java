package posPD;

import java.math.BigDecimal;

/**
 * Class that Verifies payment
 */
public class AuthorizedPayment extends Payment {

	/**
	 * code used to authorize payment
	 */
	private String authorizationCode;
	
	public AuthorizedPayment() {
		authorizationCode = "";
	}
	
	/**
	 * Constructor
	 * @authorizedPayment
	 */
	public AuthorizedPayment(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	

	/**
	 * returns if authorization was validated
	 */
	public Boolean isAuthorized() {
		return true;
	}

	/**
	 * determines if cash
	 */
	public Boolean countsAsCash() {
		return true;
	}
	
	public String toString() {
		return "Amount: "+getAmount().setScale(2, BigDecimal.ROUND_CEILING)+" AmtTendered: "+getAmtTendered().setScale(2, BigDecimal.ROUND_CEILING)+"Authorized: "+isAuthorized()+" CountsAsCash: "+countsAsCash();
	}

}