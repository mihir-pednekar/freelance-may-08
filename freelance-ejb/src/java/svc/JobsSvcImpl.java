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
import java.util.Objects;
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
    public Freelancer getFreelancersById(Freelancer userid) {
        Freelancer free = null;
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_FREELANCERS_BY_ID);
            query.setParameter("uid", userid.getUid());
            List freelancerList = query.getResultList();
            if( !freelancerList.isEmpty() && freelancerList!=null ){
                free = (Freelancer) freelancerList.get(0);
            }else{
                throw new Exception("Invalid Freelancer ID...");
            }
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
        return free;
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
    
    @Override
    public void deleteJobsByJid(Long jid) {
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
           // System.out.println("Deleting deleteJobsByJid jobid : "+jid);
            Jobs job=em.find(Jobs.class, jid);
            em.getTransaction().begin();
            em.remove(job);
            em.getTransaction().commit();

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
    }   
    public void toggleUserRegistrationForJob(long jobid, long userid){
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            
            Query query = em.createNamedQuery("Jobs.findByJobid");
            query.setParameter("jobid", jobid);
            Jobs job = (Jobs) query.getSingleResult();
            
            List<Freelancer> freelancerList = job.getFreelancerList();
            if(freelancerList.removeIf(freelancer -> Objects.equals(freelancer.getUid(), userid))){
            }
            else{
                query = em.createNamedQuery("Freelancer.findByUid");
                query.setParameter("uid", userid);
                Freelancer freelancer = (Freelancer) query.getSingleResult();
                job.getFreelancerList().add(freelancer);
            }
            
            em.getTransaction().begin();
            em.merge(job);
            em.getTransaction().commit();
        }
        catch(Exception ex){
            System.err.print(ex);
        }
        finally{
            if(em != null && em.isOpen())
                em.close();
            System.gc();
        }
        
        
    }

}
