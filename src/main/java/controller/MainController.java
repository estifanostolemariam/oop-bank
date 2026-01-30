/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.services.MainService;
import model.services.UserRepository;
import model.users.Session;
import model.users.User;
import view.MainFrame;

// Main/MasterController for passing controllers between view panels.
public class MainController {
    private AuthController authController;
    private ClientController clientController;
    private AdminController adminController;
    private Session session = new Session();
    
    public MainController(MainFrame mainFrame, MainService mainService) {
        authController = new AuthController(mainFrame, mainService.getAuthService(), session);
        clientController = new ClientController(mainFrame, mainService.getClientService(), session);
        adminController = new AdminController(mainFrame, mainService.getAdminService(), session);
    }

    // Getters
    public User getCurrentUser() {
        return session.getCurrentUser();
    }
    public AuthController getAuthController() { return authController; }
    public ClientController getClientController() { return clientController; }
    public AdminController getAdminController() { return adminController; }
}

