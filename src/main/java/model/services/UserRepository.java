/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.users.Client;
import model.account.Transaction;
import model.account.TransactionStatus;
import model.users.Role;
import model.users.User;
import view.TransactionPanel;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();
    // stores (username, User object) pairs
    public UserRepository(String filepath) { 
        loadData("db.txt");
    }
    public ArrayList<User> getAllUsers() { return new ArrayList<>(users.values()); }
    
    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> Results = new ArrayList<Transaction>();
        for (User user : getAllUsers()) {
            for (Transaction t : getTransactionsByUsername(user.getUsername())) {
                Results.add(t);
            }
        }
        return Results;
    }
    public ArrayList<Transaction> getTransactionsByUsername(String username) {
        ArrayList<Transaction> Results = new ArrayList<Transaction>();
        if (findByUsername(username).getRole() == Role.ADMIN) {
            return Results;
        }
        Client client = (Client) findByUsername(username);
        for (Transaction t : client.getAccount().getTransactions()) {
            Results.add(t);
        }
        return Results;        
    } // Get all transactions made by a certain user.
    
    public User findByUsername(String username) { return users.get(username); }
    
    public ArrayList<User> findBySearchTerm(String searchTerm) {
        ArrayList<User> Results = new ArrayList<User>();
        for (User user : getAllUsers()) {
            if (user.getName().contains(searchTerm) | user.getUsername().contains(searchTerm)) { Results.add(user); }
        }
        return Results;
    }   
    public Client findUserByTransaction(Transaction t) {
        return (Client) findByUsername(t.getUsername()); // explicit cast
    }
    
    public void addUser(User user) {
        System.out.println("Added "+user.getUsername()+" to array.");
        users.put(user.getUsername(), user);
    }
    
    private void loadData(String filepath) { 
        System.out.println("Loaded everything from DB!");
    }
    public void saveData(String filepath) { 
        System.out.println("Saved everything to DB!");
    }
}

