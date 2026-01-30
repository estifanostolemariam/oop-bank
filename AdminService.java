/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.util.ArrayList;
import model.users.Admin;
import model.users.Client;
import model.account.Transaction;
import model.exceptions.UnpaidLoanException;
import model.exceptions.InsufficientFundsException;
import model.exceptions.LowCreditScoreException;
import model.users.User;

public class AdminService {
    private UserRepository repo;

    public AdminService(UserRepository repo) { this.repo = repo; }

    public void approveLoan(Admin admin, Transaction t)  throws LowCreditScoreException, UnpaidLoanException  { 
        Client client = findUserByTransaction(t);
        client.getAccount().applyLoan(t);
        admin.approve(t);
    }
    public void rejectLoan(Admin admin, Transaction t) { 
        admin.reject(t);
    }
    
    public Client findUserByTransaction(Transaction t) { return repo.findUserByTransaction(t); }
    public ArrayList<User> findBySearchTerm(String searchTerm) { return repo.findBySearchTerm(searchTerm); }
    public ArrayList<Transaction> getTransactionsByUsername(String username) { return repo.getTransactionsByUsername(username); }
    public ArrayList<Transaction> getAllTransactions() { return repo.getAllTransactions(); }
}

