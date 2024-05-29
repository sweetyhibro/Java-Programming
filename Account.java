package com.bankingsystem;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private static int accountCounter = 1000;
    private int accountNumber;
    private String name;
    private double balance;
    private List<String> transactionHistory;

    public Account(String name, double initialDeposit) {
        this.accountNumber = accountCounter++;
        this.name = name;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account created with initial deposit: " + initialDeposit);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        addTransaction("Name changed to: " + name);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposited: " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: " + amount);
            return true;
        }
        return false;
    }

    public boolean transfer(double amount, Account targetAccount) {
        if (amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            addTransaction("Transferred: " + amount + " to account " + targetAccount.getAccountNumber());
            targetAccount.addTransaction("Received: " + amount + " from account " + this.getAccountNumber());
            return true;
        }
        return false;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }
}
