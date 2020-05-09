/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc;

import dao.PersistenceUnitConnec;
import dao.UsersDao;
import entities.Users;
import hash.SaltMaker;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
@LocalBean
public class LoginSvcImpl implements LoginSvc {

    @EJB
    private UsersDao usersDao;
    private List<Users> usersList;
    private EntityManager em;
    

    public LoginSvcImpl() {
    }
    
    @Override
    public List<Users> validateUserFromDB(String user, String passwd) {
        try{
            usersList = usersDao.findUserFromDB(user);
            
            if(usersList != null && usersList.size() == 1 && !usersList.isEmpty()){
                for(Users users : usersList){
                    String saltedPasswd = SaltMaker.generateHashSHA256(passwd, users.getSalt());
                    if(users.getPassword().compareTo(saltedPasswd) == 0){
                        return usersList;
                    }else{
                        return null;
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public boolean persist(Object obj) {
        boolean success = false;
        try{
            em = PersistenceUnitConnec.createEntityManager("TestPersistence");
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            success = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(em != null){
                em.close();
            }
            System.gc();
        }
        return success;
    }
    
}
