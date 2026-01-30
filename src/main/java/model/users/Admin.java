/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.users;

import model.account.TransactionType;
import model.account.Transaction;
import model.account.TransactionStatus;

public class Admin extends User implements Approvable {
    public Admin(String name, String username, String password) {
        super(name, username, password);
        setRole(Role.ADMIN);
    }
    @Override
    public void approve(Transaction t) {
        t.setType(TransactionType.DEBT);
    }
    @Override
    public void reject(Transaction t) {
        t.setStatus(TransactionStatus.REJECTED);
    }
}

