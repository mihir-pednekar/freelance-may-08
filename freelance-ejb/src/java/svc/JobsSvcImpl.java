/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc;

import constants.SqlQueryConstants;
import dao.PersistenceUnitConnec;
import entities.Jobs;
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
    private List<Jobs> jobsList;
    private EntityManager em;
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

    
}
