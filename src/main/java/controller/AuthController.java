/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.exceptions.EmptyFieldException;
import model.exceptions.InvalidCredentialsException;
import model.exceptions.ShortPasswordException;
import model.exceptions.UsernameExistsException;
import model.services.AuthService;
import model.services.LoginStatus;
import model.services.SignupStatus;
import model.users.Role;
import model.users.Session;
import model.users.User;
import view.MainFrame;

public class AuthController {
    private MainFrame mainFrame;
    private AuthService authService;
    private Session session;
    
    public AuthController(MainFrame mainframe, AuthService authService, Session session) {
        this.mainFrame = mainframe;
        this.authService = authService;
        this.session = session;
    }

    public LoginStatus Login(String username, String password) {
        try {
            User user = this.authService.login(username, password);
            System.out.println("Logged in");
            session.login(user);
        } catch(InvalidCredentialsException e) {
            return LoginStatus.INVALID_CREDENTIALS;
        } catch (EmptyFieldException e) {
            return LoginStatus.EMPTY_FIELDS;
        }
        return LoginStatus.SUCCESS;   
    }
    
    public SignupStatus Signup(Role role, String name, String username, String password) {
        try {
            this.authService.signUp(role, name, username, password);
        } catch(UsernameExistsException e) {
            return SignupStatus.USERNAME_EXISTS;
        } catch (EmptyFieldException e) {
            return SignupStatus.EMPTY_FIELDS;
        } catch (ShortPasswordException e) {
            return SignupStatus.SHORT_PASSWORD;
        }
        return SignupStatus.SUCCESS;
    }
    
    public void Logout() {
        session.logout();
    }
}
