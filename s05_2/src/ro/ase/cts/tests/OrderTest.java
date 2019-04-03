package ro.ase.cts.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.ase.cts.exception.NegativePriceException;
import ro.ase.cts.exception.OnlyNumbersNameException;
import ro.ase.cts.models.Coffee;
import ro.ase.cts.models.CoffeeType;
import ro.ase.cts.models.Order;

public class OrderTest {
	
	public Order order;
	
	@Before
	public void setUp() {
		order = new Order();
	}
	
	@After
	public void tearDown() {
		order.removeProducts();
	}
	
	@Test
	public void testOrderPrintOneProduct() {
		
		Coffee c1 = new Coffee("Late", CoffeeType.BRAZIL, 15);
		order.addProduct(c1);
		String value = String.join("\n",
								"Late BRAZIL 15.0",
								"TOTAL: 15.0");
		assertEquals(value, order.printOrder());
	}
	
	@Test
	public void testOrderPrintTwoProducts() {
		Coffee c1 = new Coffee("Late", CoffeeType.ARABICA, 10);
		Coffee c2 = new Coffee("Cappuccinno", CoffeeType.ETIOPIA, 20);
		String value = String.format("%s\n%s\n%s", 
				"Late ARABICA 10.0",
				"Cappuccinno ETIOPIA 20.0",
				"TOTAL: 30.0");
		this.order.addProduct(c1);
		this.order.addProduct(c2);
		assertEquals(value, this.order.printOrder());
	}
	
	@Test
	public void testOrderPrintMultipleProductsDuplicate() {
		Coffee c1 = new Coffee("Late", CoffeeType.ARABICA, 10);
		Coffee c2 = new Coffee("Cappuccinno", CoffeeType.ETIOPIA, 20);
		Coffee c3 = new Coffee("Late", CoffeeType.ARABICA, 10);
		this.order.addProduct(c1);
		this.order.addProduct(c2);
		this.order.addProduct(c3);
		List<Coffee> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		Optional<String> orderValue = list.stream().map(c -> {
			String s = c.getBeverageName() + " " + c.getCoffeeType() + " " + c.getPrice()+"\n";
			return s;
		}).reduce((complete, value) -> {
			complete += value;
			return complete;
		});
		if(orderValue.isPresent()) {
			String total = orderValue.get() + "TOTAL: 40.0";
			assertEquals(total, this.order.printOrder());
		}
	}
	
	
	@Test
	public void testDiscountPercentage() {
		Coffee c1 = new Coffee("Late", CoffeeType.ARABICA, 10);
		Coffee c2 = new Coffee("Cappuccinno", CoffeeType.ETIOPIA, 20);
		Coffee c3 = new Coffee("Late", CoffeeType.ARABICA, 10);
		this.order.addProduct(c1);
		this.order.addProduct(c2);
		this.order.addProduct(c3);
		List<Coffee> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		double discount = list.size()*0.05;
		
		assertEquals(discount,this.order.discountCalculator(),0.0001);
	}
	
	@Test
	public void testOneCoffeeDiscount() {
		Coffee c1 = new Coffee("Late", CoffeeType.ARABICA, 10);
		this.order.addProduct(c1);
		List<Coffee> list = new ArrayList<>();
		list.add(c1);
		double discount = list.size()*0.05;
		assertEquals(discount,this.order.discountCalculator(),0.0001);
		System.out.println("The discount for one coffee is "+this.order.discountCalculator()*100+"%\n");
	}
	@Test
	public void testMaxCoffeesDiscount() {
		Coffee c1 = new Coffee("Late", CoffeeType.ARABICA, 10);
		Coffee c2 = new Coffee("Cappuccinno", CoffeeType.ETIOPIA, 20);
		Coffee c3 = new Coffee("Late", CoffeeType.ARABICA, 10);
		Coffee c4 = new Coffee("Cappuccinno", CoffeeType.ETIOPIA, 20);
		Coffee c5 = new Coffee("Late", CoffeeType.ARABICA, 10);
		Coffee c6 = new Coffee("Cappuccinno", CoffeeType.ETIOPIA, 20);
		Coffee c7 = new Coffee("Late", CoffeeType.ARABICA, 10);
		Coffee c8 = new Coffee("Cappuccinno", CoffeeType.ETIOPIA, 20);
		Coffee c9 = new Coffee("Late", CoffeeType.ARABICA, 10);
		this.order.addProduct(c1);
		this.order.addProduct(c2);
		this.order.addProduct(c3);
		this.order.addProduct(c4);
		this.order.addProduct(c5);
		this.order.addProduct(c6);
		this.order.addProduct(c7);
		this.order.addProduct(c8);
		this.order.addProduct(c9);
		List<Coffee> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);
		list.add(c6);
		list.add(c7);
		list.add(c8);
		list.add(c9);
		double discount = 0;
		if(list.size()>=8) {
			 discount=0.37;
		}
		assertEquals(discount,this.order.discountCalculator(),0.0001);
		System.out.println("The maximum discount is "+this.order.discountCalculator()*100+"%\n");
	}
	@Test
	public void testZeroCoffeesDiscount() {
		List<Coffee> list = new ArrayList<>();
		double discount=1;
		if(list.size()==0) {
			discount = 0;
		}
		
		System.out.println("The discount for "+list.size()+" products is "+this.order.discountCalculator()*100+"%\n");
		assertEquals(discount,this.order.discountCalculator(),0.0001);
	}
	
	@Test
	public void testDigitsName() throws OnlyNumbersNameException {
		Coffee c1 = new Coffee("00a", CoffeeType.ARABICA, 10);
		if (c1.getBeverageName().toString().matches("[0-9]+")){
			System.out.println("nu are litere");
			throw new OnlyNumbersNameException("The name of the coffee cannot contain only numbers");
			
		}
		
	}
	
	@Test
	public void testDiscountForNegativeCofffeePrices() throws NegativePriceException {
		Coffee c1 = new Coffee("Late", CoffeeType.ARABICA, 10);
		if(c1.getPrice()<=0) {
			throw new NegativePriceException("The price of the coffee must be greater than 0!");
		}
		else {
			this.order.addProduct(c1);
		}
		
		
	}
	
	
}
