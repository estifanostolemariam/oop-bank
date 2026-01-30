/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.users;

import model.account.Account;

public class Client extends User {
    private Account account;
    
    public Client(String name, String username, String password, Account account) {
        super(name, username, password);
        setRole(Role.CLIENT);
        this.account = account;
    }
   
    public Account getAccount() {
        return account;
    }
    
    
}

