/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.users;

// User abstract class.
public abstract class User implements Authenticatable {
    private String username;
    private String password;
    private String name;
    private Role role;
    
    public User(String name, String username, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return this.role;
    }
    public String getRoleAsString() {
        String roleString = "";
        switch (this.role) {
            case Role.CLIENT: roleString="CLIENT"; break;
            case Role.ADMIN: roleString="ADMIN"; break;
            
        }
        return roleString;
    }
    public String getName() {
        return this.name;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    // Handle authentication.
    @Override
    public boolean authenticate(String password) {
        System.out.println(this.username+" "+this.password+" "+password);
        return password.equals(this.password);
    }
}

