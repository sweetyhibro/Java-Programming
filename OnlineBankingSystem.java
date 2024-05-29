package com.bankingsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineBankingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Online Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = getIntInput();

            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your initial deposit: ");
        double initialDeposit = getDoubleInput();
        Account newAccount = bank.createAccount(name, initialDeposit);
        System.out.println("Account created successfully. Your account number is " + newAccount.getAccountNumber());
    }

    private static void login() {
        System.out.print("Enter your account number: ");
        int accountNumber = getIntInput();
        Account account = bank.getAccount(accountNumber);

        if (account != null) {
            manageAccount(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void manageAccount(Account account) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\nAccount Management");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. View Transaction History");
            System.out.println("6. Update Personal Information");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");
            int option = getIntInput();

            switch (option) {
                case 1:
                    System.out.println("Your balance is: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = getDoubleInput();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = getDoubleInput();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    System.out.print("Enter target account number: ");
                    int targetAccountNumber = getIntInput();
                    Account targetAccount = bank.getAccount(targetAccountNumber);
                    if (targetAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = getDoubleInput();
                        if (account.transfer(transferAmount, targetAccount)) {
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Target account not found.");
                    }
                    break;
                case 5:
                    account.printTransactionHistory();
                    break;
                case 6:
                    updatePersonalInfo(account);
                    break;
                case 7:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updatePersonalInfo(Account account) {
        System.out.print("Enter your new name: ");
        String newName = scanner.next();
        account.setName(newName);
        System.out.println("Personal information updated successfully.");
    }

    private static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid amount: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}
