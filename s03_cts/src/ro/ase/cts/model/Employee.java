package ro.ase.cts.model;

import ro.ase.cts.exception.EmployeeTitleNotSupportedException;
import ro.ase.cts.exception.InvalidSalaryException;

public class Employee {
	private String fullName;
	private EmployeeTitle title;
	private double salary;
	
	public Employee() {
		
		
	}
	
	public Employee(String name, EmployeeTitle title, double salary) {
		this.fullName = name;
		this.title=title;
		this.salary=salary;
	}
	
	public void setCompleteName(String name) {
		this.fullName=name;
	}
	public void setEmployeeTitle (EmployeeTitle title) {
		this.title=title;
	}
	public void setSalary (double salary) {
		this.salary=salary;
	}
	public String getCompleteName() {
		return this.fullName;
	}
	public EmployeeTitle getEmployeeTitle() {
		return this.title;
	}
	public double getSalary() {
		return this.salary;
	}
	
	public double computeEmployeeBonus() throws EmployeeTitleNotSupportedException, InvalidSalaryException {
		double bonus = 0;
		if(this.salary<0) {
			throw new InvalidSalaryException("Salary should be a positive number!");
		}
		switch(this.title) {
		case CEO:
			bonus = this.salary * 0.2;
			break;
		case CTO:
			bonus = this.salary * 0.15;
			break;
		case CFO:
			bonus = this.salary * 0.1;
			break;
		case MANAGER:
			bonus = this.salary * 0.07;
			break;
		case TEAM_LEAD:
			bonus = this.salary * 0.05;
			break;
		case JUNIOR:
			bonus = this.salary * 0.1;
			break;
		default:
			throw new EmployeeTitleNotSupportedException("Please implement the logic for "+this.title.toString());	
		}
		return bonus;
		
	}
	public double computeEmployeeTaxes() throws InvalidSalaryException {
		double totalTaxes = 0;
		if(this.salary < 0) {
			throw new InvalidSalaryException("Salary should be a positive number!");
		}
		else {
			double pension = this.salary * 0.25;
			double health = this.salary * 0.1;
			double income = (this.salary-pension-health)*0.1;
			totalTaxes = pension + health + income;
			// income = (emp.getSalary()-emp.getSalary()*0.25-emp.getSalary()*0.1)*0.1
			// pension = totalTaxes - health - income
			// pension = emp.computeEmployeeTaxes() - emp.getSalary()*0.1 - (emp.getSalary()-emp.getSalary()*0.35)
		}
		
		
		return totalTaxes;
	}
}
