/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

// Net beans is pushing correctly
package com.sectiontwo.bankmanager;

import model.services.MainService;
import model.services.UserRepository;

import view.LoginPanel;
import view.MainFrame;

import controller.MainController;

public class BankManager {

    public static void main(String[] args) {
        System.out.println("Hello Login");

        // Launch the GUI on the Event Dispatch Thread
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            
            // Initialize database, and controller and service (model).
            UserRepository userRepo = new UserRepository("Credentials.txt","Transactions.txt");
            MainService mainService = new MainService(userRepo);
            MainController mainController = new MainController(mainFrame, mainService);
            
            // Start in the login panel.
            LoginPanel loginPanel = new LoginPanel(mainFrame, mainController);        
            mainFrame.setVisible(true);
            mainFrame.showScreen(loginPanel);
            
            
            // Save data on program exit.
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Program is exiting. Saving data...");
                userRepo.saveData();
            }));
        });
    }
}
