/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.ArrayList;
import model.account.Transaction;
import model.exceptions.LowCreditScoreException;
import model.exceptions.UnpaidLoanException;
import model.services.AdminService;
import model.users.Admin;
import model.users.Client;
import model.users.Session;
import model.users.User;
import view.MainFrame;

public class AdminController {
    private MainFrame mainFrame;
    private AdminService adminService;
    private Session session;
    
    public AdminController(MainFrame mainframe, AdminService adminService, Session session) {
        this.mainFrame = mainframe;
        this.adminService = adminService;
        this.session = session;
    }

    public void approveLoan(Admin admin, Transaction t) { 
        Client client = adminService.findUserByTransaction(t);
        client.getAccount().applyLoan(t);
        admin.approve(t);
    }
    public void rejectLoan(Admin admin, Transaction t) { 
        admin.reject(t);
    }
    public ArrayList<User> findBySearchTerm(String searchTerm) { return adminService.findBySearchTerm(searchTerm); }
    public ArrayList<Transaction> getTransactionsByUsername(String username) { return adminService.getTransactionsByUsername(username); }
    public ArrayList<Transaction> getAllTransactions() { return adminService.getAllTransactions(); }
}
