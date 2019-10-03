package posPD;

/**
 * The Person being serverd
 */
public class Person {

	/**
	 * Name of person.
	 */
	private String name;
	/**
	 * address of person
	 */
	private String address;
	/**
	 * phone number of person
	 */
	private String phone;
	/**
	 * social of a person
	 */
	private String sSN;
	private Cashier cashier;

	/**
	 * Default constructor
	 */
	public Person() {
		name = "";
		address ="";
		phone = "";
		sSN = "";
		cashier = null;
	}

	/**
	 * Constructer that takes parameters
	 * @param name //name of person
	 * @param address //person's address
	 * @param phone //person's phone number
	 * @param sSN //person's sSN
	 * @param cashier //person's cashier
	 */
	public Person(String name, String address, String phone, String sSN, Cashier cashier) {
		this.setName(name);
		this.setAddress(address);
		this.setPhone(phone);
		this.setsSN(sSN);
		this.setCashier(cashier);
	}

	/**
	 * returns information about the customer as a string
	 */
	public String toString() {
		if(cashier != null) {
			return "Name: "+getName()+" Address: "+getAddress()+" Phone: "+getPhone()+" sSN: "+getsSN()+" Cashier: "+getCashier();			
		} else {
			return "Name: "+getName()+" Address: "+getAddress()+" Phone: "+getPhone()+" sSN: "+getsSN();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getsSN() {
		return sSN;
	}

	public void setsSN(String sSN) {
		this.sSN = sSN;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

}