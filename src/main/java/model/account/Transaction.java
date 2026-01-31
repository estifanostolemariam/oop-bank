/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.account;

public class Transaction {
    private String username;
    private TransactionType type;
    private TransactionStatus status;
    private double amount;    

    // Constructor + getters/setters
    public Transaction(String username, TransactionType type, double amount) {
        this.username = username;
        this.type = type;
        this.amount = amount;
    }
    
    public void setStatus(TransactionStatus status) { this.status = status; }
    public void setType(TransactionType type) { this.type = type; }
    
    public String getUsername() { return this.username; }
    public double getAmount() { return this.amount; }  
    public TransactionType getType() { return this.type; }
    public TransactionStatus getStatus() { return this.status; }
    
    public String getTypeAsString() { 
        String stringType = "";
        switch (this.type) {
            case TransactionType.DEPOSIT: stringType="DEPOSIT"; break;
            case TransactionType.WITHDRAWAL: stringType="WITHDRAW"; break;
            case TransactionType.LOAN: stringType="LOAN"; break;
            case TransactionType.DEBT: stringType="DEBT"; break;
        }
        return stringType;
    }  
    public String getStatusAsString() {
        String stringStatus = "";
        switch (this.status) {
            case TransactionStatus.COMPLETED: stringStatus="COMPLETED"; break;
            case TransactionStatus.PENDING: stringStatus="PENDING"; break;
            case TransactionStatus.REJECTED: stringStatus="REJECTED"; break;
        }
        return stringStatus;
    }  
    
    
}

