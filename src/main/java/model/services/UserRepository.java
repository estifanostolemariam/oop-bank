/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.account.Account;
import model.users.Client;
import model.account.Transaction;
import model.account.TransactionStatus;
import model.account.TransactionType;
import model.users.Admin;
import model.users.Role;
import model.users.User;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();
    private String usersFilepath;
    private String transactionsFilepath;
    // stores (username, User object) pairs
    public UserRepository(String userFilepath, String transactionsFilepath) { 
        this.usersFilepath = userFilepath;
        this.transactionsFilepath = transactionsFilepath;
        loadData();
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
    
    private void loadData() { 
        loadUsers();
        loadTransactions();
        System.out.println("Loaded everything from DB!");
    }
    
    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.usersFilepath))) {
            String line;
            while ((line = br.readLine()) != null) {

                if (line.isBlank()) continue;

                String[] data = line.split(",");

                String name = data[0];
                String username = data[1];
                String password = data[2];
                Role role = Role.valueOf(data[3]);

                User user;

                if (role == Role.CLIENT) {
                    double balance = Double.parseDouble(data[4]);
                    int creditScore = Integer.parseInt(data[5]);

                    Account account = new Account();
                    account.setBalance(balance);
                    account.setCreditScore(creditScore);
                    user = new Client(name, username, password, account);

                } else {
                    // ADMIN user
                    user = new Admin(name, username, password);
                    user.setRole(role);
                }

                users.put(username, user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.transactionsFilepath))) {
            String line;
            while ((line = br.readLine()) != null) {

                if (line.isBlank()) continue;

                String[] data = line.split(",");

                String username = data[0];
                TransactionType type = TransactionType.valueOf(data[1]);
                TransactionStatus status = TransactionStatus.valueOf(data[2]);
                double amount = Double.parseDouble(data[3]);

                User user = users.get(username);
                if (user instanceof Client client) {
                    Transaction t = new Transaction(username, type, amount);
                    t.setStatus(status);
                    client.getAccount().addTransaction(t);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void saveData() { 
        saveUsers();
        saveTransactions();
        System.out.println("Saved everything to DB!");
    }
    
    private void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.usersFilepath))) {

            for (User user : users.values()) {
                StringBuilder line = new StringBuilder();

                line.append(user.getName()).append(",");
                line.append(user.getUsername()).append(",");
                line.append(user.getPassword()).append(",");
                line.append(user.getRoleAsString());

                if (user instanceof Client client) {
                    Account acc = client.getAccount();
                    line.append(",")
                        .append(String.valueOf(acc.getBalance()))
                        .append(",")
                        .append(String.valueOf(acc.getCreditScore()));
                } else {
                    // ADMIN
                    line.append(",null,null");
                }

                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveTransactions() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.transactionsFilepath))) {

            for (User user : users.values()) {
                if (user instanceof Client client) {
                    for (Transaction t : client.getAccount().getTransactions()) {
                        String line = t.getUsername() + "," +
                                      t.getTypeAsString() + "," +
                                      t.getStatusAsString() + "," +
                                      String.valueOf(t.getAmount());

                        bw.write(line);
                        bw.newLine();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

