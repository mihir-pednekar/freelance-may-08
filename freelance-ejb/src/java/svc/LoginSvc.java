/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc;

import entities.Users;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author mihir
 */
@Remote
public interface LoginSvc {
    public List<Users> validateUserFromDB(String user, String passwd);
    public boolean persist(Object obj);
}
