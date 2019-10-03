package posPD;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The session representing the sales interaction
 */
public class Session {

	/**
	 * the start time for session
	 */
	private LocalDate startDateTime;
	/**
	 * the close time of the session
	 */
	private LocalDate endDateTime;
	private LocalDateTime edateTime;
	private LocalDateTime sdateTime;
	private Register register;
	private ArrayList<Sale> sales;
	private Cashier cashier;
	DateTimeFormatter dateFormatter;

	/**
	 * the Default constructor
	 * @param cashier
	 * @param register
	 */
	
	public Session(Cashier cashier, Register register) {
		this.cashier = cashier;
		this.register = register;
		
//		dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//		startDateTime = LocalDate.parse(LocalDate.now().toString(), dateFormatter);
//		endDateTime = LocalDate.parse(LocalDate.now().toString(), dateFormatter);
		startDateTime = LocalDate.now();
		endDateTime = LocalDate.now();
		edateTime = LocalDateTime.now();
		sdateTime = LocalDateTime.now();
		
		sales = new ArrayList<Sale>();
		
	}

	/**
	 * adds sale
	 * @param sale
	 */
	public void addSale(Sale sale) {
		sales.add(sale);
	}

	/**
	 * removes sale
	 * @param sale
	 */
	public void removeSale(Sale sale) {
		// TODO - implement Session.removeSale
		throw new UnsupportedOperationException();
	}

	/**
	 * calculates the count of the cash difference
	 * @param cash
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cash) {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		//value = register.getDrawer().getAmount().subtract(cash);
		value = cash.subtract(register.getDrawer().getAmount()).negate();
		return value;
	}
	
	public BigDecimal calcTotalSalesAmount() {
		BigDecimal value = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		for(Sale s : sales) {
			value = value.add(s.getTotalPayments());
		}
		return value;
	}

	/**
	 * returns information about the session
	 */
	public String toString() {
		endDateTime = LocalDate.now();
		StringBuilder builder = new StringBuilder();
		
		//int saleNumber = 0;
		for(Sale s: sales) {
			//saleNumber++;
			//builder.append("SaleNumber "+saleNumber+": "+s.toString());
			builder.append("SaleNumber: "+s.toString());
		}
	
		String formatDateTime = endDateTime.format(dateFormatter);
		if(cashier != null && register != null) {			
			return ("\nSession: "+startDateTime.toString()+"\n"+cashier.toString()+"\n"+register.toString()+"\n"+builder.toString()+"\n"+"EndDate: "+formatDateTime);
		}
		return ("Cashier: NULL"+" Register: NULL EndDate: "+formatDateTime);
	}

	// set/getters
	public void setStartDate(String format) {
//		LocalDate date = LocalDate.parse(format, DateTimeFormatter.ofPattern("MM/dd/yy"));
		LocalDate date = LocalDate.parse(format);
 		this.startDateTime = date;
	}
	
	public LocalDate getStartDate() {
		return startDateTime;
	}
	
	public ArrayList<Sale> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Sale> sales) {
		this.sales = sales;
	}
	
	public void setRegister(Register register) {
		this.register = register;
	}
	
	public Register getRegister() { 
		return register;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}
	
	public Cashier getCashier() {
		return cashier;
	}
	
	public LocalDate getEndDate() {
		return endDateTime;
	}
	
	public void setEdatetime(LocalDateTime d) {
		this.edateTime = d;
	}
	public LocalDateTime getEDatetime() {
		return this.edateTime;
	}
	
	public void setSdatetime(LocalDateTime S) {
		this.sdateTime = S;
	}
	public LocalDateTime getSDateTime() { 
		return this.sdateTime;
	}
	
	public void setEndDate(String format) {
//		LocalDate date = LocalDate.parse(format, DateTimeFormatter.ofPattern("MM/dd/yy"));
		LocalDate date = LocalDate.parse(format);
 		this.endDateTime = date;
	}
}