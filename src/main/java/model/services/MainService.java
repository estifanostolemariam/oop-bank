/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

// Master/Main service to pass services and user repository/DB.
public class MainService {
    private AuthService authService;
    private ClientService clientService;
    private AdminService adminService;
    private UserRepository repo;

    public MainService(UserRepository repo) {
        this.repo = repo;
        this.authService = new AuthService(repo);
        this.clientService = new ClientService(repo);
        this.adminService = new AdminService(repo);
    }
    
    public AuthService getAuthService() { return authService; }
    public ClientService getClientService() { return clientService; }
    public AdminService getAdminService() { return adminService; }
}
