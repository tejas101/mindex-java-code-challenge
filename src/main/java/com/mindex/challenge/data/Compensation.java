package com.mindex.challenge.data;

public class Compensation {


    private int salary;
    private String effectiveDate;
	public String employeeId;
    public String compensationId;
	public Employee employee;

    /**
     * Default Constructor
     */
    public Compensation() {
    }

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCompensationId() {
		return compensationId;
	}

	public void setCompensationId(String compensationId) {
		this.compensationId = compensationId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}