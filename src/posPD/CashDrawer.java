package posPD;

import java.math.BigDecimal;

/**
 * helper enumeration for drawerState
 * @author jason
 *
 */
enum DrawerState {
	LOCKED(-1), CLOSED(0), OPEN(1);
	
	private int value;
	private DrawerState(int value) {
		this.value = value;
	}
	public int getState() {
		return this.value;
	}
}

/**
 * Used to store Cash. Interacts with CashRegister to resolve payment and the session.
 */
public class CashDrawer {

	/**
	 * The total amount of money in the CashDrawer.
	 */
	private BigDecimal amount;
	
	/**
	 * the state of the drawer EX open or closed
	 */
	private DrawerState drawerState; //see helper enum DrawerState

	/**
	 * Default constructor.
	 */
	public CashDrawer() {
		amount = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		drawerState = DrawerState.OPEN;
	}

	/**
	 * Adds cash into CashDrawer.
	 * @param cash
	 */
	public void addCash(BigDecimal cash) {
		// TODO - implement CashDrawer.addCash
		if(drawerState == DrawerState.OPEN) {
			amount = amount.add(cash);
		} else {
			System.out.println("CashDrawer: "+this+" Is Closed or locked");
		}
	}

	/**
	 * Removes cash from CashDrawer.
	 * @param cash
	 */
	public void removeCash(BigDecimal cash) {
		if(drawerState == DrawerState.OPEN) {
			amount = amount.subtract(cash);
		} else {
			System.out.println("CashDrawer: "+this+" Is Closed or locked");
		}
	}

	/**
	 * String of CashDrawer Data.
	 */
	public String toString() {
		return "DrawerAmount: "+amount;
	}
	
	// set/getters
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}


}