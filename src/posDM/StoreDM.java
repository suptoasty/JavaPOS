package posDM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import posPD.Cash;
import posPD.Cashier;
import posPD.Check;
import posPD.Credit;
import posPD.Item;
import posPD.Payment;
import posPD.Person;
import posPD.Price;
import posPD.PromoPrice;
import posPD.Register;
import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;
import posPD.TaxCategory;
import posPD.TaxRate;
import posPD.UPC;

public class StoreDM {
	Session tempSession;
	Sale tempSale;
	SaleLineItem tempSaleLineItem;
	Payment payment;
	Cashier tempCashier;
	
	int currentSession = 0;
	int currentSale = 0;
	
	public StoreDM() {
		
	}
	
	public void loadData(String fileName, Store store) throws FileNotFoundException {
		String line = null;
		String[] result = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				result = line.split(",");
				
				if(result[0].contentEquals("Store")) {
					store.setName(result[1]);
				} else if(result[0].contentEquals("TaxCategory")) {
					TaxCategory category = new TaxCategory(result[1]);
					DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yy");
					LocalDate date = LocalDate.parse(result[3], formatter);
					category.addTaxRate(new TaxRate(date, new BigDecimal(result[2]).setScale(2, BigDecimal.ROUND_CEILING)));
					store.addTaxCategory(category);
					
				} else if(result[0].contentEquals("Cashier")) {
					//String name, String address, String phone, String sSN, Cashier cashier
					Person person = new Person(result[2], (result[4]+"-"+result[5]+", "+result[6]+"-"+result[7]), result[8], "N/A", null);
					Cashier cashier = new Cashier(result[1], person, result[9]);
					store.addCashier(cashier);
					tempCashier = store.getCashiers().get(cashier.getNumber());
					
				} else if(result[0].contentEquals("Item")) {
					Item item = new Item(result[1], (result[2]));
					item.setName(result[3]);
					item.setDescription(result[4]);
					item.addPrice(new Price(result[5], result[6], item));
					if(result.length >= 8) {
						item.addPrice(new PromoPrice(result[7], result[8], result[9], item));
					}
					UPC upc = new UPC(result[2], item);
					item.getuPCs().put(upc.getUPC(), upc);
					item.setTaxCategory(store.getCategoryForTax(item.getDescription()));
					store.addUPC(upc);
					store.addItem(item);
					
				} else if(result[0].contentEquals("Register")) {
					store.addRegister(new Register(result[1]));
					
				} else if(result[0].contentEquals("Session")) {
					tempSession = new Session(store.getCashiers().get(result[1]), store.getRegisters().get(result[2]) );
					tempSession.setStartDate("2018-10-10");
					tempSession.setEndDate("2018-10-11");
					store.addSession(tempSession);
					store.getCashiers().get(tempCashier.getNumber()).addSession(tempSession);
					currentSession = store.getSessions().size();
					currentSession--;
					
				} else if(result[0].contentEquals("Sale")) {
					tempSale = new Sale(result[1]);
					store.getSessions().get(currentSession).addSale(tempSale);
					currentSale = store.getSessions().get(currentSession).getSales().size();
					currentSale--;
					
				} else if(result[0].contentEquals("SaleLineItem")) {	
					Sale sale = store.getSessions().get(currentSession).getSales().get(currentSale);
					tempSaleLineItem = new SaleLineItem(result[1], result[2], sale);
					tempSaleLineItem.setItem(store.getItems().get(result[1]));
					sale.addSaleLineItem(tempSaleLineItem);
					
				} else if(result[0].contentEquals("Payment")) {
					Sale sale = store.getSessions().get(currentSession).getSales().get(currentSale);
					if(result[1].contentEquals("Cash")) {
						Cash cash = new Cash(result[2], new BigDecimal(result[3]).setScale(2, BigDecimal.ROUND_CEILING));
						
						sale.addPayment(cash);
						
					} else if(result[1].contentEquals("Credit")) {
						Credit credit = new Credit(result[4], result[5], result[6]);
						credit.setAmount(new BigDecimal(result[2]).setScale(2, BigDecimal.ROUND_CEILING));
						credit.setAmtTendered(new BigDecimal(result[3]).setScale(2, BigDecimal.ROUND_CEILING));
						sale.addPayment(credit);
						
					} else if(result[1].contentEquals("Check")) {
						Check check = new Check(result[2], result[4], result[6]);
						check.setAmtTendered(new BigDecimal(result[3]).setScale(2, BigDecimal.ROUND_CEILING));
						check.setRoutingNumber(result[5]);
						sale.addPayment(check);
						
					}
				}
 			}
			
			bufferedReader.close();
		} catch(FileNotFoundException e) {
			System.out.println("Unable to Open file: '"+fileName+"'");
			e.printStackTrace();
			
		} catch(IOException e) {
			System.out.println("Error reading file: '"+fileName+"'");
			e.printStackTrace();
		}
		
	}
	
	public void saveData(String fileName, Store store) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		//store
		writer.write("Store,"+store.getName());
		writer.newLine();
		
		//TaxCategories
		for(TaxCategory tax : store.getTaxCategories().values()) {
			writer.write("TaxCategory,"+tax.getTaxCategory()+",");
			writer.newLine();
		}
		
		//Cashiers
		for(Cashier cashier : store.getCashiers().values()) {
			writer.write("Cashier,"+cashier.getNumber()+","
			+cashier.getPerson().getName()+","
			+cashier.getPerson().getsSN()+","
			+cashier.getPerson().getPhone()+","
			+cashier.getPassword());
			writer.newLine();
		}
		
		//Items
		for(Item item : store.getItems().values()) {
			writer.write("Item,"+item.getNumber()+","
			+item.getuPCs().toString()+","
			+item.getName()+","
			+item.getTaxCategory().getTaxCategory()+",");
			//+item.getPriceForDate(date)+","
			//iterate to end of line for prices
			writer.newLine();
		}
		
		//Registers
		for(Register regi : store.getRegisters().values()) {
			writer.write("Register,"+regi.getNumber());
			writer.newLine();
		}
		
		//Sessions
		for(Session session : store.getSessions()) {
			writer.write("Session,"+session.getCashier().getNumber()+","+session.getRegister().getNumber());
			writer.newLine();
			for(Sale sale : session.getSales()) {
				writer.write("Sale,"+sale.getTaxFree().toString());
				writer.newLine();
				for(SaleLineItem item : sale.getSaleLineItems()) {
					writer.write("SaleLineItem,"+item.getItem().getNumber()+","+item.getQuantity());
					writer.newLine();
				}
				//payments here
			}
		}
	
		writer.close();
	}
	
}
