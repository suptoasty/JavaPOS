package posTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import posPD.Cashier;
import posPD.Item;
import posPD.Payment;
import posPD.Person;
import posPD.Price;
import posPD.Register;
import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;
import posPD.TaxCategory;
import posPD.TaxRate;
import posPD.UPC;

public class Test {
	public Test() {
	}
	
	public void test1(Store store) {
		System.out.println(store.toString());
		System.out.println("Items: ");
		
		Item item1 = new Item("switch", "This is the best item ");
		item1.addPrice(new Price("350.0", "2018-02-01 01:01:01"));
		item1.addPrice(new Price("299.0", "2018-10-04 01:01:01"));
		item1.addPrice(new Price("299.0", "2018-10-10 01:01:01"));
		store.addItem(item1);
		item1.addUPC(new UPC("JFKSLDJFL"));
		item1.setTaxCategory(new TaxCategory("Category1"));
		
		Item item2 = new Item("xbox", "This is not the best ");
		item2.addPrice(new Price("350.0", "2017-10-01 01:01:01"));
		item2.addPrice(new Price("299.0", "2018-11-04 01:01:01"));
		item2.addPrice(new Price("299.0", "2018-01-10 01:01:01"));
		store.addItem(item2);
		item2.addUPC(new UPC("JFKSLDJFL"));
		item2.setTaxCategory(new TaxCategory("Category1"));
		
		Item item3 = new Item("ps4", "This might be the best item ");
		item3.addPrice(new Price("350.0", "2018-12-01 01:01:01"));
		item3.addPrice(new Price("299.0", "2018-09-04 01:01:01"));
		item3.addPrice(new Price("299.0", "2018-05-10 01:01:01"));
		store.addItem(item3);
		item3.addUPC(new UPC("JFKSLDJFL"));
		item3.setTaxCategory(new TaxCategory("Category1"));
		
		
		Iterator i = store.getItems().entrySet().iterator();
		
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
	}
	
	public void test2(Store store) {
		System.out.println(store.toString());
		System.out.println("Cashiers: ");
		
		Cashier joy = new Cashier("5555", new Person("Joy", "212", "580", "51", null), "password1");
		Cashier jim = new Cashier("3333", new Person("jim", "212", "749", "60", null), "password2");
		Cashier joe = new Cashier("4444", new Person("joe", "212", "127", "71", null), "password3");
		store.addCashier(joy);
		store.addCashier(jim);
		store.addCashier(joe);
		
		Iterator it = store.getCashiers().entrySet().iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}
	
	public void test3(Store store) {
		System.out.println(store.toString());	
		System.out.println("Registers: ");
		
		Register reg1 = new Register("5555555555");
		reg1.getDrawer().addCash(new BigDecimal(64));
		
		Register reg2 = new Register("1111111111");
		reg2.getDrawer().addCash(new BigDecimal(100));
		reg2.getDrawer().removeCash(new BigDecimal(50));
		
		store.addRegister(reg1);
		store.addRegister(reg2);
		
		Iterator it = store.getRegisters().entrySet().iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void test4(Store store) {
		System.out.println(store.toString());	
		System.out.println("Session: ");
		
		Register reg1 = new Register("5555555555");
		reg1.getDrawer().addCash(new BigDecimal(64));
		
		Cashier joy = new Cashier("5555", new Person("Joy", "212", "580", "51", null), "password1");
		
		LocalDate tim = LocalDate.now();

		SaleLineItem sli1 = new SaleLineItem("Burg", "4");
		sli1.setItem(new Item("teeth","this is a joke item"));
		sli1.addItemPrice(new Price("50","2018-10-01 01:01:01"));
		sli1.getItem().getTaxCategory().addTaxRate(new TaxRate(tim,new BigDecimal(0.05).setScale(2, BigDecimal.ROUND_CEILING)));
		
		SaleLineItem sli2 = new SaleLineItem("BEg", "6");
		sli2.setItem(new Item("purp drank", "get sizzurp"));
		sli2.addItemPrice(new Price("100000","2018-10-01 01:01:01"));
		sli2.getItem().getTaxCategory().addTaxRate(new TaxRate(tim,new BigDecimal(0.05).setScale(2, BigDecimal.ROUND_CEILING)));
		
		Sale s = new Sale();
		s.addSaleLineItem(sli1);
		s.addSaleLineItem(sli2);
		s.addPayment(new Payment("600", "600"));
		
		Session ses = new Session(joy, reg1);
		ses.addSale(s);
		
		System.out.println(ses.toString());
		
	}
	
	public void test5(Store store) {		
		System.out.println(store.toString()); //print out store name
		
		//print out the store info
		Iterator it = store.getSessions().iterator();

		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
