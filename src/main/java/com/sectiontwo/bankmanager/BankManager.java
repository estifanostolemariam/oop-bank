/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sectiontwo.bankmanager;

import view.LoginPanel;
import view.MainFrame;
import controller.AppController;
/**
 *
 * @author estifanos
 */
public class BankManager {

    public static void main(String[] args) {
        System.out.println("Hello Login");

        // Launch the GUI on the Event Dispatch Thread
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            AppController appController = new AppController(mainFrame);
            LoginPanel loginPanel = new LoginPanel(mainFrame, appController);
                    
            mainFrame.setVisible(true);
            mainFrame.showScreen(loginPanel);
        });
    }
}
