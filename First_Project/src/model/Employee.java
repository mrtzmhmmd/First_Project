package model;

public class Employee {

	private int employeeId;
	private String name;
	private String family;
	private int salary;

	public Employee() {
	}

	public Employee(String name, String family, int salary) {
		setName(name);
		setFamily(family);
		setSalary(salary);
	}

	public Employee(int employeeId, String name, String family, int salary) {
		this.employeeId = employeeId;
		this.name = name;
		this.family = family;
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", family=" + family + ", salary=" + salary
				+ "]";
	}
}