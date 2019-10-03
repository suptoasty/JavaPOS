package posPD;
import java.util.ArrayList;

/**
 * The representation of the Person that works at the store that helps the customer check out.
 */
public class Cashier {

	/**
	 * work number
	 */
	private String number;
	/**
	 * Password for authorization
	 */
	private String password;
	private Person person;
	private ArrayList<Session> sessions;

	/**
	 * Default constructor
	 */
	public Cashier() {
		number = "";
		password = "";
		person = null;
		//person = new Person();
		//person.setCashier(this);
		sessions = new ArrayList<Session>();
	}

	/**
	 * Constructor used for setting default values
	 * @param number
	 * @param person
	 * @param password
	 */
	public Cashier(String number, Person person, String password) {
		this.setNumber(number);
		this.setPerson(person);
		this.setPassword(password);
		sessions = new ArrayList<Session>();
	}

	/**
	 * Adds a session to sessions
	 * @param session
	 */
	public void addSession(Session session) {
		sessions.add(session);
	}

	/**
	 * Removes session from sessions
	 * @param session
	 */
	public void removeSession(Session session) {
		sessions.remove(session);
	}

	/**
	 * verifies the the password and user account
	 * @param password
	 */
	public Boolean isAuthorized(String password) {
		if(password.equals(this.password)) {
			return true;
		} 
		return false;
	}

	/**
	 * Returns information about the cashier as a string
	 */
	public String toString() {
		if(person != null) {
			return getPerson().getName();//getPerson().toString()+" Number: "+getNumber()+" Password: "+getPassword();
		}
		return getNumber();
	}

	
	// set/getters
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public ArrayList<Session> getSessions() {
		return sessions;
	}

	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}
}