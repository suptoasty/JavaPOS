package posPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * represents payment through credit
 */
public class Credit extends AuthorizedPayment {

	/**
	 * credit card type
	 */
	private String cardType;
	/**
	 * the account number
	 */
	private String accountNumber;
	/**
	 * the expiration date of the card
	 */
	private LocalDate expireDate;

//	/**
//	 * The default constructor
//	 */
//	public Credit() {
//		super(accountNumber);
//		cardType = "";
//		accountNumber = "";
//		//expireDate = LocalDate.
//		
//	}

	/**
	 * The constuctor that allows you to set attibutes
	 * @param cardType
	 * @param accountNumber
	 * @param expireDate
	 */
	public Credit(String cardType, String accountNumber, String expireDate) {
		//super();
		this.cardType = cardType;
		this.accountNumber = accountNumber;
		this.expireDate = LocalDate.parse(expireDate, DateTimeFormatter.ofPattern("MM/dd/yy"));
	}
	
	public Credit(String cardType, String accountNumber, String expireDate, String amount, BigDecimal AmtTend) {
		//super();
		this.cardType = cardType;
		this.accountNumber = accountNumber;
		this.expireDate = LocalDate.parse(expireDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		setAmtTendered(AmtTend);
		setAmount(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * returns if payment was authorized
	 */
	public Boolean isAuthorized() {
		// TODO - implement Credit.isAuthorized
		throw new UnsupportedOperationException();
	}

	/**
	 * calculates the change
	 */
	public BigDecimal calcChange() {
		return getAmtTendered().setScale(2, BigDecimal.ROUND_CEILING).subtract(getAmount().setScale(2, BigDecimal.ROUND_CEILING)).setScale(2, BigDecimal.ROUND_CEILING).negate();
		//return getAmount().subtract(getAmtTendered());
	}

	/**
	 * returns information about the credit as string
	 */
	public String toString() {
		return "Type: 'Credit'  CardType: "+getCardType()+" AccountNumber: "+getAccountNumber()+" ExpireDate: "+getExpireDate().toString();
	}

	// set/getters
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

}