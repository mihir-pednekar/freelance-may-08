/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import dao.UsersDao;
import entities.Freelancer;
import entities.Provider;
import entities.Users;
import hash.RandomString;
import hash.SaltMaker;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import svc.LoginSvcImpl;


/**
 *
 * @author OMEN
 */
@Named(value = "regCtrl")
@RequestScoped
public class RegistrationController{

    @EJB
    private LoginSvcImpl loginSvcImpl;
    
    

    
    private String username, password, email, firstName, lastName, message, role, skills;
    
    
    public RegistrationController() {
        this.username = "";
        this.password = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.message = "";
        this.role = "";
        this.skills = "";
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    
    public String getNextRegistrationPage(){
        if(this.role.equals("provider")){
            this.role = "provider";
            return "providerRegistration";
        }
        
        else if(this.role.equals("freelancer")){
            this.role = "freelancer";
            return "freelancerRegistration";
        }
        else
            return null;
    }
    
    private String addNewUser() throws NoSuchAlgorithmException{
        String salt = RandomString.generateRandomString(8);
        String passwd = SaltMaker.generateHashSHA256(this.password, salt);
        Timestamp tstamp = new Timestamp(System.currentTimeMillis());
        Long sharedPk = tstamp.getTime();
        Users usersObj = new Users(sharedPk, username, firstName, lastName, salt, passwd, role, new Date());
        if(role.equals("provider")){
            Provider prov = new Provider(sharedPk); prov.setAmount(1000);
            usersObj.setProvider(prov);
            prov.setUsers(usersObj);
        }
        else if(role.equals("freelancer")){
            Freelancer fr = new Freelancer(sharedPk, skills, message);
            usersObj.setFreelancer(fr);
            fr.setUsers(usersObj);     
        }
        loginSvcImpl.persist(usersObj);
        return "index";
    }
    public String addProvider() throws NoSuchAlgorithmException{
        role = "provider";
        return addNewUser();
    }
    public String addFreelancer() throws NoSuchAlgorithmException{
        role = "freelancer";
        return addNewUser();
    }
    
}
