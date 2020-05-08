/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import constants.SqlQueryConstants;
import entities.Users;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author mihir
 */
@Singleton
@LocalBean
public class UsersDao {
    
    private List<Users> userList;
    private EntityManager em;

    public UsersDao() {
    }
    
    public List<Users> findUserFromDB(String user){
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_USERS);
            query.setParameter("username", user);
            userList = query.getResultList();
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
        return userList;
    }
    public boolean saveIntoDB(Users user){
        boolean success = false;
        try{
            em = PersistenceUnitConnec.createEntityManager("TestPersistence");
            em.getTransaction().begin();
            em.persist(user);
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
