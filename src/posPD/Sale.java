package posPD;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Sale being made
 */
public class Sale {

	/**
	 * the time of sale
	 */
	private LocalDate dateTime;
	private LocalDateTime date;
	/**
	 * Weather the sale is tax free
	 */
	private Boolean taxFree;
	private ArrayList<Payment> payments;
	private ArrayList<SaleLineItem> saleLineItems;
	private Payment payment;

	/**
	 * Default constructor
	 */
	public Sale() {
		dateTime = LocalDate.now();
		taxFree = false;
		payments = new ArrayList<Payment>();
		payment = new Payment();
		saleLineItems = new ArrayList<SaleLineItem>();
		date = LocalDateTime.now();
	}

	/**
	 * Constructor that sets taxFree
	 * @param taxFree
	 */
	public Sale(String taxFree) {
		dateTime = LocalDate.now();
		this.taxFree = Boolean.parseBoolean(taxFree);
		payments = new ArrayList<Payment>();
		payment = new Payment();
		saleLineItems = new ArrayList<SaleLineItem>();
	}

	/**
	 * adds payment
	 * @param payment
	 */
	public void addPayment(Payment payment) {
		payments.add(payment);
	}

	/**
	 * removes payment
	 * @param payment
	 */
	public void removePayment(Payment payment) {
		payments.remove(payment);
	}

	/**
	 * adds item to sale line item
	 * @param sli
	 */
	public void addSaleLineItem(SaleLineItem sli) {
		saleLineItems.add(sli);
	}

	/**
	 * removes item from sale line item
	 * @param sli
	 */
	public void removeSaleLineItem(SaleLineItem sli) {
		saleLineItems.remove(sli);
	}

	/**
	 * calculates the sales total
	 */
	public BigDecimal calcTotal() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		if(taxFree) {
			for(SaleLineItem i: saleLineItems) {
				value = value.add(i.calcSubTotal(dateTime)).setScale(2, BigDecimal.ROUND_CEILING);
//				value = value.add(i.calcTax()).setScale(2, BigDecimal.ROUND_CEILING);
			}
		} else {
			for(SaleLineItem i: saleLineItems) {
				value = value.add(i.calcSubTotal(dateTime)).setScale(2, BigDecimal.ROUND_CEILING);
				value = value.add(i.calcTax()).setScale(2, BigDecimal.ROUND_CEILING);
			}
		}
		return value;
	}

	/**
	 * calculates the sales sub total
	 */
	public BigDecimal calcSubTotal() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		for(SaleLineItem i: saleLineItems) {
			value = value.add(i.calcSubTotal(dateTime));
		}
		System.out.println(value.toString());
		return value;
	}

	/**
	 * calculates the tax
	 */
	public BigDecimal calcTax() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		for(SaleLineItem i: saleLineItems) {
			value = value.add(i.calcTax());
		}
		return value;
	}

	/**
	 * returns the total payment
	 */
	public BigDecimal getTotalPayments() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		for(Payment i: payments) {
			value = value.add(i.getAmount());
		}
		return value;
	}

	/**
	 * evaluates if payment as met condition
	 */
	public Boolean isPaymentEnough() {
		// TODO - implement Sale.isPaymentEnough
		throw new UnsupportedOperationException();
	}

	/**
	 * calculates the amount
	 * @param amountTendered
	 */
	public void calcAmount(BigDecimal amountTendered) {
		// TODO - implement Sale.calcAmount
		throw new UnsupportedOperationException();
	}

	/**
	 * calculates the change
	 */
	public BigDecimal calcChange() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		BigDecimal temp = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		temp = calcTotal();
		value = temp.subtract(calcAmountTendered());
		return value;
	}

	/**
	 * calculates the tendered amount
	 */
	public BigDecimal calcAmountTendered() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_CEILING);
		for(Payment i: payments) {
			value = value.add(i.getAmtTendered());
		}
		//value = payment.getAmtTendered();
		return value;
	}

	/**
	 * returns information about the sale
	 */ 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//builder.append("");
		
		if(saleLineItems != null) {
//			int itemCount = 0;
			for(SaleLineItem i: saleLineItems) {
//				itemCount++;
//				builder.append("\n  Item "+itemCount+": "+i.toString());
				builder.append("\n  Item: "+i.toString());
			}
		}
		
		StringBuilder paymentString = new StringBuilder();
		
		if(payments != null) {
//			int paymentCount = 0;
			for(Payment i: payments) {
//				paymentCount++;
//				builder.append("\n Payment "+paymentCount+": "+i.toString());
				builder.append("\n Payment: "+i.toString());
			}
		}
		
		return "DateTime: "+dateTime.toString()+" TaxFree: "+taxFree.toString()+" Total: "+calcTotal().toString()+" SubTotal: "+calcSubTotal().toString()+" TotalPayments: "+getTotalPayments().toString()+builder.toString()+" Change: "+calcChange().abs().toString()
				+"\n  "+paymentString.toString();
		
	}
	
	// set/getters
	public void setSaleDate(LocalDate date) {
		dateTime = date;
	}
	
	public LocalDate getSaleDate() {
		return dateTime;
	}
	
	public ArrayList<SaleLineItem> getSaleLineItems() { 
		return saleLineItems;
	}
	
	public ArrayList<Payment> getPayments() {
		return payments;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Boolean getTaxFree() {
		return taxFree;
	}

	public void setSaleDate(String format) {
		LocalDate date = LocalDate.parse(format, DateTimeFormatter.ofPattern("MM/dd/yy"));
		setSaleDate(date);
	}
	
	public void setPayments(ArrayList<Payment> pay) {
		this.payments = pay;
	}
	
	public void setTaxfree(boolean f) {
		this.taxFree = f;
	}
	
	public LocalDateTime getTimie() {
		return date;
	}
	
	public void setTime(LocalDateTime t) {
		date = t;
	}

}