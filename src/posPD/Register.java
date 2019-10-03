package posPD;
/**
 * The representation of the cash register
 */
public class Register {

	/**
	 * the register number
	 */
	private String number;
	CashDrawer drawer;

	/**
	 * Default constructor
	 */
	public Register() {
		this.number = "";
		this.drawer = new CashDrawer();
	}

	/**
	 * Constructor that allows you to set the register number
	 * @param number
	 */
	public Register(String number) {
		this.number = number;
		this.drawer = new CashDrawer();
	}

	/**
	 * returns information about the register
	 */
	public String toString() {
		return ("RegisterNumber: "+number);//+" CashDrawer: "+drawer.toString();
	}
	
	
	// set/getters
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public CashDrawer getDrawer() {
		return drawer;
	}

}