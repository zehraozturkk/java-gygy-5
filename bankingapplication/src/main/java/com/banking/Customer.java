package com.banking;

public class Customer extends Person {
    private String customerId;
    private Account account;

    public Customer(String name, String surname, String email, String password, String customerId) {
        super(name, surname, email, password);
        this.customerId = customerId;
        
        String autoAccountNumber = "ACC-" + customerId; 
        this.account = new Account(autoAccountNumber, 0.0);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
