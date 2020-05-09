/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author OMEN
 */
@Named(value = "user")
@SessionScoped
public class UserController implements Serializable {

    private long id;
    private String username, userRole, firstName, lastName, message;
    private String[] Skills;

    public UserController() {
    }

    public UserController(long id, String username, String userRole, String firstName, String lastName, String message, String[] Skills) {
        this.id = id;
        this.username = username;
        this.userRole = userRole;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.Skills = Skills;
    }
    
    

    public UserController(long id, String username, String userRole, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.userRole = userRole;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getSkills() {
        return Skills;
    }

    public void setSkills(String[] Skills) {
        this.Skills = Skills;
    }
    
    
}
    
