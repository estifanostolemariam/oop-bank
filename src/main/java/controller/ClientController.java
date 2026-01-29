/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.exceptions.UserNotRealException;
import model.account.Transaction;
import model.account.TransactionStatus;
import model.account.TransactionType;
import model.exceptions.EmptyFieldException;
import model.exceptions.InsufficientFundsException;
import model.exceptions.LowCreditScoreException;
import model.exceptions.UnpaidLoanException;
import model.services.ClientService;
import model.users.Client;
import model.users.Session;
import view.MainFrame;

public class ClientController {
    private MainFrame mainFrame;
    private ClientService clientService;
    private Session session;
    
    public ClientController(MainFrame mainframe, ClientService clientService, Session session) {
        this.mainFrame = mainframe;
        this.clientService = clientService;
        this.session = session;
    }

    public void deposit(Client client, String rawAmount) throws NumberFormatException, EmptyFieldException  {
        if (rawAmount.length()==0) {
            throw new EmptyFieldException("Field cannot be empty");
        }
        double amount = Double.parseDouble(rawAmount);
        clientService.deposit(client, amount);
    }
    
    public void withdraw(Client client, String rawAmount) throws InsufficientFundsException, NumberFormatException, EmptyFieldException {
        if (rawAmount.length()==0) {
            throw new EmptyFieldException("Field cannot be empty");
        }
        double amount = Double.parseDouble(rawAmount);
        clientService.withdraw(client, amount);
    }
    
    public void requestLoan(Client client, String rawAmount) throws LowCreditScoreException, UnpaidLoanException, NumberFormatException, EmptyFieldException { 
        if (rawAmount.length()==0) {
            throw new EmptyFieldException("Field cannot be empty");
        }
        double amount = Double.parseDouble(rawAmount);
        clientService.requestLoan(client, amount);
    }
    
    public void repayLoan(Client client, Transaction t) throws InsufficientFundsException { 
        clientService.repayLoan(client, t);
    }
}
