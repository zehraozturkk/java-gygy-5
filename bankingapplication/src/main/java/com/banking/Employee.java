package com.banking;

public class Employee extends Person {
    private String employeeId;

    public Employee(String name, String surname, String email, String password, String employeeId) {
        super(name, surname, email, password);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
