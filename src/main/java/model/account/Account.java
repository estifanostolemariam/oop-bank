/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.account;

import model.exceptions.UnpaidLoanException;
import model.exceptions.InsufficientFundsException;
import model.exceptions.LowCreditScoreException;
import java.util.ArrayList;

// Account class to handle bank account.
public class Account{
    private double balance;
    private int creditScore;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public void deposit(Transaction t) {
        this.balance += t.getAmount();
        t.setStatus(TransactionStatus.COMPLETED);
        transactions.add(t);
    }
    public void withdraw(Transaction t) throws InsufficientFundsException {
        if (t.getAmount() > this.balance) {
            throw new InsufficientFundsException("Not enough balance for withdrawal.");
        }
        this.balance -= t.getAmount();
        t.setStatus(TransactionStatus.COMPLETED);
        transactions.add(t);
    }

    // Calculate required credit for transaction.
    public int requiredCredit(Transaction t) {
        // Calculate the loan ratio
        double loanRatio = t.getAmount() / this.balance;
        double extraPoints = (loanRatio - 0.8) * 100; // multiply by 100 to scale as per formula
        int requiredCreditScore = (int) Math.ceil(250 + extraPoints);
    
        // Make sure credit score doesn't go below 250
        if (requiredCreditScore < 250) {
            requiredCreditScore = 250;
        }
    
        return requiredCreditScore;
    }

    // Check for any unresolved loans.
    public boolean unpaidLoan() {
        for (Transaction t : transactions) {
            if (t.getStatus() == TransactionStatus.PENDING) { return true; }
        }
        return false;
    }

    // Make sure a loan request is valid before submitting it.
    public void validateLoanRequest(Transaction t) throws LowCreditScoreException, UnpaidLoanException {
        if (unpaidLoan()) { throw new UnpaidLoanException("Settle all unpaid loans before applying for new ones."); }
        if (creditScore < requiredCredit(t)) {
            throw new LowCreditScoreException("Credit score too low for this loan.");
        }
    }

    // Apply loan to account balance if accepted.
    public void applyLoan(Transaction t){ 
        this.balance += t.getAmount();
    }
    public void repayLoan(Transaction t) throws InsufficientFundsException {
        if (t.getAmount() > this.balance) {
            throw new InsufficientFundsException("Not enough balance to repay.");
        }
        this.balance -= t.getAmount();
        t.setStatus(TransactionStatus.COMPLETED);
        this.creditScore += 5;
    }
    // Add transaction to account transactions
    public void addTransaction(Transaction t) { this.transactions.add(t); }
    
    // Setters and getters.
    public void setBalance(double balance) { this.balance = balance; }
    public void setCreditScore(int creditscore) { this.creditScore = creditscore; }
  
    public ArrayList<Transaction> getTransactions() { return this.transactions; }
    public double getBalance() { return this.balance; }
    public int getCreditScore() { return this.creditScore; }
}

