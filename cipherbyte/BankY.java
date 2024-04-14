package com.intern.cipherbyte;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            System.out.println("Invalid amount for deposit.");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal.");
            return false;
        }
    }

    public boolean transfer(BankAccount recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            return true;
        } else {
            System.out.println("Transfer failed.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}

class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public String createAccount() {
        String accountNumber = generateAccountNumber();
        BankAccount account = new BankAccount(accountNumber, 0);
        accounts.put(accountNumber, account);
        return accountNumber;
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayBalance(String accountNumber) {
        BankAccount account = getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Balance for Account Number " + accountNumber + ": " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void deposit(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited " + amount + " into Account Number " + accountNumber + ".");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            System.out.println("Withdrew " + amount + " from Account Number " + accountNumber + ".");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = getAccount(fromAccountNumber);
        BankAccount toAccount = getAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            fromAccount.transfer(toAccount, amount);
            System.out.println("Transferred " + amount + " from Account Number " + fromAccountNumber + " to Account Number " + toAccountNumber + ".");
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private String generateAccountNumber() {
        Random rand = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            accountNumber.append(rand.nextInt(10));
        }
        return accountNumber.toString();
    }
}

public class BankY {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scan=new Scanner(System.in);

        // Create accounts
        String account1 = bank.createAccount();
        String account2 = bank.createAccount();

        // Deposit funds
        System.out.println("Enter deposit amount for Account Number "+account1+ ": ");
        double depositAmount1=scan.nextDouble();
        bank.deposit(account1, depositAmount1);
        System.out.println("Enter deposit amount for Account Number "+account1+ ": ");
        double depositAmount2=scan.nextDouble();
        bank.deposit(account2, depositAmount2);


        // Withdraw funds
        System.out.println("Enter withdrawal amount for Account Number: "+account1+ ": ");
        double withdrawalAmount1=scan.nextDouble();
        bank.withdraw(account1, withdrawalAmount1);

        // Transfer funds
        System.out.println("Enter transfer amount for Account Number: "+account1+ " to "+account2+": ");
        double transferAmount =scan.nextDouble();
        bank.transfer(account1, account2, transferAmount);

        // Display balances
        bank.displayBalance(account1);
        bank.displayBalance(account2);

        // Close scanner
        scan.close();
    }
}

