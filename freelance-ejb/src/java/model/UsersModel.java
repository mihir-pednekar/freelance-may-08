/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Users;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mihir
 */
public class UsersModel {
    private String id, username, userRole;

    public UsersModel() {
    }
    
    public UsersModel(String id, String username, String user_role) {
        this.id = id;
        this.username = username;
        this.userRole = user_role;
    }
    
    public static List<UsersModel> mapEntityToModel(List<Users> userList) {
        return userList.stream()
                .map(
                        x->{ return new UsersModel(Long.toString(x.getId()), x.getUsername(), x.getUserRole());
                    })
                .collect(Collectors.toList());
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    
}
