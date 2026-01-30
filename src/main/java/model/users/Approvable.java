/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.users;

import model.account.Transaction;

public interface Approvable {
    void approve(Transaction t);
    void reject(Transaction t);
}
