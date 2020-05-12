/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc;

import constants.SqlQueryConstants;
import dao.PersistenceUnitConnec;
import dao.UsersDao;
import entities.Freelancer;
import entities.Jobapps;
import entities.Jobs;
import entities.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author OMEN
 */
@Stateless
@LocalBean
public class JobsSvcImpl implements JobsSvc{
    
    @EJB
    private UsersDao usersDao;
    private List<Jobs> jobsList;
    private EntityManager em;
    private List<Jobapps> jobAppsList;

    
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
    
    
    
    @Override
    public List<Jobs> getAllJobs() {
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_ALL_JOBS);
            jobsList = query.getResultList();
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
        return jobsList;
    }
    
    public List<Jobs> getAllOpenJobs() {
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_JOBS_BY_STATUS);
            query.setParameter("jobstatus", "open");
            jobsList = query.getResultList();
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
        return jobsList;
        
    }

    @Override
    public List<Jobs> getJobsByProv(Provider userID) { 
        try{
            jobsList=new ArrayList<>();
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_JOBS_BY_PROV);
            query.setParameter("createdby", userID);
            jobsList = query.getResultList();
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
        return jobsList;
    }    

    @Override
    public List<Jobapps> getFreelancersByJobId(Jobs userid) {
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_FREELANCERS_BY_JOBID);
            query.setParameter("jobid", userid);
            jobAppsList = query.getResultList();
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
        return jobAppsList;
    }
}
