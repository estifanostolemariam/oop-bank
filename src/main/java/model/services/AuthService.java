/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import model.account.Account;
import model.exceptions.EmptyFieldException;
import model.users.Admin;
import model.users.Client;
import model.users.Role;
import model.users.User;
import model.exceptions.InvalidCredentialsException;
import model.exceptions.ShortPasswordException;
import model.exceptions.UsernameExistsException;

public class AuthService  {
    private final UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public User signUp(Role userRole, String name, String username, String password) throws ShortPasswordException, UsernameExistsException, EmptyFieldException {
        if (name.length() == 0 | username.length() == 0 | password.length() == 0) {
            throw new EmptyFieldException("No empty fields allowed");
        }
        if (password.length() < 8) {
            throw new ShortPasswordException("Password can't be less than 8 characters.");
        }
        // 1. Check if username exists
        if (repo.findByUsername(username) != null) {
            throw new UsernameExistsException("Username is taken.");
        }

        // 3. Create client user
        User newUser;
        switch (userRole) {
            case Role.CLIENT -> { 
                Account account = new Account();
                account.setBalance(0.0);
                account.setCreditScore(250);
                newUser = new Client(name, username, password, account);
            }
            case Role.ADMIN -> newUser = new Admin(name, username, password);
            default -> throw new IllegalArgumentException("Unknown role");
        }
        
        // 4. Store in repository
        repo.addUser(newUser); // in-memory
        repo.saveData("db.txt"); // persist to TXT

        return newUser;
    }

    public User login(String username, String password) throws InvalidCredentialsException, EmptyFieldException {
        if (username.length() == 0 | password.length() == 0) {
            throw new EmptyFieldException("No empty fields allowed");
        }
        User user = repo.findByUsername(username);
        if (user == null || !user.authenticate(password)) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }
        return user;
    }
}
