/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.JPanel;
import view.MainFrame;
import view.client.HomePanel;

/**
 *
 * @author estifanos
 */
public class AppController {
    private MainFrame mainFrame;
    public AppController(MainFrame mainframe) {
        this.mainFrame = mainframe;
    }

    public String checkLoginDetails(String account, String password) {
        System.out.println(account + " " + password);
        return "success";//"Incorrect username or password.";        
    }
    
    public String validateTransaction(String transaction) {
        return transaction+"valid";
    }
    
    public void addTransactionToHistory() {
        System.out.println("Added to History");
    }
}
