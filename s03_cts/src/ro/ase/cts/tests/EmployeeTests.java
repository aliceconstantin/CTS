package ro.ase.cts.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import ro.ase.cts.exception.EmployeeTitleNotSupportedException;
import ro.ase.cts.exception.InvalidSalaryException;
import ro.ase.cts.model.Employee;
import ro.ase.cts.model.EmployeeTitle;

public class EmployeeTests {
	@Test
	public void testBonusForCEO() {
		Employee emp = new Employee("John Doe", EmployeeTitle.CEO, 10000);
		try {
			assertEquals(2000,emp.computeEmployeeBonus(),0.0001);
		} catch (EmployeeTitleNotSupportedException ex) {
			System.out.println(ex.getMessage());
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testBonusForCTO() {
		Employee emp = new Employee("John Doe", EmployeeTitle.CTO, 10000);
		try {
			assertEquals(1500, emp.computeEmployeeBonus(), 0.0001);
		}
		catch(EmployeeTitleNotSupportedException ex){
			System.out.println(ex.getMessage());
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testBonusForCFO() {
		Employee emp = new Employee("John Doe", EmployeeTitle.CFO, 10000);
		try {
			assertEquals(1000, emp.computeEmployeeBonus(), 0.0001);
		}
		catch(EmployeeTitleNotSupportedException ex){
			System.out.println(ex.getMessage());
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testBonusForMANAGER() {
		Employee emp = new Employee("John Doe", EmployeeTitle.MANAGER, 10000);
		try {
			assertEquals(700, emp.computeEmployeeBonus(), 0.0001);
		}
		catch(EmployeeTitleNotSupportedException ex){
			System.out.println(ex.getMessage());
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testBonusForTEAMLEAD() {
		Employee emp = new Employee("John Doe", EmployeeTitle.TEAM_LEAD, 10000);
		try {
			assertEquals(500, emp.computeEmployeeBonus(), 0.0001);
		}
		catch(EmployeeTitleNotSupportedException ex){
			System.out.println(ex.getMessage());
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testBonusForJUNIOR() {
		Employee emp = new Employee("John Doe", EmployeeTitle.JUNIOR, 10000);
		try {
			assertEquals(1000, emp.computeEmployeeBonus(), 0.0001);
		}
		catch(EmployeeTitleNotSupportedException ex){
			System.out.println(ex.getMessage());
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Test
	public void testBonusForSenior() {
		Employee emp = new Employee("John Doe", EmployeeTitle.SENIOR, 10000);
		try {
			emp.computeEmployeeBonus();
			fail("The method should not work with the specified title.");
		}
		catch(EmployeeTitleNotSupportedException ex) {
			System.out.println(ex.getMessage());
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	@Test 
	public void testBonusForNegativeSalary() {
		Employee emp = new Employee("John Doe",EmployeeTitle.CEO, - 100);
		try {
			emp.computeEmployeeBonus();
			fail("The method should not support negative values for salary.");
		} catch (EmployeeTitleNotSupportedException | InvalidSalaryException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testTaxesForNegativeSalary(){
		Employee emp = new Employee("John Doe", EmployeeTitle.CEO, -10000);
		try {
			emp.computeEmployeeTaxes();
			fail("The salary cannot be negative.");
		}
		catch(InvalidSalaryException e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testTaxesForCEO() {
		Employee emp = new Employee("John Doe", EmployeeTitle.CEO, 10000);
		try {
			assertEquals(4150,emp.computeEmployeeTaxes(),0.0001);
			System.out.println(emp.computeEmployeeTaxes() + " taxes paid (retirement + health insurance + income tax)");
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testTaxesForCTO() {
		Employee emp = new Employee("John Doe", EmployeeTitle.CTO, 8000);
		try {
			assertEquals(3320,emp.computeEmployeeTaxes(),0.0001);
			System.out.println(emp.computeEmployeeTaxes() + " taxes paid (retirement + health insurance + income tax)");
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
			
		}
	}
	@Test
	public void testTaxesForCFO() {
		Employee emp = new Employee("John Doe", EmployeeTitle.CFO, 6000);
		try {
			assertEquals(2490,emp.computeEmployeeTaxes(),0.0001);
			System.out.println(emp.computeEmployeeTaxes() + " taxes paid (retirement + health insurance + income tax)");
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testTaxesForMANAGER() {
		Employee emp = new Employee("John Doe", EmployeeTitle.MANAGER, 5000);
		try {
			assertEquals(2075,emp.computeEmployeeTaxes(),0.0001);
			System.out.println(emp.computeEmployeeTaxes() + " taxes paid (retirement + health insurance + income tax)");
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testTaxesForTEAMLEAD() {
		Employee emp = new Employee("John Doe", EmployeeTitle.TEAM_LEAD, 4000);
		try {
			assertEquals(1660,emp.computeEmployeeTaxes(),0.0001);
			System.out.println(emp.computeEmployeeTaxes() + " taxes paid (retirement + health insurance + income tax)");
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Test
	public void testTaxesForJUNIOR() {
		Employee emp = new Employee("John Doe", EmployeeTitle.JUNIOR, 4000);
		try {
			assertEquals(1660,emp.computeEmployeeTaxes(),0.0001);
			System.out.println(emp.computeEmployeeTaxes() + " taxes paid (retirement + health insurance + income tax)");
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void testTaxesPension() throws InvalidSalaryException {
		Employee emp = new Employee("John Doe", EmployeeTitle.MANAGER, 3000);
		//System.out.println(emp.computeEmployeeTaxes());
		try {
			double empSalary = emp.getSalary();
			double empSalaryPension = empSalary*0.25;
			assertEquals(empSalaryPension,
					emp.computeEmployeeTaxes() - (empSalary-empSalary*0.35)*0.1 - empSalary*0.1,
					0.0001);
			System.out.println(emp.computeEmployeeTaxes()+" taxes paid for retirement.");
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Test
	public void testTaxesMinimumWage() throws InvalidSalaryException {
		Employee emp = new Employee("John Doe", EmployeeTitle.JUNIOR, 2000);
		if(emp.getSalary()<2080) {
		 System.out.println("The minimum salary is 2080 RON!");
		}
		else {
			System.out.println(emp.computeEmployeeTaxes());
		}
	}
//	1 000 000 000
	@Test
	public void testTaxesForbesEligibility() throws InvalidSalaryException {
		Employee emp = new Employee("John Doe", EmployeeTitle.JUNIOR, 1000000000);
		try {
			assertEquals(415000000,emp.computeEmployeeTaxes(),0.0001);
			
			System.out.printf(emp.getCompleteName() + 
					" is eligible for the next issue of Forbes. His gross income is %.0f RON and his taxes are %.0f RON",
					emp.getSalary(),emp.computeEmployeeTaxes());
		
		} catch (InvalidSalaryException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	//hw: create a function and apply at least 10 tests to it
	//you need to have at least 4 tests for the right values and then tests for exceptions, negative values, etc
	//check boundaries for integers! (what's the max/min value the integer can have)
	
	
	
}
