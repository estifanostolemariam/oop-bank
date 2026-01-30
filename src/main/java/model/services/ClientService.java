/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import model.users.Admin;
import model.users.Client;
import model.account.Transaction;
import model.account.TransactionStatus;
import model.account.TransactionType;
import model.exceptions.UnpaidLoanException;
import model.exceptions.InsufficientFundsException;
import model.exceptions.LowCreditScoreException;

public class ClientService {
    private UserRepository repo;

    public ClientService(UserRepository repo) { this.repo = repo; }

    public void deposit(Client client, double amount) { 
        Transaction t = new Transaction(client.getUsername(), TransactionType.DEPOSIT, amount);
        client.getAccount().deposit(t);
    }
    
    public void withdraw(Client client, double amount) throws InsufficientFundsException {
        Transaction t = new Transaction(client.getUsername(), TransactionType.WITHDRAWAL, amount);
        client.getAccount().withdraw(t);
    }
    public void requestLoan(Client client, double amount) throws LowCreditScoreException, UnpaidLoanException { 
        Transaction t = new Transaction(client.getUsername(), TransactionType.LOAN, amount);
        client.getAccount().validateLoanRequest(t);
        t.setStatus(TransactionStatus.PENDING);
        client.getAccount().addTransaction(t);
    }
    
    public void repayLoan(Client client, Transaction t) throws InsufficientFundsException { 
        client.getAccount().repayLoan(t);
    }
    
    public boolean validUser(String username) {
        return repo.findByUsername(username) != null;
    }
}

